/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.awt.Cursor;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;


import static javafx.beans.binding.Bindings.length;

import javafx.beans.value.ChangeListener;

import static javafx.beans.binding.Bindings.length;
import javafx.beans.value.ChangeListener;

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
import javafx.scene.control.ProgressIndicator;
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
import static javax.swing.Spring.width;
import mytunes.BE.MyTunes;
import mytunes.BE.Playlist;
import mytunes.BE.SongIDPlaylistID;

import mytunes.BLL.SongManager;
import mytunes.GUI.model.SongModel;


/**
 *
 * @author jacob
 */
public class MyTunesController implements Initializable 
{
    SongManager songManager= new SongManager();
//    MyTunes myTunes = new MyTunes();
    TableViewSelectionModel<MyTunes> selectionModel;
    
    boolean search=false; 
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
    DecimalFormat df = new DecimalFormat("#.##");

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
    @FXML
    private TableView<MyTunes> songsOnPlaylistTable;
    @FXML
    private TableColumn<MyTunes, String> songsOnPlaylistClmn;
    @FXML
    private Button searchFilter;


   

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
        
         songsOnPlaylistClmn.setCellValueFactory(new PropertyValueFactory("SongName"));
        listPlaylist.setItems((ObservableList<Playlist>) model.getAllPlaylist());
        sliderVolume.setValue(100);
    }   

   @FXML
   private void newPlaylist(ActionEvent event) throws IOException 
   {
        newFXMLplayListView();
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
    private void editPlaylist(ActionEvent event) throws IOException 
            //loads playlistview so you can edit playlists
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
          showErrorDialog("No SongSelected", null, "Please select a song first.");
            }
        }
        catch(IOException ex){
            showErrorDialog("I/O Exception", "DATASTREAM FAILED!", "Please select a song first.");
        }
      
    }  

    @FXML
    private void DeleteSong(ActionEvent event) 
            //allows you to delete a song
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
            //handles playing the song, and making sure its not null before playing
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
       progressBarTimeHandler();
    }
    
    private void showErrorDialog(String title, String header, String message)
            //allows us to create error messages
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        
        alert.showAndWait();
    }
   //changes the image of the playbutton when the song is playing
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
            //changes the image of the mute button when its muted
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
            //allows the user to mute the sound
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
            //updates the volume based on where the user positions the slider
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
    private void updateFilter(ActionEvent event) throws SQLException 
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
            //allows the user to close the program, and does a pop-up making sure the user actually wants to
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

            //handles the progress bar
    {        

        double mousePos = event.getX();
        double width = progressBar.getWidth();
        double diff = 100 / width * mousePos;
        double length = songManager.getSongLength().toSeconds();
        double lenghtDiff = length / 100 * diff;
        songManager.getMediaPlayer().seek(Duration.seconds(lenghtDiff));
    }
   
    private void progressBarTimeHandler()
    {
        songManager.getMediaPlayer().currentTimeProperty().addListener((ObservableValue<? extends Duration> listener, Duration oldVal, Duration newVal)
        ->
        {
            int selecSong = ListSongPlaylist.getSelectionModel().getSelectedIndex();
            double timeElapsed = newVal.toMillis() / songManager.getSongLength().toMillis();
            this.progressBar.setProgress(timeElapsed);
            
            df.setMinimumFractionDigits(2);
            int idMinutes = (int) (newVal.toSeconds()/60);
            double idSeconds = (int) (newVal.toSeconds()%60);
            songLength.setText(df.format(idMinutes+idSeconds / 100) + "");
            if(progressBar.getProgress()==1){
             ListSongPlaylist.selectionModelProperty().get().select(selecSong+1);
           System.out.println("hey");
                isPlaying=true;
                handlePlayButton();
            }
           
                
            
        }
        );
    }
   
    private void moveSong(boolean up)
            //allows the user to move the song up or down, depending on the button clicked
    {
      
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
            ListSongPlaylist.getItems().set(changeIndex, ListSongPlaylist.getSelectionModel().getSelectedItem());
            ListSongPlaylist.getItems().set(cIndex, changeSong);
            
        }
    }

    @FXML
    private void handleSongDown() {
        moveSong(false);
    }

    @FXML
    private void handleSongUp() {
        moveSong(true);
    }

    @FXML

    private void addSongsToPlaylist(ActionEvent event) throws SQLException {
       
       
        SongIDPlaylistID id= new SongIDPlaylistID();
        id.setIDPlaylist(listPlaylist.getSelectionModel().getSelectedItem().getID());
        id.setIDSong(ListSongPlaylist.getSelectionModel().getSelectedItem().getId());
        model.addSongToPlaylist(id);
       songsOnPlaylistTable.setItems(model.getSelectedPlaylist(listPlaylist.getSelectionModel().getSelectedItem().getID()));
    }

    @FXML
    private void getPlaylist(MouseEvent event) throws SQLException 
    {
           int playlistID
                =listPlaylist.getSelectionModel().getSelectedItem().getID();
             
            songsOnPlaylistTable.setItems(model.getSelectedPlaylist(playlistID));
    }

   

    @FXML
    private void handleAbout(ActionEvent event) {  //sets the "About Us"
             String contentText = "Hello, and welcome to our MyTunes.\n"
                +"In the file menu you can find\n"
                +"\t how to create a new song\n"
                +"\t how to create a new playlist\n"
                +"\t how to close the program \n"
                +"\tIn the edit menu you can find\n"
                +"\t how to edit a song\n"
                +"\t how to edit a playlist\n"
                +"\t how to delete a song\n"
                +"\t how to delete a playlist\n"
                +"\t We are a couple of students who made this\n"
                +"\t if you have any problems at all\n"
                +"\t you're very welcome to stream into a wall \n"
                +"\t as there will be roughly zero support from us \n"
                +"\t proudly presented by \n"
                +"\t Jacob, Kristófer, Kent & Kasper\n";
                
        Alert about = new Alert(AlertType.INFORMATION);
        about.setTitle("About us");
        about.setHeaderText("About MyTunes");
        about.setContentText(contentText);
        about.getDialogPane().setPrefWidth(480);
        about.resizableProperty().set(true);
        about.showAndWait();
    }


    }

    





    
 

