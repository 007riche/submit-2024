package web.service.booking.client.data;

import web.service.booking.client.models.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceInfoDAO implements  DBDAO<Service>{
    Connection connection = null;


    ClientDAO clientDAO =null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

//    public ServiceInfoDAO() {
//    }

    public ServiceInfoDAO(Connection connection) {
        System.out.println("From ServiceInfoDAO: Recieved connection: "+connection);
        this.connection = connection;
    }


    @Override
    public boolean createTable() {
        String sqlQueryString = "CREATE TABLE IF NOT EXISTS SERVICES (\n" +
                "    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                "    hotelName VARCHAR(50) NOT NULL,\n" +
                "    loginId VARCHAR(255),\n" +
                "    idAgency VARCHAR(255), \n" +
                "    userName VARCHAR(255), \n" +
                "    Password VARCHAR(255), \n" +
                "    hotelBookingServiceURL VARCHAR(255) NOT NULL,\n" +
                "    hotelBrowsingServiceURL VARCHAR(255) NOT NULL,\n" +
                "    hotelPartnersServiceURL VARCHAR(255) NOT NULL \n" +
                ");";
        try {
            this.ptmt = this.connection.prepareStatement(sqlQueryString);
            ptmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<Service> getAll() {
        List<Service> servicesAgency = new ArrayList<>();
        String query = "SELECT * FROM SERVICES";
        try (
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Service service = new Service();
                service.setHotelName(resultSet.getString("hotelName"));
                service.setUserName(resultSet.getString("loginId"));
                service.setIdAgency(resultSet.getString("idAgency"));
                service.setUserName(resultSet.getString("userName"));
                service.setPassword(resultSet.getString("Password"));
                service.setHotelBookingServiceURL(resultSet.getString("hotelBookingServiceURL"));
                service.setHotelBrowsingServiceURL(resultSet.getString("hotelBrowsingServiceURL"));
                service.setHotelPartnersServiceURL(resultSet.getString("hotelPartnersServiceURL"));

                servicesAgency.add(service);
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + query);
            e.printStackTrace();
        }
        return servicesAgency;
    }


    @Override
    public boolean add(Service object) {
        String query = "INSERT INTO SERVICES ( " +
                "hotelName,\n" +
                "loginId,\n" +
                "idAgency,\n" +
                "userName,\n" +
                "Password," +
                "hotelBookingServiceURL,\n" +
                "hotelBrowsingServiceURL,\n" +
                "hotelPartnersServiceURL\n" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (
                PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, object.getHotelName());
            stmt.setString(2, object.getLoginId());
            stmt.setString(3, object.getIdAgency());
            stmt.setString(4, object.getUserName());
            stmt.setString(5, object.getPassword());
            stmt.setString(6, object.getHotelBookingServiceURL());
            stmt.setString(7, object.getHotelBrowsingServiceURL());
            stmt.setString(8, object.getHotelPartnersServiceURL());
            
            boolean rowsInserted = stmt.execute();
            System.out.println("Added Agency Booking");
            return rowsInserted;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param object 
     * @return
     */
    @Override
    public boolean update(Service object) {
        return false;
    }

    /**
     * @param sqlQuery 
     * @return
     */
    @Override
    public List<Service> get(String sqlQuery) {
        return null;
    }

    /**
     * @param sqlQuery 
     * @param args
     * @return
     */
    @Override
    public List<Service> get(String sqlQuery, Object... args) {
        return null;
    }


    @Override
    public boolean dropTable() {
        boolean isDropped = false;
        try {
            Connection connection = this.connection;
            Statement statement = connection.createStatement();
            String query = "DROP TABLE IF EXISTS SERVICES" ;
            statement.executeUpdate(query);
            isDropped = true;
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error while dropping table/database: " + e.getMessage());
        }
        return isDropped;
    }
}
