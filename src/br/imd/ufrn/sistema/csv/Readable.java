package br.imd.ufrn.sistema.csv;

import java.util.Map;

public interface Readable {
  String[] getHeader();
  void add(Map<String, String> map);
}
