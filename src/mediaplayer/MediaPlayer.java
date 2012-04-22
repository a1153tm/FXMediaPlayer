/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author miyabetaiji
 */
public class MediaPlayer extends Application {
    
    public static void main(String[] args) {
        Application.launch(MediaPlayer.class, args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MediaPlayer.fxml"));
        
        stage.setScene(new Scene(root));
        stage.show();
    }
}
