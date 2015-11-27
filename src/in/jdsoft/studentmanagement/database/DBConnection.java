package in.jdsoft.studentmanagement.database;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    
    public String sDriverName=null;
    public String sServerName=null;
    public String sPort=null;
    public String sDatabaseName=null;
    public String sUserName=null;
    public String sPassword=null;

    
    
    public String getSDriverName() {
        return sDriverName;
    }

    public void setSDriverName(String driverName) {
        sDriverName = driverName;
    }

    public String getSServerName() {
        return sServerName;
    }

    public void setSServerName(String serverName) {
        sServerName = serverName;
    }

    public String getSPort() {
        return sPort;
    }

    public void setSPort(String port) {
        sPort = port;
    }

    public String getSDatabaseName() {
        return sDatabaseName;
    }

    public void setSDatabaseName(String databaseName) {
        sDatabaseName = databaseName;
    }

    public String getSUserName() {
        return sUserName;
    }

    public void setSUserName(String userName) {
        sUserName = userName;
    }

    public String getSPassword() {
        return sPassword;
    }

    public void setSPassword(String password) {
        sPassword = password;
    }



    public Connection getDBConnection() throws Exception{
        Connection conn = null;
        Class.forName(sDriverName).newInstance();
        
        String sURL ="jdbc:mysql://"+sServerName+":"+sPort+"/"+sDatabaseName+"";
        
        conn = DriverManager.getConnection(sURL,sUserName, sPassword);
      return conn;
    }
}
