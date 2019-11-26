package br.imd.ufrn.sistema.controllers;

import br.imd.ufrn.sistema.models.Bem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class ListViewBens {

  @FXML
  private ListView<Bem> lvBens;

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

  public void getSelectedItem() {
    System.out.println(lvBens.getSelectionModel().getSelectedItem());
  }

  public ListView<Bem> getLvBens() {
    return lvBens;
  }
}
