package web.service.booking.data;

import web.service.booking.models.Booking;
import web.service.booking.models.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO implements DBDAO<Booking> {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private String DB_NAME;
    static private Hotel runningHotel;

    public BookingDAO(Connection connection) {
        this.connection = connection;

    }

    @Override
    public boolean createTable() {

        String sqlQueryString = "CREATE TABLE IF NOT EXISTS BOOKINGS (\n" +
                "    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                "    bookingReference VARCHAR(50) NOT NULL,\n" +
                "    clientBookingLastName VARCHAR(50) NOT NULL,\n" +
                "    clientBookingFirstName VARCHAR(255) NOT NULL,\n" +
                "    arrivalDate DATE NOT NULL,\n" +
                "    checkoutDate DATE NOT NULL,\n" +
                "    numberPersons INT ,\n" +
                "    roomId INT, \n" +
                "FOREIGN KEY (roomId) REFERENCES ROOMS(Id) ON DELETE SET NULL "+
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
    public List<Booking> getAll()  {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM BOOKINGS";
        try (
//                Connection connection = this.connection;
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBookingReference(resultSet.getString("bookingReference"));
                booking.setClientBookingLastName(resultSet.getString("clientBookingLastName"));
                booking.setClientBookingFirstName(resultSet.getString("clientBookingFirstName"));
                booking.setArrivalDate(resultSet.getDate("arrivalDate"));
                booking.setCheckoutDate(resultSet.getDate("checkoutDate"));
                booking.setNumberPersons(resultSet.getInt("numberPersons"));
                booking.setRoomId(resultSet.getInt("roomId"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + query);
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public boolean add(Booking object) {
        String query = "INSERT INTO BOOKINGS (bookingReference, clientBookingLastName," +
                " clientBookingFirstName, " +
                "arrivalDate, checkoutDate, numberPersons, roomId" +
                " ) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (
//                Connection connection = this.connection;
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, object.getBookingReference());
            stmt.setString(2, object.getClientBookingLastName());
            stmt.setString(3, object.getClientBookingFirstName());
            stmt.setDate(4,  new java.sql.Date(object.getArrivalDate().getTime()));
            stmt.setDate(5, new java.sql.Date(object.getCheckoutDate().getTime()));
            stmt.setInt(6, object.getNumberPersons());
            stmt.setInt(7, object.getRoomId());

            System.out.println("Trying to insert: "+ object.toString());
            boolean rowsInserted = stmt.execute();
            System.out.println("Added Booking");
            return rowsInserted;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Booking object) {
        String query = "UPDATE BOOKINGS SET bookingReference=?, clientBookingLastName=?, clientBookingFirstName=?, " +
                "arrivalDate=?, checkoutDate=?, numberPersons=?, roomId=? WHERE Id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, object.getBookingReference());
            stmt.setString(2, object.getClientBookingLastName());
            stmt.setString(3, object.getClientBookingFirstName());
            stmt.setDate(4, (Date) object.getArrivalDate());
            stmt.setDate(5, (Date) object.getCheckoutDate());
            stmt.setInt(6, object.getNumberPersons());
            stmt.setInt(7, object.getRoomId());
//            stmt.setInt(8, object.getId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Booking> get(String sqlQuery)  {
        return null;
    }

    @Override
    public List<Booking> get(String sqlQuery, Object... args) {
        List<Booking> bookings = new ArrayList<>();
        try (PreparedStatement stmt = this.connection.prepareStatement(sqlQuery)) {
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
//                booking.setId(rs.getInt("Id"));
                booking.setBookingReference(rs.getString("bookingReference"));
                booking.setClientBookingLastName(rs.getString("clientBookingLastName"));
                booking.setClientBookingFirstName(rs.getString("clientBookingFirstName"));
                booking.setArrivalDate(rs.getDate("arrivalDate"));
                booking.setCheckoutDate(rs.getDate("checkoutDate"));
                booking.setNumberPersons(rs.getInt("numberPersons"));
                booking.setRoomId(rs.getInt("roomId"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }


    @Override
    public boolean dropTableOrDB(String tableOrDB) {
        boolean isDropped = false;
        try {
            Connection connection = this.connection;
            Statement statement = connection.createStatement();
            String query = "DROP TABLE IF EXISTS BOOKINGS" ;
            statement.executeUpdate(query);
            isDropped = true;
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error while dropping table/database: " + e.getMessage());
        }
        return isDropped;
    }

}
