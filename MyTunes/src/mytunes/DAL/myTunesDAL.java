/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import mytunes.BE.myTunes;
import mytunes.BLL.BLLManager;


/**
 *
 * @author mr.Andersen
 */

            
public class myTunesDAL {
    private ConnectionManager cm = new ConnectionManager();

    public List<myTunes> getAllSongsByPlaylist(String songName, String Artist, String Album, int Year) throws SQLServerException, SQLException
    {
       List<myTunes> allSongs = new ArrayList();
        
        try (Connection con = cm.getConnection())
        {
            // No good when having userinput, because SQL injection
            //Statement stmt = con.createStatement();
            
            String query 
                    = "SELECT * FROM bestTunesTable "
                    + "WHERE Name LIKE ? ";
            
            PreparedStatement pstmt
                    = con.prepareStatement(query);
            pstmt.setString(1, "%" + songName + "%");
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                myTunes m = new myTunes();
                m.setSongName(rs.getString("Name"));
                m.setAlbum(rs.getString("Album"));
                m.setArtist(rs.getString("Artist"));
                m.setYear(rs.getInt("Year"));
                
                allSongs.add(m);
            }
        }
        return allSongs;
    }
    
    
    public void remove(myTunes selectedSong)
    {
        try (Connection con = cm.getConnection()) {
        String sql = "DELETE FROM Songs WHERE id=?";
        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, selectedSong.getSongName());
        pstmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(myTunesDAL.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void add (myTunes allSongs) throws SQLException
    {
                 try (Connection con = cm.getConnection())  {

        String sql 
                = "INSERT INTO Songs"
                + "(songName, artist, album, year)"
                + "VALUES(?, ?, ?, ?)";
           PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           
            pstmt.setString(1, allSongs.getSongName());
            pstmt.setString(2, allSongs.getArtist());
            pstmt.setString(3, allSongs.getAlbum());
            pstmt.setInt(4, allSongs.getYear());
            
            int affected = pstmt.executeUpdate();
            if (affected<1)
                    throw new SQLException("Song could not be added");
           
                 }
    catch (SQLException ex) {
        Logger.getLogger(myTunesDAL.class.getName()).log(Level.SEVERE, null, ex);
    }     
  }

    public List<myTunes> getAllSong() {
      
        List<myTunes> allSong
                = new ArrayList();

        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM bestTunesTable");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                myTunes s = new myTunes();
                s.setSongName(rs.getString("Name"));
                s.setAlbum(rs.getString("Album"));
                s.setArtist(rs.getString("Artist"));
                s.setYear(rs.getInt("Year"));
                                

                allSong.add(s);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(myTunesDAL.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allSong;
    
    }
}
    
  
    
    
 

