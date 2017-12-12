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
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import mytunes.BE.MyTunes;
import mytunes.BE.Playlist;
import mytunes.BE.Song;
import mytunes.BLL.SongManager;
import mytunes.GUI.model.SongModel;


/**
 *
 * @author jacob
 */
public class MyTunesController implements Initializable 
{
    SongManager songManager= new SongManager();
    TableViewSelectionModel<MyTunes> selectionModel;
    
    
    private Label label;
    @FXML
    private TableView<MyTunes> ListSongPlaylist;
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
    
    MyTunesModel model= new MyTunesModel();
    SongViewController songview = new SongViewController();
    
    
    private Stage primaryStage;
    final Button play = new Button("Pause"); 
    int selectedSongIndex;
    int tableSongsTotalItems;
    private MediaPlayer player;
    private boolean hasBrowseButtonBeenClicked;
    private TableView<MyTunes> myTunes;
    private double sliderVolumeValue;
    private boolean isMuted=false;
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
    @FXML
    private Button arrowDown;
    @FXML
    private Button arrowUp;
    @FXML
    private ImageView arrowUpPic;
    @FXML
    private ImageView arrowDownPic;


   

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
        sliderVolume.setValue(100);
    }   

    
    void newFXMLplayListView() throws IOException{
       Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("playListView.fxml"));
        Parent root = fxLoader.load();
        PlayListController controller= fxLoader.getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
      
    }
     void newsongView() throws IOException{
       Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("songView.fxml"));
        Parent root = fxLoader.load();
        SongViewController controller= fxLoader.getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
      
    }
    
    @FXML
    private void newPlaylist(ActionEvent event) throws IOException 
    {
       newFXMLplayListView();
    }
   
    @FXML
    private void editPlaylist(ActionEvent event) throws IOException 
    {
        newFXMLplayListView();
    }

  @FXML
    private void newSong(ActionEvent event) throws IOException 
   {
        newsongView();
   }

    @FXML
    private void editSong(ActionEvent event) 
    {
        MyTunes myTunes=
       ListSongPlaylist.getSelectionModel().getSelectedItem();
        try {
            if (myTunes!=null)
            {
            newsongView();
            }
            else
            {
            showErrorDialog("I/O Exception", "DATASTREAM FAILED!", "Please select a song first.");
            }
            }
        catch(IOException ex){
            showErrorDialog("I/O Exception", "DATASTREAM FAILED!", "Please select a song first.");
        }
       }

    @FXML
    private void DeleteSong(ActionEvent event) 
    {
        MyTunes selectedMyTunes = ListSongPlaylist.getSelectionModel().getSelectedItem();
        if(selectedMyTunes==null)
        {
            showErrorDialog("Nothing selectet", null, "\"Cant delete nothing\"");
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
    
    private void showErrorDialog(String title, String header, String message)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        alert.showAndWait();
    }
   
    private void changePlayButton(boolean playing)
    {
        if (playing)
        {
            newImage("/pause.png");
            isPlaying = false;
        }
        else
        {
            newImage("/play.png");
            isPlaying = true;
        }
     }
    private void newImage(String picture)
    {
     Image image= new Image(getClass().getResourceAsStream(picture));
     imgPlay.setImage(image);
    }
    
    private void changeMuteButton(boolean muted)
    {
        if (muted)
    {
        newImage("/mute.png");
    }
    else 
    {
        newImage("/unmute.png");
    }
    }
  
    @FXML
    private void handleMuteSound()
    {
        if (!isMuted)
        {
            sliderVolumeValue = sliderVolume.getValue();
            sliderVolume.setValue(0.0);
            songManager.adjustVolume(0);
            isMuted = true;
        }
        else 
        {
            sliderVolume.setValue(sliderVolumeValue);
            songManager.adjustVolume(sliderVolumeValue);
            isMuted = false;
        }
        changeMuteButton(isMuted);
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
            showErrorDialog("Nothing selected", null, "Cant delete nothing");
        }
        else
        {
            model.removePlaylist(playlist);
        }
    }
//    
    private void macros(KeyEvent key)
    {
        if (key.getCode() == KeyCode.SPACE)
        {
            handlePlayButton();
        }
//        
//       if (key.getCode() == KeyCode.DELETE)
//        {
//            deletePlaylist();
//        }

   
  }
  
    @FXML
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
    
<<<<<<< HEAD
=======
    
    private void loadStage(String viewName) throws IOException
    {
        primaryStage = (Stage) ListSongPlaylist.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/" + viewName));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(primaryStage);

        newStage.show();
    }

    
    
    
    private void moveSong(boolean up)
    {
        System.out.println(ListSongPlaylist.getSelectionModel().getSelectedIndex());
        int cIndex = ListSongPlaylist.getSelectionModel().getSelectedIndex();
        int changeIndex = cIndex;
        boolean change = false;
        
        if (up && cIndex != 0)
        {
            changeIndex = cIndex -1;
            change = true;
        }
        else if (!up && cIndex != ListSongPlaylist.getItems().size()-1)
        {
            changeIndex = cIndex +1;
            change = true;
        }
        
        
        if (change)
        {
            MyTunes changeSong = ListSongPlaylist.getItems().get(changeIndex);
            ListSongPlaylist.getItems().set(changeIndex, selectedSong);
            ListSongPlaylist.getItems().set(cIndex, changeSong);
            selectedSong = ListSongPlaylist.getItems().get(changeIndex);
            
        }
    }

>>>>>>> d8a64ce3354b2f7efd3a86d6143ca654cae82441
    @FXML
    private void handleSongDown(ActionEvent event) {
        moveSong(false);
    }

    @FXML
    private void handleSongUp(ActionEvent event) {
        moveSong(true);
    }
}



    
 

