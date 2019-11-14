package br.imd.ufrn.sistema.telegrambot;

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

public class SistemaDePatrimonioIMDBot extends Thread {

  private static String COMMAND_START = "/start";
  private static String COMMAND_TABLE = "/table";

  private final String token;
  private final TelegramBot bot;
  //objeto responsável por receber as mensagens
  GetUpdatesResponse updatesResponse;
  //objeto responsável por gerenciar o envio de respostas
  SendResponse sendResponse;
  //objeto responsável por gerenciar o envio de ações do chat
  BaseResponse baseResponse;

  Update update;
  int m = 0;


  public SistemaDePatrimonioIMDBot(String token) {
    this.token = token;
    this.bot = TelegramBotAdapter.build(token);
  }

  private void handleTextMessage(Message message) {
    if (!isCommand(message))
      sendMessage("Não entendi...");

    else if (message.text().equals(COMMAND_START))
      handleStartCommand(message);

    else if (message.text().equals(COMMAND_TABLE))
      handleTableCommand(message);


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

  String getToken() {
    return token;
  }

  TelegramBot getBot() {
    return bot;
  }

  @Override
  public void run() {
    loop();
  }

  private void loop() {
    //controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila


    //loop infinito pode ser alterado por algum timer de intervalo curto
    while (true) {

      //executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
      updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

      //lista de mensagens
      List<Update> updates = updatesResponse.updates();
      //análise de cada ação da mensagem
      for (Update update : updates) {

        this.update = update;
        //atualização do off-set
        m = update.updateId() + 1;
        System.out.println("Recebendo user:" + update.message().from().firstName());
        System.out.println("Recebendo mensagem:" + update.message().text());
        handleTextMessage(update.message());

        //envio de "Escrevendo" antes de enviar a resposta
        //baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));

        //verificação de ação de chat foi enviada com sucesso
        //System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

        //envio da mensagem de resposta
        //sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Não entendi..."));

        //verificação de mensagem enviada com sucesso
        System.out.println("Mensagem Enviada?" + sendResponse.isOk());

      }

    }

  }
}
