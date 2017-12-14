/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private Button chooseDirectory;

    MyTunes songData ;
    MyTunesModel model;
    @FXML
    private Button closeButton;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtSongName;
    @FXML
    private TextField txtYear;
    MyTunes myTunes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
      
    }    
    

    @FXML
    private void chooseDirectory(ActionEvent event) throws UnsupportedTagException, InvalidDataException, IOException, NotSupportedException, SQLException
            //opens the directory on the PC for choosing a song
    {
        TrackUtility  choser= new TrackUtility();
        songData = choser.getdata();
        System.out.println(songData.toString());
        model.add(songData);
        Stage stage = (Stage) chooseDirectory.getScene().getWindow();
    
        stage.close();
    }

    @FXML
    private void closeSongview(ActionEvent event) 
            //closes the songview
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    void setModel(MyTunesModel model) 
    {
        this.model = model;
    }

   

    void setSelectFile(MyTunes myTunes) {
       if(myTunes!=null){
        txtSongName.setText(myTunes.getSongName());
        txtCategory.setText(myTunes.getGenre());
        txtArtist.setText(myTunes.getArtist());
        txtYear.setText(myTunes.getYear()+"");
        }
    }

    @FXML
    private void save(ActionEvent event) {
       myTunes.setArtist(txtArtist.getText());
       myTunes.setSongName(txtSongName.getText());
       myTunes.setGenre(txtCategory.getText());
       myTunes.setYear(Integer.parseInt(txtYear.getText()));
       model.changeSongInfoMation(myTunes);
       Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
}

    
  

