/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jacob
 */
public class Playlist {
     private final IntegerProperty SongID= new SimpleIntegerProperty();
    private final StringProperty playlistName =  new SimpleStringProperty();
    
    public String getplaylistName()
    {
        return playlistName.get();
    }
    
    public void setplaylistName(String playlistName)
    {
        
        this.playlistName.set(playlistName);
    }
    
    public StringProperty playlistNameProperty(){
    return playlistName;
    }
    

    public int getSongID()
    {
        return SongID.get();
    }
    
    public void setSongID(int SongID)
    {
        
        this.SongID.set(SongID);
    }
    public IntegerProperty SongIDProperty(){
    return SongID;
    }
}
