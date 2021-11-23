package game.controller;

public abstract class BaseController {

	private String fxml;
	
	public BaseController(String fxml) {
		this.fxml = fxml;
	}
	
	public String getFxml() {
		return fxml;
	}

}
