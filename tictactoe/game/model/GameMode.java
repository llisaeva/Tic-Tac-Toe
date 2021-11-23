package game.model;

public enum GameMode {

	PLAYER ("PLAYER", "PLAYER"),
	X_MASTER ("MASTER", "PLAYER"),
	O_MASTER ("PLAYER","MASTER"),
	BOT ("MASTER", "MASTER");
	
	private String xName;
	private String oName;
	
	private GameMode(String xName, String oName) {
		this.xName = xName;
		this.oName = oName;
	}
	
	public String getXName() {
		return xName;
	}
	
	public String getOName() {
		return oName;
	}
	
}
