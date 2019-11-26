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
import javafx.scene.control.Alert;
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

  private static Bem bem;

  public void display() {
      window = new Stage();
      window.initModality(Modality.APPLICATION_MODAL);
      window.setTitle("Novo Bem");
      bem = null;
      load();
  }

  public void display(Bem b) {
    try {
      if (b == null)
        throw new NullPointerException();

      window = new Stage();
      window.initModality(Modality.APPLICATION_MODAL);
      window.setTitle("Editar Bem");
      bem = new Bem(
        b.getCodigo(),
        b.getNome(),
        b.getDescricao(),
        b.getLocalizacao(),
        b.getCategoria()
      );
      load();

    } catch (NullPointerException e) {
      alert("Erro. Selecione um Bem para editar.", Alert.AlertType.ERROR);
    }
  }

  private void load() {
    try {
      Parent root = FXMLLoader.load(BemDetailsController.class.getResource("../views/BemDetailsView.fxml"));
      window.setScene(new Scene(root));
      window.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void initialize() {
    updateChoiceBoxLocalizacoes();
    updateChoiceBoxCategorias();
    updateTextFields();
  }

  private void updateTextFields() {
    if (bem != null) {
      tfNome.setText(bem.getNome());
      tfDescricao.setText(bem.getDescricao());

      cbLocalizacao.getSelectionModel().select(
        cbLocalizacao.getItems().stream().filter(c ->
          c.getNome().equals(bem.getLocalizacao().getNome())).findFirst().orElse(null)
      );

      cbCategoria.getSelectionModel().select(
        cbCategoria.getItems().stream().filter(c ->
          c.getNome().equals(bem.getCategoria().getNome())).findFirst().orElse(null)
      );
    }
  }

  @FXML
  private void btnCancelarAction(ActionEvent actionEvent) {
    window.close();
  }

  @FXML
  private void btnOkAction(ActionEvent actionEvent) {
    try {
      inputsValidate();

      Integer id = null;
      if (this.bem != null)
        id = this.bem.getCodigo();

      Bem b = new Bem(
        id,
        tfNome.getText(),
        tfDescricao.getText(),
        cbLocalizacao.getValue(),
        cbCategoria.getValue()
      );


      b.save();
      window.close();

    } catch (Exception e) {
      alert("Erro ao criar o Bem", Alert.AlertType.ERROR);
    }
  }

  private void alert(String headerText, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setHeaderText(headerText);
    alert.showAndWait();
  }


  private void inputsValidate() throws Exception {
    if (tfNome.getText().isEmpty())
      throw new Exception("O campo Nome não pode ser vazio.");
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
    cbLocalizacao.getSelectionModel().selectFirst();
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
    cbCategoria.getSelectionModel().selectFirst();
  }

}