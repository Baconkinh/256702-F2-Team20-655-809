package com.game;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Credit {
    public void Credit() {
        getGameScene().clearUINodes();
        Button Backbtn = new Button("Back");
        Backbtn.setFont(Font.font(28));
        double BackX = 80;
        double BackY = 70;
        Backbtn.setTranslateX(BackX);
        Backbtn.setTranslateY(BackY);
        Backbtn.setOnAction(e -> new App().Main());

        Rectangle infoBox = new Rectangle(800, 400);
        infoBox.setFill(Color.LIGHTYELLOW);
        infoBox.setStroke(Color.DARKBLUE);
        infoBox.setStrokeWidth(5);
        infoBox.setArcWidth(30);
        infoBox.setArcHeight(30);
        infoBox.setTranslateX(150);
        infoBox.setTranslateY(150);

        Text tutorialText = new Text(
                "Presentation By\n\n" +
                        "   1. อัจฉรา ดังดี  6730300655\n" +
                        "      ไม่ทำไรเลย\n" +
                        "   2. ปภากร จันทร์ดี  6730300809\n" +
                        "      แบกหมดทุกอย่าง");

        tutorialText.setFont(Font.font("Tahoma", 32));
        tutorialText.setFill(Color.DARKBLUE);
        tutorialText.setTranslateX(300);
        tutorialText.setTranslateY(200);
        tutorialText.setWrappingWidth(700);

        getGameScene().addUINode(infoBox);
        getGameScene().addUINode(tutorialText);
        getGameScene().addUINode(Backbtn);
    }
}
