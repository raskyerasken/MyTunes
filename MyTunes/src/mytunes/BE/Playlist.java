/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
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
    private final IntegerProperty songNumbers= new SimpleIntegerProperty();
    private final FloatProperty playlistTime= new SimpleFloatProperty();
        public int getsongNumbers()
    {
        return songNumbers.get();
    }
    
    public void setsongNumbers(int songNumbers)
    {
        this.songNumbers.set(songNumbers);
    }
    
    public IntegerProperty songNumbersProperty()
    {
        return songNumbers;
    }
    
     public float getplaylistTime()
    {
        return playlistTime.get();
    }
    
    public void setplaylistTime(float playlistTime)
    {
        this.playlistTime.set(playlistTime);
    }
    
    public FloatProperty playlistTimeProperty()
    {
        return playlistTime;
    }
    
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

    @Override
    public String toString() {
        return "Playlist{" + "ID=" + ID + ", playlistName=" + playlistName + ", songNumbers=" + songNumbers + ", playlistTime=" + playlistTime + '}';
    }
    
    
}
