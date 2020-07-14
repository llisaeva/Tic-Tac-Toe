package com.lisaeva.game.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.lisaeva.game.controller.GameController;
import com.lisaeva.game.view.IconResolver;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Grid {

	private static Mark winner;
	private static CrossLine line;
	private static boolean xTurn = false;
	private static boolean pause = false;
	private static boolean randomMasterMode = false;
	private static Label clickToStart;
	private static Box[] grid = new Box[9];
	private static GameCoder markTracker = new GameCoder();
	private static Master xMaster;
	private static Master oMaster;
	
	
	public Grid(Label prompt, Button... keys) {
		for (int i = 0; i < grid.length; i++)
			grid[i] = new Box(keys[i]);
		xMaster = new Master(Mark.X, grid, markTracker);
		oMaster = new Master(Mark.O, grid, markTracker);
		clickToStart = prompt;
		clickToStartPrompt();
	}
	
	public static void addMark(Mark mark, int index) {
		markTracker.addPositionCode(mark, GameData.POSITION_CODES[index]);
		grid[index].setMark(mark);
		grid[index].getB().setDisable(true);
		markTracker.addIndexCode(index);
		updateMarkIcons();
		scanGridForWinner(mark);
	}
	
	public static void updateMarkIcons() {
		for(int i = 0; i < grid.length; i++) {
			if (grid[i] != null) {
				if (grid[i].getMark() == Mark.X) {
					IconResolver.setIcon(grid[i].getB(), "cross");
				} else if (grid[i].getMark() == Mark.O) {
					IconResolver.setIcon(grid[i].getB(), "orb");
				}
				
			}
		}
	}
	
	public static Mark checkForWinner() {
		return winner;
	}
	
	public static boolean xTurn() {
		xTurn = !xTurn;
		return xTurn;
	}

	public static void reset() {
		GameController.reset();
		for(int i = 0; i < grid.length; i++) {
			if (grid[i] != null) {
				grid[i].getB().setGraphic(null);
				grid[i].getB().setDisable(false);
				grid[i].setMark(null);
			}
		}
		winner = null;
		line = null;
		xTurn = false;
		pause = false;
		markTracker = new GameCoder();
		xMaster = new Master(Mark.X, grid, markTracker);
		oMaster = new Master(Mark.O, grid, markTracker);
		
		if (randomMasterMode) {
			if ((int)(Math.random()*2) > 0) 
				GameData.setGameMode(GameMode.X_MASTER);
			else 
				GameData.setGameMode(GameMode.O_MASTER);
		}
		clickToStartPrompt();
	}

	public static CrossLine getLine() {
		return line;
	}
	
	public static void pause(boolean b) {
		pause = b;
	}
	
	public static boolean isPaused() {
		return pause;
	}
	
	public static void setRandomMasterMode(boolean b) {
		randomMasterMode = b;
		reset();
	}
	
	public static boolean isRandomMasterMode() {
		return randomMasterMode;
	}

	public static void callMaster() {
		switch (GameData.getGameMode()) {
			case X_MASTER:
				xMaster.play();
				break;
			case O_MASTER:
				oMaster.play();
				break;
			case BOT:
				PauseTransition pt = new PauseTransition(Duration.seconds(0.5));
				pt.setOnFinished(e -> {
					if (xTurn()) {
						Grid.callMaster(Mark.X);
					} else {
						Grid.callMaster(Mark.O);
					} checkForWinner();
				});
				
				pt.play();
			default : return;
		}
	}
	
	private static void callMaster(Mark mark) {
		switch (mark) {
		case X:
			xMaster.play();
			break;
		case O:
			oMaster.play();
			break;
		}
		if(!pause)
			callMaster();
	}

	private static void scanGridForWinner(Mark mark) {
		pause = true;
	
		Pattern p;
		Matcher m;
		String codes = markTracker.getPositionCode(mark);
		
		for (int i = 1; i < 9; i++) {
			p = Pattern.compile(".*[" + i + "].*[" + i + "].*[" + i + "].*");
			m = p.matcher(codes);
			if (m.matches()) {
				winner = mark;
				switch (i) {
					case 1:
						line = CrossLine.DIAGONAL_LEFT;
						break;
					case 2: 
						line = CrossLine.DIAGONAL_RIGHT;
						break;
					case 3: 
						line = CrossLine.COL_LEFT;
						break;
					case 4:
						line = CrossLine.COL_RIGHT;
						break;
					case 5:
						line = CrossLine.ROW_TOP;
						break;
					case 6:
						line = CrossLine.ROW_LOW;
						break;
					case 7:
						line = CrossLine.COL_MID;
						break;
					case 8:
						line = CrossLine.ROW_MID;
				}
				
				GameData.addPlayerScore(mark);
				return;
			}
		}
		pause = false;		
	}

	private static void clickToStartPrompt() {
		switch (GameData.getGameMode()) {
			case PLAYER:
			case O_MASTER:
				clickToStart.setVisible(false);
				break;
			case X_MASTER:
			case BOT:
				clickToStart.setVisible(true);
				break;
		}
	}
}
