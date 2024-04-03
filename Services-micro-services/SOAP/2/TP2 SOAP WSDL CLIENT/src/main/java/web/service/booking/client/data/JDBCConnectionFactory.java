package web.service.booking.client.data;


import java.sql.*;

public class JDBCConnectionFactory {

   final String DB_HOST_CONNECTION_URL= "jdbc:mysql://localhost:3306/";
    static final String driverClassName = "com.mysql.jdbc.Driver";
     String DB_USER;
      String DB_PASSWORD;
    private String DB_NAME;
    private String DB_CONNECTION_URL;
    private Connection DB_CONNECTION;


    public JDBCConnectionFactory() {
    }

    public JDBCConnectionFactory(String DB_NAME, String DB_USER, String DB_PASSWORD, boolean connect) {
        this.DB_NAME = DB_NAME.toUpperCase().trim();

        // attention ici, risque d'ecrasement

if (!connect) {
    String sqlCreateDB = "CREATE DATABASE IF NOT EXISTS "+this.DB_NAME;
    String sqlCreateServices = "CREATE TABLE IF NOT EXISTS SERVICES (\n" +
            "    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    agencyName VARCHAR(255) NOT NULL,\n" +
            "    city VARCHAR(255) NOT NULL\n" +
            ");";
    System.out.println(sqlCreateDB);
//        System.out.println("User cred: "+DB_USER);
//        System.out.println("Password cred: "+DB_PASSWORD);
//        System.out.println("DB name: "+this.DB_NAME);
    try {
        Connection conn = DriverManager.getConnection(DB_HOST_CONNECTION_URL, DB_USER, DB_PASSWORD );
        PreparedStatement stmt = conn.prepareStatement(sqlCreateDB);
        stmt.execute();
        conn = DriverManager.getConnection(this.DB_HOST_CONNECTION_URL+"AGENCYDB", DB_USER, DB_PASSWORD );
        stmt = conn.prepareStatement(sqlCreateServices);
        stmt.execute();
        conn.close();
    }
    catch (SQLException e) {
        System.out.println("JDBC try get Host connection");
    }
    try {
        this.DB_CONNECTION_URL = this.DB_HOST_CONNECTION_URL + this.DB_NAME;
        this.DB_CONNECTION = DriverManager.getConnection(this.DB_CONNECTION_URL, DB_USER, DB_PASSWORD );
        System.out.println("DB Connection: "+this.DB_CONNECTION);
    } catch (SQLException e) {
        System.out.println("JDBC try set DB connection after creation");
    }

}
       else {
    try {
        this.DB_CONNECTION_URL = this.DB_HOST_CONNECTION_URL + this.DB_NAME;
        this.DB_CONNECTION = DriverManager.getConnection(this.DB_CONNECTION_URL, DB_USER, DB_PASSWORD );
        System.out.println("DB Connection: "+this.DB_CONNECTION);
    } catch (SQLException e) {
        System.out.println("JDBC try set DB connection");
    }
}


//        String sqlCreateServicesDB = "CREATE DATABASE IF NOT EXISTS AGENCYDB;";
//        String sqlCreateServices = "CREATE TABLE IF NOT EXISTS SERVICES (\n" +
//                "    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
//                "    agencyName VARCHAR(255) NOT NULL,\n" +
//                "    city VARCHAR(255) NOT NULL\n" +
//                ");";
//        try {
//            Connection conn = DriverManager.getConnection(this.DB_HOST_CONNECTION_URL, DB_USER, DB_PASSWORD );
//            PreparedStatement stmt = conn.prepareStatement(sqlCreateServicesDB);
//            stmt.execute();
//            conn = DriverManager.getConnection(this.DB_HOST_CONNECTION_URL+"AGENCYDB", DB_USER, DB_PASSWORD );
//            stmt = conn.prepareStatement(sqlCreateServices);
//            stmt.execute();
//            conn.close();
//        } catch (SQLException e) {
//            System.out.println("JDBC try To create AGENCYDB");
//        }

    }


    private static JDBCConnectionFactory jdbcConnectionFactoryInit=null;
    private static JDBCConnectionFactory jdbcConnectionFactoryDB=null;




    public Connection getConnectionDB() throws SQLException {
        Connection connection = this.DB_CONNECTION;
        return connection;
    }
    public Connection getConnectionDB(String DB_NAME, String DB_USER, String DB_PASSWORD) throws SQLException {
        Connection connection = DriverManager.getConnection(this.DB_HOST_CONNECTION_URL+DB_NAME, DB_USER, DB_PASSWORD );;
        return connection;
    }



    public boolean destroyDB() {
        String sql = "DROP DATABASE  "+this.DB_NAME+";";
        try ( PreparedStatement stmt = this.DB_CONNECTION.prepareStatement(sql);) {
            stmt.execute();
            this.DB_CONNECTION.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean destroyDB(String DB_NAME, String DB_USER, String DB_PASSWORD) {
        String sql = "DROP DATABASE  "+DB_NAME+";";
        try (Connection conn = DriverManager.getConnection(this.DB_HOST_CONNECTION_URL, DB_USER, DB_PASSWORD );
             PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}





//    public boolean executeSQL(String DB_NAME, String DB_USER, String DB_PASSWORD, String sqlCommand) {
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(this.DB_HOST_CONNECTION_URL+DB_NAME, DB_USER, DB_PASSWORD );
//            PreparedStatement stmt = conn.prepareStatement(sqlCommand);
//            stmt.execute();
//            conn.close();
//            return true;
//        } catch (SQLException e) {
////            throw new RuntimeException(e);
//            return false;
//        }
//
//    }

//        this.DB_USER = DB_USER;
//        this.DB_PASSWORD = DB_PASSWORD;

//    public Connection getConnectionHost() throws SQLException {
//        Connection connection = null;
//        connection = (Connection) DriverManager.getConnection(DB_HOST_CONNECTION_URL, DB_USER, DB_PASSWORD);
//        return connection;
//    }

//    public JDBCConnectionFactory(String DB_NAME) {
//        this.DB_NAME = DB_NAME;
//        this.DB_CONNECTION_URL = this.DB_HOST_CONNECTION_URL + this.DB_NAME.trim();
//        try ( Connection conn = DriverManager.getConnection(DB_HOST_CONNECTION_URL, DB_USER, DB_PASSWORD );
//              )
//        {
////            stmt.execute();
////            conn.close();
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

//public static JDBCConnectionFactory getConnectionInstanceInit() {
//        if (jdbcConnectionFactoryInit == null) {
//            jdbcConnectionFactoryInit= new JDBCConnectionFactory();
//        }
//        return jdbcConnectionFactoryInit;
//}

//    public JDBCConnectionFactory getConnectionInstanceDB() {
//        if (jdbcConnectionFactoryDB == null) {
//            jdbcConnectionFactoryDB= new JDBCConnectionFactory(this.DB_CONNECTION_URL);
//        }
//        return jdbcConnectionFactoryDB;
//    }