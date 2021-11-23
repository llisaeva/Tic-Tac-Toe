package game.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import game.model.GameData;
import game.model.GameMode;
import game.model.Grid;
import game.view.ViewGenerator;

public class OptionsController extends BaseController implements Initializable {

    @FXML private ImageView pvpChosenIcon;
    @FXML private ImageView pvmOIcon;
    @FXML private ImageView pvmXIcon;
    @FXML private Label pvmRandIcon;
    @FXML private Label randIcon1;
    @FXML private Label randIcon2;
    @FXML private ImageView mvmChosenIcon;
    @FXML private Label selectMastersMark;
    @FXML private Button xMasterKey;
    @FXML private Button oMasterKey;
    @FXML private Button randMasterKey;
    @FXML private ImageView xNameIcon;
    @FXML private ImageView oNameIcon;
    @FXML private Label vsName;
	@FXML private TextField xPlayerName;
    @FXML private TextField oPlayerName;

	public OptionsController(String fxml) {
		super(fxml);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		xPlayerName.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));
		
		oPlayerName.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));
		
		updateNames();
		updateStyles();
	}

	@FXML
    void playersMode() {
    	saveEnteredNames();
    	Grid.setRandomMasterMode(false);
    	GameData.setGameMode(GameMode.PLAYER);
    	Grid.reset();
    	makeModeIconVisible();
    	updateNames();
    }
    
    @FXML
    void masterMode() {
    	openMastersMarkSelection();	
    }
    
    @FXML
    void xMaster() {
    	saveEnteredNames();
    	Grid.setRandomMasterMode(false);
    	GameData.setGameMode(GameMode.X_MASTER);
    	Grid.reset();
    	makeModeIconVisible();
    	updateNames();
    }

    @FXML
    void oMaster() {
    	saveEnteredNames();
    	Grid.setRandomMasterMode(false);
    	GameData.setGameMode(GameMode.O_MASTER);
    	Grid.reset();
    	makeModeIconVisible();
    	updateNames();
    }
    
    @FXML
    void xoMaster() {
    	saveEnteredNames();
    	Grid.setRandomMasterMode(true);
    	Grid.reset();
    	makeModeIconVisible();
    	updateNames();
    }
    
    @FXML
    void botMode() {
    	saveEnteredNames();
    	Grid.setRandomMasterMode(false);
    	GameData.setGameMode(GameMode.BOT);
    	Grid.reset();
    	makeModeIconVisible();
    	updateNames();
    }

    @FXML
    void acceptOptions() {
    	saveEnteredNames();
    	ViewGenerator.showBoard();
    }
    
    public void updateStyles() {
  		makeModeIconVisible();
  	}
    
    private void makeModeIconVisible() {
		resetIcons();
		if(Grid.isRandomMasterMode()) {
			xNameIcon.setVisible(false);
        	oNameIcon.setVisible(false);
    		pvmRandIcon.setVisible(true);
        	randIcon1.setVisible(true);
        	randIcon2.setVisible(true);
        	return;
		}
    	switch (GameData.getGameMode()) {
    		case PLAYER:
    			pvpChosenIcon.setVisible(true);
    			break;
    		case X_MASTER: 
    			pvmXIcon.setVisible(true);
    			break;
    		case O_MASTER: 
    			pvmOIcon.setVisible(true);
    			break;
    		case BOT:
    			mvmChosenIcon.setVisible(true);
    			break; 	
    	    }
    }
    
    private void saveEnteredNames() {
    	if (Grid.isRandomMasterMode()) {
    		GameData.setPlayerNames(xPlayerName.getText(), "");
    		return;
    	}
    	GameData.setPlayerNames(xPlayerName.getText(), oPlayerName.getText());
    }
    
    private void updateNames() {
    	if (Grid.isRandomMasterMode()) {
    		xPlayerName.setText(GameData.getTrueXPlayerName());
    		oPlayerName.setText(GameMode.BOT.getOName());
    		return;
    	}
    	xPlayerName.setText(GameData.getXPlayerName());
    	oPlayerName.setText(GameData.getOPlayerName());
    	
    }

    private void resetIcons() {
    	pvpChosenIcon.setVisible(false);
		pvmXIcon.setVisible(false);
		pvmOIcon.setVisible(false);
		pvmRandIcon.setVisible(false);
		randIcon1.setVisible(false);
		randIcon2.setVisible(false);
		mvmChosenIcon.setVisible(false);
		selectMastersMark.setVisible(false);
		xMasterKey.setDisable(true);
    	xMasterKey.setVisible(false);
    	oMasterKey.setDisable(true);
    	oMasterKey.setVisible(false);
    	randMasterKey.setDisable(true);
    	randMasterKey.setVisible(false);
    	
    	xNameIcon.setVisible(true);
    	oNameIcon.setVisible(true);
    }
    
    private void openMastersMarkSelection() {
    	selectMastersMark.setVisible(true);
		xMasterKey.setDisable(false);
    	xMasterKey.setVisible(true);
    	oMasterKey.setDisable(false);
    	oMasterKey.setVisible(true);
    	randMasterKey.setDisable(false);
    	randMasterKey.setVisible(true);
    }
}
