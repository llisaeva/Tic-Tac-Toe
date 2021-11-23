package game.view;

import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class IconResolver {

	public static void setIcon(Labeled item, String type) {
		ImageView icon = null;
		String folder = getFolderPath(type);
		
		switch (type) {
			case "orb":
				icon = new ImageView(new Image(IconResolver.class.getResourceAsStream(folder + "Orb.png")));
				icon.setFitWidth(90);
				icon.setFitHeight(90);
				break;
			case "cross":
				icon = new ImageView(new Image(IconResolver.class.getResourceAsStream(folder + "Cross.png")));
				icon.setFitWidth(75);
				icon.setFitHeight(75);
				break;
		}
		
		item.setGraphic(icon);
	}
	
	private static String getFolderPath(String type) {
		String path;
		path = ViewGenerator.getTheme().toString().toLowerCase();
		if (path.equals("default"))
			path = "def";
		path = "/res/img/" + path + "/";
		return path;
	}
}
