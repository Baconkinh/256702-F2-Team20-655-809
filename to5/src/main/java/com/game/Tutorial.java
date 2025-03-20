package com.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Tutorial {
    private Stage primaryStage;

    public Tutorial(Stage stage) {
        this.primaryStage = stage;
    }

    public void showTutorialScene() {
        // 🔹 โหลดรูปภาพพื้นหลัง
        Image backgroundImage = new Image("Background/1.jpg");
        BackgroundImage bgImage = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1100, 790, false, false, false, false)
        );

        // 🎨 ตั้งค่าพื้นหลัง
        BorderPane root = new BorderPane();
        root.setBackground(new Background(bgImage));

        // 🏆 หัวข้อ "Tutorial"
        Label creditTitle = new Label("Tutorial");
        creditTitle.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label name1 = new Label("1. ตั้งชื่อและเลือกตัวละครแต่ละตัวที่ต้องการเล่นแล้วกดเริ่ม");
        Label name2 = new Label("2. เดินไปหามอนสเตอร์ที่สามารถสู้ได้");
        Label name3 = new Label("3. กดสกิลที่ต้องการใช้และต้องมีManaที่เพียงพอถึงจะใช้ได้ \nเมื่อจบ 1 เทิร์น ฝ่ายที่จบจะได้ 1 Mana");
        Label name4 = new Label("4. เมื่อฝ่ายไหน Hp น้อยกว่าหรือเท่ากับ 0 ฝ่ายนั้นจะเป็นผู้แพ้ \nและอีกฝ่ายจะเป็นผู้ชนะ");
        Label name5 = new Label("5. หากชนะมอนสเตอร์ได้จะได้รับแต้มเพื่อปลดล็อค\nมอนสเตอร์ตัวถัดไป");

        name1.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        name2.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        name3.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        name4.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        name5.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");

        VBox creditBox = new VBox(10, name1, name2, name3, name4, name5);
        creditBox.setAlignment(Pos.CENTER_LEFT);
        creditBox.setPadding(new Insets(45, 0, 0, 340));

        VBox creditBox1 = new VBox(10, creditTitle);
        creditBox1.setAlignment(Pos.CENTER_LEFT);
        creditBox1.setPadding(new Insets(0, 0, 300, 475));

        // 🎨 ใส่กรอบสี่เหลี่ยมสวยงาม
        StackPane creditContainer = new StackPane();
        creditContainer.getChildren().addAll(createBackgroundBox(), creditBox, creditBox1);
        creditContainer.setAlignment(Pos.CENTER);

        // 🔙 ปุ่มกลับไปเมนูหลัก พร้อมปรับปรุงสไตล์ให้มีกรอบสีดำและเปลี่ยนสีเมื่อ hover
        Button btnBack = new Button("BACK");
        String defaultStyle = 
            "-fx-font-size: 18px; " +
            "-fx-background-color: #FF6347; " +  // สีพื้นหลังปกติ
            "-fx-text-fill: white; " +
            "-fx-border-color: black; " +       // กรอบสีดำ
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px; " +
            "-fx-padding: 10 20;";
        String hoverStyle = 
            "-fx-font-size: 18px; " +
            "-fx-background-color: #FF4500; " +  // สีพื้นหลังเมื่อ hover
            "-fx-text-fill: white; " +
            "-fx-border-color: black; " +
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px; " +
            "-fx-padding: 10 20;";
        btnBack.setStyle(defaultStyle);
        btnBack.setOnMouseEntered(e -> btnBack.setStyle(hoverStyle));
        btnBack.setOnMouseExited(e -> btnBack.setStyle(defaultStyle));
        btnBack.setOnAction(e -> new MainMenu(primaryStage).showMainMenu());

        root.setCenter(creditContainer);
        root.setTop(btnBack);
        BorderPane.setAlignment(btnBack, Pos.TOP_LEFT);
        BorderPane.setMargin(btnBack, new Insets(10));

        // 📺 แสดง Scene
        Scene tutorialScene = new Scene(root, 1100, 790);
        primaryStage.setScene(tutorialScene);
    }

    // 🔹 ฟังก์ชันสร้างกรอบสี่เหลี่ยม
    private StackPane createBackgroundBox() {
        Rectangle backgroundBox = new Rectangle(500, 450);
        backgroundBox.setFill(Color.rgb(0, 0, 0, 0.7)); // พื้นหลังสีดำโปร่งแสง
        backgroundBox.setStroke(Color.WHITE);
        backgroundBox.setStrokeWidth(3);
        backgroundBox.setArcWidth(30); // ขอบมน
        backgroundBox.setArcHeight(30);

        // ✨ เพิ่มเงาให้กรอบ
        DropShadow boxShadow = new DropShadow(20, Color.BLACK);
        backgroundBox.setEffect(boxShadow);

        return new StackPane(backgroundBox);
    }
}
