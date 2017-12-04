/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BLL;

import java.sql.SQLException;
import java.util.List;
import mytunes.BE.MyTunes;
import mytunes.DAL.myTunesDAL;


/**
 *
 * @author mr.Andersen
 */
public class BLLManager 
{
    myTunesDAL mtdal = new myTunesDAL();
     
    
    public List<MyTunes> getAllSongsByPlaylist(String songName, String Artist, String Album, int Year) throws SQLException
    {
        return mtdal.getAllSongsByPlaylist(songName, Artist, Album, 0);
    }
    
    
    public void add(MyTunes allSongs) throws SQLException
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
    
    
    
    
   
}

