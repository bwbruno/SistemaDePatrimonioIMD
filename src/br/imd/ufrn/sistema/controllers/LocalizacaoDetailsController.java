package br.imd.ufrn.sistema.controllers;

import br.imd.ufrn.sistema.models.Localizacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LocalizacaoDetailsController {
  private static Stage window;
  private static Localizacao localizacao;

  @FXML
  private TextField tfNome;
  @FXML
  private TextField tfDescricao;


  public void display() {
    window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Nova Localizacao");
    localizacao = null;
    load();
  }

  public void display(Localizacao l) {
    try {
      if (l == null)
        throw new NullPointerException();

      window = new Stage();
      window.initModality(Modality.APPLICATION_MODAL);
      window.setTitle("Editar Localizacao");
      localizacao = new Localizacao(
        l.getCodigo(),
        l.getNome(),
        l.getDescricao()
      );
      load();

    } catch (NullPointerException e) {
      alert("Erro. Selecione uma Localizacao para editar.", Alert.AlertType.ERROR);
    }
  }

  private void load() {
    try {
      Parent root = FXMLLoader.load(LocalizacaoDetailsController.class.getResource("../views/LocalizacaoDetailsView.fxml"));
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
    if (localizacao != null) {
      tfNome.setText(localizacao.getNome());
      tfDescricao.setText(localizacao.getDescricao());
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
      if (this.localizacao != null)
        id = this.localizacao.getCodigo();

      Localizacao l = new Localizacao(
        id,
        tfNome.getText(),
        tfDescricao.getText()
      );

      l.save();
      window.close();

    } catch (Exception e) {
      alert("Erro ao criar o Localizacao", Alert.AlertType.ERROR);
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
