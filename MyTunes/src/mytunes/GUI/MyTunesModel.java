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
import mytunes.BE.SongIDPlaylistID;
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
    private ObservableList<MyTunes> songOnPlaylist2
            = FXCollections.observableArrayList();
      private ObservableList<SongIDPlaylistID> songOnPlaylist
            = FXCollections.observableArrayList();
    private ObservableList<Playlist> playlist
            = FXCollections.observableArrayList();
 
    List<MyTunes> getAllSong() 
    {
        songList.clear();
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

    public void remove(MyTunes selectedMyTunes) 
    {
        bllManager.remove(selectedMyTunes);
        songList.remove(selectedMyTunes);
    }
    
    

    public void add (Playlist playlistSong) throws SQLException
    {   
        bllManager.add(playlistSong);
        playlist.add(playlistSong);
    }

    List<Playlist> getAllPlaylist() 
    {
        playlist.addAll(bllManager.getallPlaylist());
        return playlist;
    }

    public void removePlaylist(Playlist playlistSongs) 
    {
        bllManager.remove(playlistSongs);
        playlist.remove(playlistSongs);
    }

    public void addSongToPlaylist(SongIDPlaylistID ID) 
    {
        bllManager.addSongToPlaylist(ID);
        songOnPlaylist.add(ID);
    }
    
    
    public List<MyTunes> getAllSongsByPlaylist(String song) throws SQLException
    {
        songList.setAll(bllManager.getAllSongsByPlaylist(song));
        return songList;        
    }

    ObservableList<MyTunes> removeSongPlaylist(SongIDPlaylistID SongPlaylist) throws SQLException 
    {
          songOnPlaylist.clear();
          bllManager.removeSongToPlaylist(SongPlaylist);
          songOnPlaylist.remove(SongPlaylist);
          
          return getSelectedPlaylist(SongPlaylist.getIDPlaylist());
     }

    ObservableList<MyTunes> getSelectedPlaylist(int playlistID) throws SQLException {
        songOnPlaylist.clear();
        songOnPlaylist.addAll( bllManager.getSelectedPlaylist(playlistID));
        songOnPlaylist2.clear();
        
        
        
        for (SongIDPlaylistID hey : songOnPlaylist) 
        {
            for (MyTunes myTunes : songList) 
            {
                if(myTunes.getId()==hey.getIDSong())
                {
                   songOnPlaylist2.add(myTunes);
                }
                
            }
            
        }
        
    
    return songOnPlaylist2;
    }
} 




    
   

    
    
    
   
     