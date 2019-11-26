package br.imd.ufrn.sistema.controllers;

import br.imd.ufrn.sistema.models.Bem;
import br.imd.ufrn.sistema.models.Categoria;
import br.imd.ufrn.sistema.models.Localizacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class HomeController {

  @FXML
  private Button btnBens;
  @FXML
  private Button btnCategorias;
  @FXML
  private Button btnLocalizacoes;
  @FXML
  private ScrollPane scrollPane;

  private ListView<Bem> lvBens;
  private ListView<Categoria> lvCategorias;
  private ListView<Localizacao> lvLocalizacoes;
  private String currentListView;

  @FXML
  protected void initialize() {
      loadListView("lvBens");
  }

  public void handleClicks(ActionEvent actionEvent) {
      if (actionEvent.getSource() == btnBens)
        loadListView("lvBens");

      if (actionEvent.getSource() == btnCategorias)
        loadListView("lvCategorias");

      if (actionEvent.getSource() == btnLocalizacoes)
        loadListView("lvLocalizacoes");
  }

  private void loadListView(String listView) {
    try {

      if (listView == "lvBens") {
        Node node = FXMLLoader.load(getClass().getResource("../views/ListViewBens.fxml"));
        lvBens = (ListView<Bem>) node.lookup("#lvBens");
        scrollPane.setContent(node);
      }

      if (listView == "lvCategorias") {
        Node node = FXMLLoader.load(getClass().getResource("../views/ListViewCategorias.fxml"));
        lvCategorias = (ListView<Categoria>) node.lookup("#lvCategorias");
        scrollPane.setContent(node);
      }

      if (listView == "lvLocalizacoes") {
        Node node = FXMLLoader.load(getClass().getResource("../views/ListViewLocalizacoes.fxml"));
        lvLocalizacoes = (ListView<Localizacao>) node.lookup("#lvLocalizacoes");
        scrollPane.setContent(node);
      }

      currentListView = listView;

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void reloadListView() {
    loadListView(currentListView);
  }


  public void btnNovoAction(ActionEvent actionEvent) {


    if (currentListView.equals("lvBens")) {
      BemDetailsController bemDetailsController = new BemDetailsController();
      bemDetailsController.display();
    }

    if (currentListView.equals("lvCategorias")) {
      CatagoriaDetailsController categoriaDetailsController = new CatagoriaDetailsController();
      categoriaDetailsController.display();
    }

    if (currentListView.equals("lvLocalizacoes")) {
      LocalizacaoDetailsController localizacaoDetailsController = new LocalizacaoDetailsController();
      localizacaoDetailsController.display();
    }

    reloadListView();
  }

  public void btnEditarAction(ActionEvent actionEvent) {

    if (currentListView.equals("lvBens")) {
      BemDetailsController bemDetailsController = new BemDetailsController();
      Bem bem =  lvBens.getSelectionModel().getSelectedItem();
      bemDetailsController.display(bem);
    }

    if (currentListView.equals("lvCategorias")) {
      CatagoriaDetailsController categoriaDetailsController = new CatagoriaDetailsController();
      Categoria categoria = lvCategorias.getSelectionModel().getSelectedItem();
      categoriaDetailsController.display(categoria);
    }

    if (currentListView.equals("lvLocalizacoes")) {
      LocalizacaoDetailsController localizacaoDetailsController = new LocalizacaoDetailsController();
      Localizacao localizacao = lvLocalizacoes.getSelectionModel().getSelectedItem();
      localizacaoDetailsController.display(localizacao);
    }

    reloadListView();
  }

  public void btnApagarAction(ActionEvent actionEvent) {

    try {
      if (currentListView.equals("lvBens")) {
        lvBens.getSelectionModel().getSelectedItem().delete();
      }

      if (currentListView.equals("lvCategorias")) {
        lvCategorias.getSelectionModel().getSelectedItem().delete();
      }

      if (currentListView.equals("lvLocalizacoes")) {
        lvLocalizacoes.getSelectionModel().getSelectedItem().delete();
      }

      reloadListView();

    } catch (NullPointerException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Selecione um item da lista para apagar.");
      alert.showAndWait();
    }

  }


}
