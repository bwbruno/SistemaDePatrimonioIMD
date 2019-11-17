package br.imd.ufrn.sistema;

import br.imd.ufrn.sistema.telegrambot.BotArgs;
import com.beust.jcommander.JCommander;

public class Main {

  public static void main(String[] args) {

//    SistemaDePatrimonioIMDBot bot = new SistemaDePatrimonioIMDBot("946562570:AAHdDbrehT8n_S1Nl7AwFHkSrM2xWVqTUbU");
//    bot.run();

    BotArgs botArgs = new BotArgs();
    JCommander.newBuilder()
      .addObject(botArgs)
      .build()
      .parse(("/create bem -n Bruno Wagner -d Operador de caixa".split(" ")));

/*
    System.out.println(Categoria.all());
    System.out.println(Categoria.find(2));
    System.out.println(Localizacao.all());
    System.out.println(Bem.all());
*/

  }
}
