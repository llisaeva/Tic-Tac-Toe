package com.lisaeva.game.view;

public enum Theme {
	DEFAULT("Default"),
	DARK ("Dark"),
	BEIGE ("Beige"),
	ASH ("Ash");
	
	
	private final String fileName;
	
	private Theme(String fileName) {
		this.fileName = fileName;
	}

	public static String getCSSPath(Theme theme) {
		switch (theme) {
			case DEFAULT: return "/css/themeDefault.css";
			case DARK: return "/css/themeDark.css";
			case BEIGE: return "/css/themeBeige.css";
			case ASH: return "/css/themeAsh.css";
			default : return null;
		}
	}
	
	public static String getSwatchCSSPath() {
		return "/css/swatch.css";
	}

	public static Theme getThemeByID(String id) {
		switch (id) {
			case "swatch-default":
				return Theme.DEFAULT;
			case "swatch-dark":
				return Theme.DARK;
			case "swatch-ash":
				return Theme.ASH;
			case "swatch-beige":
				return Theme.BEIGE;
		}
		return null;
	}
	
	public String toString() {
		return fileName;
	}
}


