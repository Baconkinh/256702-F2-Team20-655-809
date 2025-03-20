package com.game;

import java.io.File;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadGame {
    private Stage primaryStage;

    public LoadGame(Stage stage) {
        this.primaryStage = stage;
    }

    public void showLoadGameScene() {
        // ปุ่ม BACK พร้อมตกแต่งแบบสวยๆ
        Button btnBack = new Button("BACK");
        String defaultStyle = 
            "-fx-font-size: 18px; " +
            "-fx-background-color: #FF6347; " +
            "-fx-text-fill: white; " +
            "-fx-border-color: black; " +
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px; " +
            "-fx-padding: 10 20;";
        String hoverStyle = 
            "-fx-font-size: 18px; " +
            "-fx-background-color: #FF4500; " +
            "-fx-text-fill: white; " +
            "-fx-border-color: black; " +
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px; " +
            "-fx-padding: 10 20;";
        
        btnBack.setStyle(defaultStyle);
        btnBack.setOnMouseEntered(e -> btnBack.setStyle(hoverStyle));
        btnBack.setOnMouseExited(e -> btnBack.setStyle(defaultStyle));
        btnBack.setOnAction(e -> new MainMenu(primaryStage).showMainMenu());

        // ใช้ BorderPane เป็น root แล้วตกแต่งพื้นหลังด้วย background image
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-image: url('/Background/1.jpg');" +
                      "-fx-background-size: 1100px  790px;");
        BorderPane.setAlignment(btnBack, Pos.TOP_LEFT);
        BorderPane.setMargin(btnBack, new Insets(10));
        root.setTop(btnBack);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox vboxList = new VBox(10);
        vboxList.setPadding(new Insets(20));
        vboxList.setAlignment(Pos.CENTER);

        String filePath = "src/main/resources/data/savegame.json";
        File file = new File(filePath);

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                StringBuilder sb = new StringBuilder();
                int c;
                while ((c = reader.read()) != -1) {
                    sb.append((char) c);
                }

                String content = sb.toString().trim();
                if (!content.isEmpty()) {
                    JSONArray jsonArray = new JSONArray(content);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String imgPath = obj.optString("img", "");
                        String name = obj.optString("name", "");
                        String time = obj.optString("time", "");
                        String job = obj.optString("job", "");
                        int point = obj.optInt("point", 0);

                        HBox itemBox = createSaveItem(imgPath, name, job, point, time);

                        // เมื่อคลิกที่รายการ จะพิมพ์ข้อมูลออกมาและเข้าสู่เกม
                        itemBox.setOnMouseClicked(e -> {
                            System.out.println("\"img\": \"" + imgPath + "\",");
                            System.out.println("\"name\": \"" + name + "\",");
                            System.out.println("\"time\": \"" + time + "\",");
                            System.out.println("\"job\": \"" + job + "\",");
                            System.out.println("\"point\": " + point);
                            System.out.println("----------------------------------");

                            new GameScene(primaryStage, name, job, imgPath, point).showGameScene();
                        });

                        vboxList.getChildren().add(itemBox);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        scrollPane.setContent(vboxList);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1100, 790);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Load Game");
        primaryStage.show();
    }

    private HBox createSaveItem(String imgPath, String name, String job, int point, String time) {
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER_LEFT);
        // ตกแต่งรายการด้วยพื้นหลังโปร่งแสงและเส้นขอบที่เน้นความเรียบร้อย
        hBox.setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0; " +
                      "-fx-background-color: rgba(255,255,255,0.6); " +
                      "-fx-background-radius: 10px;");

        ImageView imageView;
        try {
            Image img = new Image(getClass().getResourceAsStream(imgPath));
            imageView = new ImageView(img);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
        } catch (Exception e) {
            System.err.println("Cannot load image: " + imgPath);
            imageView = new ImageView();
        }

        javafx.scene.control.Label nameLabel = new javafx.scene.control.Label("Name: " + name);
        javafx.scene.control.Label jobLabel = new javafx.scene.control.Label("Job: " + job);
        javafx.scene.control.Label pointLabel = new javafx.scene.control.Label("Point: " + point);
        javafx.scene.control.Label timeLabel = new javafx.scene.control.Label("Time: " + time);

        // กำหนดรูปแบบให้ label ต่าง ๆ
        String labelStyle = "-fx-font-size: 16px; -fx-text-fill: #333;";
        nameLabel.setStyle(labelStyle);
        jobLabel.setStyle(labelStyle);
        pointLabel.setStyle(labelStyle);
        timeLabel.setStyle(labelStyle);

        hBox.getChildren().addAll(imageView, nameLabel, jobLabel, pointLabel, timeLabel);

        // เอฟเฟคเมื่อกดและปล่อยเมาส์ในแต่ละรายการ
        hBox.setOnMousePressed(e -> {
            hBox.setStyle("-fx-background-color: rgba(173,216,230,0.8); -fx-border-color: blue; -fx-border-width: 1; -fx-background-radius: 10px;");
        });
        hBox.setOnMouseReleased(e -> {
            hBox.setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0; " +
                          "-fx-background-color: rgba(255,255,255,0.6); -fx-background-radius: 10px;");
        });
        hBox.setOnMouseExited(e -> {
            hBox.setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0; " +
                          "-fx-background-color: rgba(255,255,255,0.6); -fx-background-radius: 10px;");
        });
        // เมื่อเลื่อนเมาส์เข้ามาในแต่ละ savegame (หรือเมื่อลากเมาส์ผ่าน) เปลี่ยนสีพื้นหลัง
        hBox.setOnMouseEntered(e -> {
            hBox.setStyle("-fx-background-color: rgba(240,230,140,0.8); -fx-border-color: gray; -fx-border-width: 0 0 1 0; -fx-background-radius: 10px;");
        });
        // เมื่อลากเมาส์ (drag) ผ่านรายการ ให้ใช้สีเดียวกับเมื่อกด
        hBox.setOnMouseDragged(e -> {
            hBox.setStyle("-fx-background-color: rgba(173,216,230,0.8); -fx-border-color: blue; -fx-border-width: 1; -fx-background-radius: 10px;");
        });

        return hBox;
    }
}
