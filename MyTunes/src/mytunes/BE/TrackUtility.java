/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.BE;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JFileChooser;

/**
 *
 * @author kasper
 */
public class TrackUtility {
    
    
    String  title ;
    String artist;
    String album;
    String year ; 
    String URLAdressSong;
    int songLength;
    /**
     * Get all tracklists stored in user preferences
     *
     * @param trackList
     * @param trackTable
     * @return List
     */
   private void choseFile() throws UnsupportedTagException, InvalidDataException, IOException, NotSupportedException {
       JFileChooser chooser = new JFileChooser();
chooser.setCurrentDirectory(new java.io.File("."));
chooser.setDialogTitle("choosertitle");



if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
 
  System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
} else {
  System.out.println("No Selection ");
}
        URLAdressSong=""+chooser.getSelectedFile();
        Mp3File mp3file = new Mp3File(chooser.getSelectedFile());
songLength=(int) mp3file.getLengthInSeconds();
        System.out.println("Length of this mp3 is: " + mp3file.getLengthInSeconds() + " seconds");
      
        System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
       
      if (mp3file.hasId3v2Tag()) {
        	ID3v2 id3v2Tag = mp3file.getId3v2Tag();
        	artist= id3v2Tag.getArtist();
                title = id3v2Tag.getTitle();
                album = id3v2Tag.getAlbum();
                year = id3v2Tag.getYear();
           }
        
        if (mp3file.hasId3v2Tag()) {
        	ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            byte[] imageData = id3v2Tag.getAlbumImage();
            if (imageData != null) {
				String mimeType = id3v2Tag.getAlbumImageMimeType();
				System.out.println("Mime type: " + mimeType);
				
				RandomAccessFile file = new RandomAccessFile("album-artwork", "rw");
				file.write(imageData);
				file.close();
            }
        }
	}

    public MyTunes getdata() throws UnsupportedTagException, InvalidDataException, IOException, NotSupportedException {
        choseFile();
        MyTunes song = new MyTunes();
        if (year != null)
        {
               int yearr= Integer.parseInt(year);   
               song.setYear(yearr);
        }
        
        
        song.setSongLength(songLength);
        song.setAlbum(album);
        song.setArtist(artist);
        song.setPath(URLAdressSong);
        song.setSongName(title);
        
        
        return song;
    
       
    }

    @Override
    public String toString() {
        return "TrackUtility{" + "title=" + title + ", artist=" + artist + ", album=" + album + ", year=" + year +",URLAdressSong"+URLAdressSong+ '}';
    }
}
    

