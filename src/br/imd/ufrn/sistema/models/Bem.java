package br.imd.ufrn.sistema.models;

import br.imd.ufrn.sistema.db.BemData;
import br.imd.ufrn.sistema.db.DAO;

import java.util.List;

public class Bem implements DAO {

  private Integer codigo;
  private String nome;
  private String descricao;
  private Localizacao localizacao;
  private Categoria categoria;

  public Bem(String nome, String descricao, Localizacao localizacao, Categoria categoria) {
    this.nome = nome;
    this.descricao = descricao;
    this.localizacao = localizacao;
    this.categoria = categoria;
  }


  public Bem(int codigo, String nome, String descricao, Localizacao localizacao, Categoria categoria) {
    this.codigo = codigo;
    this.nome = nome;
    this.descricao = descricao;
    this.localizacao = localizacao;
    this.categoria = categoria;
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

  public Localizacao getLocalizacao() {
    return localizacao;
  }

  public Categoria getCategoria() {
    return categoria;
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

  private static BemData dao = new BemData();

  public void save() {
    if (codigo != null && dao.find(codigo) != null)
      dao.update(this);
    else
      dao.create(this);
  }

  public void delete() {
    if (dao.find(codigo) != null)
      dao.delete(this);
  }

  public static List<Bem> all(){
    return dao.all();
  }

  public static Bem find(int key) {
    return dao.find(key);
  }

}
