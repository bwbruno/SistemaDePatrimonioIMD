package br.imd.ufrn.sistema.models;

import br.imd.ufrn.sistema.csv.Readable;
import br.imd.ufrn.sistema.csv.Writeable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalizacaoHashMap implements Writeable, Readable {

  Map<String, Localizacao> localizacoes;

  public LocalizacaoHashMap() {
    if (localizacoes == null)
      localizacoes = new HashMap<>();
  }

  public void put(Localizacao local) {
    localizacoes.put(local.getNome(), local);
  }

  public void remove(String local) {
    if(!localizacoes.containsKey(local))
      return;

    localizacoes.remove(local);
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("BensHashMap{\n");
    for (String local : localizacoes.keySet()) {
      Localizacao value = localizacoes.get(local);
      str.append("\t" + local + " = " + value + "\n");
    }
    return str.toString() + "}";
  }

  @Override
  public List<String[]> getRows() {
    return null;
  }

  @Override
  public String[] getHeader() {
    return new String[0];
  }

  @Override
  public void add(Map<String, String> toMap) {

  }
}
