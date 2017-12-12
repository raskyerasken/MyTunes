/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import static sun.audio.AudioPlayer.player;


/**
 *
 * @author mr.Andersen
 */
public class MyTunes 
{
    List<MyTunes> allSongs = new ArrayList();
    private MyTunes song;
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty songName =  new SimpleStringProperty();
    private final StringProperty artist = new SimpleStringProperty();
    private final StringProperty album =  new SimpleStringProperty();
    private final IntegerProperty year = new SimpleIntegerProperty();
    private final StringProperty path =  new SimpleStringProperty();
    private final FloatProperty songLength= new SimpleFloatProperty();
 
    private StringProperty genre = new SimpleStringProperty();

    public MyTunes() {
        this.song = song;
    }
   

  
    public float getSongLength()
    {
        return songLength.get();
    }
    
    public void setSongLength(float songLength)
    {
        this.songLength.set(songLength);
    }
    
    public FloatProperty songLengthProperty()
    {
        return songLength;
    }

    public int getId()
    {
        return id.get();
    }
    
    public void setId(int id)
    {

        this.id.set(id);
    }
    
    public IntegerProperty idProperty()
    {
        return id;
    }
    
    public String getSongName()
    {
        return songName.get();
    }
    
    public String getArtist()
    {
        return artist.get();
    }
    
    public void setArtist(String artist)
    {
        this.artist.set(artist);
    }   
     
    public StringProperty ArtistProperty()
    {
        return artist;
    }
    
    public String getAlbum()
    {
        return album.get();
    }
    
    public void setAlbum(String album)
    {
        this.album.set(album);
    }
    
    public StringProperty albumProperty()
    {
        return album;
    }
    
    public int getYear()
    {
        return year.get();
    }
    
    public void setYear(int Year)
    {
        this.year.set(Year);
    }
    
    public IntegerProperty yearProperty()
    {
        return year;
    }
    
    public String getPath() 
    {
        return path.get();
    }
    
    public void setPath(String path) 
    {
        this.path.set(path);
    }
    
    public StringProperty pathProperty()
    {
        return path;
    }
    
    public void setSongName(String songName)
    {
        this.songName.set(songName);
    }
 
    
    public StringProperty getGenre()
    {
        return genre;
       
    }
 
   
     
    
    public void setGenre(String genre)
    {
        this.genre.set(genre);
    }
    
    
    public StringProperty genreProperty()
    {
        return genre;
    }
    
     
    
    
    @Override
    public String toString() 
    {
        return "myTunes{" + "songName=" + songName + ", artist=" + artist + ", album=" + album + ", year=" + year + ",path="+path+'}';
    }
}
