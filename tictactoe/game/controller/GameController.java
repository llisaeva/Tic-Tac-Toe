package game.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import game.model.GameData;
import game.model.Grid;
import game.model.Mark;
import game.view.IconResolver;
import game.view.Theme;
import game.view.ViewGenerator;

public class GameController extends BaseController implements Initializable {

	@FXML private Button b0;
	@FXML private Button b1;
	@FXML private Button b2;
	@FXML private Button b3;
	@FXML private Button b4;
	@FXML private Button b5;
	@FXML private Button b6;
	@FXML private Button b7;
	@FXML private Button b8;
	
	@FXML private Label clickToStart;
	
	@FXML private Label xPlayerScore;
	@FXML private Label oPlayerScore;
	@FXML private Label xPlayerLabel;
	@FXML private Label oPlayerLabel;
	
	@FXML private Line leftDiagonal;
	@FXML private Line rightDiagonal;
	@FXML private Line midColumn;
	@FXML private Line rightColumn;
	@FXML private Line leftColumn;
	@FXML private Line topRow;
	@FXML private Line midRow;
	@FXML private Line lowRow;
	
	private static final double sec = 0.1;
	private static Line line = new Line();
	private static boolean firstTime = true;
	private static Button savedSwatch;

	public GameController(String fxml) {
		super(fxml);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		new GameData(xPlayerLabel, xPlayerScore, oPlayerLabel, oPlayerScore);
		new Grid(clickToStart,b0,b1,b2,b3,b4,b5,b6,b7,b8);
		Grid.updateMarkIcons();
		xPlayerScore.setText(GameData.getXPlayerScore());
		oPlayerScore.setText(GameData.getOPlayerScore());
		clickToStart.setMouseTransparent(true);
	}

	public static void reset() {
		if (line != null) {
			line.setVisible(false);
			line = null;
		}
		firstTime = true;
	}
	
	@FXML
	void changeTheme(ActionEvent e) {
		Button b = (Button) e.getSource();
		ViewGenerator.setTheme(Theme.getThemeByID(b.getId()));
		Grid.updateMarkIcons();
		ViewGenerator.updateStyles();
		if (savedSwatch != null)
			savedSwatch.setVisible(true);
		b.setVisible(false);
		savedSwatch = b;
	}
	
	@FXML
	void playerChoosesSquare(ActionEvent event) {
		clickToStart.setVisible(false);
		if (!Grid.isPaused())
			makeMove((Button)event.getSource());
	}
	
	@FXML
	void openOptions() {
		ViewGenerator.showOptions();
	}
	
	@FXML
	void replay() {
		Grid.reset();
	}
	
	private void makeMove(Button b) {	 
		Grid.pause(true);
		switch (GameData.getGameMode()) {
		
			case PLAYER:
				if (Grid.xTurn()) {
					IconResolver.setIcon(b, "cross");
					Grid.addMark(Mark.X, Integer.parseInt(b.getId()));
				} else {
					IconResolver.setIcon(b, "orb");
					Grid.addMark(Mark.O, Integer.parseInt(b.getId()));
				}
				checkForWinner();
				break;
				
			case X_MASTER:
				if (firstTime) {
					PauseTransition pt = new PauseTransition(Duration.seconds(sec));
					pt.setOnFinished(e -> {
						Grid.callMaster();
						checkForWinner();
						});
					pt.play();
					firstTime = false;
				} else {
					IconResolver.setIcon(b, "orb");
					
					Grid.addMark(Mark.O, Integer.parseInt(b.getId()));
					Grid.updateMarkIcons();
					checkForWinner();
					
					PauseTransition pt = new PauseTransition(Duration.seconds(sec));
					pt.setOnFinished(e -> {
						Grid.callMaster();
						checkForWinner();
						});
					pt.play();
				}
				break;
				
			case O_MASTER:
					IconResolver.setIcon(b, "cross");
					
					Grid.addMark(Mark.X, Integer.parseInt(b.getId()));
					Grid.updateMarkIcons();
					checkForWinner();
					
					PauseTransition pt = new PauseTransition(Duration.seconds(sec));
					pt.setOnFinished(e -> {
						Grid.callMaster();
						checkForWinner();
						});
					pt.play();
				break;
				
			case BOT:
				Grid.callMaster();
				break;
			}
		Grid.pause(false);
	}
	
	private void checkForWinner() {
		
		if(Grid.getLine() == null) 
			return;
		
		xPlayerScore.setText(GameData.getXPlayerScore());
		oPlayerScore.setText(GameData.getOPlayerScore());
		
		switch (Grid.getLine()) {
			case DIAGONAL_LEFT: 
				line = leftDiagonal;
				break;
			case DIAGONAL_RIGHT:
				line = rightDiagonal;
				break;
			case ROW_TOP:
				line = topRow;
				break;
			case ROW_MID:
				line = midRow;
				break;
			case ROW_LOW:
				line = lowRow;
				break;
			case COL_RIGHT:
				line = rightColumn;
				break;
			case COL_MID:
				line = midColumn;
				break;
			case COL_LEFT:
				line = leftColumn;
				break;
		}
		line.setVisible(true);
	}

}
