package com.game;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TurnBasedScene {
    private Stage primaryStage;
    private String Namecha;
    private JSONObject playerData;  
    private int playerMaxHp;
    private int playerCurrentHp;
    private int playerMaxMana;
    private int playerCurrentMana;
    
    private JSONArray skill1;
    private JSONArray skill2;
    private JSONArray skill3;
    
    // ฝ่าย Kiki
    private int enemyMaxHp = 10;
    private int enemyCurrentHp = 10;
    
    // UI หลัก
    private Label hpLabel;
    private Label manaLabel;
    private Label turnLabel;
    private Label enemyHpLabel;
    private ImageView enemyImageView;
    private ImageView playerImageView;

    // Label สำหรับแสดงข้อความแจ้งเตือน
    private Label notificationLabel;

    // ---- เพิ่มตัวแปรเก็บ Points จากภายนอก (ส่งค่าเข้ามาเหมือนปกติ) ----
    private int currentPoints;

    // เก็บ path รูปของ player เพื่อกลับไปหน้า GameScene
    private String charImagePath;

    /**
     * Constructor
     * @param stage Stage หลัก
     * @param job Class ของตัวละคร (ใช้ดึงข้อมูลจาก character.json)
     * @param characterName ชื่อที่ผู้เล่นตั้ง
     * @param currentPoints คะแนนปัจจุบัน (จาก GameScene)
     */
    public TurnBasedScene(Stage stage, String job, String characterName, int currentPoints) {
        this.primaryStage = stage;
        this.Namecha = characterName;
        this.currentPoints = currentPoints;
        
        loadPlayerData(job);
        // กำหนด mana เริ่มต้นเป็น 2/10
        playerMaxMana = 10;
        playerCurrentMana = 2;
    }
    
    private void loadPlayerData(String job) {
        String filePath = "src/main/resources/data/character.json";
        File file = new File(filePath);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                StringBuilder sb = new StringBuilder();
                int c;
                while ((c = reader.read()) != -1) {
                    sb.append((char) c);
                }
                String content = sb.toString().trim();
                JSONObject json = new JSONObject(content);
                JSONArray characters = json.getJSONArray("Character");
                for (int i = 0; i < characters.length(); i++) {
                    JSONObject character = characters.getJSONObject(i);
                    if (character.getString("Class").equalsIgnoreCase(job)) {
                        playerData = character;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (playerData == null) {
            playerData = new JSONObject();
            playerData.put("Name", "Unknown");
            playerData.put("Class", job);
            playerData.put("Hp", 10);
            playerData.put("img_part", "/assets/default.png");
            playerData.put("Skill1", new JSONArray());
            playerData.put("Skill2", new JSONArray());
            playerData.put("Skill3", new JSONArray());
        }
        playerMaxHp = playerData.optInt("Hp", 10);
        playerCurrentHp = playerMaxHp;
        
        skill1 = playerData.optJSONArray("Skill1");
        skill2 = playerData.optJSONArray("Skill2");
        skill3 = playerData.optJSONArray("Skill3");
        
        // เก็บ path รูปของตัวละครเรา เพื่อกลับไปหน้า GameScene เอาไฟล์ default.png มาใส่ได้ถ้าระบบดึงข้อมูลพลาดจะเอารูปนี้มาใช้แทน
        charImagePath = playerData.optString("img_part", "/assets/default.png");
    }
    
    private String createBar(int current, int max) {
        int totalBars = 10;
        int filled = (int) Math.round((current * totalBars) / (double) max);
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < totalBars; i++) {
            bar.append(i < filled ? "⭕" : " ");
        }
        bar.append("]");
        return bar.toString();
    }
    
    private void updatePlayerStatus() {
        hpLabel.setText("HP: " + createBar(playerCurrentHp, playerMaxHp) + " " + playerCurrentHp + "/" + playerMaxHp);
        manaLabel.setText("Mana: " + createBar(playerCurrentMana, playerMaxMana) + " " + playerCurrentMana + "/" + playerMaxMana);
    }
    
    private void updateEnemyStatus() {
        enemyHpLabel.setText("HP: " + createBar(enemyCurrentHp, enemyMaxHp) + " " + enemyCurrentHp + "/" + enemyMaxHp);
    }
    
    // ฟังก์ชันสำหรับแสดงข้อความชั่วคราว 1 วินาที
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

    // ตรวจสอบว่าจบเกมหรือยัง
    private void checkEndGame() {
        // ถ้า enemy HP <= 0 ก่อน
        if (enemyCurrentHp <= 0) {
            // ผู้เล่นชนะ
            endBattle(true);
        }
        // ถ้า HP ของเราลดเหลือ 0
        else if (playerCurrentHp <= 0) {
            // ผู้เล่นแพ้
            endBattle(false);
        }
    }
    
    // ฟังก์ชันจบการต่อสู้
    private void endBattle(boolean isWin) {
        if (isWin) {
            // เพิ่ม Point +100
            currentPoints += 100;
            showMessage("You Win! +100 Points");
        } else {
            showMessage("You Lose!");
        }
        
        // หน่วงเวลาให้ผู้เล่นเห็นข้อความ
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(e -> {
            // กลับไปหน้า GameScene โดยส่ง points ปัจจุบัน
            new GameScene(primaryStage, Namecha, playerData.optString("Class"), charImagePath, currentPoints).showGameScene();
        });
        pause.play();
    }

    private void useSkill(JSONObject skill) {
        int requiredMana = skill.optInt("mana", 0);
        if (playerCurrentMana < requiredMana) {
            showMessage("Insufficient mana");
            return;
        }
        
        // Animation ตัวเราโจมตี
        TranslateTransition playerAttackTransition = new TranslateTransition(Duration.seconds(0.5), playerImageView);
        playerAttackTransition.setByX(20);
        playerAttackTransition.setAutoReverse(true);
        playerAttackTransition.setCycleCount(2);
        playerAttackTransition.play();
        
        // หัก mana
        playerCurrentMana -= requiredMana;
        
        int totalDamage = skill.optInt("Dmg", 0);
        boolean extraTurn = false;
        
        if (skill.has("Sp")) {
            JSONArray spArray = skill.getJSONArray("Sp");
            if (spArray.length() > 0) {
                JSONObject sp = spArray.getJSONObject(0);
                int spDmg = sp.optInt("Dmg", 0);
                int spMana = sp.optInt("mana", 0);
                int spHp = sp.optInt("Hp", 0);
                boolean spTurn = sp.optBoolean("turn", false);
                
                if (spDmg != 0) {
                    totalDamage += spDmg;
                    showMessage("special +Dmg " + spDmg);
                }
                if (spMana != 0) {
                    playerCurrentMana += spMana;
                    if (playerCurrentMana > playerMaxMana) {
                        playerCurrentMana = playerMaxMana;
                    }
                    showMessage("special +Mana " + spMana);
                }
                if (spHp != 0) {
                    playerCurrentHp += spHp;
                    if (playerCurrentHp > playerMaxHp) {
                        playerCurrentHp = playerMaxHp;
                    }
                    showMessage("special +HP " + spHp);
                }
                if (spTurn) {
                    extraTurn = true;
                    showMessage("special: extra turn");
                }
            }
        }
        
        // ลด HP ของ Kiki
        enemyCurrentHp -= totalDamage;
        if (enemyCurrentHp < 0) enemyCurrentHp = 0;
        
        showMessage("Dealt " + totalDamage + " damage to enemy.");
        updatePlayerStatus();
        updateEnemyStatus();
        
        // ตรวจสอบว่าจบเกมหรือยัง (กรณีศัตรูตายจาก skill นี้)
        checkEndGame();
        
        // ถ้ายังไม่จบเกม
        if (enemyCurrentHp > 0) {
            // ถ้าไม่มี extra turn ให้ enemy โจมตี
            if (!extraTurn) {
                processEndTurn();
            } else {
                turnLabel.setText("Turn: Your Turn");
            }
        }
    }
    
    private void processEndTurn() {
        turnLabel.setText("Turn: Enemy's Turn");
        
        // หน่วงเวลา 1 วินาที ก่อนเริ่ม animation โจมตี
        PauseTransition pause = new PauseTransition(Duration.seconds(1.0));
        pause.setOnFinished(e -> {
            TranslateTransition attackTransition = new TranslateTransition(Duration.seconds(0.5), enemyImageView);
            attackTransition.setByX(-20);
            attackTransition.setAutoReverse(true);
            attackTransition.setCycleCount(2);
            attackTransition.setOnFinished(event -> {
                if (playerCurrentHp > 0) {
                    playerCurrentHp -= 1;
                }
                if (playerCurrentMana < playerMaxMana) {
                    playerCurrentMana += 1;
                }
                updatePlayerStatus();
                turnLabel.setText("Turn: Your Turn");
                
                // ตรวจสอบว่าเราตายหรือยัง
                checkEndGame();
            });
            attackTransition.play();
        });
        pause.play();
    }
    
    public void showTurnBasedScene() {
        BorderPane root = new BorderPane();
        
        // ด้านบน: ปุ่ม Back, Title, Turn Indicator
        HBox topPane = new HBox(10);
        topPane.setPadding(new Insets(10));
        topPane.setAlignment(Pos.CENTER_LEFT);
        
        Button btnBack = new Button("BACK");
        
        btnBack.setOnAction(e -> new MainMenu(primaryStage).showMainMenu());
        
        Label title = new Label("Turn Based Battle");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        turnLabel = new Label("Turn: Your Turn");
        turnLabel.setStyle("-fx-font-size: 18px;");
        
        topPane.getChildren().addAll(btnBack, title, turnLabel);
        root.setTop(topPane);
        
        // Panel ของ Player
        VBox playerPane = new VBox(10);
        playerPane.setPadding(new Insets(10));
        playerPane.setAlignment(Pos.CENTER);
        
        try {
            Image playerImg = new Image(getClass().getResourceAsStream(charImagePath));
            playerImageView = new ImageView(playerImg);
            playerImageView.setFitWidth(100);
            playerImageView.setFitHeight(100);
        } catch (Exception e) {
            playerImageView = new ImageView();
        }
        
        Label playerNameLabel = new Label(Namecha + " (" + playerData.optString("Class", "Unknown") + ")");
        hpLabel = new Label("HP: " + createBar(playerCurrentHp, playerMaxHp) + " " + playerCurrentHp + "/" + playerMaxHp);
        manaLabel = new Label("Mana: " + createBar(playerCurrentMana, playerMaxMana) + " " + playerCurrentMana + "/" + playerMaxMana);
        
        playerPane.getChildren().addAll(playerImageView, playerNameLabel, hpLabel, manaLabel);
        
        // Panel ของ Enemy (Kiki)
        VBox enemyPane = new VBox(10);
        enemyPane.setPadding(new Insets(10));
        enemyPane.setAlignment(Pos.CENTER);
        
        try {
            Image enemyImg = new Image(getClass().getResourceAsStream("/assets/Kiki.png"));
            enemyImageView = new ImageView(enemyImg);
            enemyImageView.setFitWidth(100);
            enemyImageView.setFitHeight(100);
        } catch (Exception e) {
            enemyImageView = new ImageView();
        }
        
        Label enemyNameLabel = new Label("Kiki");
        enemyHpLabel = new Label("HP: " + createBar(enemyCurrentHp, enemyMaxHp) + " " + enemyCurrentHp + "/" + enemyMaxHp);
        enemyPane.getChildren().addAll(enemyImageView, enemyNameLabel, enemyHpLabel);
        
        // Center Panel: Skill Buttons + Skip Turn
        VBox centerBottomPane = new VBox(10);
        centerBottomPane.setPadding(new Insets(10));
        centerBottomPane.setAlignment(Pos.CENTER);
        
        HBox skillPane = new HBox(20);
        skillPane.setAlignment(Pos.CENTER);
        
        Button skillBtn1 = new Button();
        Button skillBtn2 = new Button();
        Button skillBtn3 = new Button();
        
        if (skill1 != null && skill1.length() > 0) {
            JSONObject s1 = skill1.getJSONObject(0);
            skillBtn1.setText("Skill1 : " + s1.optString("Name", "Skill1"));
            skillBtn1.setOnAction(e -> useSkill(s1));
        } else {
            skillBtn1.setText("Skill1");
            skillBtn1.setOnAction(e -> {
                showMessage("Skill1 pressed");
                processEndTurn();
            });
        }
        
        if (skill2 != null && skill2.length() > 0) {
            JSONObject s2 = skill2.getJSONObject(0);
            skillBtn2.setText("Skill2 : " + s2.optString("Name", "Skill2"));
            skillBtn2.setOnAction(e -> useSkill(s2));
        } else {
            skillBtn2.setText("Skill2");
            skillBtn2.setOnAction(e -> {
                showMessage("Skill2 pressed");
                processEndTurn();
            });
        }
        
        if (skill3 != null && skill3.length() > 0) {
            JSONObject s3 = skill3.getJSONObject(0);
            skillBtn3.setText("Skill3 : " + s3.optString("Name", "Skill3"));
            skillBtn3.setOnAction(e -> useSkill(s3));
        } else {
            skillBtn3.setText("Skill3");
            skillBtn3.setOnAction(e -> {
                showMessage("Skill3 pressed");
                processEndTurn();
            });
        }
        
        skillPane.getChildren().addAll(skillBtn1, skillBtn2, skillBtn3);
        
        // Skip Turn
        Button btnSkipTurn = new Button("Skip Turn");
        btnSkipTurn.setOnAction(e -> processEndTurn());
        
        centerBottomPane.getChildren().addAll(skillPane, btnSkipTurn);
        
        // รวม Panel player (left), enemy (right), และ skillPane (bottom)
        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(playerPane);
        mainPane.setRight(enemyPane);
        mainPane.setBottom(centerBottomPane);
        
        BorderPane.setMargin(playerPane, new Insets(20));
        BorderPane.setMargin(enemyPane, new Insets(20));
        BorderPane.setMargin(centerBottomPane, new Insets(20));
        
        root.setCenter(mainPane);

        // Label สำหรับข้อความแจ้งเตือน
        notificationLabel = new Label();
        notificationLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 16px; -fx-background-color: rgba(0,0,0,0.6); -fx-padding: 5px;");
        notificationLabel.setVisible(false);
        
        // ใส่ notificationLabel ไว้ด้านล่าง เวลาที่ skill SP ทำงาน จะบวก ดาเมจและแสดงให้ และก็จะ เตือนถ้า mana ไม่พอ
        BorderPane.setAlignment(notificationLabel, Pos.CENTER);
        root.setBottom(notificationLabel);

        Scene scene = new Scene(root, 1100, 790);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Turn Based Battle");
        primaryStage.show();
    }
}