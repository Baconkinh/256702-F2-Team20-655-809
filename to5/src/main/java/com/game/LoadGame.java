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
public class LoadGame {

    public void LoadGame() {
        getGameScene().clearUINodes();

        Image imagebg = getAssetLoader().loadImage("bg.jpg");
        ImageView background = new ImageView(imagebg);
        background.setFitWidth(1100);
        background.setFitHeight(790);
        getGameScene().addUINode(background);

        Button Backbtn = new Button("Back");
        Backbtn.setFont(Font.font("Arial", 30));
        Backbtn.setStyle("-fx-background-color: #FF4500; -fx-text-fill: white; -fx-border-radius: 15px; -fx-background-radius: 15px; -fx-padding: 10 30 10 30;");
        double BackX = 80;
        double BackY = 70;
        Backbtn.setTranslateX(BackX);
        Backbtn.setTranslateY(BackY);
        Backbtn.setOnAction(e -> new App().Main());

        getGameScene().addUINode(Backbtn);
    }

}
