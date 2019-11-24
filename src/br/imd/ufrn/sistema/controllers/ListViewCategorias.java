package br.imd.ufrn.sistema.controllers;

import br.imd.ufrn.sistema.models.Categoria;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListViewCategorias {
  @FXML
  ListView<Categoria> lvCategorias;

  @FXML
  protected void initialize() {
    updateList();
  }

  private void updateList() {
    lvCategorias.getItems().clear();
    for (Categoria categoria: Categoria.all()) {
      lvCategorias.getItems().add(categoria);
    }
  }
}
