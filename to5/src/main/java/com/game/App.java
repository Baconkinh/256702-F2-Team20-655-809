package com.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
    Main();
    }

    public void Main() {
        getGameScene().clearUINodes();
        NewGame n = new NewGame();
        LoadGame L = new LoadGame();
        Tutorial T = new Tutorial();
        Credit C = new Credit();

        getGameScene().setBackgroundColor(Color.rgb(173, 216, 230)); // background color
        // title Tournament
        Text title = new Text("Tournament");
        title.setFont(Font.font(72));
        title.setStyle("-fx-fill: #FF6347; -fx-font-weight: bold;");
        double titleCenterX = 150;
        double titleCenterY = 150;
        title.setTranslateX(titleCenterX);
        title.setTranslateY(titleCenterY);
        // title1 Of
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
        // title2 5 Hearts
        Text title2 = new Text("5 Hearts");
        title2.setFont(Font.font(72));
        title2.setStyle("-fx-fill: #FF6347; -fx-font-weight: bold;");
        double titleCenterX2 = 550;
        double titleCenterY2 = 300;
        title2.setTranslateX(titleCenterX2);
        title2.setTranslateY(titleCenterY2);
        // button New Game
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
        // button Load Game
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
        //button Tutorial
        Button btnTutorial = new Button("Tutorial");
        btnTutorial.setFont(Font.font(28));
        double centerX2 = 300;
        double centerY2 = 510;
        btnTutorial.setTranslateX(centerX2);
        btnTutorial.setTranslateY(centerY2);
        btnTutorial.setOnAction(e -> T.Tutorial());
        btnTutorial.setStyle("-fx-text-fill: white; " +
                "-fx-border-color: #1E90FF; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 20; " +
                "-fx-background-color: transparent; " +
                "-fx-padding: 5; " +
                "-fx-background-insets: 0, 10; " +
                "-fx-border-insets: 0, 5; " +
                "-fx-background-color: transparent, #00008B; ");
        btnTutorial.setPrefWidth(500);
        btnTutorial.setPrefHeight(60);
        // button Credit
        Button btnCredit = new Button("Credit");
btnCredit.setFont(Font.font(28));
        double centerX3 = 300;
        double centerY3 = 590;
        btnCredit.setTranslateX(centerX3);
        btnCredit.setTranslateY(centerY3);
        btnCredit.setOnAction(e -> C.Credit());
        btnCredit.setStyle("-fx-text-fill: white; " +
                "-fx-border-color: #1E90FF; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 20; " +
                "-fx-background-color: transparent; " +
                "-fx-padding: 5; " +
                "-fx-background-insets: 0, 10; " +
                "-fx-border-insets: 0, 5; " +
                "-fx-background-color: transparent, #00008B; ");
        btnCredit.setPrefWidth(500);
        btnCredit.setPrefHeight(60);
        // แสดง UI
        getGameScene().addUINode(title);
        getGameScene().addUINode(title1);
        getGameScene().addUINode(title2);
        getGameScene().addUINode(btnStart);
        getGameScene().addUINode(btnLoad);
        getGameScene().addUINode(btnTutorial);
        getGameScene().addUINode(btnCredit);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
