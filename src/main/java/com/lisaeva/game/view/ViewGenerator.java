package com.lisaeva.game.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.lisaeva.game.controller.BaseController;
import com.lisaeva.game.controller.GameController;
import com.lisaeva.game.controller.OptionsController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class ViewGenerator {
	
	private static Theme theme = Theme.DEFAULT;
	private static String fontFile1 = "/font/BAHNSCHRIFT.TTF";
	private static String fontFile2 = "/font/BS_Static_Bold.ttf";
	private static Stage board = new Stage();
	private static ArrayList<Scene> scenes = new ArrayList<>();

	
	public static void initializeBoard() {
		BaseController game = new GameController("/fxml/tictactoe.fxml");
		BaseController options = new OptionsController("/fxml/options.fxml");
		loadFont();
		scenes.add(0, initializeScene(game));
		scenes.add(1, initializeScene(options));
		board.setScene(scenes.get(0));
		board.show();
		updateStyles();
	}
	
	public static void showOptions() {
		board.setScene(scenes.get(1));
		updateStyles();
	}
	
	public static void showBoard() {
		board.setScene(scenes.get(0));
		updateStyles();
	}
	
	private static Scene initializeScene(BaseController controller) {
		FXMLLoader fxmlLoader = new FXMLLoader(ViewGenerator.class.getResource(controller.getFxml()));
		fxmlLoader.setController(controller);
		Parent parent;
		try {
			parent = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		Scene scene = new Scene(parent);
		return scene;
		
	}

	public static void updateStyles() {
			Scene scene = board.getScene();
			scene.getStylesheets().clear();
			scene.getStylesheets().add(ViewGenerator.class.getResource(Theme.getCSSPath(theme)).toExternalForm());
			scene.getStylesheets().add(ViewGenerator.class.getResource(Theme.getSwatchCSSPath()).toExternalForm());
		
	}
	
	private static void loadFont() {
		try (InputStream in = ViewGenerator.class.getResourceAsStream(fontFile1); 
			 InputStream in2 = ViewGenerator.class.getResourceAsStream(fontFile2)) {
			if (in != null) {
				Font.loadFont(in, 1);
				Font.loadFont(in2, 1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setTheme(Theme newTheme) {
		theme = newTheme;
	}
	
	public static Theme getTheme() {
		return theme;
	}


}
