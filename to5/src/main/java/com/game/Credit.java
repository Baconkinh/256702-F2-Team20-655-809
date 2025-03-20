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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Credit {
    private Stage primaryStage;

    public Credit(Stage stage) {
        this.primaryStage = stage;
    }

    public void showCreditScene() {
        // 🔹 โหลดรูปภาพพื้นหลัง
        Image backgroundImage = new Image("Background/1.jpg"); // ตรวจสอบ path ให้ถูกต้อง
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

        // 🏆 หัวข้อ "CREDITS"
        Label creditTitle = new Label("CREDITS");
        creditTitle.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: white;");
        Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Sriracha-Regular.ttf"), 20);
        Font font1 = Font.loadFont(getClass().getResourceAsStream("/Font/Pridi-Bold.ttf"), 20);
        Font font2 = Font.loadFont(getClass().getResourceAsStream("/Font/Kanit-Bold.ttf"), 20);
        // 👥 รายชื่อทีมงาน
        Label name1 = new Label("1. อัจฉรา ดังดี  6730300655\nตกแต่งหน้า UI , ทำรูปภาพประกอบเกม , \nออกแบบสกิลตัวละคร");
        Label name2 = new Label("2. ปภากร จันทร์ดี  6730300809\nจัดหน้า UI , เพิ่มฟังก์ชันต่างๆ , ออกแบบรูปแบบการเล่น");

        name1.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-family: Kanit;");
        name2.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-family: Kanit;");

        name1.setFont(font2);
        name2.setFont(font2);
        
        VBox creditBox = new VBox(10, name1, name2);
        creditBox.setAlignment(Pos.CENTER_LEFT);
        creditBox.setPadding(new Insets(50, 0, 0, 340));

        VBox creditBox1 = new VBox(10, creditTitle);
        creditBox1.setAlignment(Pos.CENTER_LEFT);
        creditBox1.setPadding(new Insets(0, 0, 200, 475));

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
            "-fx-background-color: #FF4500; " +  // สีพื้นหลังเมื่อ hover (ปรับได้ตามต้องการ)
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
        Scene creditScene = new Scene(root, 1100, 790);
        creditScene.getStylesheets().add(getClass().getResource("/Font/Sriracha-Regular.ttf").toExternalForm());
        primaryStage.setScene(creditScene);
    }

    // 🔹 ฟังก์ชันสร้างกรอบสี่เหลี่ยม
    private StackPane createBackgroundBox() {
        Rectangle backgroundBox = new Rectangle(500, 300);
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
