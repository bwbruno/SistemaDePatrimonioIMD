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

  private double x, y;

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("views/HomeView.fxml"));
    primaryStage.setScene(new Scene(root));

    //drag it here
    root.setOnMousePressed(event -> {
      x = event.getSceneX();
      y = event.getSceneY();
    });
    root.setOnMouseDragged(event -> {

      primaryStage.setX(event.getScreenX() - x);
      primaryStage.setY(event.getScreenY() - y);

    });
    primaryStage.show();
  }

  public static String executeCommandUsingFactory(String args[]) {
    Command targetCommand = CommandFactory
      .getCommand(args[0])
      .orElseThrow(() -> {
        return new IllegalArgumentException("");
      });

    try {
      return targetCommand.execute(args);
    } catch (CommandException e) {
      return e.getMessage();
    }
  }

  public static void main(String[] args) {

    //Bot bot = new Bot("835498287:AAF5lpKlz6ZrK9lfwktx8ZikUVLAiVkBeTs");

    //ExecutorService executor = Executors.newFixedThreadPool(2);
    //Runnable bot = new Bot("835498287:AAF5lpKlz6ZrK9lfwktx8ZikUVLAiVkBeTs");
    //executor.execute(bot);

    launch(args);

    //System.out.println(executeCommandUsingFactory("/create bem -n Bruno Wagner -d Operador de caixa -lid 1 -cid 1".split(" ")));
    //System.out.println(executeCommandUsingFactory("/delete bem -c 4 -n PC 1 -d Computador bom -lid 1 -cid 1".split(" ")));
    System.out.println(executeCommandUsingFactory("/show bem".split(" ")));
/*
    System.out.println(Categoria.all());
    System.out.println(Categoria.find(2));
    System.out.println(Localizacao.all());
    System.out.println(Bem.all());
*/

  }
}
