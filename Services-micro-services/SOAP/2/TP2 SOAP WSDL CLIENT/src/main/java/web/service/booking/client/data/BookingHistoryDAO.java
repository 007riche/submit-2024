package web.service.booking.client.data;

import web.service.booking.client.models.BookingAgency;
import web.service.booking.client.models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingHistoryDAO implements  DBDAO<BookingAgency>{
    Connection connection = null;



    ClientDAO clientDAO =null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public BookingHistoryDAO() {
    }

    public BookingHistoryDAO(Connection connection, ClientDAO clientDAO) {
        this.connection = connection;
        this.clientDAO = clientDAO;
    }


    @Override
    public boolean createTable() {
        String sqlQueryString = "CREATE TABLE IF NOT EXISTS AGENCYBOOKINGS (\n" +
                "    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                "    bookingReference VARCHAR(50) NOT NULL,\n" +
                "    totalPrice DECIMAL(7, 2) NOT NULL,\n" +
                "    arrivalDate DATE NOT NULL,\n" +
                "    checkoutDate DATE NOT NULL,\n" +
                "    numberPersons INT ,\n" +
                "    clientId INT, \n" +
                "FOREIGN KEY (clientId) REFERENCES CLIENTS(Id) ON DELETE SET NULL "+
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
    public List<BookingAgency> getAll() {
        List<BookingAgency> bookingAgency = new ArrayList<>();
        String query = "SELECT * FROM AGENCYBOOKINGS";
        try (
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                BookingAgency booking = new BookingAgency();
                booking.setBookingSNo(resultSet.getInt("Id"));
                booking.setBookingReference(resultSet.getString("bookingReference"));
                booking.setMainPersonne(this.clientDAO.getById(resultSet.getInt("clientId")).getLastName()
                        +" "+this.clientDAO.getById(resultSet.getInt("clientId")).getLastName()  );
                booking.setArrivalDate(resultSet.getDate("arrivalDate"));
                booking.setTotalPrice(resultSet.getDouble("totalPrice"));
                booking.setCheckoutDate(resultSet.getDate("checkoutDate"));
                booking.setNumberPersons(resultSet.getInt("numberPersons"));
                bookingAgency.add(booking);
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + query);
            e.printStackTrace();
        }
        return bookingAgency;
    }

    @Override
    public boolean add(BookingAgency object) {
            return false;
    }

    public boolean compoundAdd(BookingAgency bookingAgency, Client client) {

        int clientId = -1;
        List<Client> allCurrent = this.clientDAO.getAll();

        for (int i = 0; i < allCurrent.size(); i++) {
            Client indexCl = allCurrent.get(i);
            if (indexCl.getFirstName().contentEquals(client.getFirstName())
            && indexCl.getLastName().contentEquals(client.getLastName())
            && indexCl.getCardNumber().contentEquals(client.getCardNumber())) {
                clientId = indexCl.getId();
            }
        }
        if(clientId == -1) {
            this.clientDAO.add(client);
            clientId = this.clientDAO.getClientID(client);
        }


        String query = "INSERT INTO AGENCYBOOKINGS ( bookingReference, totalPrice, " +
                "arrivalDate, checkoutDate, numberPersons, clientId) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, bookingAgency.getBookingReference());
            stmt.setDouble(2, bookingAgency.getTotalPrice());
            stmt.setDate(3,  new java.sql.Date(bookingAgency.getArrivalDate().getTime()));
            stmt.setDate(4, new java.sql.Date(bookingAgency.getCheckoutDate().getTime()));
            stmt.setInt(5, bookingAgency.getNumberPersons());
            stmt.setInt(6,  clientId);

            boolean rowsInserted = stmt.execute();
            System.out.println("Added Agency Booking");
            return rowsInserted;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(BookingAgency object) {
        return false;
    }

    @Override
    public List<BookingAgency> get(String sqlQuery) {
        return null;
    }

    @Override
    public List<BookingAgency> get(String sqlQuery, Object... args) {
        return null;
    }

    @Override
    public boolean dropTable() {
        boolean isDropped = false;
        try {
            Connection connection = this.connection;
            Statement statement = connection.createStatement();
            String query = "DROP TABLE IF EXISTS AGENCYBOOKINGS" ;
            statement.executeUpdate(query);
            isDropped = true;
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error while dropping table/database: " + e.getMessage());
        }
        return isDropped;
    }
}
