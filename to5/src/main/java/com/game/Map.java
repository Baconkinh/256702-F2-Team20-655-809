package com.game;
import static com.almasb.fxgl.dsl.FXGL.*;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Map {

    private ImageView character;
    private Text playerNameText;
    private double speed = 5.0;
    private final double MAP_WIDTH = 1100;
    private final double MAP_HEIGHT = 790;
    private final double CHARACTER_SIZE = 60;
    private int points = 0;

    private Circle npc1, npc2, npc3;

    public void Map(String playerName, String characterImagePath) {
        getGameScene().clearUINodes();
        
        // Background
        Image bgImage = getAssetLoader().loadImage("5.jpg");
        ImageView background = new ImageView(bgImage);
        background.setFitWidth(MAP_WIDTH);
        background.setFitHeight(MAP_HEIGHT);
        getGameScene().addUINode(background);

        Button Backbtn = new Button("Back");
        Backbtn.setFont(Font.font("Arial", 30));
        Backbtn.setStyle("-fx-background-color: #FF4500; -fx-text-fill: white; -fx-border-radius: 15px; -fx-background-radius: 15px; -fx-padding: 10 30 10 30;");
        double BackX = 80;
        double BackY = 70;
        Backbtn.setTranslateX(BackX);
        Backbtn.setTranslateY(BackY);
        Backbtn.setOnAction(e -> new App().Main());

        getGameScene().addUINode(Backbtn);
        
        
        // Character Setup
        Image characterImage = new Image(getClass().getResource(characterImagePath).toExternalForm());
        character = new ImageView(characterImage);
        character.setFitWidth(CHARACTER_SIZE);
        character.setFitHeight(CHARACTER_SIZE);
        character.setTranslateX(MAP_WIDTH / 2);
        character.setTranslateY(MAP_HEIGHT / 2);
        character.setScaleX(-1);
        // Player Name Above Character
        playerNameText = new Text(playerName);
        playerNameText.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 16));
        playerNameText.setFill(Color.WHITE);
        playerNameText.setStroke(Color.BLACK);
        playerNameText.setStrokeWidth(1);
        playerNameText.setTextAlignment(TextAlignment.CENTER);
        
        updatePlayerNamePosition();

        getGameScene().addUINode(character);
        getGameScene().addUINode(playerNameText);

        // NPC Setup
        npc1 = createNPC(1000, 110);
        npc2 = createNPC(1000, 400);
        npc3 = createNPC(1000, 680);

        // Add NPCs to Scene
        getGameScene().addUINode(npc1);
        getGameScene().addUINode(npc2);
        getGameScene().addUINode(npc3);

        // Movement Controls
        onKey(KeyCode.W, () -> moveCharacter(0, -speed));
        onKey(KeyCode.S, () -> moveCharacter(0, speed));
        onKey(KeyCode.A, () -> moveCharacter(-speed, 0));
        onKey(KeyCode.D, () -> moveCharacter(speed, 0));
    }

    private Circle createNPC(double x, double y) {
        Circle npc = new Circle(10, Color.RED);
        npc.setTranslateX(x);
        npc.setTranslateY(y);
        return npc;
    }

    private void moveCharacter(double dx, double dy) {
        double newX = character.getTranslateX() + dx;
        double newY = character.getTranslateY() + dy;
    
        // Ensure character doesn't move outside the map bounds
        if (newX >= 0 && newX <= MAP_WIDTH - CHARACTER_SIZE) {
            character.setTranslateX(newX);
        }
        if (newY >= 0 && newY <= MAP_HEIGHT - CHARACTER_SIZE) {
            character.setTranslateY(newY);
        }
    
        // Update the character's direction based on movement
        if (dx > 0) {  // Moving right (D key)
            character.setScaleX(-1);  // Face right
        } else if (dx < 0) {  // Moving left (A key)
            character.setScaleX(1);  // Face left
        }
    
        // Update the position of the player name and check for collisions
        updatePlayerNamePosition();
        checkCollision();
    }
    

    private void updatePlayerNamePosition() {
        playerNameText.setTranslateX(character.getTranslateX() + CHARACTER_SIZE / 4 - 5);  // Shift left by 50 pixels
        playerNameText.setTranslateY(character.getTranslateY() - 12);
    }

    private void checkCollision() {
        if (character.getBoundsInParent().intersects(npc1.getBoundsInParent())) {
            new Mon1(); // Transition to Mon1
        } else if (character.getBoundsInParent().intersects(npc2.getBoundsInParent())) {
            if (points >= 1000) {
                new Mon2(); // Transition to Mon2
            } else {
                showAlert(1000 - points);
            }
        } else if (character.getBoundsInParent().intersects(npc3.getBoundsInParent())) {
            if (points >= 2000) {
                new Mon3(); // Transition to Mon3
            } else {
                showAlert(2000 - points);
            }
        }
    }

    private void showAlert(int missingPoints) {
        VBox alertBox = new VBox();
        alertBox.setPadding(new Insets(10));
        alertBox.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5), Insets.EMPTY)));
        Text alertText = new Text("You need " + missingPoints + " more points to enter!");
        alertBox.getChildren().add(alertText);
        alertBox.setTranslateX(400);
        alertBox.setTranslateY(350);
        getGameScene().addUINode(alertBox);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> getGameScene().removeUINode(alertBox));
        pause.play();
    }

    public void addPoints(int additionalPoints) {
        points += additionalPoints;
    }
    
}
