/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author mr.Andersen
 */
public class MyTunes {
    List<MyTunes> allSongs = new ArrayList();
    
    private final IntegerProperty id= new SimpleIntegerProperty();
    private final StringProperty songName =  new SimpleStringProperty();
    private final StringProperty artist= new SimpleStringProperty();
    private final StringProperty album =  new SimpleStringProperty();
    private final IntegerProperty year= new SimpleIntegerProperty();
    private final StringProperty path =  new SimpleStringProperty();
    private final IntegerProperty songLength= new SimpleIntegerProperty();
    
  
 public int getSongLength()
    {
        return songLength.get();
    }
    
    public void setSongLength(int songLength)
    {
        
        this.songLength.set(songLength);
    }
    public IntegerProperty songLengthProperty(){
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
    public IntegerProperty idProperty(){
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
     public StringProperty ArtistProperty(){
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
    public StringProperty albumProperty(){
    return album;
    }
    
    public int getYear()
    {
        return year.get();
    }
    
    public void setYear(int Year){
        this.year.set(Year);
           
    }
    public IntegerProperty yearProperty(){
    return year;
    }
    
    public String getPath() {
        return path.get();
    }
    
    public void setPath(String path) {
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
        


 
    
   
    
    @Override
    public String toString() {
        return "myTunes{" + "songName=" + songName + ", artist=" + artist + ", album=" + album + ", year=" + year + ",path="+path+'}';
    }

    
    
}
