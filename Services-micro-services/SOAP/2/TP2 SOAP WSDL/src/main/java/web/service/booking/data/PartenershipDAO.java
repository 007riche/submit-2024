package web.service.booking.data;

import web.service.booking.models.Booking;
import web.service.booking.models.Partnership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PartenershipDAO implements DBDAO<Partnership> {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    private String DB_NAME;

    public PartenershipDAO(Connection connection) {
        this.connection = connection;
    }

    //    public PartenershipDAO(String DB_NAME) {
//        this.DB_NAME = DB_NAME;
//    }


    private Connection getConnection() throws SQLException {
//        JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory(this.DB_NAME);
//        Connection conn;
//        conn =  jdbcConnectionFactory.getConnectionInstanceDB().getConnectionDB();
        return this.connection;
    }

    @Override
    public boolean createTable()  {
        String sqlQueryString = "CREATE TABLE IF NOT EXISTS PARTNERS (\n" +
                "    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                "    idAgency VARCHAR(50) NOT NULL UNIQUE,\n" +
                "    agencyName VARCHAR(255) NOT NULL,\n" +
                "    loginId VARCHAR(255) NOT NULL UNIQUE,\n" +
                "    password VARCHAR(255) NOT NULL,\n" +
                "    discountRate DECIMAL(5,2) NOT NULL \n" +
//                " CONSTRAINT UC_PARTNERS UNIQUE (loginId)\n"+
                ");";
        try {
            this.ptmt = this.connection.prepareStatement(sqlQueryString);
            ptmt.execute();
            return true;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return false;
        }
    }

    @Override
    public List<Partnership> getAll() {
        List<Partnership> partnerships = new ArrayList<>();
        String sqlQuery = "SELECT * FROM PARTNERS";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                Partnership partnership = new Partnership();
//                partnership.setId(resultSet.getInt("Id"));
                partnership.setIdAgency(resultSet.getString("idAgency"));
                partnership.setAgencyName(resultSet.getString("agencyName"));
                partnership.setLoginId(resultSet.getString("loginId"));
                partnership.setPassword(resultSet.getString("password"));
                partnership.setDiscountRate(resultSet.getDouble("discountRate"));
                partnerships.add(partnership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partnerships;
    }

    @Override
    public boolean add(Partnership partnership) {
        String sqlQuery = "INSERT INTO PARTNERS (idAgency, agencyName, loginId, password, discountRate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, partnership.getIdAgency());
            preparedStatement.setString(2, partnership.getAgencyName());
            preparedStatement.setString(3, partnership.getLoginId());
            preparedStatement.setString(4, partnership.getPassword());
            preparedStatement.setDouble(5, partnership.getDiscountRate());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Partnership partnership) {
        String sqlQuery = "UPDATE PARTNERS SET  loginId=?, password=? WHERE  idAgency=? AND agencyName=? AND discountRate=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, partnership.getLoginId());
            preparedStatement.setString(2, partnership.getPassword());
            preparedStatement.setString(3, partnership.getIdAgency());
            preparedStatement.setString(4, partnership.getAgencyName());
            preparedStatement.setDouble(5, partnership.getDiscountRate());
            int result = preparedStatement.executeUpdate();
            System.err.println("Partner updation result: "+result);
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Partnership> get(String sqlQuery) {
        List<Partnership> partnerships = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                Partnership partnership = new Partnership();
//                partnership.setId(resultSet.getInt("Id"));
                partnership.setIdAgency(resultSet.getString("idAgency"));
                partnership.setAgencyName(resultSet.getString("agencyName"));
                partnership.setLoginId(resultSet.getString("loginId"));
                partnership.setPassword(resultSet.getString("password"));
                partnership.setDiscountRate(resultSet.getDouble("discountRate"));
                partnerships.add(partnership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partnerships;
    }

    @Override
    public List<Partnership> get(String sqlQuery, Object... args) {
        List<Partnership> partnerships = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Partnership partnership = new Partnership();
//                    partnership.setId(rs.getInt("Id"));
                    partnership.setIdAgency(rs.getString("idAgency"));
                    partnership.setAgencyName(rs.getString("agencyName"));
                    partnership.setLoginId(rs.getString("loginId"));
                    partnership.setPassword(rs.getString("password"));
                    partnership.setDiscountRate(rs.getDouble("discountRate"));
                    partnerships.add(partnership);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partnerships;
    }

    @Override
    public boolean dropTableOrDB(String tableOrDB) {
        boolean isDropped = false;
        try {
            Connection connection = this.connection;
            Statement statement = connection.createStatement();
            String query = "DROP TABLE IF EXISTS PARTNERS" ;
            statement.executeUpdate(query);
            isDropped = true;
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error while dropping table/database: " + e.getMessage());
        }
        return isDropped;
    }
}
