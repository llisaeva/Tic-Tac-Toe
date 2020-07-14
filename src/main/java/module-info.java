module com.lisaeva.game {
	
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.base;
	
	opens com.lisaeva.game.controller;
	opens com.lisaeva.game to javafx.fxml, javafx.graphics;
	
	exports com.lisaeva.game;
	
}