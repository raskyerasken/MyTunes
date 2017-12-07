/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BLL;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import mytunes.BE.MyTunes;
import mytunes.BE.Song;

/**
 *
 * @author kasper
 */
public class SongManager 
{
    
    private MyTunes song;
    private MediaPlayer player;

    
     public void playSong(MyTunes song, boolean overwrite) 
     {
         
        pauseSong();
        if (this.song != song || overwrite)
        {
            this.song=song;
            File soundFile = new File(this.song.getPath());
            Media media = new Media(soundFile.toURI().toString());
            player = new MediaPlayer(media);
        }
        
        player.play();
    }
    
    public void pauseSong()
    {
        if (song != null)
        {
            player.pause();
        }
    }
    
    public MyTunes getCurrentlyPlayingSong()
    {
        return this.song;
    }
    
    public javafx.util.Duration getSongLength()
    {
        return player.getTotalDuration();
        
    }
    
    public javafx.util.Duration getSongTimeElapsed()
    {
        return player.getCurrentTime();
    }
    
    public MediaPlayer getMediaPlayer()
    {
        return player;
    }
    
    public void adjustVolume (double value)
    {
        player.setVolume(value);
    }
    
}
