/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.BE.MyTunes;
import mytunes.BLL.BLLManager;

/**
 *
 * @author mr.Andersen
 */
public class MyTunesModel 
{  
    private BLLManager bllManager = new BLLManager();

     private ObservableList<MyTunes> songList
            = FXCollections.observableArrayList();

    List<MyTunes> getAllSong() {
        songList.addAll(bllManager.getallSong());
        return songList;
    }
    List<MyTunes> updateAllSong()
    {
    return songList;
    }
    public void add (MyTunes mytunes) throws SQLException
    {   
        bllManager.add(mytunes);
        songList.add(mytunes);
        
    
    }

    public void remove(MyTunes selectedMyTunes) {
       
    bllManager.remove(selectedMyTunes);
        songList.remove(selectedMyTunes);
   
    
     }}
    
    
   

    
    
    
   
     