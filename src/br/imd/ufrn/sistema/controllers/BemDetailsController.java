package br.imd.ufrn.sistema.controllers;

import br.imd.ufrn.sistema.models.Bem;
import br.imd.ufrn.sistema.models.Categoria;
import br.imd.ufrn.sistema.models.Categoria;
import br.imd.ufrn.sistema.models.Localizacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BemDetailsController {
  private static Stage window;

  @FXML
  private TextField tfNome;
  @FXML
  private TextField tfDescricao;
  @FXML
  private ChoiceBox<Localizacao> cbLocalizacao;
  @FXML
  private ChoiceBox<Categoria> cbCategoria;

  @FXML
  protected void initialize() {
    updateChoiceBoxLocalizacoes();
    updateChoiceBoxCategorias();
  }

  public void display(String title) throws IOException {
    window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);

    Parent root = FXMLLoader.load(BemDetailsController.class.getResource("../views/BemDetailsView.fxml"));
    window.setScene(new Scene(root));
    window.showAndWait();
  }

  @FXML
  private void close(ActionEvent actionEvent) {
    window.close();
  }

  @FXML
  private void btnOkAction(ActionEvent actionEvent) {
    Bem bem = new Bem(
      tfNome.getText(),
      tfDescricao.getText(),
      cbLocalizacao.getValue(),
      cbCategoria.getValue()
    );

    bem.save();
  }

  private void updateChoiceBoxLocalizacoes() {
    cbLocalizacao.setConverter(new StringConverter<Localizacao>() {
      @Override
      public String toString(Localizacao object) {
        return object.getNome();
      }
      @Override
      public Localizacao fromString(String string) {
        return cbLocalizacao.getItems().stream().filter(l ->
          l.getNome().equals(string)).findFirst().orElse(null);
      }
    });

    cbLocalizacao.valueProperty().addListener((obs, oldval, newval) -> {
      if(newval != null)
        System.out.println("Selected localizacao: " + newval.getNome()
          + ". ID: " + newval.getCodigo());
    });

    ObservableList<Localizacao> localizacoes = FXCollections.observableArrayList(Localizacao.all());
    cbLocalizacao.setItems(localizacoes);
  }

  private void updateChoiceBoxCategorias() {
    cbCategoria.setConverter(new StringConverter<Categoria>() {
      @Override
      public String toString(Categoria object) {
        return object.getNome();
      }
      @Override
      public Categoria fromString(String string) {
        return cbCategoria.getItems().stream().filter(c ->
          c.getNome().equals(string)).findFirst().orElse(null);
      }
    });

    cbCategoria.valueProperty().addListener((obs, oldval, newval) -> {
      if(newval != null)
        System.out.println("Selected categoria: " + newval.getNome()
          + ". ID: " + newval.getCodigo());
    });

    ObservableList<Categoria> categorias = FXCollections.observableArrayList(Categoria.all());
    cbCategoria.setItems(categorias);
  }

}