package br.imd.ufrn.sistema.controllers;

import br.imd.ufrn.sistema.models.Bem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListViewBens {

  @FXML
  ListView<Bem> lvBens;

  @FXML
  protected void initialize() {
    updateList();
  }

  private void updateList() {
    lvBens.getItems().clear();
    for (Bem bem: Bem.all()) {
      lvBens.getItems().add(bem);
    }
  }
}
