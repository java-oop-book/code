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

        // HBox holds ImageView and VBox with radio buttons, added to center of BorderPane
        ImageView currentImage = new ImageView(images[0]);
        currentImage.setFitWidth(300);
        currentImage.setPreserveRatio(true);
        StackPane imagePane = new StackPane(currentImage); 
        imagePane.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 2;");
        VBox radioButtonVBox = new VBox(20);
        radioButtonVBox.setAlignment(Pos.CENTER_LEFT);
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(imagePane, radioButtonVBox);
        root.setCenter(hBox);

        // FlowPane holds play button, added to bottom of BorderPane
        Button playButton = new Button("Play Song");
        FlowPane playButtonFlowPane = new FlowPane();
        playButtonFlowPane.getChildren().add(playButton);
        playButtonFlowPane.setAlignment(Pos.CENTER);
        playButtonFlowPane.setPadding(new Insets(10, 0, 0, 0));
        root.setBottom(playButtonFlowPane);

        // handles radio button clicks by setting image of selected bird
        class BirdSelectionHandler implements EventHandler<ActionEvent> {
            private final int indexOfBird;

            private BirdSelectionHandler(int indexOfBird) {
                this.indexOfBird = indexOfBird;
            }

            @Override
            public void handle(ActionEvent event) {
                currentImage.setImage(images[indexOfBird]);
                for (AudioClip clip : clips) {
                    clip.stop();
                }
            }
        }

        // create radio buttons and register handlers
        RadioButton[] radioButtons = new RadioButton[birdNames.length];
        ToggleGroup toggleGroup = new ToggleGroup();
        for (int i = 0; i < birdNames.length; i++) {
            radioButtons[i] = new RadioButton(birdNames[i]);
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

    public static void main(String[] args) {
        launch(args);
    }
}
