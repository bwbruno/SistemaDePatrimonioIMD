package br.imd.ufrn.sistema;

import br.imd.ufrn.sistema.db.*;
import br.imd.ufrn.sistema.models.*;

import java.util.List;

public class Main {

  public static void main(String[] args) {

    System.out.println(Categoria.all());
    System.out.println(Categoria.find(2));
    System.out.println(Localizacao.all());
    System.out.println(Bem.all());
  }
}
