// package com.game;

// import javafx.scene.control.Button;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Rectangle;
// import javafx.scene.text.Font;
// import javafx.scene.text.Text;
// import javafx.geometry.Insets;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import static com.almasb.fxgl.dsl.FXGL.*;
// public class Credit {

//     public void Credit() {
//         getGameScene().clearUINodes();
//         Image imagebg = getAssetLoader().loadImage("bg.jpg");
//         ImageView background = new ImageView(imagebg);
//         background.setFitWidth(1100);
//         background.setFitHeight(790);
//         getGameScene().addUINode(background);


//         // ปุ่มกลับ (Back Button)
//         Button Backbtn = new Button("Back");
//         Backbtn.setFont(Font.font("Arial", 30));
//         Backbtn.setStyle("-fx-background-color: #FF4500; -fx-text-fill: white; -fx-border-radius: 15px; -fx-background-radius: 15px; -fx-padding: 10 30 10 30;");
//         double BackX = 80;
//         double BackY = 70;
//         Backbtn.setTranslateX(BackX);
//         Backbtn.setTranslateY(BackY);
//         Backbtn.setOnAction(e -> new App().Main());
//         getGameScene().addUINode(Backbtn);
//         // กรอบข้อความ (infoBox) สไตล์ใหม่
//         Rectangle infoBox = new Rectangle(800, 400);
//         infoBox.setFill(Color.rgb(255, 255, 204));  // สีพื้นหลังกรอบอ่อน
//         infoBox.setStroke(Color.DARKBLUE);  // ขอบกรอบสีน้ำเงิน
//         infoBox.setStrokeWidth(7);  // ความหนาของขอบ
//         infoBox.setArcWidth(40);  // มุมโค้งมนของกรอบ
//         infoBox.setArcHeight(40);
//         infoBox.setTranslateX(150);
//         infoBox.setTranslateY(150);

//         // เพิ่ม DropShadow ให้กับกรอบ
//         DropShadow boxShadow = new DropShadow(15, 10, 10, Color.GRAY);
//         infoBox.setEffect(boxShadow);

//         // ข้อความในกรอบ
//         Text tutorialText = new Text(
//                 "Presentation By\n\n"
//                 + "   1. อัจฉรา ดังดี  6730300655\n"
//                 + "      ตกแต่งหน้า UI , ทำรูปภาพประกอบเกม , ออกแบบสกิลตัวละคร\n"
//                 + "   2. ปภากร จันทร์ดี  6730300809\n"
//                 + "      จัดหน้า UI , เพิ่มฟังก์ชันต่างๆ , ออกแบบรูปแบบการเล่น");

//         tutorialText.setFont(Font.font("Tahoma", 28));  // ฟอนต์ใหม่
//         tutorialText.setFill(Color.DARKSLATEBLUE);  // สีข้อความ
//         tutorialText.setTranslateX(210);  // จัดตำแหน่งข้อความให้อยู่กลางกรอบ
//         tutorialText.setTranslateY(230);
//         tutorialText.setWrappingWidth(700);  // กำหนดให้ข้อความไม่เกินกรอบ

//         // เพิ่มการตกแต่งข้อความด้วยเงา
//         DropShadow textShadow = new DropShadow(10, 3, 3, Color.GRAY);
//         tutorialText.setEffect(textShadow);

//         // เพิ่มการตกแต่งปุ่มกลับให้ดูโดดเด่น
//         Backbtn.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-padding: 15 40 15 40;");

//         // เพิ่มข้อมูลและปุ่มกลับเข้าไปในฉาก
//         getGameScene().addUINode(infoBox);
//         getGameScene().addUINode(tutorialText);
    
//     }
// }


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
        // name1.setFont(Font.font("Sriracha", 20));
        // name2.setFont(Font.font("tahoma", 28));
        
        VBox creditBox = new VBox(10, name1, name2);
        creditBox.setAlignment(Pos.CENTER_LEFT);
        creditBox.setPadding(new Insets(50,0,0,340));

        VBox creditBox1 = new VBox(10, creditTitle);
        creditBox1.setAlignment(Pos.CENTER_LEFT);
        creditBox1.setPadding(new Insets(0,0,200,475));

        // 🎨 ใส่กรอบสี่เหลี่ยมสวยงาม
        StackPane creditContainer = new StackPane();
        
        creditContainer.getChildren().addAll(createBackgroundBox(), creditBox ,creditBox1);
       
        creditContainer.setAlignment(Pos.CENTER);

        // 🔙 ปุ่มกลับไปเมนูหลัก
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
