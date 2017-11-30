/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.sql.SQLException;
import java.util.List;
import java.util.prefs.Preferences;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import mytunes.BE.myTunes;
import mytunes.BLL.BLLManager;

/**
 *
 * @author mr.Andersen
 */
public class MyTunesModel 
{  
    private BLLManager bllManager = new BLLManager();

     private ObservableList<myTunes> songList
            = FXCollections.observableArrayList();

    List<myTunes> getAllSong() {
        songList.addAll(bllManager.getallSong());
        return songList;
    }
    
    
   
    
     }
    
    
   

    
    
    
   
     