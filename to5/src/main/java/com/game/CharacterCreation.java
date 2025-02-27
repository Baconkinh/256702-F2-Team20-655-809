package com.game;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CharacterCreation {
    private Stage primaryStage;
    private List<Character> characterList;
    private String selectedCharacterImage = null;
    private String selectedCharacterName = null;
    private String selectedCharacterClass = null;

    public CharacterCreation(Stage stage) {
        this.primaryStage = stage;
        loadCharacterData();
    }

    private void loadCharacterData() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/data/character.json");
            if (inputStream == null) {
                throw new RuntimeException("The character.json file was not found!");
            }
            String jsonText = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            JSONObject jsonObject = new JSONObject(jsonText);
            JSONArray characterArray = jsonObject.getJSONArray("Character");

            characterList = new ArrayList<>();
            for (int i = 0; i < characterArray.length(); i++) {
                JSONObject charObj = characterArray.getJSONObject(i);
                Character character = new Character();
                character.Name = charObj.getString("Name");
                character.Class = charObj.getString("Class");
                character.Hp = charObj.getInt("Hp");
                character.img_part = charObj.getString("img_part");
                characterList.add(character);
            }

            System.out.println("----------------");
            System.out.println("----------------");
            System.out.println("Loaded Characters: " + characterList);
            System.out.println("----------------");
            System.out.println("----------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCharacterCreationScene() {
        Label title = new Label("Choose your character");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: white;");

        TextField nameField = new TextField();
        nameField.setPromptText("Name your character...");
        nameField.setStyle("-fx-font-size: 18px; -fx-pref-width: 300px;");

        HBox characterBox = new HBox(20);
        characterBox.setAlignment(Pos.CENTER);

        ToggleGroup characterGroup = new ToggleGroup();
        Label selectedJobLabel = new Label("Class: -");
        selectedJobLabel.setStyle("-fx-font-size: 22px; -fx-text-fill: white;");

        for (Character character : characterList) {
            Image img = new Image(getClass().getResourceAsStream(character.img_part));
            ImageView charImageView = new ImageView(img);
            charImageView.setFitWidth(100);
            charImageView.setFitHeight(100);

            RadioButton charButton = new RadioButton();
            charButton.setGraphic(charImageView);
            charButton.setToggleGroup(characterGroup);
            charButton.setOnAction(e -> {
                selectedCharacterImage = character.img_part;
                selectedCharacterName = character.Name;
                selectedCharacterClass = character.Class;
                selectedJobLabel.setText("Class: " + character.Class);
            });

            characterBox.getChildren().add(charButton);
        }

        Button btnConfirm = new Button("Confirm");
        btnConfirm.setStyle("-fx-font-size: 18px; -fx-background-color: green; -fx-text-fill: white;");
        btnConfirm.setOnAction(e -> {
            String playerName = nameField.getText();
            if (playerName.isEmpty() || selectedCharacterImage == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please choose a character and name it!", ButtonType.OK);
                alert.showAndWait();
            } else {
                    Character selectedCharacter = characterList.stream()
                    .filter(c -> c.img_part.equals(selectedCharacterImage))
                    .findFirst()
                    .orElse(null);
                if (selectedCharacter != null) {
                    System.out.println("  ตัวละครที่เลือก:");
                    System.out.println("   ชื่อที่ตั้ง: " + playerName);
                    System.out.println("   ชื่อตัวละครเกม: " + selectedCharacter.Name);
                    System.out.println("   อาชีพ: " + selectedCharacter.Class);
                    System.out.println("   HP: " + selectedCharacter.Hp);
                    System.out.println("   รูปภาพ: " + selectedCharacter.img_part);
                }

                new GameScene(primaryStage, playerName, selectedCharacterClass, selectedCharacterImage).showGameScene();
            }
        });

        Button btnBack = new Button("BACK");
        btnBack.setOnAction(e -> new MainMenu(primaryStage).showMainMenu());

        VBox layout = new VBox(20, title, nameField, characterBox, selectedJobLabel, btnConfirm);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #222;");
        layout.setPadding(new Insets(20));

        BorderPane root = new BorderPane();
        root.setCenter(layout);
        root.setTop(btnBack);
        BorderPane.setAlignment(btnBack, Pos.TOP_LEFT);
        BorderPane.setMargin(btnBack, new Insets(10));

        Scene scene = new Scene(root, 1100, 790);
        primaryStage.setScene(scene);
    }

    public static class Character {
        public String Name;
        public String Class;
        public int Hp;
        public String img_part;

        @Override
        public String toString() {
            return "Character{" +
                   "Name='" + Name + '\'' +
                   ", Class='" + Class + '\'' +
                   ", Hp=" + Hp +
                   ", img_part='" + img_part + '\'' +
                   '}';
        }
    }
}