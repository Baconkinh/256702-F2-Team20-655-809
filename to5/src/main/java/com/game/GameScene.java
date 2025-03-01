package com.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameScene {
    private Stage primaryStage;
    private ImageView characterSprite;
    private Label nameLabel;
    private double characterX = 0, characterY = 0; // แก้ไขตรงนี้
    
    private final double MOVE_STEP = 30;
    private final double MIN_X = -500, MAX_X = 500;
    private final double MIN_Y = -350, MAX_Y = 350;

    public GameScene(Stage stage, String characterName, String job, String characterImagePath) {
        this.primaryStage = stage;

        // โหลดภาพพื้นหลัง
        Image backgroundImage = new Image("/Background/5.jpg");
        BackgroundImage bgImage = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1100, 790, false, false, false, false)
        );

        // โหลดสไปรท์ตัวละคร
        Image img = new Image(getClass().getResourceAsStream(characterImagePath));
        characterSprite = new ImageView(img);
        characterSprite.setFitWidth(100);
        characterSprite.setFitHeight(100);
        characterSprite.setTranslateX(characterX);
        characterSprite.setTranslateY(characterY);

        // ชื่อของตัวละคร
        nameLabel = new Label(characterName + " (" + job + ")");
        nameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        nameLabel.setTranslateX(characterX);
        nameLabel.setTranslateY(characterY - 60);

        // ปุ่ม BACK
        Button btnBack = new Button("BACK");
        btnBack.setStyle(
            "-fx-font-size: 18px; " +
            "-fx-background-color: #FF6347; " +
            "-fx-text-fill: white; " +
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px; " +
            "-fx-padding: 10 20;"
        );
        btnBack.setOnAction(e -> new MainMenu(primaryStage).showMainMenu());

        StackPane root = new StackPane(characterSprite, nameLabel);
        root.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setBackground(new Background(bgImage)); // ตั้งค่าพื้นหลัง
        layout.setCenter(root);
        layout.setTop(btnBack);
        BorderPane.setAlignment(btnBack, Pos.TOP_LEFT);
        BorderPane.setMargin(btnBack, new Insets(10));

        Scene scene = new Scene(layout, 1100, 790);

        scene.setOnKeyPressed(event -> {
            double newX = characterX;
            double newY = characterY;

            switch (event.getCode()) {
                case W -> newY -= MOVE_STEP;
                case S -> newY += MOVE_STEP;
                case A -> newX -= MOVE_STEP;
                case D -> newX += MOVE_STEP;
            }

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