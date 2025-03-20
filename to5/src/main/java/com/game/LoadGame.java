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
        // 🔙 ปุ่มกลับไปเมนูหลัก (ปรับสไตล์ให้เหมือนใน Tutorial)
        Button btnBack = new Button("BACK");
        btnBack.setStyle(
            "-fx-font-size: 18px; " +
            "-fx-background-color: #FF6347; " +  // สีปุ่มโทนส้มแดง
            "-fx-text-fill: white; " +
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px; " +
            "-fx-padding: 10 20;"
        );
        btnBack.setOnAction(e -> new MainMenu(primaryStage).showMainMenu());

        BorderPane root = new BorderPane();
        BorderPane.setAlignment(btnBack, Pos.TOP_LEFT);
        BorderPane.setMargin(btnBack, new Insets(10));  // กำหนด margin เหมือนใน Tutorial
        root.setTop(btnBack);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

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

                        itemBox.setOnMouseClicked(e -> {
                            System.out.println("\"img\": \"" + imgPath + "\",");
                            System.out.println("\"name\": \"" + name + "\",");
                            System.out.println("\"time\": \"" + time + "\",");
                            System.out.println("\"job\": \"" + job + "\",");
                            System.out.println("\"point\": " + point);
                            System.out.println("----------------------------------");
            
                            new GameScene(primaryStage, name, job, imgPath, point).showGameScene();
                            System.out.println("xxxxxxxxx");
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
        hBox.setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0;");

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

        hBox.getChildren().addAll(imageView, nameLabel, jobLabel, pointLabel, timeLabel);
        hBox.setOnMousePressed(e -> {
            hBox.setStyle("-fx-background-color: lightblue; -fx-border-color: blue; -fx-border-width: 1;");
        });
        hBox.setOnMouseReleased(e -> {
            hBox.setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0;");
        });
        hBox.setOnMouseExited(e -> {
            hBox.setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0;");
        });

        return hBox;
    }
}
