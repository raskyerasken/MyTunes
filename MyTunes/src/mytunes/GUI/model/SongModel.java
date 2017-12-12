/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.BE.MyTunes;
import mytunes.BE.Song;
import mytunes.DAL.SongDAO;

/**
 *
 * @author kasper
 */
public class SongModel {
    
    private MyTunes contextSong;
    
    private SongDAO songDAO;
    
    private static SongModel instance;
        
    ObservableList<MyTunes> songs = FXCollections.observableArrayList();
    
    public static SongModel getInstance()
    {
        if (instance == null)
        {
            instance = new SongModel();
            
        }
        return instance;
    }
    
    private SongModel() 
    {
        songDAO = new SongDAO();
    }
    
    
    public void addSong(MyTunes myTunes)
    {
        songs.add(myTunes);
        
    }
    
    
    public void editSong(MyTunes contextSong)
    {
        for (int i = 0; i < songs.size(); i++)
        {
            MyTunes song = songs.get(i);
            if (song.getId() == contextSong.getId())
            {
                song.setSongName(contextSong.getSongName());
                song.setArtist(contextSong.getArtist());
                song.setGenre(contextSong.getArtist());
                
                songs.set(i, song);
            }
        }
    }
    
    public ObservableList<MyTunes> getSongs()
    {
        return songs;
    }
    
    public MyTunes getContextSong()
    {
        return contextSong;
    }
    
    public void setContextSong(MyTunes contextSong)
    {
        this.contextSong = contextSong;
    }
    
//    public void saveSongDataOffline() throws IOException 
//    {
//        ArrayList<Song> songsToSave = new ArrayList<>();
//        for (MyTunes song : songs)
//        {
//            songsToSave.add(song);
//        }
//        songDAO.writeObjectData(songsToSave, "SongsData.dat");
//    }
//    
//    public void loadSongDataOffline() throws FileNotFoundException 
//    {
//        songs.clear();
//        songs.addAll(songDAO.readObjectData("SongsData.dat"));
//    }
    
    
    
}
