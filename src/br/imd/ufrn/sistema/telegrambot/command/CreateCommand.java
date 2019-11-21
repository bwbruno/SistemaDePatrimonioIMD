package br.imd.ufrn.sistema.telegrambot.command;

import br.imd.ufrn.sistema.db.DAO;
import br.imd.ufrn.sistema.db.DAOFactory;
import br.imd.ufrn.sistema.telegrambot.BotArgs;
import com.beust.jcommander.JCommander;

public class CreateCommand implements Command {

  @Override
  public String execute(String[] args) {
    BotArgs botArgs = new BotArgs();
    JCommander jCommander = JCommander.newBuilder().addObject(botArgs).build();
    jCommander.parse(args);

    System.out.println(botArgs.getCodigo());
    System.out.println(botArgs.getNome());

    DAO daoTarget = DAOFactory
      .getDAO(botArgs.getCreate())
      .orElseThrow(() -> {
        return new IllegalArgumentException("");
      });

    daoTarget.setBotArgs(botArgs);
    daoTarget.save();

    return "Criação feita com sucesso.";
  }
}
