package br.imd.ufrn.sistema.controllers;

import br.imd.ufrn.sistema.models.Categoria;
import br.imd.ufrn.sistema.models.Categoria;
import br.imd.ufrn.sistema.models.Categoria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CatagoriaDetailsController {
  private static Stage window;
  private static Categoria categoria;

  @FXML
  private TextField tfNome;
  @FXML
  private TextField tfDescricao;


  public void display() {
    window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Nova Categoria");
    categoria = null;
    load();
  }

  public void display(Categoria c) {
    try {
      if (c == null)
        throw new NullPointerException();

      window = new Stage();
      window.initModality(Modality.APPLICATION_MODAL);
      window.setTitle("Editar Categoria");
      categoria = new Categoria(
        c.getCodigo(),
        c.getNome(),
        c.getDescricao()
      );
      load();

    } catch (NullPointerException e) {
      alert("Erro. Selecione uma Categoria para editar.", Alert.AlertType.ERROR);
    }
  }

  private void load() {
    try {
      Parent root = FXMLLoader.load(LocalizacaoDetailsController.class.getResource("../views/CategoriaDetailsView.fxml"));
      window.setScene(new Scene(root));
      window.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void initialize() {
    updateTextFields();
  }

  private void updateTextFields() {
    if (categoria != null) {
      tfNome.setText(categoria.getNome());
      tfDescricao.setText(categoria.getDescricao());
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
      if (this.categoria != null)
        id = this.categoria.getCodigo();

      Categoria c = new Categoria(
        id,
        tfNome.getText(),
        tfDescricao.getText()
      );

      c.save();
      window.close();

    } catch (Exception e) {
      alert("Erro ao criar a Categoria", Alert.AlertType.ERROR);
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
}
