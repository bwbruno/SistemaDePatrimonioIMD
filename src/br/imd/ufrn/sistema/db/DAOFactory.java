package br.imd.ufrn.sistema.db;

import br.imd.ufrn.sistema.models.Bem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DAOFactory {
  static Map<String, DAO> daoMap = new HashMap<>();

  static {
    daoMap.put("bem", new Bem());
  }

  public static Optional<DAO> getDAO(String dao) {
    return Optional.ofNullable(daoMap.get(dao));
  }
}
