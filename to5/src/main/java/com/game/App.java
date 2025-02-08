package com.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import static com.almasb.fxgl.dsl.FXGL.*;

import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class App extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1100);
        settings.setHeight(790);
        settings.setTitle("Tournament Of 5 Hearts");
        settings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        NewGame n = new NewGame();
        LoadGame L = new LoadGame();
        getGameScene().setBackgroundColor(Color.rgb(173, 216, 230));
        Text title = new Text("Tournament");
        title.setFont(Font.font(72));
        title.setStyle("-fx-fill: #FF6347; -fx-font-weight: bold;");
        double titleCenterX = 150;
        double titleCenterY = 150;
        title.setTranslateX(titleCenterX);
        title.setTranslateY(titleCenterY);

        Text title1 = new Text("Of");
        title1.setFont(Font.font(72));
        title1.setStyle("-fx-fill: #FF6347; -fx-font-weight: bold;");
        double titleCenterX1 = 500;
        double titleCenterY1 = 220;
        title1.setTranslateX(titleCenterX1);
        title1.setTranslateY(titleCenterY1);
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
        shadow.setColor(Color.DARKORANGE);
        shadow.setRadius(5);
        title1.setEffect(shadow);

        Text title2 = new Text("5 Hearts");
        title2.setFont(Font.font(72));
        title2.setStyle("-fx-fill: #FF6347; -fx-font-weight: bold;");
        double titleCenterX2 = 550;
        double titleCenterY2 = 300;
        title2.setTranslateX(titleCenterX2);
        title2.setTranslateY(titleCenterY2);

        Button btnStart = new Button("New Game");
        btnStart.setFont(Font.font(28));
        double centerX = 300;
        double centerY = 350;
        btnStart.setTranslateX(centerX);
        btnStart.setTranslateY(centerY);
        btnStart.setOnAction(e -> n.NewGame());
        btnStart.setStyle("-fx-text-fill: white; " +
                "-fx-border-color: #1E90FF; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 20; " +
                "-fx-background-color: transparent; " +
                "-fx-padding: 5; " +
                "-fx-background-insets: 0, 10; " +
                "-fx-border-insets: 0, 5; " +
                "-fx-background-color: transparent, #00008B; ");
        btnStart.setPrefWidth(500);
        btnStart.setPrefHeight(60);

        Button btnLoad = new Button("Load Game");
        btnLoad.setFont(Font.font(28));
        double centerX1 = 300;
        double centerY1 = 430;
        btnLoad.setTranslateX(centerX1);
        btnLoad.setTranslateY(centerY1);
        btnLoad.setOnAction(e -> L.LoadGame());
        btnLoad.setStyle("-fx-text-fill: white; " +
                "-fx-border-color: #1E90FF; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 20; " +
                "-fx-background-color: transparent; " +
                "-fx-padding: 5; " +
                "-fx-background-insets: 0, 10; " +
                "-fx-border-insets: 0, 5; " +
                "-fx-background-color: transparent, #00008B; ");
        btnLoad.setPrefWidth(500);
        btnLoad.setPrefHeight(60);

        getGameScene().addUINode(title);
        getGameScene().addUINode(title1);
        getGameScene().addUINode(title2);
        getGameScene().addUINode(btnStart);
        getGameScene().addUINode(btnLoad);
    }

    

    
    public static void main(String[] args) {
        launch(args);
    }
}
