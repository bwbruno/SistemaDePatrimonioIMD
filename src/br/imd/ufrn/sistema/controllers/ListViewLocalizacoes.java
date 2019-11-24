package br.imd.ufrn.sistema.controllers;

import br.imd.ufrn.sistema.models.Localizacao;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListViewLocalizacoes {
  @FXML
  ListView<Localizacao> lvLocalizacoes;

  @FXML
  protected void initialize() {
    updateList();
  }

  private void updateList() {
    lvLocalizacoes.getItems().clear();
    for (Localizacao localizacao: Localizacao.all()) {
      lvLocalizacoes.getItems().add(localizacao);
    }
  }
}
