package com.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tutorial {
    private Stage primaryStage;

    public Tutorial(Stage stage) {
        this.primaryStage = stage;
    }

    public void showTutorialScene() {
        Label tutorialTitle = new Label("TUTORIAL");
        tutorialTitle.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label tutorialText = new Label("TUTORIAL: \n1. New Game \n2. Create\n3. GoGo");
        tutorialText.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-alignment: center;");

        VBox tutorialBox = new VBox(10, tutorialTitle, tutorialText);
        tutorialBox.setAlignment(Pos.CENTER);
        tutorialBox.setStyle("-fx-padding: 20px; -fx-border-color: white; -fx-border-width: 3px; -fx-background-color: #333;");
        tutorialBox.setPrefSize(400, 250);

        StackPane tutorialContainer = new StackPane(tutorialBox);
        tutorialContainer.setAlignment(Pos.CENTER);

        Button btnBack = new Button("BACK");
        btnBack.setStyle("-fx-font-size: 16px; -fx-background-color: red; -fx-text-fill: white;");
        btnBack.setOnAction(e -> new MainMenu(primaryStage).showMainMenu());

        BorderPane root = new BorderPane();
        root.setCenter(tutorialContainer);
        root.setTop(btnBack);
        BorderPane.setAlignment(btnBack, Pos.TOP_LEFT);
        BorderPane.setMargin(btnBack, new Insets(10));

        Scene tutorialScene = new Scene(root, 1100, 790);
        primaryStage.setScene(tutorialScene);
    }
}