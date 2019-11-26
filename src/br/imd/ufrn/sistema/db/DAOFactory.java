package br.imd.ufrn.sistema.db;

import br.imd.ufrn.sistema.models.Bem;
import br.imd.ufrn.sistema.models.Categoria;
import br.imd.ufrn.sistema.models.Localizacao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DAOFactory {
  static Map<String, DAO> daoMap = new HashMap<>();

  static {
    daoMap.put("bem", new Bem());
    daoMap.put("categoria", new Categoria());
    daoMap.put("localizacao", new Localizacao());
  }

  public static Optional<DAO> getDAO(String dao) {
    return Optional.ofNullable(daoMap.get(dao));
  }
}
