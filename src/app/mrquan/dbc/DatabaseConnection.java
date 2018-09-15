package app.mrquan.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://47.94.13.255:3306/quan?useUnicode=true&amp;characterEncoding=utf-8";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "quan";
    private Connection connection = null;

    public DatabaseConnection(){
        try {
            Class.forName(DBDRIVER);
            this.connection = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
