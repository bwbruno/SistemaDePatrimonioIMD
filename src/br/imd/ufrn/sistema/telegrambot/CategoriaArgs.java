package br.imd.ufrn.sistema.telegrambot;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class CategoriaArgs {

  @Parameter(names = {"--codigo", "-c"})
  private int codigo;

  @Parameter(names = {"--nome", "-n"}, variableArity = true)
  private List<String> nome = new ArrayList<>();

  @Parameter(names = {"--descricao", "-d"}, variableArity = true)
  private List<String> descricao = new ArrayList<>();

}
