package game.view;

public enum Theme {
	DEFAULT("Default"),
	DARK ("Dark"),
	BEIGE ("Beige"),
	ASH ("Ash");
	
	
	private final String fileName;
	private static final String folder = "/res/css/";
	
	private Theme(String fileName) {
		this.fileName = fileName;
	}

	public static String getCSSPath(Theme theme) {
		switch (theme) {
			case DEFAULT: return folder + "themeDefault.css";
			case DARK: return folder + "themeDark.css";
			case BEIGE: return folder + "themeBeige.css";
			case ASH: return folder + "themeAsh.css";
			default : return null;
		}
	}
	
	public static String getSwatchCSSPath() {
		return "/res/css/swatch.css";
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


