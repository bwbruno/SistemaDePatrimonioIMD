package br.imd.ufrn.sistema.models;

public class Localizacao {
  private String nome;
  private String descricao;

  public Localizacao(String nome, String descricao) {
    this.nome = nome;
    this.descricao = descricao;
  }

  public String getNome() {
    return nome;
  }

  public String[] getRow() {
    return new String[]{ nome, descricao };
  }

  @Override
  public String toString() {
    return "Localizacao{" +
      "nome='" + nome + '\'' +
      ", descricao='" + descricao + '\'' +
      '}';
  }

}
