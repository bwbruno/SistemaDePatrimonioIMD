package br.imd.ufrn.sistema.telegrambot;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class BotArgs {

  @Parameter(names = {"/h", "/help"}, help = true, description = "Mostra a ajuda.")
  private boolean help;

  @Parameter(names = {"/c", "/create"})
  public String create;

  @Parameter(names = {"/s", "/show"}, variableArity = true,
    description = "Exibe todas as localizações, categorias ou bens cadastrados.")
  public List<String> show = new ArrayList<>();

  @Parameter(names = {"--codigo", "-c"})
  private int codigo;

  @Parameter(names = {"--nome", "-n"}, variableArity = true)
  private List<String> nome = new ArrayList<>();

  @Parameter(names = {"--descricao", "-d"}, variableArity = true)
  private List<String> descricao = new ArrayList<>();

  @Parameter(names = {"--localizacaoid", "-lid"})
  private int localizacaoid;

  @Parameter(names = {"--categoriaid", "-cid"}, variableArity = true)
  private int categoriaid;

}
