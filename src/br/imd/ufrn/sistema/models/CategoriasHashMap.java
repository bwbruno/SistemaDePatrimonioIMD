package br.imd.ufrn.sistema.models;

import br.imd.ufrn.sistema.csv.Readable;
import br.imd.ufrn.sistema.csv.Writeable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriasHashMap implements Writeable, Readable {

  Map<Integer, Categoria> categorias;

  public CategoriasHashMap() {
    if (categorias == null)
      categorias = new HashMap<>();
  }

  public void put(Categoria categoria) {
    categorias.put(categoria.getCodigo(), categoria);
  }

  public void remove(Integer codigo) {
    if(!categorias.containsKey(codigo))
      return;

    categorias.remove(codigo);
  }

  @Override
  public List<String[]> getRows() {
    return null;
  }

  @Override
  public String[] getHeader() {
    return new String[]{"codigo", "nome", "descricao"};
  }

  @Override
  public void add(Map<String, String> m) {
      this.put(
        new Categoria(Integer.parseInt(m.get("codigo")), m.get("nome"), m.get("descricao"))
      );
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("CategoriasHashMap{\n");
    for (int codigo : categorias.keySet()) {
      Categoria value = categorias.get(codigo);
      str.append("\t" + codigo + " = " + value + "\n");
    }
    return str.toString() + "}";
  }
}
