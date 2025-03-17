package com.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
// ++++++++
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
// ++++++++

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class GameScene {
    private Stage primaryStage;
    private ImageView characterSprite;
    private Label nameLabel;
    // ---------- Add ----------
    private Label pointLabel;
    private int points;
    // ---------- Add ----------
    // +++++++++++++++
     // Array สำหรับเก็บ Kiki sprites จำนวน 5 ตัว
    private ImageView[] kikiSprites;
    
    // จุดไป Map2
    private Circle map2Portal;
    private Label notificationLabel; // แสดงข้อความชั่วคราว
    // +++++++++++++++

    private double characterX = 0, characterY = 0; // แก้ไขตรงนี้
    
    private final double MOVE_STEP = 30;
    private final double MIN_X = -500, MAX_X = 500;
    private final double MIN_Y = -350, MAX_Y = 350;
    

    public GameScene(Stage stage, String characterName, String job, String characterImagePath, int initialPoints) {
        this.primaryStage = stage;
        this.points = initialPoints;

        // โหลดภาพพื้นหลัง
        Image backgroundImage = new Image("/Background/5.jpg");
        BackgroundImage bgImage = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1100, 790, false, false, false, false)
        );

        // โหลดสไปรท์ตัวละคร
        Image img = new Image(getClass().getResourceAsStream(characterImagePath));
        characterSprite = new ImageView(img);
        characterSprite.setFitWidth(100);
        characterSprite.setFitHeight(100);
        characterSprite.setTranslateX(characterX);
        characterSprite.setTranslateY(characterY);

        // ชื่อของตัวละคร
        nameLabel = new Label(characterName + " (" + job + ")");
        nameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        nameLabel.setTranslateX(characterX);
        nameLabel.setTranslateY(characterY - 60);

        // ---------- Add ----------
        pointLabel = new Label("Point: " + points);
        pointLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-border-color: white; -fx-padding: 5px;");
        // ---------- Add ----------

        // ปุ่ม BACK
        Button btnBack = new Button("BACK");
        btnBack.setStyle(
            "-fx-font-size: 18px; " +
            "-fx-background-color: #FF6347; " +
            "-fx-text-fill: white; " +
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px; " +
            "-fx-padding: 10 20;"
        );
        btnBack.setOnAction(e -> new MainMenu(primaryStage).showMainMenu());

        // ---------- Add ----------
        Button btnSave = new Button("SAVE");
        btnSave.setOnAction(e -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            System.out.println("ชื่อที่ตั้ง: " + characterName);
            System.out.println("อาชีพ: " + job);
            System.out.println("รูปภาพ: " + characterImagePath);
            System.out.println("คะแนน: " + pointLabel.getText());
            System.out.println("เวลา: " + dtf.format(now));

            JSONObject newObject = new JSONObject();
            newObject.put("name", characterName);
            newObject.put("job", job);
            newObject.put("img", characterImagePath);
            newObject.put("point", points);
            newObject.put("time", dtf.format(now));

            String filePath = "src/main/resources/data/savegame.json";
            File file = new File(filePath);

            JSONArray jsonArray = new JSONArray();
            if (file.exists()) {
                try (FileReader reader = new FileReader(file)) {
                    StringBuilder sb = new StringBuilder();
                    int i;
                    while ((i = reader.read()) != -1) {
                        sb.append((char) i);
                    }
                    String content = sb.toString().trim();
                    if (!content.isEmpty()) {
                        jsonArray = new JSONArray(content);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (org.json.JSONException ex) {
                    System.err.println("Not From JSON");
                    jsonArray = new JSONArray();
                }
            }
            jsonArray.put(newObject);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jsonArray.toString(4));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        HBox leftButtons = new HBox(10, btnBack, btnSave);
        leftButtons.setAlignment(Pos.TOP_LEFT);

        BorderPane topPane = new BorderPane();
        topPane.setLeft(leftButtons);
        topPane.setCenter(pointLabel);
        BorderPane.setAlignment(pointLabel, Pos.TOP_CENTER);
        // ---------- Add ----------

        // StackPane root = new StackPane(characterSprite, nameLabel);
        // root.setAlignment(Pos.CENTER);
        
        // สุ่มสร้าง Kiki 5 ตัว โดยใช้รูปจาก /assets/Kiki.png
        kikiSprites = new ImageView[5];
        Image kikiImage = new Image(getClass().getResourceAsStream("/assets/Kiki.png"));
        for (int i = 0; i < 5; i++) {
            kikiSprites[i] = new ImageView(kikiImage);
            kikiSprites[i].setFitWidth(50);
            kikiSprites[i].setFitHeight(50);
            // สุ่มตำแหน่งในขอบเขตที่กำหนด
            double randomX = MIN_X + Math.random() * (MAX_X - MIN_X);
            double randomY = MIN_Y + Math.random() * (MAX_Y - MIN_Y);
            kikiSprites[i].setTranslateX(randomX);
            kikiSprites[i].setTranslateY(randomY);
        }

        // สร้าง Circle สำหรับไป Map2
        map2Portal = new Circle(20, Color.ORANGE);
        // วางให้อยู่ทางซ้ายกึ่งกลางจอ (translateX ใกล้ MIN_X, translateY = 0)
        map2Portal.setTranslateX(-500 + 50); // เลื่อนจากขอบซ้ายเข้ามา 50
        map2Portal.setTranslateY(0);

        // สร้าง Label แสดงข้อความ "Go to Map 2" ไว้ใกล้ ๆ circle
        Label map2Label = new Label("Go to Map 2");
        map2Label.setTextFill(Color.ORANGE);
        map2Label.setTranslateX(map2Portal.getTranslateX());
        map2Label.setTranslateY(map2Portal.getTranslateY() - 40);

        // สร้าง StackPane สำหรับแสดงตัวละครหลัก, Kiki ทั้งหมด, และ portal
        StackPane centerPane = new StackPane();
        // เพิ่ม Kiki
        for (ImageView kiki : kikiSprites) {
            centerPane.getChildren().add(kiki);
        }
        // เพิ่ม Circle portal และ Label
        centerPane.getChildren().addAll(map2Portal, map2Label);

        centerPane.getChildren().add(characterSprite);
        centerPane.getChildren().add(nameLabel);
        centerPane.setAlignment(Pos.CENTER);
        centerPane.setStyle("-fx-background-color: black;");

        // notificationLabel สำหรับแสดงข้อความชั่วคราว
        notificationLabel = new Label();
        notificationLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16px; -fx-background-color: rgba(0,0,0,0.6); -fx-padding: 5px;");
        notificationLabel.setVisible(false);

        // ใส่ notificationLabel ไว้บนสุดของ StackPane
        centerPane.getChildren().add(notificationLabel);
        StackPane.setAlignment(notificationLabel, Pos.TOP_CENTER);

        BorderPane layout = new BorderPane();
        layout.setTop(topPane);
        layout.setCenter(centerPane);

        Scene scene = new Scene(layout, 1100, 790);

        // ควบคุมการเคลื่อนที่ของตัวละครหลัก พร้อมตรวจจับการชนกับ Kiki และ Map2 Portal
        scene.setOnKeyPressed(event -> {
            double newX = characterX;
            double newY = characterY;

            if (event.getCode() == KeyCode.W) newY -= MOVE_STEP;
            if (event.getCode() == KeyCode.S) newY += MOVE_STEP;
            if (event.getCode() == KeyCode.A) newX -= MOVE_STEP;
            if (event.getCode() == KeyCode.D) newX += MOVE_STEP;

            if (newX >= MIN_X && newX <= MAX_X) characterX = newX;
            if (newY >= MIN_Y && newY <= MAX_Y) characterY = newY;

            characterSprite.setTranslateX(characterX);
            characterSprite.setTranslateY(characterY);
            nameLabel.setTranslateX(characterX);
            nameLabel.setTranslateY(characterY - 60);

            // ตรวจจับ collision ระหว่างตัวหลักกับ Kiki แต่ละตัว
            for (ImageView kiki : kikiSprites) {
                double dx = Math.abs(characterX - kiki.getTranslateX());
                double dy = Math.abs(characterY - kiki.getTranslateY());
                // ถ้า collision เกิดขึ้น
                if (dx < 50 && dy < 50) {
                    System.out.println("Kikiiiiiiiii");
                    // สุ่มตำแหน่งใหม่ให้กับ Kiki ตัวที่โดน
                    double rx = MIN_X + Math.random() * (MAX_X - MIN_X);
                    double ry = MIN_Y + Math.random() * (MAX_Y - MIN_Y);
                    kiki.setTranslateX(rx);
                    kiki.setTranslateY(ry);
                    // เปลี่ยนไปฉาก turn based โดยส่งชื่อของตัวละครหลัก
                    new TurnBasedScene(primaryStage, job, characterName, points).showTurnBasedScene();
                }
            }

            // ตรวจจับ collision ระหว่างตัวหลักกับ circle map2Portal
            double dxPortal = Math.abs(characterX - map2Portal.getTranslateX());
            double dyPortal = Math.abs(characterY - map2Portal.getTranslateY());
            if (dxPortal < 50 && dyPortal < 50) {
                // เช็คว่า point >= 10000 หรือไม่
                if (points >= 10000) {
                    // ไปด่าน 2
                    new GameScene2(primaryStage, characterName, job, characterImagePath, points).showGameScene2();
                } else {
                    // แจ้งเตือนบนจอ
                    showMessage("You need at least 10000 points to enter Map 2.");
                }
            }
        });

        primaryStage.setScene(scene);
    }

    public void showGameScene() {
        primaryStage.show();
    }

    // เมธอดสำหรับแสดงข้อความสั้น ๆ 1 วินาที
    private void showMessage(String text) {
        notificationLabel.setText(text);
        notificationLabel.setVisible(true);

        javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            notificationLabel.setVisible(false);
            notificationLabel.setText("");
        });
        pause.play();
    }
}

