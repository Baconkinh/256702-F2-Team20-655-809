package com.game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {

    private Stage primaryStage;

    public MainMenu() { }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        showMainMenu();
    }

    public MainMenu(Stage stage) { 
        this.primaryStage = stage;
    }

    public void showMainMenu() {
        Button btnNewGame = createStyledButton("New Game");
        Button btnLoadGame = createStyledButton("Load Game");
        Button btnTutorial = createStyledButton("Tutorial");
        Button btnCredit = createStyledButton("Credit");

        // ตั้ง Action ให้กับแต่ละปุ่ม
        btnNewGame.setOnAction(e -> new CharacterCreation(primaryStage).showCharacterCreationScene());
        btnLoadGame.setOnAction(e -> new LoadGame(primaryStage).showLoadGameScene());
        btnTutorial.setOnAction(e -> new Tutorial(primaryStage).showTutorialScene());
        btnCredit.setOnAction(e -> new Credit(primaryStage).showCreditScene()); 

        VBox vbox = new VBox(20, btnNewGame, btnLoadGame, btnTutorial, btnCredit);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-image: url('/Background/real.jpg');" +
                      "-fx-background-size: 1100px 790px;");

        // สร้าง Scene
        Scene scene = new Scene(vbox, 1100, 790);

        // โหลดภาพสำหรับเมาส์ (Cursor) ที่เป็นสีฟ้า และระบุขนาดให้ใหญ่ขึ้น (48x48 พิกเซล)
        Image blueCursorImage = new Image("file:src/main/resources/assets/cc.png", 48, 48, true, true);
        scene.setCursor(new ImageCursor(blueCursorImage));

        primaryStage.setScene(scene);
        primaryStage.setTitle("TOURNAMENT OF 5 Hearts");
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(200, 50);
        button.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #333; -fx-text-fill: white; -fx-border-radius: 10px; -fx-border-color: white;");

        // เมื่อเมาส์ชี้ปุ่ม
        button.setOnMouseEntered(e -> button.setStyle(
            "-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #ff5733; " +
            "-fx-text-fill: white; -fx-border-radius: 10px; -fx-border-color: white; " +
            "-fx-scale-x: 1.1; -fx-scale-y: 1.1; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.6), 10, 0, 0, 0);"
        ));

        // เมื่อเมาส์ออกจากปุ่ม
        button.setOnMouseExited(e -> button.setStyle(
            "-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #333; " +
            "-fx-text-fill: white; -fx-border-radius: 10px; -fx-border-color: white;"
        ));

        return button;
    }

    public static void main(String[] args) {
        launch();
    }
}