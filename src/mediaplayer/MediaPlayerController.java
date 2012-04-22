/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer;

import mediaplayer.model.*;
import java.io.File;
import java.net.URL;
import java.util.*;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.*;
import javafx.scene.media.MediaView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author miyabetaiji
 */
public class MediaPlayerController implements Initializable {
    
    /**
     * UI Controls
     */
    @FXML
    TreeView<String> treeMedia;
    
    @FXML
    Tab tabMedia;

    @FXML
    Tab tabDetail;

    @FXML
    VBox vBoxMedia;

    @FXML
    Slider sliderTime;
    
    /**
     * Models
     */
    private Map<String, String> entryMap = new HashMap<String, String>();
    private Entry rootEntry;
    private static final String LIBLARY_URL = "/Users/miyabetaiji/pbl/proto_types/medias";

    /**
     * Media Player
     */
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Duration duration;
    private static final String MEDIA_URL = "file:///Users/miyabetaiji/pbl/proto_types/medias/flv_on2vp6vp62_mp3.flv";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize model
        rootEntry = new DirectoryEntry(new File(LIBLARY_URL));

        // Initialize tree view
        TreeItem<String> rootItem = new TreeItem<String>();
        setTreeItem(rootItem, rootEntry);
        treeMedia.setRoot(rootItem);
        treeMedia.setShowRoot(false);
        treeMedia.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        treeMedia.getSelectionModel().selectedItemProperty().addListener(mediaItemSelected);
        
        // Initialize media view
        mediaPlayer = new MediaPlayer(new Media(MEDIA_URL));
        mediaPlayer.setAutoPlay(true);
        //tabMedia.setContent(new MediaView(mediaPlayer));
        mediaView = new MediaView(mediaPlayer);
        vBoxMedia.getChildren().add(mediaView);
        
        //sliderTime.valueProperty().addListener(rb);
    }

    private void setTreeItem(TreeItem<String> parentItem, Entry parentEntry) {
        for (Iterator it = parentEntry.iterator(); it.hasNext(); ) {
            Entry entry = (Entry)it.next();

            TreeItem<String> item = new TreeItem<String>(entry.getName());
            parentItem.getChildren().add(item);

            if (entry.isFile()) {
                entryMap.put(entry.getName(), entry.getPath());
            } else if (entry.isDirectory()) {
                setTreeItem(item, entry);
            }
        }
    }

    public void handleButtonPlayMedia() {
        Status status = mediaPlayer.getStatus();
        if (status == Status.UNKNOWN
            || status == Status.HALTED)
        {
            return;
        }
         
        if (status == Status.PAUSED
         || status == Status.STOPPED
         || status == Status.READY)
        {
            setMediaView();
            mediaPlayer.play();
        }
    }
 
    public void handleButtonStopMedia() {
        mediaPlayer.stop();
    }

    
    private void playMedia() {
        mediaPlayer.play();
        duration = mediaPlayer.getMedia().getDuration();
    }
    
    private void setMediaView() {
        mediaView.setMediaPlayer(mediaPlayer);
    }
    
    private final ChangeListener<TreeItem<String>> mediaItemSelected = new ChangeListener<TreeItem<String>>() {
        @Override
        public void changed(ObservableValue<? extends TreeItem<String>> observable,
                    TreeItem<String> oldValue, TreeItem<String> newValue) {
            String path = null;
            path = entryMap.get(newValue.getValue());
            if (path != null) {
                mediaPlayer.stop();
                mediaPlayer = new MediaPlayer(new Media("file://" + path));
                setMediaView();
                playMedia();
            }
        }
    };

    /*
    private InvalidationListener sliderListenr = new InvalidationListener() {
        public void invalidated(Observable ov) {
            if (sliderTime.isValueChanging()) {
            // multiply duration by percentage calculated by slider position
                mediaPlayer.seek(duration.multiply(sliderTime.getValue() / 100.0));
            }
        }
    };
    * 
    */
    
}
