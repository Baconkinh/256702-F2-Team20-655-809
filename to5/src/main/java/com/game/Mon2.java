package com.game;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.geometry.Insets;



public class Mon2 {

    public Mon2() {
        // แสดงข้อความเมื่อชนกับ NPC2
        VBox messageBox = new VBox();
        messageBox.setPadding(new Insets(10));
        messageBox.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundFill(Color.LIGHTBLUE, new javafx.scene.layout.CornerRadii(5), Insets.EMPTY)));
        
        Text messageText = new Text("You have encountered Mon2!");
        messageBox.getChildren().add(messageText);
        messageBox.setTranslateX(400);
        messageBox.setTranslateY(350);
        
        // เพิ่มกล่องข้อความไปยังฉาก
        FXGL.getGameScene().addUINode(messageBox);
        
        // ลบข้อความหลังจาก 2 วินาที
       
    }
}


