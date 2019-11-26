package br.imd.ufrn.sistema;

import br.imd.ufrn.sistema.models.Bem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import br.imd.ufrn.sistema.telegrambot.Bot;
import br.imd.ufrn.sistema.telegrambot.command.Command;
import br.imd.ufrn.sistema.telegrambot.command.CommandException;
import br.imd.ufrn.sistema.telegrambot.command.CommandFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("views/HomeView.fxml"));
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void main(String[] args) {

    ExecutorService executor = Executors.newFixedThreadPool(2);
    Runnable bot = new Bot("853554959:AAEFZAhCvUH0143fTS3VCV_lS3JbA85XMTc");
    executor.execute(bot);

    launch(args);
  }
}
