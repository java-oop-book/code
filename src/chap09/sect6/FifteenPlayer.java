package chap09.sect6;

import chap08.sect4.FifteenGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * A GUI for the Game of Fifteen.
 *
 * @author Drue Coles
 */
public class FifteenPlayer extends Application {

    private FifteenGame game;
    private final Button[][] buttons = new Button[FifteenGame.ROWS][FifteenGame.COLS];

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root);

        game = new FifteenGame(chooseDifficulty());

        // arrange button clicks in grid pane
        GridPane gridPane = new GridPane();
        root.getChildren().add(gridPane);
        gridPane.setStyle("-fx-background-color: BLACK");
        final int hvGap = 3;
        gridPane.setHgap(hvGap);
        gridPane.setVgap(hvGap);

        // handles button by sliding selected tile
        class ButtonHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {
                Button button = (Button) event.getSource();
                int tile = Integer.parseInt(button.getText());
                game.slide(tile);
                setButtonText();

                if (game.over()) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("GAME OVER");
                    alert.setHeaderText("You have solved the puzzle!");
                    alert.setContentText("Goodbye.");
                    alert.showAndWait();
                    stage.close();
                }
            }
        }
        ButtonHandler buttonHandler = new ButtonHandler();

        // create buttons, style them, and register event handlers
        final int fontSize = 28;
        final int buttonSize = 75;
        Font font = Font.font("Monotype", FontWeight.BOLD, fontSize);
        String buttonStyle = "-fx-border-color: SLATEGRAY; -fx-border-width: 2";

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setFont(font);
                buttons[i][j].setPrefSize(buttonSize, buttonSize);
                buttons[i][j].setStyle(buttonStyle);
                buttons[i][j].setOnAction(buttonHandler);
                gridPane.add(buttons[i][j], j, i);
            }
        }
        setButtonText();

        stage.setTitle("Fifteen Puzzle");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the text on each button to corresponding tile number.
     */
    private void setButtonText() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int tile = game.tileAt(i, j);
                if (tile == FifteenGame.EMPTY_SPACE) {
                    buttons[i][j].setVisible(false);
                } else {
                    buttons[i][j].setVisible(true);
                    buttons[i][j].setText(String.valueOf(tile));
                }
            }
        }
    }

    /**
     * Prompts the user to select a difficulty level and returns the corresponding value.
     */
    private static int chooseDifficulty() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Easy", "Easy", "Medium", "Hard");

        dialog.setTitle("Difficulty");
        dialog.setHeaderText("Select difficulty level");
        dialog.setContentText("Difficulty:");

        return dialog.showAndWait()
                .map(choice -> switch (choice) {
                    case "Medium" -> 40;
                    case "Hard" -> 60;
                    default -> 20;
                })
                .orElse(20); // default if dialog closed
    }

    public static void main(String[] args) {
        launch(args);
    }
}
