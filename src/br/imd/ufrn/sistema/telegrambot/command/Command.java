package br.imd.ufrn.sistema.telegrambot.command;

import java.util.List;

public interface Command {
  String execute(String args[]);
}