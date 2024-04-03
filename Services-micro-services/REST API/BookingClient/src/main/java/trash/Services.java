//package com.todo.company.hai704.restapi.Client.services;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Services  {
//    String SERVICES_DB = "SOAPSERVICEDB";
//    String DB_USER = "soap";
//    String DB_PASSWORD = "password";
//
//    Agency runningAgency;
//
//    private BookingHistoryDAO bookingHistoryDAO;
//    private Connection serviceDBConnection;
//    private ClientDAO clientDAO;
//    private ServiceInfoDAO serviceInfoDAO;
//    static private  Services instance;
//
//    private Services() {
//        this.runningAgency = Agency.getInstance();
//
//        JDBCConnectionFactory jdbcConnectionFactoryAgenceDB = new JDBCConnectionFactory(this.runningAgency.getAGENCY_DB_NAME(), DB_USER, DB_PASSWORD, false);
//        try {
//            this.serviceDBConnection= jdbcConnectionFactoryAgenceDB.getConnectionDB();
//            this.clientDAO = new ClientDAO(jdbcConnectionFactoryAgenceDB.getConnectionDB());
//            this.bookingHistoryDAO = new BookingHistoryDAO(jdbcConnectionFactoryAgenceDB.getConnectionDB(), this.clientDAO);
//            this.serviceInfoDAO = new ServiceInfoDAO(jdbcConnectionFactoryAgenceDB.getConnectionDB());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        this.clientDAO.createTable();
//        this.bookingHistoryDAO.createTable();
//        this.serviceInfoDAO.createTable();
//
////        System.err.println("New SQL Exception at SERVICE ");
//    }
//
//    static public synchronized Services getInstance() {
//        if (instance==null)
//            instance = new Services();
//
//        return instance;
//    }
//
//    private Services(String SERVICES_DB, String DB_USER, String DB_PASSWORD) {
//        this.SERVICES_DB = SERVICES_DB;
//        this.DB_USER = DB_USER;
//        this.DB_PASSWORD = DB_PASSWORD;
//
//        JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory();
//
//        try {
//            this.serviceDBConnection= jdbcConnectionFactory.getConnectionDB(SERVICES_DB, DB_USER, DB_PASSWORD);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    static public synchronized Services getInstance(String SERVICES_DB, String DB_USER, String DB_PASSWORD) {
//        if (instance==null)
//            instance = new Services( SERVICES_DB,  DB_USER,  DB_PASSWORD);
//
//        return instance;
//    }
//
//    public   boolean loadAvailableServices(ServiceInfoDAO serviceInfoDAO) {
//        Agency running = Agency.getInstance();
//        running.setAllServices(getAllServices(this.serviceDBConnection, serviceInfoDAO));
//    if (running.getAllServices().size()<=0)
//        return false;
//    return true;
//    }
//
//
//    public  static List<Service> getAllServices(Connection connection, ServiceInfoDAO serviceInfoDAO) {
//        List<Service> retrieved = new ArrayList<Service>();
//        List<Service> fromAgecySide = new ArrayList<>();
//
//        String query = "SELECT * FROM SERVICES";
//        try (PreparedStatement statement = connection.prepareStatement(query);
//             ResultSet resultSet = statement.executeQuery()){
//            while (resultSet.next()) {
//                Service service = new Service();
//                service.setHotelName(resultSet.getString("hotelName"));
//                service.setHotelCity(resultSet.getString("hotelCity"));
//                service.setHotelStars(resultSet.getDouble("hotelStars"));
//                service.setHotelImageUrl(resultSet.getString("hotelImageURL"));
//                service.setHotelBookingServiceURL(resultSet.getString("hotelBookingServiceURL"));
//                service.setHotelBrowsingServiceURL(resultSet.getString("hotelBrowsingServiceURL"));
//                service.setHotelPartnersServiceURL(resultSet.getString("hotelPartnersServiceURL"));
//                retrieved.add(service);
//            }
//
//            fromAgecySide = serviceInfoDAO.getAll();
//
//            for (Service service : retrieved) {
//                for (Service servAge : fromAgecySide) {
//                    if(service.getHotelName().trim().equalsIgnoreCase(servAge.getHotelName().trim())
//                    && service.getHotelBookingServiceURL().trim().equalsIgnoreCase(servAge.getHotelBookingServiceURL().trim())
//                    && service.getHotelBrowsingServiceURL().trim().equalsIgnoreCase(servAge.getHotelBrowsingServiceURL().trim())
//                    && service.getHotelPartnersServiceURL().trim().equalsIgnoreCase(servAge.getHotelPartnersServiceURL().trim())) {
//                        service.setLoginId(servAge.getLoginId());
//                        service.setIdAgency(servAge.getIdAgency());
//                        service.setPassword(servAge.getPassword());
//                        service.setUserName(servAge.getUserName());
//                    }
//                }
//            }
//
//
//        } catch (SQLException e) {
//            System.err.println("SQL Exception Retrieving from SERVICES tables");
//        }
//        return retrieved;
//    }
//
//
//}
