/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.BE.MyTunes;
import mytunes.BE.Playlist;
/**
 *
 * @author jacob
 */
public class PlaylistDAL {
     private ConnectionManagerMyTunes cm = new ConnectionManagerMyTunes();

    
    public void remove(MyTunes selectedMyTunes)
    {
        try (Connection con = cm.getConnection()) {
        String sql = "DELETE FROM myTunes WHERE Name=?";
        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,selectedMyTunes.getSongName());
        pstmt.execute();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(myTunesDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void add (Playlist playlist) throws SQLException
    {
        try (Connection con = cm.getConnection())  
        {
            String sql 
                = "INSERT INTO Playlist"
                + "(Playlist,SongID) "
                + "VALUES(?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, playlist.getplaylistName());
            pstmt.setInt(2, playlist.getSongID());
            double affected = pstmt.executeUpdate();
            if (affected<1)
            {
                    throw new SQLException("Playlist could not be added");
            }
            
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(myTunesDAL.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    public List<MyTunes> getAllSong() 
    {
      
        List<MyTunes> allSong
                = new ArrayList();

        try (Connection con = cm.getConnection()) 
        {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM myTunes");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                MyTunes s = new MyTunes();
                s.setSongName(rs.getString("Name"));
                s.setAlbum(rs.getString("Album"));
                s.setArtist(rs.getString("Artist"));
                s.setYear(rs.getInt("Year"));
                s.setId(rs.getInt("id"));
                s.setSongLength(rs.getDouble("songLength"));
                s.setPath(rs.getString("path"));

                allSong.add(s);
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(myTunesDAL.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allSong;
    }
}
