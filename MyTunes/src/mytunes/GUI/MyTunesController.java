/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import mytunes.BE.SongIDPlaylistID;
import mytunes.BLL.SongManager;


/**
 *
 * @author jacob
 */
public class MyTunesController implements Initializable 
{
    SongManager songManager= new SongManager();
    TableViewSelectionModel<MyTunes> selectionModel;
    private MyTunes selectedSongPlayList;
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
    TableView playfrom;
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
    boolean search=false; 
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
    private TableColumn<MyTunes, String> columnPlaylist;
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
    SongIDPlaylistID songIdPlaylistId = new SongIDPlaylistID(); 
    @FXML
    private TableColumn<Playlist, Integer> columsSongs;
    @FXML
    private TableColumn<Playlist, Integer> columTime;

   

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
        columsSongs.setCellValueFactory(
            new PropertyValueFactory("songNumbers"));
        columTime.setCellValueFactory(
            new PropertyValueFactory("playlistTime"));
        
        songsOnPlaylistClmn.setCellValueFactory(new PropertyValueFactory("SongName"));
         
        try 
        {
            listPlaylist.setItems((ObservableList<Playlist>) model.getAllPlaylist());
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(MyTunesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sliderVolume.setValue(100);
    }   

    @FXML
    private void newPlaylist(ActionEvent event) throws IOException 
    {
         newFXMLplayListView();
    }
    
    void newFXMLplayListView() throws IOException
            //sets the stage for the playlistview
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("playListView.fxml"));
        Parent root = fxLoader.load();
        PlayListController controller= fxLoader.getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
     
    void newsongView() throws IOException
    {
        Stage newStage = new Stage();

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("songView.fxml"));
        Parent root = fxLoader.load();
        SongViewController controller= fxLoader.getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
    
