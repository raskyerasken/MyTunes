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
import javafx.geometry.Side;


/**
 *
 * @author mr.Andersen
 */
public class myTunes {
    List<myTunes> allSongs = new ArrayList();

    private final StringProperty  songName =  new SimpleStringProperty();
    private final StringProperty artist= new SimpleStringProperty();
    private final StringProperty album =  new SimpleStringProperty();
    private final IntegerProperty year= new SimpleIntegerProperty();
    private final StringProperty path =  new SimpleStringProperty();
    

   /* public myTunes(String songName, String artist, String album, int year, String path) {
       this.songName= new SimpleStringProperty();
       this.artist = new SimpleStringProperty(); 
       this.album = new SimpleStringProperty();
       this.year = new SimpleIntegerProperty();
      this.path = new SimpleStringProperty();
        
        this.songName.set(songName);
        this.artist.set(artist);
        this.album.set(album);
        this.year.set(year);
        this.path.set(path);
    }*/

    

    public String getSongName()
    {
        return songName.get();
    }
    
    public void setSongName(String songName)
    {
        
        this.songName.set(songName);
    }
    public StringProperty songNameProperty(){
    return songName;
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
    public StringProperty pathProperty(){
    return path;
    }
        


 
    
   
    
    @Override
    public String toString() {
        return "myTunes{" + "songName=" + songName + ", artist=" + artist + ", album=" + album + ", year=" + year + ",path="+path+'}';
    }

    
    
}
