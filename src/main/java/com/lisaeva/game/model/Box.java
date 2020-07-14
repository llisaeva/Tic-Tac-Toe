package com.lisaeva.game.model;

import javafx.scene.control.Button;

public class Box {
	private Mark mark;
	private Button b;
	
	public Box(Button b) {
		this.b = b;
	}
	
	public Box(Button b, Mark mark) {
		this.b = b;
		this.mark = mark;
	}

	public Mark getMark() {
		return mark;
	}

	public Button getB() {
		return b;
	}

	public void setMark(Mark mark) {
		this.mark = mark;
		
	}

}
