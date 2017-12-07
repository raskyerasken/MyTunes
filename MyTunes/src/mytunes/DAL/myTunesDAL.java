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
 * @author mr.Andersen
 */

            
public class myTunesDAL {
    private ConnectionManagerMyTunes cm = new ConnectionManagerMyTunes();

    public List<MyTunes> getAllSongsByPlaylist(String search) throws SQLServerException, SQLException
    {
       List<MyTunes> allSongs = new ArrayList();
        
        try (Connection con = cm.getConnection())
        {
            // No good when having userinput, because SQL injection
            //Statement stmt = con.createStatement();
            
            String query 
                    = "SELECT * FROM myTunes "
                    + "WHERE name LIKE ?  OR artist LIKE ?";
          
            PreparedStatement pstmt
                    = con.prepareStatement(query);
            pstmt.setString(1, "%" + search + "%");
            pstmt.setString(2, "%" + search + "%");
           ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                MyTunes m = new MyTunes();
                m.setSongName(rs.getString("Name"));
                m.setAlbum(rs.getString("Album"));
                m.setArtist(rs.getString("Artist"));
                m.setYear(rs.getInt("Year"));
                m.setId(rs.getInt("id"));
                m.setPath(rs.getString("path"));
                m.setSongLength(rs.getDouble("songLength"));
                
                allSongs.add(m);
            }
        }
        return allSongs;
    }
    
    
    public void remove(MyTunes selectedMyTunes)
    {
        try (Connection con = cm.getConnection()) {
        String sql = "DELETE FROM myTunes WHERE Name=?";
        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,selectedMyTunes.getSongName());
        pstmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(myTunesDAL.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void add (MyTunes allSongs) throws SQLException
    {
                 try (Connection con = cm.getConnection())  {

        String sql 
                = "INSERT INTO myTunes"
                + "(name, album, year, path, songLength, artist) "
                + "VALUES(?,?,?,?,?,?)";
           PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, allSongs.getSongName());
            pstmt.setString(2, allSongs.getAlbum());
            pstmt.setInt(3, allSongs.getYear());
            pstmt.setString(4, allSongs.getPath());
            pstmt.setDouble(5,allSongs.getSongLength());
            pstmt.setString(6, allSongs.getArtist());
            
            int affected = pstmt.executeUpdate();
            if (affected<1){
                    throw new SQLException("Song could not be added");}
            
            
              ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                allSongs.setId(rs.getInt(1));
           }
                 }
    catch (SQLException ex) {
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

    public List<Playlist> getAllPlaylist() {
        
        List<Playlist> playlist
                = new ArrayList();

        try (Connection con = cm.getConnection()) 
        {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM Playlist");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                Playlist p = new Playlist();
               
                p.setplaylistName(rs.getString("Playlist"));
                p.setSongID(rs.getInt("SongID"));
               

                 playlist.add(p);
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(myTunesDAL.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return  playlist;
    
    }

    public void removePlaylist(Playlist playlistSongs) {
     try (Connection con = cm.getConnection()) {
        String sql = "DELETE FROM Playlist WHERE Playlist=?";
        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,playlistSongs.getplaylistName());
        pstmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(myTunesDAL.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
}
    
  
    
    
 

