package br.imd.ufrn.sistema.models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public interface Indexable {

  int getCodigo();
  static void setProximoCodigo(Integer codigo) { }
  private int getProximoCodigo() { return -1; }

  public static void setNextID(Set<Integer> proximoCodigo, int codigo) {
    if (!proximoCodigo.contains(codigo))
      proximoCodigo.add(codigo);
  }

  public default int getProximoCodigo(Set<Integer> proximoCodigo) {
    if (proximoCodigo.isEmpty()) {
      proximoCodigo.add(2);
      return 1;
    }

    if (proximoCodigo.size() == 1) {
      Iterator<Integer> it = proximoCodigo.iterator();
      Integer codigo =  it.next();
      proximoCodigo.remove(codigo);
      proximoCodigo.add(codigo + 1);
      return codigo;
    }

    Iterator<Integer> it = proximoCodigo.iterator();
    Integer codigo =  it.next();
    proximoCodigo.remove(codigo);
    return codigo;
  }

}
