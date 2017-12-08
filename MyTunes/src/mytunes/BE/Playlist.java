/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jacob
 */
public class Playlist 
{
    private final IntegerProperty ID= new SimpleIntegerProperty();
    private final StringProperty playlistName =  new SimpleStringProperty();
    private ArrayList<Song> songList;
    
    public String getplaylistName()
    {
        return playlistName.get();
    }
    
    public void setplaylistName(String playlistName)
    {
        this.playlistName.set(playlistName);
    }
    
    public StringProperty playlistNameProperty()
    {
        return playlistName;
    }
    
    public int getID()
    {
        return ID.get();
    }
    
    public void setID(int ID)
    {
        this.ID.set(ID);
    }
    
    public IntegerProperty IDProperty()
    {
        return ID;
    }
    
    public ArrayList<Song> getSongList()
    {
        return songList;
    }
}