    //loads playlistview so you can edit playlists
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
        try 
        {
            if (myTunes!=null)
            {
                newsongView();
            }
            else
            {
                showErrorDialog("No SongSelected", null, "Please select a song first.");
            }
        }
        catch(IOException ex)
        {
            showErrorDialog("I/O Exception", "DATASTREAM FAILED!", "Please select a song first.");
        }
      
    }  

    //allows you to delete a song
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
        
    //handles playing the song, and making sure its not null before playing
    @FXML
    private void handlePlayButton() 
    {
      
       selectedSong = (MyTunes) ListSongPlaylist.getSelectionModel().getSelectedItem();
       selectedSongPlayList=songsOnPlaylistTable.getSelectionModel().getSelectedItem();
       if (selectedSong == null && selectedSongPlayList == null)
       {
            songsOnPlaylistTable.selectionModelProperty().get().select(0);
            selectedSongPlayList=songsOnPlaylistTable.getSelectionModel().getSelectedItem();
       }
        
       //if the play button gets pressed
       if (isPlaying)
       {
           if(selectedSong != null)
           {playfrom=ListSongPlaylist;
                songManager.playSong(selectedSong, false);
                songLength.setText(selectedSong.getSongLength()+"");
                  progressBarTimeHandler(ListSongPlaylist);
           }
           else if (selectedSongPlayList != null)
           {
               playfrom=songsOnPlaylistTable;
                System.out.println("hey");
                songManager.playSong(selectedSongPlayList, false);
                songLength.setText(selectedSongPlayList.getSongLength()+"");
                  progressBarTimeHandler(songsOnPlaylistTable);
           }
       }
       else 
       {
           songManager.pauseSong();
       }
      changePlayButton(isPlaying);
       
    }
    
    //allows us to create error messages
    private void showErrorDialog(String title, String header, String message)
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
            newImageForPlay("/pause.png");
            isPlaying = false;
        }
        else
        {
            newImageForPlay("/play.png");
            isPlaying = true;
        }
     }
    
        //changes the image of the mute button when its muted
    private void changeMuteButton(boolean muted)
    {
        if (muted)
        {
            newImageForMute("/mute.png");
        }
        else 
        {
            newImageForMute("/unmute.png");
        }
    }
    
    //The method to change the image, since we change the image on the 
    //play button here we used the same method twice, to change the mute button as well
    private void newImageForPlay(String picture)
    {
        Image image= new Image(getClass().getResourceAsStream(picture));
        imgPlay.setImage(image);
    }
    private void newImageForMute(String picture)
    {
        Image image= new Image(getClass().getResourceAsStream(picture));
        imgMute.setImage(image);
    }
    

    //allows the user to mute the sound
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
    
    //updates the volume based on where the user positions the slider
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
    
    
    private void macros(KeyEvent key)
    {
        if (key.getCode() == KeyCode.SPACE)
        {
            handlePlayButton();
        }   
    }
  
    @FXML
    private void nextSong() 
            //Does so you can click 'Next' and it goes to the next song
    {
        update();
        if (selectedSongIndex == tableSongsTotalItems || playfrom.getSelectionModel().getSelectedItem()==null)
        {
            selectionModel.clearAndSelect(0);            
        }
        else
        {
            selectionModel.clearAndSelect(selectedSongIndex + 1);
        }
        selectedSong = (MyTunes) playfrom.getSelectionModel().getSelectedItem();
        songManager.playSong(selectedSong, false);
          progressBarTimeHandler(playfrom);
    }

    @FXML
    private void prevSong(MouseEvent event) 
            //does so you can press previous and it'll play the previous song
    {
        update();
        if( playfrom.getSelectionModel().getSelectedItem()==null)
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
        selectedSong = (MyTunes) playfrom.getSelectionModel().getSelectedItem();
        songManager.playSong(selectedSong, false);
          progressBarTimeHandler(playfrom);
    }

    private void update()
    {
        selectionModel = (TableViewSelectionModel<MyTunes>) playfrom.selectionModelProperty().getValue();
        selectedSongIndex = selectionModel.getSelectedIndex();
        tableSongsTotalItems = playfrom.getItems().size() - 1;
    }

    //allows the user to close the program, and does a pop-up making sure the user actually wants to
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

    //handles the progress bar
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
   
    private void progressBarTimeHandler(TableView playfrom)
    {
        songManager.getMediaPlayer().currentTimeProperty().addListener((ObservableValue<? extends Duration> listener, Duration oldVal, Duration newVal)
        ->
        {
            int selecSong = playfrom.getSelectionModel().getSelectedIndex();
            double timeElapsed = newVal.toMillis() / songManager.getSongLength().toMillis();
            this.progressBar.setProgress(timeElapsed);
            
            df.setMinimumFractionDigits(2);
            int idMinutes = (int) (newVal.toSeconds()/60);
            double idSeconds = (int) (newVal.toSeconds()%60);
            songLength.setText(df.format(idMinutes+idSeconds / 100) + "");
            if(progressBar.getProgress()==1)
            {
                nextSong();
                progressBarTimeHandler(playfrom);
            }
             
        });
    }
   
    //allows the user to move the song up or down, depending on the button clicked
    private void moveSong(boolean up)
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
    private void handleSongDown() 
    {
        moveSong(false);
    }

    @FXML
    private void handleSongUp() 
    {
        moveSong(true);
    }
    
    @FXML
    private void handleAbout(ActionEvent event) {  //sets the "About Us"
             String contentText = "\t Hello, and welcome to our MyTunes."
                +"\n\t In the file menu you can find:\n"
                +"\t * How to create a new song\n"
                +"\t * How to create a new playlist\n"
                +"\t * How to close the program \n"
                +"\n\t In the edit menu you can find:\n"
                +"\t * How to edit a song\n"
                +"\t * How to edit a playlist\n"
                +"\t * How to delete a song\n"
                +"\t * How to delete a playlist\n"
                +"\n\t We are a four students that created this program.\n"
                +"\t If you have any problems at all,\n"
                +"\t you are very welcome to stream into a wall \n"
                +"\t since there will be roughly zero support from us \n"
                +"\t unless you throw money at the screen. \n"
                +"\t Proudly presented by De Raske: \n"
                +"\t Jacob, Kristófer, Kent & Kasper Raskafar\n";
                
        Alert about = new Alert(AlertType.INFORMATION);
        about.setTitle("About us and MyTunes");
        about.setHeaderText(null);
        about.setContentText(contentText);
        about.getDialogPane().setPrefWidth(480);
        about.resizableProperty().set(true);
        about.showAndWait();
    }
   


    
    
    @FXML
    private void updateFilter(ActionEvent event) throws SQLException {
     
       if(search)
       {
            search=false;
            searchFilter.setText("Search");
            ListSongPlaylist.setItems((ObservableList<MyTunes>) model.getAllSong());
       }
       else
       {
            String a=textField.getText();
            ListSongPlaylist.setItems((ObservableList<MyTunes>) model.getAllSongsByPlaylist(a));
            search=true;
            searchFilter.setText("All song");
       }
    
    }

    @FXML
    private void addSongsToPlaylist(MouseEvent event) throws SQLException 
    {
        SongIDPlaylistID id= new SongIDPlaylistID();
        if(listPlaylist.getSelectionModel().getSelectedItem() != null && ListSongPlaylist.getSelectionModel().getSelectedItem() != null) 
        {
            int lastSelected=listPlaylist.getSelectionModel().getSelectedIndex();
            id.setIDPlaylist(listPlaylist.getSelectionModel().getSelectedItem().getID());
            id.setIDSong(ListSongPlaylist.getSelectionModel().getSelectedItem().getId());
            model.addSongToPlaylist(id);
            songsOnPlaylistTable.setItems(model.getSelectedPlaylist(listPlaylist.getSelectionModel().getSelectedItem().getID()));
            listPlaylist.setItems((ObservableList<Playlist>) model.getAllPlaylist());
            listPlaylist.selectionModelProperty().get().select(lastSelected);
        }
        else
        {
            showErrorDialog("Selection Error", null, "Please select a song and/or a playlist");
        }
    }

    @FXML
    private void getSelectedPlaylist(MouseEvent event) throws SQLException 
    {
        songsOnPlaylistTable.setItems(model.getSelectedPlaylist(listPlaylist.getSelectionModel().getSelectedItem().getID()));
    }

    //Removes a song from the slected playlist, if nothing is slected you get a popup window
    @FXML
    private void removeSongFromPlaylist(ActionEvent event) throws SQLException 
    {
        if (listPlaylist.getSelectionModel().getSelectedItem() != null && songsOnPlaylistTable.getSelectionModel().getSelectedItem() != null) 
        {
            songIdPlaylistId.setIDPlaylist(listPlaylist.getSelectionModel().getSelectedItem().getID());
            songIdPlaylistId.setIDSong(songsOnPlaylistTable.getSelectionModel().getSelectedItem().getId());
            songsOnPlaylistTable.setItems(model.removeSongPlaylist(songIdPlaylistId));
        }
        else
        {
            showErrorDialog("Selection Error", null, "Please select a song in the playlist in order to remove it");
        }
    }
}

    





    
 

