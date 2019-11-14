package br.imd.ufrn.sistema.db;

import java.util.ArrayList;
import java.util.List;

public interface DAO {
  public void save();
  public void delete();
  public static List<Object> all() { return new ArrayList<>(); }
  public static Object find(int key) { return new Object(); }
}
