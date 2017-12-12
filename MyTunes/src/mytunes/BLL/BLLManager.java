/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BLL;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
import mytunes.BE.MyTunes;
import mytunes.BE.Playlist;

import mytunes.DAL.myTunesDAL;


/**
 *
 * @author mr.Andersen
 */
public class BLLManager 
{
    myTunesDAL mtdal = new myTunesDAL();
     
    public List<MyTunes> getAllSongsByPlaylist(String song) throws SQLException
            //creates the list used to getting songs for each playlist
    {
        return mtdal.getAllSongsByPlaylist(song);
    }
    
    
    public void add(MyTunes allSongs) throws SQLException
            //adds songs to the allSongs playlist
    {
        mtdal.add(allSongs);
    }
    

    public List<MyTunes> getallSong() 
    {
        return mtdal.getAllSong();
    }

    public void remove(MyTunes selectedMyTunes) 
    {
        mtdal.remove(selectedMyTunes);
    }
    
    public void add(Playlist playlist) throws SQLException
    {
        mtdal.addPlaylist(playlist);
    }

    public  List<Playlist> getallPlaylist() {
       return mtdal.getAllPlaylist();
    }

    public void remove(Playlist playlistSongs) {
      mtdal.removePlaylist(playlistSongs);
    }

    
    
   
}

