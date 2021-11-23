module tictactoe {
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.base;
	
	opens game.controller;
	opens game to javafx.fxml, javafx.graphics;
	
	exports game;
}