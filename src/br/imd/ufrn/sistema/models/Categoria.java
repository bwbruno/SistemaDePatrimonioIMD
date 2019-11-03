package br.imd.ufrn.sistema.models;

import java.util.HashSet;
import java.util.Set;

public class Categoria implements Indexable {

  public static Set<Integer> proximoCodigo;
  private int codigo;
  private String nome;
  private String descricao;

  public Categoria(int codigo, String nome, String descricao) {
    inicialize();
    this.codigo = codigo;
    this.nome = nome;
    this.descricao = descricao;
  }

  public Categoria(String nome, String descricao) {
    inicialize();
    this.codigo = getProximoCodigo();
    this.nome = nome;
    this.descricao = descricao;
  }

  private void inicialize() {
    if (proximoCodigo == null)
      proximoCodigo = new HashSet<>();
  }


  public int getCodigo() {
    return codigo;
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }


  public static void setProximoCodigo(Integer codigo) {
    Indexable.setNextID(proximoCodigo, codigo);
  }

  private int getProximoCodigo() {
    return getProximoCodigo(proximoCodigo);
  }

  @Override
  public String toString() {
    return "Categoria{" +
      "codigo=" + codigo +
      ", nome='" + nome + '\'' +
      ", descricao='" + descricao + '\'' +
      '}';
  }

  public String[] getRow() {
    return new String[]{ String.valueOf(codigo), nome, descricao };
  }
}
