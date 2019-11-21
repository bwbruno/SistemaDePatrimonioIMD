package br.imd.ufrn.sistema;

import br.imd.ufrn.sistema.telegrambot.Bot;
import br.imd.ufrn.sistema.telegrambot.BotArgs;
import br.imd.ufrn.sistema.telegrambot.command.Command;
import br.imd.ufrn.sistema.telegrambot.command.CommandFactory;
import com.beust.jcommander.JCommander;

public class Main {

  public static String executeCommandUsingFactory(String args[]) {
    Command targetCommand = CommandFactory
      .getCommand(args[0])
      .orElseThrow(() -> {
        return new IllegalArgumentException("");
      });

    return targetCommand.execute(args);
  }

  public static void main(String[] args) {

    //Bot bot = new Bot("835498287:AAF5lpKlz6ZrK9lfwktx8ZikUVLAiVkBeTs");
    //bot.run();

    System.out.println(executeCommandUsingFactory("/create bem -n Bruno Wagner -d Operador de caixa -lid 1 -cid 1".split(" ")));
    System.out.println(executeCommandUsingFactory("/delete bem -c 4 -n PC 1 -d Computador bom -lid 1 -cid 1".split(" ")));
    System.out.println(executeCommandUsingFactory("/show bem".split(" ")));
/*
    System.out.println(Categoria.all());
    System.out.println(Categoria.find(2));
    System.out.println(Localizacao.all());
    System.out.println(Bem.all());
*/

  }
}
