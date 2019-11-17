package br.imd.ufrn.sistema.telegrambot;

import br.imd.ufrn.sistema.models.Bem;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
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

import java.util.ArrayList;
import java.util.List;

public class Bot extends Thread {

  private final TelegramBot bot;
  GetUpdatesResponse updatesResponse;
  SendResponse sendResponse;
  BaseResponse baseResponse;
  Update update;

  BotArgs botArgs;
  JCommander jBotCommander;

  public Bot(String token) {
    this.bot = TelegramBotAdapter.build(token);
     botArgs = new BotArgs();
     jBotCommander = JCommander.newBuilder().addObject(botArgs).build();
  }

  private void handleTextMessage(Message message) {

    String args[] = message.text().split(" ");
    jBotCommander.parse(args);

    if (args[0].equals("create"))
      handleCreateCommand();

    else
      sendMessage("Não entendi...");
  }

  private void handleCreateCommand() {

    //TODO

  }

  private void sendMessage(String message) {
    sendTyping(update);
    sendResponse = bot.execute(new SendMessage(update.message().chat().id(), message));
  }

  private void sendTyping(Update update) {
      baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
  }

  private boolean isCommand(Message message) {
    if (message.text().charAt(0) == '/')
      return true;
    return false;
  }


  private void handleStartCommand(Message message) {
    SendMessage sm = new SendMessage(update.message().chat().id(), "" +
      "*Comandos para manipular o Sistema de Patrimônio IMD:*\n" +
      "/start - show this welcome screen\n" +
      "/show - get variable number of items in given category (if no id provided, will show all items in category)\n" +
      "/create - to create new item, e.g. '/create products title=IPhone price_rub=10'\n" +
      "/update - to change item info, e.g. '/update products 1 price_rub=999'\n" +
      "/delete - to remove item, e.g. '/delete products 1'\n" +
      "\n" +
      "Link [Github](https://github.com/bwbruno/SistemaDePatrimonioIMD)");
    sendResponse =  bot.execute(sm.parseMode(ParseMode.Markdown));
    return;
  }

  private void handleTableCommand(Message message) {
    SendMessage sm = new SendMessage(update.message().chat().id(), "" +
      "```| Tables   |      Are      |  Cool |\n" +
      "|----------|:-------------:|------:|\n" +
      "| col 1 is |  left-aligned | $1600 |\n" +
      "| col 2 is |    centered   |   $12 |\n" +
      "| col 3 is | right-aligned |    $1 |```\n");
    sendResponse =  bot.execute(sm.parseMode(ParseMode.Markdown));
    return;
  }

  @Override
  public void run() {
    loop();
  }

  private void loop() {

    int m = 0;

    while (true) {

      //executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
      updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

      //lista de mensagens
      List<Update> updates = updatesResponse.updates();
      //análise de cada ação da mensagem
      for (Update update : updates) {

        this.update = update;
        //atualização do off-set
        m = update.updateId()+1;
        System.out.println(m);
        System.out.println("Recebendo mensagem:" + update.message().text());
        handleTextMessage(update.message());

        //verificação de mensagem enviada com sucesso
        System.out.println("Mensagem Enviada?" + sendResponse.isOk());

      }

    }

  }
}
