package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import server.SchoolServer;
import ui.UserWindow;

public class serverController {
	String port;
	SchoolServer server;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button connectBtn;

    @FXML
    private Label portLbl;

    @FXML
    private Label schoolServerLbl;

    @FXML
    private Label statusLbl1;

    @FXML
    private Button disconBtn;

    @FXML
    private Label statusLbl2;

    @FXML
    private TextField portTxt;

    @FXML
    void connect(ActionEvent event) {
    	port=portTxt.getText();
    	try{
    		Integer.parseInt(port);
    	}
    	catch(NumberFormatException e){
    		new Alert(AlertType.ERROR, "Please enter 4-digit port number", ButtonType.OK).showAndWait();
    		return;
    	}
    	if(port.length()!=4){
    		new Alert(AlertType.ERROR, "Please enter 4-digit port number", ButtonType.OK).showAndWait();
    	}
    	else{
	    	server = new SchoolServer(Integer.parseInt(port));
	    	statusLbl2.setTextFill(Paint.valueOf("green"));
	    	statusLbl2.setText("CONNECTED");
	    	connectBtn.setDisable(true);
	    	disconBtn.setDisable(false);
	    	try {
				server.listen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//Main.stack.push("server");
    	//UserWindow.createUserWindow((Stage)statusLbl2.getScene().getWindow(), "server", getClass());
    }

    @FXML
    void disconnect(ActionEvent event) {
    	try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	statusLbl2.setTextFill(Paint.valueOf("red"));
    	statusLbl2.setText("DISCONNECTED");
    	connectBtn.setDisable(false);
    	disconBtn.setDisable(true);
    }

    @FXML
    void initialize() {
        assert connectBtn != null : "fx:id=\"connectBtn\" was not injected: check your FXML file 'server.fxml'.";
        assert portLbl != null : "fx:id=\"portLbl\" was not injected: check your FXML file 'server.fxml'.";
        assert schoolServerLbl != null : "fx:id=\"schoolServerLbl\" was not injected: check your FXML file 'server.fxml'.";
        assert statusLbl1 != null : "fx:id=\"statusLbl1\" was not injected: check your FXML file 'server.fxml'.";
        assert disconBtn != null : "fx:id=\"disconBtn\" was not injected: check your FXML file 'server.fxml'.";
        assert statusLbl2 != null : "fx:id=\"statusLbl2\" was not injected: check your FXML file 'server.fxml'.";
        assert portTxt != null : "fx:id=\"portTxt\" was not injected: check your FXML file 'server.fxml'.";

    }
}
