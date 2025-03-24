package com.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        // โหลดรูปภาพพื้นหลัง
        Image backgroundImage = new Image("Background/1.jpg");
        BackgroundImage bgImage = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1100, 790, false, false, false, false)
        );

        BorderPane root = new BorderPane();
        root.setBackground(new Background(bgImage));

        // หัวข้อ Tutorial
        Label tutorialTitle = new Label("Tutorial");
        tutorialTitle.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: white;");

        // คำอธิบายกติกาการเล่น
        Label[] tutorialTexts = {
            new Label("1. ตั้งชื่อและเลือกตัวละครที่ต้องการเล่นแล้วกดเริ่ม"),
            new Label("2. เดินไปหามอนสเตอร์ที่สามารถสู้ได้"),
            new Label("3. กดสกิลที่ต้องการใช้และต้องมี Mana ที่เพียงพอถึงจะใช้ได้"),
            new Label("   เมื่อจบ 1 เทิร์น ฝ่ายที่จบจะได้ 1 Mana"),
            new Label("4. เมื่อฝ่ายไหน HP น้อยกว่าหรือเท่ากับ 0 ฝ่ายนั้นจะแพ้"),
            new Label("   และอีกฝ่ายจะเป็นผู้ชนะ"),
            new Label("5. หากชนะมอนสเตอร์ได้จะได้รับแต้มเพื่อปลดล็อคมอนสเตอร์ตัวถัดไป")
        };
        for (Label label : tutorialTexts) {
            label.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        }

        VBox tutorialBox = new VBox(10, tutorialTexts);
        tutorialBox.setAlignment(Pos.CENTER);
        tutorialBox.setPadding(new Insets(20, 20, 20, 30));

        // กรอบข้อความสีดำโปร่งใส
        StackPane textContainer = new StackPane();
        Rectangle textBackground = new Rectangle(600, 300);
        textBackground.setFill(Color.rgb(0, 0, 0, 0.7));
        textBackground.setStroke(Color.WHITE);
        textBackground.setStrokeWidth(3);
        textBackground.setArcWidth(30);
        textBackground.setArcHeight(30);
        textBackground.setEffect(new DropShadow(20, Color.BLACK));

        textContainer.getChildren().addAll(textBackground, tutorialBox);
        textContainer.setAlignment(Pos.TOP_CENTER);
        textContainer.setPadding(new Insets(30, 0, 0, 0));

        // โหลดและแสดงรูปภาพด้านล่าง
        ImageView infoImage = new ImageView(new Image("Background/info.png"));
        infoImage.setFitWidth(450);
        infoImage.setPreserveRatio(true);

        StackPane imageContainer = new StackPane(infoImage);
        imageContainer.setAlignment(Pos.BOTTOM_CENTER);
        imageContainer.setPadding(new Insets(0, 0, 30, 0));

        // ปุ่ม BACK อยู่ด้านบนซ้าย
        Button btnBack = new Button("BACK");
        btnBack.setStyle(
            "-fx-font-size: 18px; " +
            "-fx-background-color: #FF6347; " +
            "-fx-text-fill: white; " +
            "-fx-border-color: black; " +
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px; " +
            "-fx-padding: 10 20; " +
            "-fx-scale-x: 1.0; " +
            "-fx-scale-y: 1.0;"
        );
        btnBack.setOnMouseEntered(e -> btnBack.setStyle(
            "-fx-font-size: 18px; " +
            "-fx-background-color: #FF4500; " +
            "-fx-text-fill: white; " +
            "-fx-border-color: black; " +
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px; " +
            "-fx-padding: 10 20; " +
            "-fx-scale-x: 1.0; " +
            "-fx-scale-y: 1.0;"
        ));
        btnBack.setOnMouseExited(e -> btnBack.setStyle(
            "-fx-font-size: 18px; " +
            "-fx-background-color: #FF6347; " +
            "-fx-text-fill: white; " +
            "-fx-border-color: black; " +
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px; " +
            "-fx-padding: 10 20; " +
            "-fx-scale-x: 1.0; " +
            "-fx-scale-y: 1.0;"
        ));
        btnBack.setOnAction(e -> new MainMenu(primaryStage).showMainMenu());

        BorderPane.setAlignment(btnBack, Pos.TOP_LEFT);
        BorderPane.setMargin(btnBack, new Insets(10));

        // จัดองค์ประกอบทั้งหมดเข้าด้วยกัน
        VBox content = new VBox(10, textContainer, imageContainer);
        content.setAlignment(Pos.CENTER);

        root.setTop(btnBack);
        root.setCenter(content);

        // แสดง Scene
        Scene tutorialScene = new Scene(root, 1100, 790);
        primaryStage.setScene(tutorialScene);
    }
}
