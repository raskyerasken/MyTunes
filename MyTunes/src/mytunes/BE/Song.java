/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import java.io.Serializable;
import java.util.UUID;


/**
 *
 * @author kasper
 */
public class Song implements Serializable{

    private String songName;
    private String artist;
    private String genre;
    private final String duration;
    private int rating;
    private final String path;
    private final String id;
    
    
    public Song(String songName, String artist, String genre, String duration, String path)
    {
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.path = path;
        this.id = UUID.randomUUID().toString();
    }
    
    
    public String getPath()
    {
        return path;
    }
  
    
    public String getArtist()
    {
        return artist;
    }
    
    public String getId()
    {
        return id;
    }
    
    public String getSongName()
    {
        return songName;
    }
    
    public String getGenre()
    {
        return genre;
       
    }
    
    public String getDuration()
    {
        return duration;
    }
    
    public void setSongName(String songName)
    {
        this.songName = songName;
    }
    
    public void setArtist(String artist)
    {
        this.artist = artist;
        
    }
    
    public void setGenre(String genre)
    {
        this.genre = genre;
    }
    
   
}