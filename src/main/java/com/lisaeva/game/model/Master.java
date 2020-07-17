package com.lisaeva.game.model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Master {
	private Mark mark;
	private Box[] grid;
	
	// The board is symmetric. By using rotation codes, 
	// the board is rotated based on the positions of the first non-center mark.
	// This decreases the amount of strategies needed to avoid loosing the game.
	
	private Map<Integer, int[]> rotationCodes = new HashMap<Integer, int[]>();
	
	// Strategy codes are pairs of keys and values, 
	// where the key is the current state of the board and
	// the value is the index of the best possible turn.
	//
	// Strategy codes are used:
	// - when there is no way to win at the current turn
	// - when the opponent does not need to be blocked

	private Map<String, Integer> strategyCodes = new HashMap<String, Integer>();
	private GameCoder gameCodes;
	private String masterPositionCode;
	private String playerPositionCode;

	public Master(Mark mark, Box[] grid, GameCoder gameCodes) {
		this.mark = mark;
		this.grid = grid;
		this.gameCodes = gameCodes;
		
		rotationCodes.put(0, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });
		rotationCodes.put(1, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });
		rotationCodes.put(2, new int[] { 2, 5, 8, 1, 4, 7, 0, 3, 6 });
		rotationCodes.put(3, new int[] { 6, 3, 0, 7, 4, 1, 8, 5, 2 });
		rotationCodes.put(4, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });
		rotationCodes.put(5, new int[] { 2, 5, 8, 1, 4, 7, 0, 3, 6 });
		rotationCodes.put(6, new int[] { 6, 3, 0, 7, 4, 1, 8, 5, 2 });
		rotationCodes.put(7, new int[] { 8, 7, 6, 5, 4, 3, 2, 1, 0 });
		rotationCodes.put(8, new int[] { 8, 7, 6, 5, 4, 3, 2, 1, 0 });
		rotationCodes.put(40, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });
		rotationCodes.put(41, new int[] { 2, 5, 8, 1, 4, 7, 0, 3, 6 });
		rotationCodes.put(42, new int[] { 2, 5, 8, 1, 4, 7, 0, 3, 6 });
		rotationCodes.put(43, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });
		rotationCodes.put(45, new int[] { 8, 7, 6, 5, 4, 3, 2, 1, 0 });
		rotationCodes.put(46, new int[] { 6, 3, 0, 7, 4, 1, 8, 5, 2 });
		rotationCodes.put(47, new int[] { 6, 3, 0, 7, 4, 1, 8, 5, 2 });
		rotationCodes.put(48, new int[] { 8, 7, 6, 5, 4, 3, 2, 1, 0 });

		strategyCodes.put("43|4|143|14526|146|147", 0);
		strategyCodes.put("048|04635", 1);
		strategyCodes.put("430.|4087|045.|03|05|06|0863|40358|045|14308|145|148", 2);
		strategyCodes.put("408217|04217", 3);
		strategyCodes.put("01|07|0|1", 4);
		strategyCodes.put("04", 5);
		strategyCodes.put("4085|045178|0148|02|0521|08|40178|408|047", 6);
		strategyCodes.put("408635", 7);
		strategyCodes.put("40|045173|0263|0321|0621", 8);
	}

	public void play() {
		if (!Grid.isPaused()) {			
			getPositionCodes();
			if (checkForEmptyGrid())
				return;
			if (hasTwoInARow(masterPositionCode))
				return;
			if (hasTwoInARow(playerPositionCode))
				return;
			if (applyStrategy())
				return;
			randomMove();
		}
	}
	
	private void getPositionCodes() {
		masterPositionCode = "";
		playerPositionCode = "";
		masterPositionCode = gameCodes.getPositionCode(mark);
		
		switch (mark) {
		case X:
			playerPositionCode = gameCodes.getPositionCode(Mark.O);
			break;
		case O:
			playerPositionCode = gameCodes.getPositionCode(Mark.X);
			break;
		}
	}
	
	
	private boolean checkForEmptyGrid() {
		for (int i = 0; i < grid.length; i++) {
			if (grid[i].getMark() != null)
				break;
			else if (i == grid.length - 1) {
				if ((int) (Math.random() * 4) > 0)
					addMark(4);
				else {
					int temp = (int) (Math.random() * 4);
					if (temp == 0)
						addMark(0);
					else if (temp == 1)
						addMark(2);
					else if (temp == 2)
						addMark(6);
					else
						addMark(8);

				}
				return true;
			}
		} return false;
	}
	
	private boolean hasTwoInARow(String positionCode) {
		Pattern p;
		Matcher m;

		for (int i = 1; i < 9; i++) {
			p = Pattern.compile(".*[" + i + "].*[" + i + "].*");
			m = p.matcher(positionCode);
			if (m.matches()) {
				if (searchPosition(mark, i))
					return true;
			}
		} return false;
	}
	
	private boolean applyStrategy() {
		Pattern p;
		Matcher m;

		int rotationKey;
		int[] rotationCode;
		String stateCode = "";
		String indexCode = gameCodes.getIndexCode();
		
		if (indexCode.length() % 2 == 0 && indexCode.substring(0, 1).equals("4"))
			rotationKey = Integer.parseInt(indexCode.substring(0, 2));
		else
			rotationKey = Integer.parseInt(indexCode.substring(0, 1));
		rotationCode = rotationCodes.get(rotationKey);
		for (int i = 0; i < indexCode.length(); i++) {
			for (int j = 0; j < rotationCode.length; j++) {
				if (Integer.parseInt(indexCode.substring(i, i + 1)) == rotationCode[j]) {
					stateCode += j;
				}
			}
		}
		
		for (String strategy : strategyCodes.keySet()) {
			p = Pattern.compile(strategy);
			m = p.matcher(stateCode);
			if (m.matches()) {
				addMark(rotationCode[strategyCodes.get(strategy)]);
				return true;
			}
		} return false;
	}
	
	private void randomMove() {
		for (int i = 0; i < grid.length; i++) {
			if (grid[i].getMark() == null) {
				addMark(i);
				return;
			} 
			if (i == grid.length -1) {
				Grid.pause(true);
				return;
			}
		}
	}
		
	private void addMark(int index) {
		Grid.addMark(mark,index);
	}

	private boolean searchPosition(Mark mark, int num) {
		for (int i = 0; i < GameData.POSITION_CODES.length; i++) {
			if (GameData.POSITION_CODES[i].contains(String.valueOf(num)) && grid[i].getMark() == null) {
				addMark(i);
				return true;
			}
		}
		return false;
	}
	

}
