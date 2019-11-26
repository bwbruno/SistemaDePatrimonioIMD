package br.imd.ufrn.sistema.models;

import br.imd.ufrn.sistema.db.CategoriaData;
import br.imd.ufrn.sistema.db.DAO;
import br.imd.ufrn.sistema.telegrambot.BotArgs;

import java.util.List;

public class Categoria implements DAO {

  private Integer codigo;
  private String nome;
  private String descricao;

  public Categoria(String nome, String descricao) {
    this.nome = nome;
    this.descricao = descricao;
  }

  public Categoria(Integer codigo, String nome, String descricao) {
    this.codigo = codigo;
    this.nome = nome;
    this.descricao = descricao;
  }

  public Integer getCodigo() {
    return codigo;
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
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



  private static CategoriaData dao = new CategoriaData();

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

  public static List<Categoria> all(){
    return dao.all();
  }

  public static Categoria find(int key) {
    return dao.find(key);
  }

  @Override
  public String allString() {
    return null;
  }

  @Override
  public String findString(int key) {
    return null;
  }

  @Override
  public void setBotArgs(BotArgs botArgs) {
    nome = String.join(" ", botArgs.getNome());
    descricao = String.join(" ", botArgs.getDescricao());
  }
}
