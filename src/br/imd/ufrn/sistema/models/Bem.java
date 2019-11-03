package br.imd.ufrn.sistema.models;

import java.util.*;

public class Bem implements Indexable {

  public static Set<Integer> proximoCodigo;
  private int codigo;
  private String nome;
  private String descricao;
  private Localizacao localizacao;
  private Categoria categoria;


  public Bem(String nome, String descricao) {
    inicialize();
    this.codigo = getProximoCodigo();
    this.nome = nome;
    this.descricao = descricao;
  }

  public Bem(int codigo, String nome, String descricao) {
    inicialize();
    this.codigo = codigo;
    this.nome = nome;
    this.descricao = descricao;
  }

  public Bem(int codigo, String nome, String descricao, Localizacao localizacao, Categoria categoria) {
    inicialize();
    this.codigo = codigo;
    this.nome = nome;
    this.descricao = descricao;
    this.localizacao = localizacao;
    this.categoria = categoria;
  }

  public Bem(String nome, String descricao, Localizacao localizacao, Categoria categoria) {
    inicialize();
    this.codigo = getProximoCodigo();
    this.nome = nome;
    this.descricao = descricao;
    this.localizacao = localizacao;
    this.categoria = categoria;
  }

  private void inicialize() {
    if (proximoCodigo == null)
      proximoCodigo = new HashSet<>();
  }

  public int getCodigo() {
    return codigo;
  }

  public static void setProximoCodigo(Integer codigo) {
    Indexable.setNextID(proximoCodigo, codigo);
  }

  private int getProximoCodigo() {
    return getProximoCodigo(proximoCodigo);
  }

  public String[] getRow() {
    return new String[]{String.valueOf(codigo), nome, descricao, "localizacao.getNome()", "String.valueOf(categoria.getCodigo())"};
  }

  @Override
  public String toString() {
    return "Bem{" +
      "codigo=" + codigo +
      ", nome='" + nome + '\'' +
      ", descricao='" + descricao + '\'' +
      ", localizacao=" + localizacao +
      ", categoria=" + categoria +
      '}';
  }

}
