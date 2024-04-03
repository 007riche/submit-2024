package web.service.booking.data;


import web.service.booking.models.Booking;
import web.service.booking.models.Hotel;

import java.sql.*;

public class JDBCConnectionFactory {

   final String DB_HOST_CONNECTION_URL=    "jdbc:mysql://localhost:3306/";   //"jdbc:mysql://localhost:3306/publisedrestservices";
    static final String driverClassName =  "com.mysql.jdbc.Driver"; // ;  "com.mysql.cj.jdbc.Driver"
     String DB_USER;
      String DB_PASSWORD;
    private String DB_NAME;
    private String DB_CONNECTION_URL;
    private Connection DB_CONNECTION;


    public JDBCConnectionFactory() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load the MySQL JDBC driver", e);
        }
    }

    public JDBCConnectionFactory(String DB_NAME, String DB_USER, String DB_PASSWORD) {
this.DB_NAME = DB_NAME.toUpperCase().trim();
        String sqlCreateDB = "CREATE DATABASE IF NOT EXISTS "+this.DB_NAME;

        System.out.println(sqlCreateDB);

//        try {
//            Class.forName(driverClassName);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("Failed to load the MySQL JDBC driver", e);
//        }
//

        try
            {
                Connection conn = DriverManager.getConnection(DB_HOST_CONNECTION_URL, DB_USER, DB_PASSWORD );
                PreparedStatement stmt = conn.prepareStatement(sqlCreateDB);
                stmt.execute();
                conn.close();
            }
         catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            this.DB_CONNECTION_URL = this.DB_HOST_CONNECTION_URL + this.DB_NAME;
            this.DB_CONNECTION = DriverManager.getConnection(this.DB_CONNECTION_URL, DB_USER, DB_PASSWORD );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String sqlCreateServicesDB = "CREATE DATABASE IF NOT EXISTS SOAPSERVICEDB;";
        String sqlCreateServices = "CREATE TABLE IF NOT EXISTS SERVICES (\n" +
                "    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                "    hotelName VARCHAR(255) NOT NULL,\n" +
                "    hotelStars DECIMAL(2, 1) NOT NULL,\n" +
                "    hotelCountry VARCHAR(255) NOT NULL,\n" +
                "    hotelCity VARCHAR(255) NOT NULL,\n" +
                "    hotelStreetNumber INTEGER,\n" +
                "    hotelStreet VARCHAR(255),\n" +
                "    hotelGPS VARCHAR(255),\n" +
                "    hotelImageURL TEXT(4000) NOT NULL, \n" +
                "    hotelBookingServiceURL TEXT(4000) NOT NULL, \n" +
                "    hotelBrowsingServiceURL TEXT(4000) NULL, \n" +
                "    hotelPartnersServiceURL TEXT(4000) NULL \n" +
                ");";
        try {
            Connection conn = DriverManager.getConnection(this.DB_HOST_CONNECTION_URL, DB_USER, DB_PASSWORD );
            PreparedStatement stmt = conn.prepareStatement(sqlCreateServicesDB);
            stmt.execute();
            conn = DriverManager.getConnection(this.DB_HOST_CONNECTION_URL+"SOAPSERVICEDB", DB_USER, DB_PASSWORD );
            stmt = conn.prepareStatement(sqlCreateServices);
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void reloadHotel() {
        System.out.println("Reload dans JDBC ....");
        Hotel reloadHotel = Hotel.getInstance();

            String query = "SELECT * FROM SERVICES WHERE hotelName = ? AND hotelCity = ?";

//        try {
//            Class.forName(driverClassName);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("Failed to load the MySQL JDBC driver", e);
//        }

            try (Connection connection = getConnectionDB("SOAPSERVICEDB","soap","password");
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, reloadHotel.getName());
                System.out.println("Hotel to reload:  "+reloadHotel.getName());
                statement.setString(2, reloadHotel.getCity());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        reloadHotel.setName(resultSet.getString("hotelName"));
                        System.out.println("HOTEL NAME: "+resultSet.getString("hotelName"));
                        reloadHotel.setStars(resultSet.getDouble("hotelStars"));
                        reloadHotel.setCountry(resultSet.getString("hotelCountry"));
                        reloadHotel.setCity(resultSet.getString("hotelCity"));
                        reloadHotel.setHotelImgUrl(resultSet.getString("hotelImageURL"));
                        reloadHotel.setStreetNumber(resultSet.getInt("hotelStreetNumber"));
                        reloadHotel.setStreet(resultSet.getString("hotelStreet"));
                        reloadHotel.setGpsPosition(resultSet.getString("hotelGPS"));
                        reloadHotel.setHOTEL_DOMAIN();


                        reloadHotel.setBOOKING_URL(resultSet.getString("hotelBookingServiceURL"));
                        reloadHotel.setBROWSING_URL(resultSet.getString("hotelBrowsingServiceURL"));
                        reloadHotel.setPARTNERS_URL(resultSet.getString("hotelPartnersServiceURL"));


                        System.out.println("Redemarrage: Etape 1 terminee");
                        return;
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error executing SQL query: " + query);
                e.printStackTrace();
            }

    }

    private static JDBCConnectionFactory jdbcConnectionFactoryInit=null;
    private static JDBCConnectionFactory jdbcConnectionFactoryDB=null;




    public Connection getConnectionDB() throws SQLException {
//        try {
//            Class.forName(driverClassName);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("Failed to load the MySQL JDBC driver", e);
//        }

        Connection connection = this.DB_CONNECTION;
        return connection;
    }
    public Connection getConnectionDB(String DB_NAME, String DB_USER, String DB_PASSWORD) throws SQLException {
//        try {
//            Class.forName(driverClassName);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("Failed to load the MySQL JDBC driver", e);
//        }

        Connection connection = DriverManager.getConnection(this.DB_HOST_CONNECTION_URL+DB_NAME, DB_USER, DB_PASSWORD );;
        return connection;
    }


    public boolean destroyDB(String DB_NAME, String DB_USER, String DB_PASSWORD) {
        String sql = "DROP DATABASE  "+DB_NAME+";";
//        try {
//            Class.forName(driverClassName);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("Failed to load the MySQL JDBC driver", e);
//        }

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


//    public boolean destroyDB() {
//        String sql = "DROP DATABASE  "+this.DB_NAME+";";
//        try ( PreparedStatement stmt = this.DB_CONNECTION.prepareStatement(sql);) {
//            stmt.execute();
//            this.DB_CONNECTION.close();
//            return true;
//        } catch (SQLException e) {
//            return false;
//        }
//    }


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