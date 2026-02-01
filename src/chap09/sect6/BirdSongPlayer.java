package chap09.sect6;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 * Displays a picture of a bird selected by the user and plays an audio clip of the bird's song.
 *
 * Stage
 *  └── Scene
 *      └── BorderPane (root)
 *          ├── HBox (center)
 *          │   ├── StackPane (with image)
 *          │   └── VBox (with radio buttons)
 *          └── FlowPane (with button)
 *
 * @author Drue Coles
 */
public class BirdSongPlayer extends Application {

    private static final String[] birdNames = {
        "bluebird", "chickadee", "goldfinch", "nuthatch", "oriole", "robin", "sparrow", "wren"
    };
    private static final Image[] images = new Image[birdNames.length];
    private static final AudioClip[] clips = new AudioClip[birdNames.length];

    static { // initialize images and audio clips
        for (int i = 0; i < birdNames.length; i++) {
            images[i] = new Image("images/" + birdNames[i] + ".jpg");
            clips[i] = new AudioClip("file:resources/audio/" + birdNames[i] + ".mp3");
        }
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root);

        // control nodes
        ImageView currentImage = new ImageView(images[0]);
        currentImage.setFitWidth(300);
        currentImage.setPreserveRatio(true);
        RadioButton[] radioButtons = new RadioButton[birdNames.length];
        ToggleGroup toggleGroup = new ToggleGroup();
        Button playButton = new Button("Play Song");

        // wrap in a StackPane to apply a border
        StackPane imagePane = new StackPane(currentImage);
        imagePane.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 2;");

        // two children of the root node
        FlowPane playButtonFlowPane = new FlowPane();
        HBox hBox = new HBox(10);

        // initialize flow pane containing the play button
        playButtonFlowPane.getChildren().add(playButton);
        playButtonFlowPane.setAlignment(Pos.CENTER);
        playButtonFlowPane.setPadding(new Insets(10, 0, 0, 10));
        root.setBottom(playButtonFlowPane);

        // initialize HBox to hold image and VBox with radio buttons
        VBox radioButtonVBox = new VBox(20);
        radioButtonVBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(imagePane, radioButtonVBox);
        root.setCenter(hBox);

        // handles radio button clicks by setting image of selected bird
        class BirdSelectionHandler implements EventHandler<ActionEvent> {
            private final int indexOfBird; // radio button index

            private BirdSelectionHandler(int indexOfBird) {
                this.indexOfBird = indexOfBird;
            }

            // sets image of selected bird and stop all audio clips
            @Override
            public void handle(ActionEvent event) {
                currentImage.setImage(images[indexOfBird]);
                for (AudioClip clip : clips) {
                    clip.stop();
                }
            }
        }

        // create radio buttons, add to VBox, toggle group, and register handler
        for (int i = 0; i < birdNames.length; i++) {
            radioButtons[i] = new RadioButton(capitalizeFirstLetter(birdNames[i]));
            radioButtonVBox.getChildren().add(radioButtons[i]);
            radioButtons[i].setOnAction(new BirdSelectionHandler(i));
            toggleGroup.getToggles().add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        // plays audio clip for selected bird
        class PlayAudioHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < birdNames.length; i++) {
                    if (radioButtons[i].isSelected()) {
                        clips[i].play();
                    }
                }
            }
        }
        playButton.setOnAction(new PlayAudioHandler());

        stage.setTitle("Bird Song");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Returns a given string with the first letter capitalized
     */
    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
