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
import mytunes.BE.SongIDPlaylistID;


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
                    = "SELECT * FROM myTunes3 "
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
                m.setSongLength(rs.getFloat("songLength"));
                
                allSongs.add(m);
            }
        }
        return allSongs;
    }
    
    
    public void remove(MyTunes selectedMyTunes)
            
    {
        try (Connection con = cm.getConnection()) {
        String sql = "DELETE FROM myTunes3 WHERE Name=?";
        
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
                = "INSERT INTO myTunes3"
                + "(name, album, year, path, songLength, artist) "
                + "VALUES(?,?,?,?,?,?)";
           PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, allSongs.getSongName());
            pstmt.setString(2, allSongs.getAlbum());
            pstmt.setInt(3, allSongs.getYear());
            pstmt.setString(4, allSongs.getPath());
            pstmt.setFloat(5,  allSongs.getSongLength());
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
                    = con.prepareStatement("SELECT * FROM myTunes3");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                MyTunes s = new MyTunes();
                s.setSongName(rs.getString("Name"));
                s.setAlbum(rs.getString("Album"));
                s.setArtist(rs.getString("Artist"));
                s.setYear(rs.getInt("Year"));
                s.setId(rs.getInt("id"));
                s.setSongLength(rs.getFloat("songLength"));
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
                    = con.prepareStatement("SELECT * FROM Playlist1");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                Playlist p = new Playlist();
               
                p.setplaylistName(rs.getString("PlaylistName"));
                p.setID(rs.getInt("ID"));
               

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
        String sql = "DELETE FROM Playlist1 WHERE playlistName=?";
        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,playlistSongs.getplaylistName());
        pstmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(myTunesDAL.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    public void addPlaylist(Playlist playlist) {
         try (Connection con = cm.getConnection())  {

        String sql 
                = "INSERT INTO Playlist1"
                + "(playlistName ) "
                + "VALUES(?)";
           PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, playlist.getplaylistName());
           
            
            
            int affected = pstmt.executeUpdate();
            if (affected<1){
                    throw new SQLException("Song could not be added");}
            
            
              ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                playlist.setID(rs.getInt(1));
           }
                 }
    catch (SQLException ex) {
        Logger.getLogger(myTunesDAL.class.getName()).log(Level.SEVERE, null, ex);
    }     
    
    }
      public void addSongToPlaylist(SongIDPlaylistID ID) {
       try (Connection con = cm.getConnection())  {
          System.out.println(ID.getIDSong());
        String sql 
                = "INSERT INTO songOnPlaylist"
                + "(songID, playlistID ) "
                + "VALUES(?,?)";
           PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           pstmt.setInt(1, ID.getIDSong());
            pstmt.setInt(2, ID.getIDPlaylist());
           
            
            
            int affected = pstmt.executeUpdate();
            if (affected<1){
                    throw new SQLException("Song could not be added");}
            
                 }
    catch (SQLException ex) {
        Logger.getLogger(myTunesDAL.class.getName()).log(Level.SEVERE, null, ex);
    }     
    }
       public void removeSongPlaylist(SongIDPlaylistID SongPlaylist) {
         try (Connection con = cm.getConnection()) {
        String sql = "DELETE FROM songOnPlaylist WHERE SongID=? AND playlistID=?";
       
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,SongPlaylist.getIDSong());
        pstmt.setInt(2,SongPlaylist.getIDPlaylist());
        pstmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(myTunesDAL.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

   

    public List<SongIDPlaylistID> getSelectedPlaylist(int playlistID) throws SQLServerException, SQLException {
         List<SongIDPlaylistID> SongOnPlaylist = new ArrayList();
        
        try (Connection con = cm.getConnection())
        {
                 
            String query 
                    = "SELECT * FROM songOnPlaylist "
                    + "WHERE playlistID LIKE ?";
          
            PreparedStatement pstmt
                    = con.prepareStatement(query);
            pstmt.setInt(1, playlistID);
           ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                SongIDPlaylistID sOP = new SongIDPlaylistID();
               sOP.setIDSong(rs.getInt("songID"));
                sOP.setIDPlaylist(rs.getInt("PlaylistID"));
               
                
                 SongOnPlaylist.add(sOP);
            }
        }
        return  SongOnPlaylist;
    }

    public void update(MyTunes myTunes) {
     try (Connection con = cm.getConnection()) {
            String sql
                    = "UPDATE myTunes3 SET "
                    + "name=?, album=?, year=?, path=?, songLength=?, artist=? "
                    + "WHERE id=?";
            
           
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setString(1, myTunes.getSongName());
            pstmt.setString(2, myTunes.getAlbum());
            pstmt.setInt(3, myTunes.getYear());
            pstmt.setString(4, myTunes.getPath());
            pstmt.setFloat(5, myTunes.getSongLength());
            pstmt.setString(6, myTunes.getArtist());
            pstmt.setInt(7, myTunes.getId());

            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("Prisoner could not be updated");

        }
        catch (SQLException ex) {
            Logger.getLogger(myTunesDAL.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
      
}
    
  
    
    
 

