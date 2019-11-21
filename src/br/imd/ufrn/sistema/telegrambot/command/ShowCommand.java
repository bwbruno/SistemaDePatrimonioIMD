package br.imd.ufrn.sistema.telegrambot.command;

import br.imd.ufrn.sistema.db.DAO;
import br.imd.ufrn.sistema.db.DAOFactory;
import br.imd.ufrn.sistema.telegrambot.BotArgs;
import com.beust.jcommander.JCommander;

public class ShowCommand implements Command {

  @Override
  public String execute(String args[]) {
    BotArgs botArgs = new BotArgs();
    JCommander jCommander = JCommander.newBuilder().addObject(botArgs).build();
    jCommander.parse(args);

    DAO daoTarget = DAOFactory
      .getDAO(String.valueOf(botArgs.getShow()))
      .orElseThrow(() -> {
        return new IllegalArgumentException("DAO inválido");
      });

    return daoTarget.allString();
  }
}
