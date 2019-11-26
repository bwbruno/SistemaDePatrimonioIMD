package br.imd.ufrn.sistema.telegrambot.command;

import br.imd.ufrn.sistema.db.DAO;
import br.imd.ufrn.sistema.db.DAOFactory;
import br.imd.ufrn.sistema.models.Bem;
import br.imd.ufrn.sistema.models.Categoria;
import br.imd.ufrn.sistema.models.Localizacao;
import br.imd.ufrn.sistema.telegrambot.BotArgs;
import com.beust.jcommander.JCommander;

public class DeleteCommand implements Command {
  @Override
  public String execute(String[] args) {
    BotArgs botArgs = new BotArgs();
    JCommander jCommander = JCommander.newBuilder().addObject(botArgs).build();
    jCommander.parse(args);

    DAO daoTarget = DAOFactory
      .getDAO(botArgs.getDelete())
      .orElseThrow(() -> {
        return new IllegalArgumentException("DAO inválido");
      });

    daoTarget.setBotArgs(botArgs);
    if (daoTarget instanceof Categoria) {
      Categoria c = Categoria.find(botArgs.getCodigo());
      c.delete();
    }

    if (daoTarget instanceof Bem) {
      Bem b = Bem.find(botArgs.getCodigo());
      b.delete();
    }

    if (daoTarget instanceof Localizacao) {
      Localizacao c = Localizacao.find(botArgs.getCodigo());
      c.delete();
    }
    return "Deletado com sucesso.";
  }
}
