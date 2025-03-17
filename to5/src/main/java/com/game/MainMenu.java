package com.game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainMenu extends Application {

    private Stage primaryStage;
    public MainMenu() { }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        showMainMenu();
    }

    public MainMenu(Stage stage) { 
        this.primaryStage = stage;
    }

    public void showMainMenu() {
        // Label title = new Label("TOURNAMENT OF 5 Hearts");
        // title.setStyle("-fx-font-size: 70px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF;");

      
        Button btnNewGame = createStyledButton("New Game");
        Button btnLoadGame = createStyledButton("Load Game");
        Button btnTutorial = createStyledButton("Tutorial");
        Button btnCredit = createStyledButton("Credit");

        // btnNewGame.setOnAction(e -> System.out.println("Starting New Game..."));
        // btnLoadGame.setOnAction(e -> System.out.println("Loading Game..."));
        // btnTutorial.setOnAction(e -> System.out.println("Opening Tutorial..."));
        btnNewGame.setOnAction(e -> new CharacterCreation(primaryStage).showCharacterCreationScene());
        btnLoadGame.setOnAction(e -> new LoadGame(primaryStage).showLoadGameScene());
        btnTutorial.setOnAction(e -> new Tutorial(primaryStage).showTutorialScene());
        btnCredit.setOnAction(e -> new Credit(primaryStage).showCreditScene()); 

        VBox vbox = new VBox(20,  btnNewGame, btnLoadGame, btnTutorial, btnCredit);
        vbox.setAlignment(Pos.CENTER);
        // vbox.setStyle("-fx-background-color: #222;");
        vbox.setStyle("-fx-background-image: url('/Background/real.jpg');" +
                      "-fx-background-size: 1100px  790px;");
        

        Scene scene = new Scene(vbox, 1100, 790);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu - TOURNAMENT OF 5 Hearts");
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(200, 50);
        button.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #333; -fx-text-fill: white; -fx-border-radius: 10px; -fx-border-color: white;");

        button.setOnMouseEntered(e -> button.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #ff5733; -fx-text-fill: white; -fx-border-radius: 10px; -fx-border-color: white; -fx-scale-x: 1.1; -fx-scale-y: 1.1; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.6), 10, 0, 0, 0);"));

        button.setOnMouseExited(e -> button.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #333; -fx-text-fill: white; -fx-border-radius: 10px; -fx-border-color: white;"));

        return button;
    }

    public static void main(String[] args) {
        launch();
    }
}