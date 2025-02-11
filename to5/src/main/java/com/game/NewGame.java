package com.game;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.*;

public class NewGame {
    public void NewGame() {
        getGameScene().clearUINodes();

        // Background
        Image imagebg = getAssetLoader().loadImage("bg.jpg");
        ImageView background = new ImageView(imagebg);
        background.setFitWidth(1100);
        background.setFitHeight(790);
        getGameScene().addUINode(background);

        // Button Back
        Button Backbtn = new Button("Back");
        Backbtn.setFont(Font.font("Arial", 30));
        Backbtn.setStyle("-fx-background-color: #FF4500; -fx-text-fill: white; -fx-border-radius: 15px; -fx-background-radius: 15px; -fx-padding: 10 30 10 30;");
        double BackX = 80;
        double BackY = 70;
        Backbtn.setTranslateX(BackX);
        Backbtn.setTranslateY(BackY);
        Backbtn.setOnAction(e -> new App().Main());

        // Title SELECT CHARACTER
        Text title4 = new Text("SELECT CHARACTER");
        title4.setFont(Font.font(60));
        title4.setStyle("-fx-background-color: transparent; -fx-font-weight: bold;");
        title4.setTranslateX(200);
        title4.setTranslateY(120);
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
        shadow.setColor(Color.DARKORANGE);
        shadow.setRadius(5);
        title4.setEffect(shadow);

        // Detail Box
        Text detailText = new Text("");
        detailText.setFont(new Font("Arial", 16));
        VBox detailBox = new VBox(detailText);
        detailBox.setTranslateX(900);
        detailBox.setTranslateY(500);
        detailBox.setPadding(new Insets(10));
        detailBox.setSpacing(5);
        detailBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        detailBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 10;");

        // Player Name Input Box
        Text playerNameLabel = new Text("Player Name:");
        playerNameLabel.setFont(new Font("Arial", 16));
        TextField playerNameField = new TextField();
        playerNameField.setPromptText("Enter your name");
        VBox playerNameBox = new VBox(playerNameLabel, playerNameField);
        playerNameBox.setTranslateX(900);
        playerNameBox.setTranslateY(50);
        playerNameBox.setPadding(new Insets(10));
        playerNameBox.setSpacing(5);
        playerNameBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        playerNameBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 10;");

        // Selected Character Path
        final String[] selectedCharacterPath = {""};

        // Start Button
        Button startBtn = new Button("Start");
        startBtn.setFont(Font.font(28));
        startBtn.setTranslateX(900);
        startBtn.setTranslateY(200);
        startBtn.setOnAction(e -> {
            String playerName = playerNameField.getText();
            if (!playerName.isEmpty() && !selectedCharacterPath[0].isEmpty()) {
                new Map().Map(playerName, selectedCharacterPath[0]);
            } else {
                detailText.setText("Please enter a name and select a character.");
            }
        });

        // Images and Click Handlers
        String[] imagePaths = {"/com/game/001.png", "/com/game/002.png", "/com/game/003.png", "/com/game/004.png", "/com/game/005.png"};
        String[] characterNames = {"Archer", "Swords man", "Tank", "Mage", "Gunner"};
        double[][] positions = {{80, 160}, {290, 160}, {500, 160}, {185, 420}, {400, 420}};

        for (int i = 0; i < imagePaths.length; i++) {
            Image image = new Image(getClass().getResource(imagePaths[i]).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(300);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);
            imageView.setTranslateX(positions[i][0]);
            imageView.setTranslateY(positions[i][1]);

            int index = i;
            imageView.setOnMouseClicked(event -> {
                detailText.setText(characterNames[index]);
                selectedCharacterPath[0] = imagePaths[index];
            });

            getGameScene().addUINode(imageView);
        }

        // Add UI Nodes
        getGameScene().addUINode(Backbtn);
        getGameScene().addUINode(title4);
        getGameScene().addUINode(detailBox);
        getGameScene().addUINode(playerNameBox);
        getGameScene().addUINode(startBtn);
    }
}