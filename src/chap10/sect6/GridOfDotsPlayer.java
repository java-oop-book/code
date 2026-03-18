package chap10.sect6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * GUI front end for two Grid of Dots games. Displays a game board with clickable edges and supports
 * starting new games.
 *
 * @author Drue Coles
 */
public class GridOfDotsPlayer extends Application {

    private static final int DOT_RADIUS = 6;
    private static final int SPACING = 60;
    private static final int MARGIN = 20;
    private static final int GAME_SIZE = 5;

    private Stage stage;
    private GridOfDots game = new Cram(GAME_SIZE, GAME_SIZE);

    private Label status;
    private Pane board;

    private final RadioButton cramRadioButton = new RadioButton("Cram");
    private final RadioButton slitherRadioButton = new RadioButton("Slither");
    private final Button newGameButton = new Button("New Game");

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 5, 10, 5));

        root.setTop(newGamePane());
        root.setBottom(statusPane());
        root.setCenter(centeredBoard());

        BorderPane.setMargin(root.getCenter(), new Insets(10, 0, 10, 0));

        stage.setTitle(game.getGameName().toUpperCase());
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.show();
    }

    /**
     * Returns the width of the game board in pixels.
     */
    private double boardWidth() {
        return 2 * MARGIN + (game.getCols() - 1) * SPACING;
    }

    /**
     * Returns the height of the game board in pixels.
     */
    private double boardHeight() {
        return 2 * MARGIN + (game.getRows() - 1) * SPACING;
    }

    /**
     * Returns a pane containing the game board centered within a wrapper.
     */
    private Pane centeredBoard() {
        board = new Pane();
        board.setPrefSize(boardWidth(), boardHeight());
        board.setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        board.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        redrawBoard();

        StackPane wrapper = new StackPane(board);
        wrapper.setAlignment(Pos.CENTER);
        wrapper.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        return wrapper;
    }

    /**
     * Returns a pane containing the current player / winner status label.
     */
    private Pane statusPane() {
        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        status = new Label("Player to move: " + game.getPlayerToMove());
        pane.getChildren().add(status);
        return pane;
    }

    /**
     * Returns a pane containing game-type radio buttons and a new game button.
     */
    private Pane newGamePane() {
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(cramRadioButton, slitherRadioButton);
        cramRadioButton.setSelected(true);
        newGameButton.setOnAction(e -> newGame());

        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(20);
        pane.getChildren().addAll(cramRadioButton, slitherRadioButton, newGameButton);
        return pane;
    }

    /**
     * Starts a new game of the selected type and redraws the board.
     */
    private void newGame() {
        if (cramRadioButton.isSelected()) {
            game = new Cram(GAME_SIZE, GAME_SIZE);
        } else {
            game = new Slither(GAME_SIZE, GAME_SIZE);
        }
        stage.setTitle(game.getGameName().toUpperCase());
        redrawBoard();
        status.setText("Player to move: " + game.getPlayerToMove());
    }

    /**
     * Clears and redraws all edges and dots on the board.
     */
    private void redrawBoard() {
        board.getChildren().clear();
        drawEdges();
        drawDots();
    }

    /**
     * Draws all edges that are either claimed or currently legal to claim.
     */
    private void drawEdges() {
        for (Edge edge : game.getEdges()) {
            if (edge.isUnclaimed() && !game.isLegalMove(edge)) {
                continue;
            }
            Line line = createLine(edge);
            styleLine(line, edge);
            board.getChildren().add(line);
        }
    }

    /**
     * Draws a dot at each grid position.
     */
    private void drawDots() {
        for (int r = 0; r < game.getRows(); r++) {
            for (int c = 0; c < game.getCols(); c++) {
                board.getChildren().add(new Circle(screenX(c), screenY(r), DOT_RADIUS));
            }
        }
    }

    /**
     * Returns a line connecting the two endpoints of the given edge.
     */
    private Line createLine(Edge edge) {
        int[] p = edge.getEndpoints();
        Line line = new Line(screenX(p[1]), screenY(p[0]), screenX(p[3]), screenY(p[2]));
        line.setStrokeWidth(10);
        return line;
    }

    /**
     * Styles a line based on whether its edge is unclaimed or owned by a player. Unclaimed edges
     * get hover effects and a click handler.
     */
    private void styleLine(Line line, Edge edge) {
        if (edge.isUnclaimed()) {
            line.setStroke(Color.LIGHTGRAY);
            line.setOnMouseEntered(e -> line.setStroke(Color.DARKGRAY));
            line.setOnMouseExited(e -> line.setStroke(Color.LIGHTGRAY));
            line.setOnMouseClicked(e -> handleMove(edge));
        } else if (edge.getOwner() == GridOfDots.Player.PLAYER_ONE) {
            line.setStroke(Color.BLUE);
        } else {
            line.setStroke(Color.RED);
        }
    }

    /**
     * Handles a click on an edge. If the move succeeds, redraws the board and updates the status
     * label.
     */
    private void handleMove(Edge edge) {
        int[] p = edge.getEndpoints();
        if (!game.makeMove(p[0], p[1], p[2], p[3])) return;
        redrawBoard();
        status.setText(game.gameOver()
                ? "Winner: " + game.winner()
                : "Player to move: " + game.getPlayerToMove());
    }

    /**
     * Returns the x screen coordinate of the given column.
     */
    private double screenX(int col) { return MARGIN + col * SPACING; }

    /**
     * Returns the y screen coordinate of the given row.
     */
    private double screenY(int row) { return MARGIN + row * SPACING; }

    public static void main(String[] args) { launch(args); }
}
