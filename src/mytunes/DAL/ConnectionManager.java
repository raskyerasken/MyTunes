/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.DAL;



import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author kasper
// */
public class ConnectionManager {
    private final SQLServerDataSource ds = new SQLServerDataSource();
    public ConnectionManager() {
        ds.setDatabaseName("CS2017B_17_bestTunes");
        ds.setUser("CS2017B_17_java");
        ds.setPassword("javajava");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
 }
    
    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
}

