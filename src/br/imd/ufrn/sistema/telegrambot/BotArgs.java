package br.imd.ufrn.sistema.telegrambot;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class BotArgs {

  @Parameter(names = {"/h", "/help"}, help = true, description = "Mostra a ajuda.")
  private boolean help;

  @Parameter(names = {"/c", "/create"})
  private String create;

  @Parameter(names = {"/s", "/show"},
    description = "Exibe todas as localizações, categorias ou bens cadastrados.")
  private String show;

  @Parameter(names = {"/f", "/find"})
  private String find;

  @Parameter(names = {"/d", "/delete"})
  private String delete;

  @Parameter(names = {"--codigo", "-c"})
  private Integer codigo;

  @Parameter(names = {"--nome", "-n"}, variableArity = true)
  private List<String> nome = new ArrayList<>();

  @Parameter(names = {"--descricao", "-d"}, variableArity = true)
  private List<String> descricao = new ArrayList<>();

  @Parameter(names = {"--localizacaoid", "-lid"})
  private int localizacaoid;

  @Parameter(names = {"--categoriaid", "-cid"}, variableArity = true)
  private int categoriaid;

  public String getCreate() {
    return create;
  }

  public String getShow() {
    return show;
  }

  public String getFind() {
    return find;
  }

  public String getNome() {
    return String.join(" ", nome);
  }

  public String getDescricao() {
    return String.join(" ", descricao);
  }

  public Integer getCodigo() {
    return codigo;
  }

  public int getLocalizacaoid() {
    return localizacaoid;
  }

  public int getCategoriaid() {
    return categoriaid;
  }

  public String getDelete() {
    return delete;
  }
}