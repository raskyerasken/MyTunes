/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BLL;

import java.sql.SQLException;
import java.util.List;
import mytunes.BE.MyTunes;
import mytunes.BE.Playlist;
import mytunes.BE.SongIDPlaylistID;
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

    
    public void addSongToPlaylist(SongIDPlaylistID ID ) {
         mtdal.addSongToPlaylist(ID);
    }

    public void removeSongToPlaylist(SongIDPlaylistID SongPlaylist) {
      mtdal.removeSongPlaylist(SongPlaylist);
    }

    public List<SongIDPlaylistID> getSelectedPlaylist(int playlistID) throws SQLException {
   return mtdal.getSelectedPlaylist(playlistID);
           }

    public void update(MyTunes myTunes) {
    mtdal.update(myTunes);
    }
   
}

