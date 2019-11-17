package br.imd.ufrn.sistema.telegrambot;

import br.imd.ufrn.sistema.models.Bem;
import br.imd.ufrn.sistema.models.Categoria;
import br.imd.ufrn.sistema.models.Localizacao;
import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class BemArgs {

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

  private Bem bem;

  public String getNome() {
    return String.join(" ", nome);
  }

  public Bem getBem() {
    bem = new Bem(
      String.join(" ", nome),
      String.join(" ", descricao),
      Localizacao.find(localizacaoid),
      Categoria.find(categoriaid)
    );
    return bem;
  }
}
