package br.imd.ufrn.sistema;

import br.imd.ufrn.sistema.csv.CSV;
import br.imd.ufrn.sistema.models.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

      // Criando uma localização
      LocalizacaoHashMap localizacaoHashMap = new LocalizacaoHashMap();
      Localizacao localizacao = new Localizacao("Sala-B422", "Sala de aula dos cursos e graduação");
      localizacaoHashMap.put(localizacao);

      // Criando uma categoria
      CategoriasHashMap categoriasHashMap = new CategoriasHashMap();
      Categoria categoria  = new Categoria("Eletrônicos", "Aparelhos eletrônicos.");
      categoriasHashMap.put(categoria);

      // Criando bens
      BensHashMap bensHashMap = new BensHashMap();
      bensHashMap.put(new Bem("Projetor", "Projetor de imagens e vídeos de sala de aula.", localizacao, categoria));
      bensHashMap.put(new Bem("Impressora", "Impressora de imagens e vídeos de sala de aula.", localizacao, categoria));

      // Exibindo bens
      System.out.println(bensHashMap);

      // Salvando bens
      CSV.write("bens.csv", bensHashMap);







      // Apenas comentei
      /*BensHashMap bens = new BensHashMap();

      bens.put(new Bem("pc", ""));
      bens.put(new Bem("pc", ""));
      bens.put(new Bem("pc", ""));
      bens.put(new Bem("pc", ""));
      bens.put(new Bem("pc", ""));
      bens.put(new Bem("pc", ""));

      //System.out.println(bens);

      bens.remove(3);
      bens.remove(7);
      bens.remove(1);
      System.out.println();
      //System.out.println(bens);
      bens.put(new Bem("not", ""));
      bens.put(new Bem("not", ""));
      bens.put(new Bem("not", ""));
      bens.put(new Bem("not", ""));
      bens.put(new Bem("not", ""));
      bens.put(new Bem("not", ""));
      bens.put(new Bem("not", ""));

      bens.remove(9);
      bens.remove(8);
      System.out.println();
      //System.out.println(bens);

      BensHashMap nbens = new BensHashMap();

      CSV.write("bens.csv", bens);
      CSV.read("bens.csv", nbens);

      System.out.println();
      System.out.println(nbens);
      */
/*
//Criação do objeto bot com as informações de acesso
      TelegramBot bot = TelegramBotAdapter.build("1025812891:AAHmk_u7H_SUneO2240kMuNqXVengNI15y4");

      //objeto responsável por receber as mensagens
      GetUpdatesResponse updatesResponse;
      //objeto responsável por gerenciar o envio de respostas
      SendResponse sendResponse;
      //objeto responsável por gerenciar o envio de ações do chat
      BaseResponse baseResponse;

      //controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila
      int m=0;

      //loop infinito pode ser alterado por algum timer de intervalo curto
      while (true){

        //executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
        updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(m));

        //lista de mensagens
        List<Update> updates = updatesResponse.updates();

        //análise de cada ação da mensagem
        for (Update update : updates) {

          //atualização do off-set
          m = update.updateId()+1;

          System.out.println("Recebendo mensagem:"+ update.message().text());

          //envio de "Escrevendo" antes de enviar a resposta
          baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
          //verificação de ação de chat foi enviada com sucesso
          System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

          //envio da mensagem de resposta
          sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Não entendi..."));
          //verificação de mensagem enviada com sucesso
          System.out.println("Mensagem Enviada?" +sendResponse.isOk());

        }

      }


 */

/*
        ListaBens bens = new ListaBens();
        ListaLocalizacoes localizacoes = new ListaLocalizacoes();
        ListaCategorias categorias = new ListaCategorias();

        Scanner sc = new Scanner(System.in);
        String opcao = "/cadastrar";

        while(!opcao.equals("/sair")){

            if ((!bens.getBens().isEmpty()))
                System.out.println(bens);
            if ((!localizacoes.getLocalizacoes().isEmpty()))
                System.out.println(localizacoes);
            if ((!categorias.getCategorias().isEmpty()))
                System.out.println(categorias);

            System.out.println();
            System.out.println("/cadastrar");
            System.out.println("\t/bem");
            System.out.println("\t/localizacao");
            System.out.println("\t/categoria\n\n");
            System.out.print("Opção: ");
            opcao = sc.next();
            System.out.println();



            if (opcao.equals("/bem")) {
                System.out.print("Bem (codigo nome descricao): ");
                bens.add(new Bem(sc.nextInt(), sc.next(), sc.next()));
            }

            if (opcao.equals("/categoria")) {
                System.out.print("Categoria (codigo nome descricao): ");
                categorias.add(new Categoria(sc.nextInt(), sc.next(), sc.next()));
            }

            if (opcao.equals("/localizacao")) {
                System.out.print("Localizacao (nome descricao): ");
                localizacoes.add(new Localizacao(sc.next(), sc.next()));
            }

        }
        sc.close(); //Encerra o programa
*/
    }
}
