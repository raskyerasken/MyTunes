/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import mytunes.BE.MyTunes;
import mytunes.BE.Playlist;
import mytunes.BE.Song;
import mytunes.BLL.SongManager;


/**
 *
 * @author jacob
 */
public class MyTunesController implements Initializable 
{
    SongManager songManager= new SongManager();
    int selectedSongIndex;
    int tableSongsTotalItems;
    TableViewSelectionModel<MyTunes> selectionModel;
    private Label label;
    @FXML
    private TableView<MyTunes> ListSongPlaylist ;
    @FXML
    private Label labelSongTheirIsPlaying;
 
    private TableColumn<MyTunes, String> columName;
    private TableColumn<MyTunes, String> columSongs;
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
    private Button playBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button nextBtn;
    
    MyTunesModel model= new MyTunesModel();
    SongViewController songview = new SongViewController();
    
    
    final Button play = new Button("Pause"); 
    private MediaPlayer player;
    private boolean hasBrowseButtonBeenClicked;
    private TableView<MyTunes> myTunes;
    private double sliderVolumeValue;
    private boolean isMuted;
    private boolean isPlaying = false;
    private MyTunes selectedSong;
    private ObservableList<MyTunesModel> observableTracksView;
    private MyTunesModel nextTrack;
    private MyTunesModel prevTrack;
    private MyTunesModel currentTrack;
    private Media currentMedia;
    private Playlist selectedPlaylist;
    private boolean isShuffleToggled;
    private boolean isRepeatToggled;
    @FXML
    private ImageView imgPlay;
    @FXML
    private Slider sliderVolume;
    @FXML
    private ImageView imgMute;
    @FXML
    private MenuItem newSong;
    @FXML
    private MenuItem newPlaylist;
    @FXML
    private TextField textField;
    @FXML
    private TableView<Playlist> listPlaylist;
    @FXML
    private Label songLength;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button muteBtn;
    @FXML
    private TableColumn<?, ?> columnPlaylist;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        listSongTitle.setCellValueFactory(
            new PropertyValueFactory("songName"));
        listSongArtist.setCellValueFactory(
            new PropertyValueFactory("artist"));
        listSongCategory.setCellValueFactory(
            new PropertyValueFactory("album"));
        listSongTime.setCellValueFactory(
            new PropertyValueFactory("songLength"));
        
        
        ListSongPlaylist.setItems((ObservableList<MyTunes>) model.getAllSong());
        changePlayButton(isPlaying);
        columnPlaylist.setCellValueFactory(
            new PropertyValueFactory("playlistName"));
        
            
        listPlaylist.setItems((ObservableList<Playlist>) model.getAllPlaylist());
    }   

 
    @FXML
    private void newPlaylist(ActionEvent event) throws IOException 
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("playListView.fxml"));
        Parent root = fxLoader.load();
        PlayListController controller
              = fxLoader.getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
   
    @FXML
    private void editPlaylist(ActionEvent event) throws IOException 
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("playListView.fxml"));

        Parent root = fxLoader.load();
        PlayListController controller
                    = fxLoader.getController();
        controller.setModel(model);

        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

  


    @FXML
    private void newSong(ActionEvent event) throws IOException 
    {
         Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("songView.fxml"));

        Parent root = fxLoader.load();
        SongViewController controller
                    = fxLoader.getController();

        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    private void editSong(ActionEvent event) 
    {
        
    }

    @FXML
    private void DeleteSong(ActionEvent event) 
    {
        MyTunes selectedMyTunes
                = ListSongPlaylist.getSelectionModel().getSelectedItem();
        if(selectedMyTunes==null)
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Nothing selectet");
            alert.setHeaderText(null);
            alert.setContentText("Cant delete nothing");
            alert.showAndWait();
        }
        else
        {
            model.remove(selectedMyTunes);
        }
    }
        
    
    @FXML
    private void handlePlayButton() 
    {

       selectedSong = (MyTunes) ListSongPlaylist.getSelectionModel().getSelectedItem();

       if (selectedSong == null )
       {
            ListSongPlaylist.selectionModelProperty().get().select(0);
       }
       //if the play button gets pressed
       if (isPlaying)
       {
          
           songManager.playSong(selectedSong, false);
           System.out.println(selectedSong.getPath());
       }
       else 
       {
           songManager.pauseSong();
       }
        
        songLength.setText(selectedSong.getSongLength()+"");
       changePlayButton(isPlaying);
      
    }
    
   
    private void changePlayButton(boolean playing)
    {
        Image image;
        if (playing)
        {
            image = new Image(getClass().getResourceAsStream("/pause.png"));
            imgPlay.setImage(image);
            isPlaying = false;
        }
        else
        {
            image = new Image(getClass().getResourceAsStream("/play.png"));
            imgPlay.setImage(image);
            isPlaying = true;
        }
    }

    private void lastSong(MouseEvent event) 
    {
        System.out.println("last");
    }

    private void pause(MouseEvent event) 
    {
        System.out.println("pause");
    }


    @FXML
    private void handleMuteSound()
    {
        if (!isMuted)
        {
            sliderVolumeValue = sliderVolume.getValue();
            sliderVolume.setValue(0.0);
            isMuted = true;
        }
        else 
        {
            sliderVolume.setValue(sliderVolumeValue);
            isMuted = false;
        }
        System.out.println("i muted it");

    }
    
   @FXML
    private void VolumeSliderUpdate()
    {
        sliderVolume.valueProperty().addListener((ObservableValue<? extends Number> listener, Number oldVal, Number newVal)
                ->
                {
                    if (songManager.getMediaPlayer() != null)
                    {
                        songManager.adjustVolume(newVal.doubleValue() / 100);
                        
                        isMuted = false;
                    }
                });
    }

    @FXML
    private void update(ActionEvent event) throws SQLException 
    {
        String a=textField.getText();
        System.out.println(a);
        ListSongPlaylist.setItems((ObservableList<MyTunes>) model.getAllSongsByPlaylist(a));
    }

    

    @FXML
    private void deletePlaylist(ActionEvent event) 
    {
        Playlist playlist
                = listPlaylist.getSelectionModel().getSelectedItem();
        if(playlist==null)
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Nothing selectet");
            alert.setHeaderText(null);
            alert.setContentText("Cant delete nothing");
            alert.showAndWait();}
        else
        {
            model.removePlaylist(playlist);
        }
    }
