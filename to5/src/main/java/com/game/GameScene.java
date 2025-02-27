package com.game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameScene {
    private Stage primaryStage;
    private ImageView characterSprite;
    private Label nameLabel;
    private double characterX = 500, characterY = 350;
    
    private final double MOVE_STEP = 30;
    private final double MIN_X = -500, MAX_X = 500;
    private final double MIN_Y = -350, MAX_Y = 350; 

    public GameScene(Stage stage, String characterName, String job, String characterImagePath) {
        this.primaryStage = stage;

        Image img = new Image(getClass().getResourceAsStream(characterImagePath));
        characterSprite = new ImageView(img);
        characterSprite.setFitWidth(100);
        characterSprite.setFitHeight(100);
        characterSprite.setTranslateX(characterX);
        characterSprite.setTranslateY(characterY);

        nameLabel = new Label(characterName + " (" + job + ")");
        nameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        nameLabel.setTranslateX(characterX);
        nameLabel.setTranslateY(characterY - 60);

        Button btnBack = new Button("BACK");
        btnBack.setOnAction(e -> new MainMenu(primaryStage).showMainMenu());

        StackPane root = new StackPane(characterSprite, nameLabel);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: black;");

        BorderPane layout = new BorderPane();
        layout.setCenter(root);
        layout.setTop(btnBack);
        BorderPane.setAlignment(btnBack, Pos.TOP_LEFT);

        Scene scene = new Scene(layout, 1100, 790);

        scene.setOnKeyPressed(event -> {
            double newX = characterX;
            double newY = characterY;

            if (event.getCode() == KeyCode.W) newY -= MOVE_STEP;
            if (event.getCode() == KeyCode.S) newY += MOVE_STEP;
            if (event.getCode() == KeyCode.A) newX -= MOVE_STEP;
            if (event.getCode() == KeyCode.D) newX += MOVE_STEP;

            if (newX >= MIN_X && newX <= MAX_X) characterX = newX;
            if (newY >= MIN_Y && newY <= MAX_Y) characterY = newY;

            characterSprite.setTranslateX(characterX);
            characterSprite.setTranslateY(characterY);

            nameLabel.setTranslateX(characterX);
            nameLabel.setTranslateY(characterY - 60);
        });

        primaryStage.setScene(scene);
    }

    public void showGameScene() {
        primaryStage.show();
    }
}