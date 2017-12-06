/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.BE.MyTunes;
import mytunes.BE.Playlist;
import mytunes.BLL.BLLManager;

/**
 *
 * @author mr.Andersen
 */
public class MyTunesModel 
{  
    private BLLManager bllManager = new BLLManager();

     private ObservableList<MyTunes> songList
            = FXCollections.observableArrayList();
     
private ObservableList<Playlist> playlist
            = FXCollections.observableArrayList();

    List<MyTunes> getAllSong() {
        songList.addAll(bllManager.getallSong());
        return songList;
    }
    List<MyTunes> updateAllSong()
    {
    return songList;
    }
     List<Playlist> getPlaylist()
    {
    return playlist;
    }
    public void add (MyTunes mytunes) throws SQLException
    {   
        bllManager.add(mytunes);
        songList.add(mytunes);
     
    }

    public void remove(MyTunes selectedMyTunes) {
       
    bllManager.remove(selectedMyTunes);
        songList.remove(selectedMyTunes);
   
    
     }
public List<MyTunes> getAllSongsByPlaylist(String song) throws SQLException
    {
        songList.setAll(bllManager.getAllSongsByPlaylist(song));
        return songList;
        
    }

public void add (Playlist playlistSong) throws SQLException
    {   
        for (Playlist play :playlist ) {
           
            if(playlistSong.getplaylistName().equals(play.getplaylistName()))
            {
                
                playlistSong.setSongID(playlistSong.getSongID()+1);
            }
            else{
                bllManager.add(playlistSong);
               playlist.add(playlistSong);
            }
            bllManager.add(playlistSong);
        }
            
        
     
    }
 List<Playlist> getAllPlaylist() {
        playlist.addAll(bllManager.getallPlaylist());
        return playlist;
    }

    void removePlaylist(Playlist playlistSongs) {
       bllManager.remove(playlistSongs);
        playlist.remove(playlistSongs);
    }
}
    
    
   

    
    
    
   
     