/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.BE.Playlist;

/**
 * FXML Controller class
 *
 * @author Kentg
 */
public class PlayListController implements Initializable 
{

    @FXML
    private TextField txtPlaylist;
    @FXML
    private Button close;
    @FXML
    private Button save;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        
    } 
    
    MyTunesModel model;
    Playlist songData =new Playlist();
    
    @FXML
    private void closeButton(ActionEvent event) 
            
    {
        Stage stage = (Stage) close.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    void setModel(MyTunesModel model) 
    {
        this.model = model;
    }

    @FXML
    private void save(ActionEvent event) throws SQLException 
            //saves the playlist 
    {
        songData.setplaylistName(txtPlaylist.getText());
        System.out.println(txtPlaylist.getText());
         model.add(songData);
        Stage stage = (Stage) save.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
