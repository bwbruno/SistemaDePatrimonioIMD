package br.imd.ufrn.sistema.models;

import br.imd.ufrn.sistema.db.DAO;
import br.imd.ufrn.sistema.db.LocalizacaoData;
import br.imd.ufrn.sistema.telegrambot.BotArgs;

import java.util.List;

public class Localizacao implements DAO {
  private Integer codigo;
  private String nome;
  private String descricao;

  public Localizacao(String nome, String descricao) {
    this.nome = nome;
    this.descricao = descricao;
  }

  public Localizacao(Integer codigo, String nome, String descricao) {
    this.codigo = codigo;
    this.nome = nome;
    this.descricao = descricao;
  }

  public Localizacao() {

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

  public String[] getRow() {
    return new String[]{ nome, descricao };
  }

  @Override
  public String toString() {
    return "Localizacao{" +
      "codigo='" + codigo + '\'' +
      ", nome='" + nome + '\'' +
      ", descricao='" + descricao + '\'' +
      '}';
  }


  private static LocalizacaoData dao = new LocalizacaoData();

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

  public static List<Localizacao> all(){
    return dao.all();
  }

  public static Localizacao find(int key) {
    return dao.find(key);
  }

  @Override
  public String allString() {
    List<Localizacao> localizacoes = all();
    String str = new String();

    str += "<b>ID - NOME - DESCRIÇÃO</b>\n";

    for (Localizacao c: localizacoes) {
      str += c.getCodigo() + " - ";
      str += c.getNome() + " - ";
      str += c.getDescricao();
      str += "\n";
    }
    return str + "";
  }

  @Override
  public String findString(int key) {
    return find(key).toString();
  }

  @Override
  public void setBotArgs(BotArgs botArgs) {
    nome = String.join(" ", botArgs.getNome());
    descricao = String.join(" ", botArgs.getDescricao());
  }

}
