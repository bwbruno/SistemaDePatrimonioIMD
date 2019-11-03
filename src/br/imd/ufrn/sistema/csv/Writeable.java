package br.imd.ufrn.sistema.csv;

import java.util.List;

public interface Writeable {
  List<String[]> getRows();
}
