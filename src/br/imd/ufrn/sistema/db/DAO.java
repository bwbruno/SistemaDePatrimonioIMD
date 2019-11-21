package br.imd.ufrn.sistema.db;

import br.imd.ufrn.sistema.telegrambot.BotArgs;

import java.util.ArrayList;
import java.util.List;

public interface DAO {
  public void save();
  public void delete();
  public static List<Object> all() { return new ArrayList<>(); }
  public static Object find(int key) { return new Object(); }
  public String allString();
  public String findString(int key);
  public void setBotArgs(BotArgs botArgs);

}
