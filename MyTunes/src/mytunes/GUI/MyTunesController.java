/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import mytunes.BE.MyTunes;
import mytunes.BE.Song;
import mytunes.BLL.SongManager;


/**
 *
 * @author jacob
 */
public class MyTunesController implements Initializable 
{
   SongManager songManager= new SongManager();
   
    private Label label;
    @FXML
    private TableView<MyTunes> ListSongPlaylist ;
    @FXML
    private Label labelSongTheirIsPlaying;
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
    private Button playBtn;
    @FXML
    private ImageView backBtn;
    @FXML
    private ImageView nextBtn;
    
    MyTunesModel model= new MyTunesModel();
    SongViewController songview = new SongViewController();

    
    final Button play = new Button("Pause"); 
    private MediaPlayer player;
    private TableView<MyTunes> myTunes;
    private double sliderVolumeValue;
    private boolean isMuted;
    private boolean isPlaying;
    private Song selectedSong;
    private ObservableList<MyTunesModel> observableTracksView;
    private MyTunesModel nextTrack;
    private MyTunesModel prevTrack;
    private MyTunesModel currentTrack;
    private Media currentMedia;
    private boolean isShuffleToggled;
    private boolean isRepeatToggled;
   // private final Random rand;
    
    
    
    
    @FXML
    private ImageView imgPlay;
   @FXML
    private Slider sliderVolume;
    @FXML
    private Button imgMute;
    @FXML
    private MenuItem closeBtn;
    @FXML
    private MenuItem newSong;
    @FXML
    private MenuItem newPlaylist;
    @FXML
    private TextField textField;
    
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
            new PropertyValueFactory("year"));
        
        ListSongPlaylist.setItems((ObservableList<MyTunes>) model.getAllSong());
    }   

    
    
    
    
    
    
   /* public MyTunesController(Random rand) {
        this.rand = rand;
    }*/
 
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
    private void editPlaylist(ActionEvent event) throws IOException 
    {
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

        model.remove(selectedMyTunes);
    }
        
    
    @FXML
    private void handlePlayButton() 
    {
       /*if (selectedSong == null )
       {
           ListSongPlaylist.selectionModelProperty().get().select(0);
       }
       
       selectedSong = (Song) ListSongPlaylist.selectionModelProperty().getValue().getSelectedItems();
       
       //if the play button gets pressed
       if (!isPlaying)
       {
           songManager.playSong(selectedSong, false);
       }
       
       else 
       {
           songManager.pauseSong();
       }*/
       
       
       }
    
   
    
    

    private void changePlayButton(boolean playing)
    {/*
        Image image;
        if (playing)
        {
            image = new Image(getClass().getResourceAsStream("/mytunes/src/images/play.png"));
            imgPlay.setImage(image);
            isPlaying = false;
        }
        else
        {
            image = new Image(getClass().getResourceAsStream("/mytunes/src/images/pause.png"));
            imgPlay.setImage(image);
            isPlaying = true;
        }*/
    }

    @FXML
    private void lastSong(MouseEvent event) 
    {
        System.out.println("last");
    }

    private void pause(MouseEvent event) 
    {
        System.out.println("pause");
    }


    @FXML
    private void nextSong(MouseEvent event) 
    {
        
    }
    
    private void handleMuteSound()
    {/*
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
                    System.out.println("i muted it");*/

    }
    
    private void VolumeSliderUpdate()
    {/*
        sliderVolume.valueProperty().addListener((ObservableValue<? extends Number> listener, Number oldVal, Number newVal)
                ->
                {
                    if (songManager.getMediaPlayer() != null)
                    {
                        songManager.adjustVolume(newVal.doubleValue() / 100);
                        
                        isMuted = false;
                    }
                });*/
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        String a=textField.getText();
        System.out.println(a);
        ListSongPlaylist.setItems((ObservableList<MyTunes>) model.getAllSongsByPlaylist(a));
        
    }

    
    
    private void prevOrNextSong(boolean next)
    {
        /*TableViewSelectionModel<Song> selectionModel = (TableViewSelectionModel<Song>) ListSongPlaylist.selectionModelProperty().getValue();
        int selectedSongIndex = selectionModel.getSelectedIndex();
        int tableSongsTotalItems = ListSongPlaylist.getItems().size() - 1;
        
        if (next)
        {
            if (isShuffleToggled)
            {
                selectionModel.clearAndSelect(rand.nextInt(ListSongPlaylist.getItems().size()));
            }
            
            else if (selectedSongIndex == tableSongsTotalItems || selectedSong == null)
            {
                selectionModel.clearAndSelect(0);
            }
            else
            {
                selectionModel.clearAndSelect(selectedSongIndex + 1);
            }
        }
        
        else if (songManager.getSongTimeElapsed().toMillis() <= 3500.0)
        {
            if (selectedSongIndex == 0 || selectedSong == null)
            {
                selectionModel.clearAndSelect(tableSongsTotalItems);
            }
            else 
            {
                selectionModel.clearAndSelect(selectedSongIndex);
            }
            
            selectedSong = selectionModel.getSelectedItem();
            songManager.playSong(selectedSong, true);
            songManager.adjustVolume(sliderVolume.getValue() / 100);
        }*/
    }

    @FXML
    private void deletePlaylist(ActionEvent event) {
    }
 
}
