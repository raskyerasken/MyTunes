/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import mytunes.BE.TrackUtility;
import mytunes.BE.MyTunes;

/**
 * FXML Controller class
 *
 * @author Kentg
 */
public class SongViewController implements Initializable {

    @FXML
    private TextField textTitle;
    @FXML
    private TextField textArtist;
    @FXML
    private TextField textFile;
    @FXML
    private TextField textAlbum;
    @FXML
    private TextField textYear;
    @FXML
    private Button chooseDirectory;

    private Stage dialogStage;
    private MyTunes trackList;
    private boolean okClicked = false;
     MyTunes songData ;
    MyTunesModel model = new MyTunesModel();
    @FXML
    private Button closeButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void save(ActionEvent event) throws SQLException {
      model.add(songData);
      MyTunesController MTModel = new MyTunesController();
      //MTModel.playlistUpdate();
      
    }
    
    

    @FXML
    private void chooseDirectory(ActionEvent event) throws UnsupportedTagException, InvalidDataException, IOException, NotSupportedException {
         TrackUtility  choser= new TrackUtility();
        songData = choser.getdata();
         
        System.out.println(songData.toString());
        textTitle.setText(songData.getSongName().toString());
       textArtist.setText(songData.getArtist().toString());
        textFile.setText(songData.getPath().toString());
        textYear.setText(""+songData.getYear());
        textAlbum.setText(songData.getAlbum().toString());
        
        
        
    }

    @FXML
    private void closeSongview(ActionEvent event) {
         Stage stage = (Stage) closeButton.getScene().getWindow();
    // do what you have to do
    stage.close();
    }

   
    }

    
  