//         BorderPane layout = new BorderPane();
//         layout.setBackground(new Background(bgImage)); // ตั้งค่าพื้นหลัง
//         layout.setCenter(root);
//         layout.setTop(topPane);
//         // layout.setTop(btnBack);
//         // BorderPane.setAlignment(btnBack, Pos.TOP_LEFT);
//         // BorderPane.setMargin(btnBack, new Insets(10));

// // ---------
//         Scene scene = new Scene(layout, 1100, 790);

//         scene.setOnKeyPressed(event -> {
//             double newX = characterX;
//             double newY = characterY;

//             switch (event.getCode()) {
//                 case W -> newY -= MOVE_STEP;
//                 case S -> newY += MOVE_STEP;
//                 case A -> newX -= MOVE_STEP;
//                 case D -> newX += MOVE_STEP;
//             }

//             if (newX >= MIN_X && newX <= MAX_X) characterX = newX;
//             if (newY >= MIN_Y && newY <= MAX_Y) characterY = newY;

//             characterSprite.setTranslateX(characterX);
//             characterSprite.setTranslateY(characterY);
//             nameLabel.setTranslateX(characterX);
//             nameLabel.setTranslateY(characterY - 60);
//         });

//         primaryStage.setScene(scene);
//     }

//     public void showGameScene() {
//         primaryStage.show();
//     }
// }