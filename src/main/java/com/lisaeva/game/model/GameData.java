package com.lisaeva.game.model;

import javafx.scene.control.Label;

public class GameData {
	
	 // Each square on the grid has an associated code.
	 // 3 matching digits identify winning positions.
	 //
	 // 135   57    245 
	 // 38   1278    48 
 	 // 236   67    146 

	public static final String[] POSITION_CODES = {"135", "57", "245", "38", "1278", "48", "236", "67", "146"};
	private static final String DEFAULT_PLAYER_NAME = "PLAYER";
	private static final int DEFAULT_SCORE = 0;
	
	private static GameMode gameMode = GameMode.PLAYER;

	private static String xPlayerName = DEFAULT_PLAYER_NAME;
	private static String oPlayerName = DEFAULT_PLAYER_NAME;
	private static int xPlayerScore = DEFAULT_SCORE;
	private static int oPlayerScore = DEFAULT_SCORE;
	
	private static Label xNameLabel;
	private static Label xScoreLabel;
	private static Label oNameLabel;
	private static Label oScoreLabel;
	
	public GameData(Label xName, Label xScore, Label oName, Label oScore) {
		xNameLabel = xName;
		xScoreLabel = xScore;
		oNameLabel = oName;
		oScoreLabel = oScore;
		
		xNameLabel.setText(xPlayerName);
		oNameLabel.setText(oPlayerName);
	}
	
	public static void addXPlayerScore() {
		xPlayerScore += 1;
	}
	
	public static void addOPlayerScore() {
		oPlayerScore += 1;
	}
	
	public static void addPlayerScore(Mark mark) {
		switch(mark) {
			case X:
				xPlayerScore += 1;
				break;
			case O:
				oPlayerScore += 1;
				break;
		}
	}
	
	public static void clearScores() {
		xPlayerScore = DEFAULT_SCORE;
		oPlayerScore = DEFAULT_SCORE;
	}
	
	private static void setPlayerLabels(String x, String o) {
		xNameLabel.setText(x);
		oNameLabel.setText(o);
	}
	
	public static void setPlayerNames(String x, String o) {
		if (!x.isBlank() && !x.equals(gameMode.getXName()))
			xPlayerName = x;
		if (!o.isBlank() && !o.equals(gameMode.getOName()))
			oPlayerName = o;
		switch (gameMode) {
			case PLAYER:
				setPlayerLabels(xPlayerName, oPlayerName);
				break;
			case X_MASTER:
				setPlayerLabels(GameMode.X_MASTER.getXName(), oPlayerName);
				break;
			case O_MASTER:
				setPlayerLabels(xPlayerName, GameMode.O_MASTER.getOName());
				break;
			case BOT:
				setPlayerLabels(GameMode.BOT.getXName(), GameMode.BOT.getOName());
				break;
				
		}
	}
	
	public static String getOPlayerName() {
		switch (gameMode) {
		case PLAYER:
		case X_MASTER:
			return oPlayerName;
		case O_MASTER:
			return GameMode.O_MASTER.getOName();
		case BOT:
			return GameMode.BOT.getOName();
		}
		return null;
	}
	
	public static String getXPlayerName() {
		switch (gameMode) {
		case PLAYER:
		case O_MASTER:
			return xPlayerName;
		case X_MASTER:
			return GameMode.X_MASTER.getXName();
		case BOT:
			return GameMode.BOT.getXName();
		}
		return null;
	}
	
	public static String getTrueXPlayerName() {
		return xPlayerName;
	}
	
	public static String getXPlayerScore() {
		return String.valueOf(xPlayerScore);
	}
	
	public static String getOPlayerScore() {
		return String.valueOf(oPlayerScore);
	}

	public static GameMode getGameMode() {
		return gameMode;
	}

	public static void setGameMode(GameMode gameMode) {
		GameData.gameMode = gameMode;
		setPlayerNames("","");
	}
}