//    
//    private void macros(KeyEvent key)
//    {
//        if (key.getCode() == KeyCode.SPACE)
//        {
//            handlePlayButton();
//        }
        
//        if (key.getCode() == KeyCode.DELETE)
//        {
//            deletePlaylist();
//        }

   
//    }
  
    private void nextSong(MouseEvent event) 
    {
        update();

        if (selectedSongIndex == tableSongsTotalItems || ListSongPlaylist.getSelectionModel().getSelectedItem()==null)
        {
            selectionModel.clearAndSelect(0);            
        }
        else
        {
            System.out.println("hey");
            selectionModel.clearAndSelect(selectedSongIndex + 1);
        }

        selectedSong = (MyTunes) ListSongPlaylist.getSelectionModel().getSelectedItem();
        songManager.playSong(selectedSong, false);

    }

    @FXML
    private void prevSong(MouseEvent event) 
    {
        update();
        if( ListSongPlaylist.getSelectionModel().getSelectedItem()==null)
        {
            selectionModel.clearAndSelect(0);
        }
        else if (selectedSongIndex == 0)
        {
            selectionModel.clearAndSelect(tableSongsTotalItems);
        }
        else
        {
            selectionModel.clearAndSelect(selectedSongIndex -1);
        }

        selectedSong = (MyTunes) ListSongPlaylist.getSelectionModel().getSelectedItem();
        songManager.playSong(selectedSong, false);

    }

    private void update()
    {
        selectionModel = (TableViewSelectionModel<MyTunes>) ListSongPlaylist.selectionModelProperty().getValue();
        selectedSongIndex = selectionModel.getSelectedIndex();
        tableSongsTotalItems = ListSongPlaylist.getItems().size() - 1;
    }

    @FXML
    private void closeProgram(ActionEvent event) 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit this awesome mp3 player?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            Platform.exit();
        } 
    } 

     @FXML
    private void handleProgressBar(MouseEvent event)
    {
        double mousePos = event.getX();
        double width = progressBar.getWidth();
        double diff = 100 / width * mousePos;
        double length = songManager.getSongLength().toSeconds();
        double lenghtDiff = length / 100 * diff;

        songManager.getMediaPlayer().seek(Duration.seconds(lenghtDiff));
    }
    
    
    
//    private void moveSong(boolean up)
//    {
//        System.out.println(ListSongPlaylist.getSelectionModel().getSelectedIndex());
//        int nowIndex = ListSongPlaylist.getSelectionModel().getSelectedIndex();
//        
//        int changeIndex = nowIndex;
//        boolean change = false;
//        
//        if (up && nowIndex != 0)
//        {
//            changeIndex = nowIndex -1;
//            change = true;
//           
//        }
//        else if (!up && nowIndex != ListSongPlaylist.getItems().size() -1)
//        {
//            changeIndex = nowIndex +1;
//            change = true;
//        }
//        
//        if (change)
//        {
//            MyTunes changeSong = ListSongPlaylist.getItems().get(changeIndex);
//            ListSongPlaylist.getItems().set(changeIndex, selectedSong);
//            ListSongPlaylist.getItems().set(nowIndex, selectedSong);
//            selectedSong = ListSongPlaylist.getItems().get(changeIndex);
//            if (!hasBrowseButtonBeenClicked)
//            {
//                selectedPlaylist.getSongList().set(changeIndex, selectedSong);
//                selectedPlaylist.getSongList().set(nowIndex, changeSong);
//            }
//                    
//             
            
//        }
//    }
}
 

