/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author jacob
 */
public class SongIDPlaylistID {
    private final IntegerProperty IDSong= new SimpleIntegerProperty();
    private final IntegerProperty IDPlaylist= new SimpleIntegerProperty();
       
    public int getIDPlaylist()
    {
        return IDPlaylist.get();
    }
    
    public void setIDPlaylist(int IDPlaylist)
    {
        this.IDPlaylist.set(IDPlaylist);
    }
    
    public IntegerProperty IDPlaylistProperty()
    {
        return IDPlaylist;
    }
        public int getIDSong()
    {
        return IDSong.get();
    }
    
    public void setIDSong(int IDSong)
    {
        this.IDSong.set(IDSong);
    }
    
    public IntegerProperty IDSongProperty()
    {
        return IDSong;
    }
}
