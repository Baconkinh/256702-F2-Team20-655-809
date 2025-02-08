package com.game;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.showMessage;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;  
public class LoadGame {
    public void LoadGame() {

        getGameScene().clearUINodes();
        showMessage("Game Loading..");

        Button Backbtn = new Button("Back");
        Backbtn.setFont(Font.font(28));
        double BackX = 80;
        double BackY = 70;
        Backbtn.setTranslateX(BackX);
        Backbtn.setTranslateY(BackY);
        Backbtn.setOnAction(e -> new App().Main());

        Text title4 = new Text("SELECT CHARACTER");
        title4.setFont(Font.font(60));
        title4.setStyle("\"-fx-background-color: transparent; -fx-font-weight: bold;");
        double title4CenterX1 = 200;
        double title4CenterY1 = 120;
        title4.setTranslateX(title4CenterX1);
        title4.setTranslateY(title4CenterY1);
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
        shadow.setColor(Color.DARKORANGE);
        shadow.setRadius(5);
        title4.setEffect(shadow);

        Image image = new Image(getClass().getResource("/com/game/001.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
        imageView.setTranslateX(80); 
        imageView.setTranslateY(100);

        getGameScene().addUINode(Backbtn);
        getGameScene().addUINode(title4);
        getGameScene().addUINode(imageView);
    }

}
