package com.lisaeva.game;

import com.lisaeva.game.view.ViewGenerator;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
	public static void main(String... args) {launch(args);}

	@Override
	public void start(Stage primaryStage) {
		try {
			ViewGenerator.initializeBoard();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}