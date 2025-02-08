package com.game;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NewGame {
    public void NewGame() {
        getGameScene().clearUINodes();
        //bg
        //Image bgImage = new Image(getClass().getResource("/com/game/bg.jpg").toExternalForm());
        //ImageView bgView = new ImageView(bgImage);
        
        //getGameScene().addUINode(bgView);

        
        // button Back
        Button Backbtn = new Button("Back");
        Backbtn.setFont(Font.font(28));
        double BackX = 80;
        double BackY = 70;
        Backbtn.setTranslateX(BackX);
        Backbtn.setTranslateY(BackY);
        Backbtn.setOnAction(e -> new App().Main());
        // title SELECT CHARACTER
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
        // Image 001
        Image image = new Image(getClass().getResource("/com/game/001.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
        imageView.setTranslateX(80);
        imageView.setTranslateY(160);
        // Image 002
        Image image1 = new Image(getClass().getResource("/com/game/002.png").toExternalForm());
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(300);
        imageView1.setFitHeight(200);
        imageView1.setPreserveRatio(true);
        imageView1.setTranslateX(290);
        imageView1.setTranslateY(160);
        // Image003
        Image image2 = new Image(getClass().getResource("/com/game/003.png").toExternalForm());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(300);
        imageView2.setFitHeight(200);
        imageView2.setPreserveRatio(true);
        imageView2.setTranslateX(500);
        imageView2.setTranslateY(160);
        // Image004
        Image image3 = new Image(getClass().getResource("/com/game/004.png").toExternalForm());
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(300);
        imageView3.setFitHeight(200);
        imageView3.setPreserveRatio(true);
        imageView3.setTranslateX(185);
        imageView3.setTranslateY(420);
        // Image005
        Image image4 = new Image(getClass().getResource("/com/game/005.png").toExternalForm());
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitWidth(300);
        imageView4.setFitHeight(200);
        imageView4.setPreserveRatio(true);
        imageView4.setTranslateX(400);
        imageView4.setTranslateY(420);
        
        // ช่องสี่เหลี่ยมเลือกตัว


        // แสดง UI
        getGameScene().addUINode(Backbtn);
        getGameScene().addUINode(title4);
        getGameScene().addUINode(imageView);
        getGameScene().addUINode(imageView1);
        getGameScene().addUINode(imageView2);
        getGameScene().addUINode(imageView3);
        getGameScene().addUINode(imageView4);
  

    }
}
