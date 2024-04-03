package web.service.booking.client.data;

import web.service.booking.client.models.Client;
import web.service.booking.client.models.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements DBDAO<Client> {
     Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

//    public ClientDAO() {
//    }

    public ClientDAO(Connection connection) {
        System.out.println("From clientDAO: Recieved connection: "+connection);
        this.connection = connection;
    }


    @Override
    public boolean createTable() {
        String sqlQueryString = "CREATE TABLE IF NOT EXISTS CLIENTS (\n" +
                "    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                "    lastName VARCHAR(50) ,\n" +
                "    firstName VARCHAR(50) NOT NULL,\n" +
                "    cardNumber VARCHAR(255) NOT NULL\n" +
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
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM CLIENTS";
        try (
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("Id"));
                client.setFirstName(resultSet.getString("firstName"));
                client.setLastName(resultSet.getString("lastName"));
                client.setCardNumber(resultSet.getString("cardNumber"));
                clients.add(client);
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + query);
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public boolean add(Client object) {
        try {
            String sqlQueryString =  "INSERT INTO CLIENTS(lastName, firstName, cardNumber) VALUES(?,?,?)";

            ptmt = connection.prepareStatement(sqlQueryString);
            ptmt.setString(1, object.getLastName().trim());
            ptmt.setString(2, object.getFirstName().trim());
            ptmt.setString(3, object.getCardNumber().trim());
            ptmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("new SQL Exception in add()");
            return false;
        }

    }


    public List<Service> getAllServices() {
        List<Service> loadedServices = new ArrayList<Service>();

        String sql = "SELECT * from SERVICES";
        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Service service = new Service();
                service.setHotelName(resultSet.getString("hotelName"));
                service.setHotelCity(resultSet.getString("hotelCity"));
                service.setHotelImageUrl(resultSet.getString("hotelImageURL"));
                service.setHotelBookingServiceURL(resultSet.getString("hotelBookingServiceURL"));
                service.setHotelBrowsingServiceURL(resultSet.getString("hotelBrowsingServiceURL"));
                service.setHotelPartnersServiceURL(resultSet.getString("hotelPartnersServiceURL"));
               loadedServices.add(service);
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + sql);
            e.printStackTrace();
        }

        return loadedServices;
    }

    @Override
    public boolean update(Client object) {
        return false;
    }

    @Override
    public List<Client> get(String sqlQuery) {
        return null;
    }

    @Override
    public List<Client> get(String sqlQuery, Object... args) {
        return null;
    }

    public Client getById(int clientId) {

        Client found = new Client();
        String query = "SELECT * FROM CLIENTS WHERE Id=?  LIMIT 1";

        try  {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                found.setFirstName(resultSet.getString("firstName"));
                found.setLastName(resultSet.getString("lastName"));
                found.setCardNumber(resultSet.getString("cardNumber"));
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + query);
            e.printStackTrace();
        }
        return found;
    }

    public int getClientID(Client client) {

        int ClientId = 0;
        String query = "SELECT * FROM CLIENTS WHERE lastName=? AND firstName=? AND cardNumber=?  LIMIT 1";

        try  {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getLastName().trim());
            statement.setString(2, client.getFirstName().trim());
            statement.setString(3, client.getCardNumber().trim());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ClientId = resultSet.getInt("Id");
                return ClientId;
            }

        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + query);
            e.printStackTrace();
        }
        return ClientId;
    }


    @Override
    public boolean dropTable() {
        boolean isDropped = false;
        try {
            Connection connection = this.connection;
            Statement statement = connection.createStatement();
            String query = "DROP TABLE IF EXISTS CLIENTS" ;
            statement.executeUpdate(query);
            isDropped = true;
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error while dropping table/database: " + e.getMessage());
        }
        return isDropped;
    }

}
