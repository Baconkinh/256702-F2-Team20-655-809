package com.game;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class Tutorial {

    public void Tutorial() {
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

        Text tutorialText = new Text("Bank\n" +
                                     "is Better\n" +
                                     "Than\n" +
                                     "Nong\n" +
                                     "View\n" +
                                     "View");
        tutorialText.setFont(Font.font("Arial", 50)); 
        tutorialText.setFill(Color.DARKBLUE);
        tutorialText.setTranslateX(500);
        tutorialText.setTranslateY(200);
        tutorialText.setWrappingWidth(700);

        

        getGameScene().addUINode(infoBox);
        getGameScene().addUINode(tutorialText);
        getGameScene().addUINode(Backbtn);
    }
}
