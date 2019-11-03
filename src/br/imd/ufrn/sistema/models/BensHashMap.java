package br.imd.ufrn.sistema.models;

import br.imd.ufrn.sistema.csv.Readable;
import br.imd.ufrn.sistema.csv.Writeable;

import java.util.*;

public class BensHashMap implements Writeable, Readable {

  Map<Integer, Bem> bens;

  public BensHashMap() {
    if (bens == null)
      bens = new HashMap<>();
  }

  public void put(Bem bem) {
    bens.put(bem.getCodigo(), bem);
  }

  public void remove(Integer codigo) {
    if(!bens.containsKey(codigo))
      return;

    bens.remove(codigo);
    Bem.setProximoCodigo(codigo);
  }

  public List<String[]> getRows() {
    List<String[]> rows = new ArrayList<>();
    for (int codigo : bens.keySet()) {
      Bem bem = bens.get(codigo);
      rows.add(bem.getRow());
    }
    return rows;
  }

  public String[] getHeader() {
    return new String[]{"codigo", "nome", "descricao", "categoria", "localizacao"};
  }

  @Override
  public void add(Map<String, String> m) {
    this.put(
      new Bem(Integer.parseInt(m.get("codigo")), m.get("nome"), m.get("descricao"))
    );
  }
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("BensHashMap{\n");
    for (int codigo : bens.keySet()) {
      Bem value = bens.get(codigo);
      str.append("\t" + codigo + " = " + value + "\n");
    }
    return str.toString() + "}";
  }
}
