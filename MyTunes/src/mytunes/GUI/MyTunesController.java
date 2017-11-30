/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import mytunes.BE.MyTunes;


/**
 *
 * @author jacob
 */
public class MyTunesController implements Initializable {
    
    private Label label;
    @FXML
    private ListView<String> ListSongPlaylist ;
    @FXML
    private Label labelSongTheirIsPlaying;
    @FXML
    private TextField textFieldFilter;
    private ObservableList<String> playlist; 
    @FXML
    private TableColumn<MyTunes, String> columName;
    @FXML
    private TableColumn<MyTunes, String> columSongs;
    @FXML
    private TableColumn<MyTunes, Integer> colomTime;
    @FXML
    private TableColumn<MyTunes, String> listSongTitle;
    @FXML
    private TableColumn<MyTunes,String> listSongArtist;
    @FXML
    private TableColumn<MyTunes, String> listSongCategory;
    @FXML
    private TableColumn<MyTunes, Integer> listSongTime;
    @FXML
    private ImageView playBtn;
    @FXML
    private ImageView backBtn;
    @FXML
    private ImageView nextBtn;
    @FXML
    private ImageView pauseBtn;
    private MediaPlayer player;
    MyTunesModel model= new MyTunesModel();
    @FXML
    private TableView<MyTunes> myTunes;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
      listSongTitle.setCellValueFactory(
            new PropertyValueFactory("songName"));
        listSongArtist.setCellValueFactory(
            new PropertyValueFactory("artist"));
        listSongCategory.setCellValueFactory(
            new PropertyValueFactory("album"));
        listSongTime.setCellValueFactory(
            new PropertyValueFactory("year"));
        
         

        myTunes.setItems((ObservableList<MyTunes>) model.getAllSong());
  
    }   
     
        
    
    public void playlistUpdate(MyTunesModel model)
    {
        this.model= model;
       myTunes.setItems((ObservableList<MyTunes>) model.updateAllSong());
       
    }

    @FXML
    private void newPlaylist(ActionEvent event) throws IOException {
         Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("playListView.fxml"));

            Parent root = fxLoader.load();
            PlayListController controller
                    = fxLoader.getController();


            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
    }

    @FXML
    private void editPlaylist(ActionEvent event) throws IOException {
         Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("playListView.fxml"));

            Parent root = fxLoader.load();
            PlayListController controller
                    = fxLoader.getController();


            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
    }

    @FXML
    private void deletePlaylist(ActionEvent event) {
    }


    @FXML
    private void deleteSongOnPlaylist(ActionEvent event) {
    }

    @FXML
    private void newSong(ActionEvent event) throws IOException {
         Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("songView.fxml"));

            Parent root = fxLoader.load();
           SongViewController controller
                    = fxLoader.getController();


            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
       
    }

    @FXML
    private void editSong(ActionEvent event) 
    {
        
    }

    @FXML
    private void DeleteSong(ActionEvent event) {
        MyTunes selectedMyTunes
                = myTunes.getSelectionModel().getSelectedItem();

        model.remove(selectedMyTunes);
    }
        
    

    @FXML
    private void closeProgram(ActionEvent event) {
    }


    @FXML
    private void playBtn() 
    {
        System.out.println("lalal");
        if(player != null) {
            boolean playing = player.getStatus().equals(MediaPlayer.Status.PLAYING);
            if(playing) {
                player.pause();
                System.out.println("lol");
            } else {
                player.play();
                System.out.println("lolz");
            }
        }
    }


    @FXML
    private void play(MouseEvent event) {
        System.out.println("play");
    }

    @FXML
    private void lastSong(MouseEvent event) {
        System.out.println("last");
    }

    @FXML
    private void pause(MouseEvent event) {
        System.out.println("pause");
    }

    @FXML
    private void nextSong(MouseEvent event) {
        System.out.println("next");
    }
 
}
