package com.lisaeva.game.model;

public enum Mark {
	X ("cross"),
	O ("nought");
	
	private String name;
	private Mark(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
