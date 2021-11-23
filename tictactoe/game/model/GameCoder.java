package game.model;

public class GameCoder {
	
	// This keeps track of where each player's mark is, 
	// by creating a string of index positions for each player.
	//
	// The board's index positions:
	//
	// 0 1 2
	// 3 4 5
	// 6 7 8
	// 
	private String xPositionCode = "";
	private String oPositionCode = "";
	private String indexCode = "";
	
	public void addPositionCode(Mark mark, String code) {
		switch (mark) {
			case X:
				xPositionCode += code;
				break;
			case O:
				oPositionCode += code;
				break;
		}
	}
	
	public synchronized void addIndexCode(int code) {
		indexCode += code;
	}
	
	public synchronized String getPositionCode(Mark mark) {
		switch (mark) {
			case X:
				return new String(xPositionCode);
			case O:
				return new String(oPositionCode);
		}
		return null;
	}
	
	public synchronized String getIndexCode() {
		return new String(indexCode);
	}
}
