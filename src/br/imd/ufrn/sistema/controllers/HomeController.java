package br.imd.ufrn.sistema.controllers;

import br.imd.ufrn.sistema.models.Bem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class HomeController {

  @FXML
  public Button btnBens;
  @FXML
  public Button btnCategorias;
  @FXML
  public Button btnLocalizacoes;
  @FXML
  public ScrollPane scrollPane;

  @FXML
  protected void initialize() {
    try {
      Node node = FXMLLoader.load(getClass().getResource("../views/ListViewBens.fxml"));
      scrollPane.setContent(node);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



  public void handleClicks(ActionEvent actionEvent) {
    try {
      if (actionEvent.getSource() == btnBens) {
        Node node = FXMLLoader.load(getClass().getResource("../views/ListViewBens.fxml"));
        scrollPane.setContent(node);
      }

      if (actionEvent.getSource() == btnCategorias) {
        Node node = FXMLLoader.load(getClass().getResource("../views/ListViewCategorias.fxml"));
        scrollPane.setContent(node);
      }

      if (actionEvent.getSource() == btnLocalizacoes) {
        Node node = FXMLLoader.load(getClass().getResource("../views/ListViewLocalizacoes.fxml"));
        scrollPane.setContent(node);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
