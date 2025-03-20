package com.game;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameScene2 {
    private Stage primaryStage;
    private ImageView characterSprite;
    private Label nameLabel;
    private Label pointLabel; 
    private double characterX = 500, characterY = 350;
    private final double MOVE_STEP = 30;
    private final double MIN_X = -500, MAX_X = 500; 
    private final double MIN_Y = -350, MAX_Y = 350; 
    private int points; 

    private ImageView[] kikiSprites;
    // จุดกลมกลับไป Map1
    private Circle backPortal;
    private Label notificationLabel;

    public GameScene2(Stage stage, String characterName, String job, String characterImagePath, int initialPoints) {
        this.primaryStage = stage;
        this.points = initialPoints;

        // โหลดภาพพื้นหลัง (เหมือนใน GameScene)
        Image backgroundImage = new Image("/Background/4.png");
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
        characterSprite.setFitWidth(50);
        characterSprite.setFitHeight(50);
        characterSprite.setTranslateX(characterX);
        characterSprite.setTranslateY(characterY);

        nameLabel = new Label(characterName + " (" + job + ")");
        nameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        nameLabel.setTranslateX(characterX);
        nameLabel.setTranslateY(characterY - 60);

        pointLabel = new Label("Point: " + points);
        pointLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-border-color: white; -fx-padding: 5px;");

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

        // ปุ่ม SAVE
        Button btnSave = new Button("SAVE");
        btnSave.setOnAction(e -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("Map2 SAVE => " + characterName + " job=" + job + " point=" + points);
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
                    System.err.println("ไฟล์ JSON ไม่ถูกต้อง จะเริ่มใหม่เป็นอาเรย์ว่าง");
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

        // จัดวางปุ่มใน topPane ตามแบบ GameScene
        HBox leftButtons = new HBox(10, btnBack, btnSave);
        leftButtons.setAlignment(Pos.TOP_LEFT);
        BorderPane topPane = new BorderPane();
        topPane.setLeft(leftButtons);
        topPane.setCenter(pointLabel);
        BorderPane.setAlignment(pointLabel, Pos.TOP_CENTER);
        topPane.setPadding(new Insets(10));

        // สร้าง Kiki
        kikiSprites = new ImageView[5];
        Image kikiImage = new Image(getClass().getResourceAsStream("/assets/Kiki.png"));
        for (int i = 0; i < 5; i++) {
            kikiSprites[i] = new ImageView(kikiImage);
            kikiSprites[i].setFitWidth(150);
            kikiSprites[i].setFitHeight(150);
            double randomX = MIN_X + Math.random() * (MAX_X - MIN_X);
            double randomY = MIN_Y + Math.random() * (MAX_Y - MIN_Y);
            kikiSprites[i].setTranslateX(randomX);
            kikiSprites[i].setTranslateY(randomY);
        }

        // สร้าง Circle กลับไป Map1
        backPortal = new Circle(20, Color.GREEN);
        backPortal.setTranslateX(-500 + 50); 
        backPortal.setTranslateY(0);

        Label backLabel = new Label("Go back to Map 1");
        backLabel.setTextFill(Color.GREEN);
        backLabel.setTranslateX(backPortal.getTranslateX());
        backLabel.setTranslateY(backPortal.getTranslateY() - 40);

        // สร้าง StackPane สำหรับจัดวางองค์ประกอบ (ตัวละคร, Kiki, portal, notificationLabel)
        StackPane centerPane = new StackPane();
        // เพิ่ม Kiki
        for (ImageView kiki : kikiSprites) {
            centerPane.getChildren().add(kiki);
        }
        // เพิ่ม portal และ label
        centerPane.getChildren().addAll(backPortal, backLabel);
        // เพิ่มตัวละครและชื่อ
        centerPane.getChildren().add(characterSprite);
        centerPane.getChildren().add(nameLabel);
        centerPane.setAlignment(Pos.CENTER);

        notificationLabel = new Label();
        notificationLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16px; -fx-background-color: rgba(0,0,0,0.6); -fx-padding: 5px;");
        notificationLabel.setVisible(false);
        centerPane.getChildren().add(notificationLabel);
        StackPane.setAlignment(notificationLabel, Pos.TOP_CENTER);

        // สร้าง BorderPane หลัก และตั้งพื้นหลังด้วย BackgroundImage
        BorderPane layout = new BorderPane();
        layout.setTop(topPane);
        layout.setCenter(centerPane);
        layout.setBackground(new Background(bgImage));

        Scene scene = new Scene(layout, 1100, 790);

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

            // ตรวจจับการชนกับ Kiki
            for (ImageView kiki : kikiSprites) {
                double dx = Math.abs(characterX - kiki.getTranslateX());
                double dy = Math.abs(characterY - kiki.getTranslateY());
                if (dx < 50 && dy < 50) {
                    System.out.println("Kikiiiiiiiii in MAP 2");
                    double rx = MIN_X + Math.random() * (MAX_X - MIN_X);
                    double ry = MIN_Y + Math.random() * (MAX_Y - MIN_Y);
                    kiki.setTranslateX(rx);
                    kiki.setTranslateY(ry);
                    new TurnBasedScene(primaryStage, job, characterName, points).showTurnBasedScene();
                }
            }

            // ตรวจจับการชนกับ backPortal => กลับไป Map1
            double dxPortal = Math.abs(characterX - backPortal.getTranslateX());
            double dyPortal = Math.abs(characterY - backPortal.getTranslateY());
            if (dxPortal < 50 && dyPortal < 50) {
                new GameScene(primaryStage, characterName, job, characterImagePath, points).showGameScene();
            }
        });

        primaryStage.setScene(scene);
    }

    public void showGameScene2() {
        primaryStage.show();
    }
    
    // ฟังก์ชันแสดงข้อความแจ้งเตือน (ยังไม่ได้ใช้งาน)
    private void showMessage(String text) {
        notificationLabel.setText(text);
        notificationLabel.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            notificationLabel.setVisible(false);
            notificationLabel.setText("");
        });
        pause.play();
    }
}
