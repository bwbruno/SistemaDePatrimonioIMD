package br.imd.ufrn.sistema.telegrambot.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {
  static Map<String, Command> commandMap = new HashMap<>();

  static {
    commandMap.put("/create", new CreateCommand());
    commandMap.put("/show", new ShowCommand());
    commandMap.put("/find", new FindCommand());
    commandMap.put("/delete", new DeleteCommand());
    commandMap.put("/help", new HelpCommand());
  }

  public static Optional<Command> getCommand(String command) {
    return Optional.ofNullable(commandMap.get(command));
  }
}
