package br.imd.ufrn.sistema.telegrambot;

import br.imd.ufrn.sistema.telegrambot.command.Command;
import br.imd.ufrn.sistema.telegrambot.command.CommandFactory;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.List;

public class Bot extends Thread {

  private final TelegramBot bot;
  private GetUpdatesResponse updatesResponse;
  private SendResponse sendResponse;
  private BaseResponse baseResponse;
  private Update update;

  public Bot(String token) {
    this.bot = TelegramBotAdapter.build(token);
  }

  private void handleTextMessage(Message message) {
    String args[] = message.text().split(" ");
    String response = executeCommandUsingFactory(args);
    sendMessage(response);
  }

  public String executeCommandUsingFactory(String args[]) {
    Command targetCommand = CommandFactory
      .getCommand(args[0])
      .orElseThrow(() -> new IllegalArgumentException("Command inválido"));

    return targetCommand.execute(args);
  }

  private void sendMessage(String message) {
    sendTyping(update);
    SendMessage sm = new SendMessage(update.message().chat().id(), message);
    sendResponse =  bot.execute(sm.parseMode(ParseMode.Markdown));
  }

  private void sendTyping(Update update) {
      baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
  }

  @Override
  public void run() {
    loop();
  }

  private void loop() {
    int m = 0;
    while (true) {
      updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));
      List<Update> updates = updatesResponse.updates();

      for (Update update : updates) {
        this.update = update;
        m = update.updateId()+1;
        System.out.println(m);
        System.out.println("Recebendo mensagem: " + update.message().text());
        handleTextMessage(update.message());
        System.out.println("Mensagem Enviada? " + sendResponse.isOk());
      }
    }
  }
}
