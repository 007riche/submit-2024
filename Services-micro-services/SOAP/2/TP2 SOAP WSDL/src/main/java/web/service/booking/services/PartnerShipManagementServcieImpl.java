package web.service.booking.services;

import jakarta.jws.WebService;
import web.service.booking.data.BookingDAO;
import web.service.booking.data.JDBCConnectionFactory;
import web.service.booking.data.PartenershipDAO;
import web.service.booking.data.RoomDAO;
import web.service.booking.models.Hotel;
import web.service.booking.models.Partnership;
import web.service.booking.models.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebService(endpointInterface = "web.service.booking.services.PartnerShipManagementService")
public class PartnerShipManagementServcieImpl implements PartnerShipManagementService {
    private Hotel hotel;
    private RoomDAO roomDAO ;
    private PartenershipDAO partenershipDAO;
    private BookingDAO bookingDAO;
    public PartnerShipManagementServcieImpl(Hotel launchedHotel, String serviceURL) {
        this.hotel = launchedHotel;
        JDBCConnectionFactory jdbcConnectionFactory = new JDBCConnectionFactory(this.hotel.getHOTEL_DOMAIN(),"soap","password");
        try {
//            this.roomDAO = new RoomDAO(jdbcConnectionFactory.getConnectionDB());
            this.partenershipDAO = new PartenershipDAO(jdbcConnectionFactory.getConnectionDB());
//            this.bookingDAO = new BookingDAO(jdbcConnectionFactory.getConnectionDB());
            String sqlCommand = "UPDATE  SERVICES SET  hotelPartnersServiceURL=?   WHERE hotelName=? AND hotelCity=? AND hotelCountry=?;";
            Connection conn = jdbcConnectionFactory.getConnectionDB("SOAPSERVICEDB","soap","password");
            PreparedStatement stmt = conn.prepareStatement(sqlCommand);
            stmt.setString(1, serviceURL);
            stmt.setString(2, launchedHotel.getName().trim());
            stmt.setString(3, launchedHotel.getCity().trim());
            stmt.setString(4, launchedHotel.getCountry().trim());
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println("New exception");
            e.printStackTrace();
        }
    }


    public Response signUpUser(String userName, String password, String agencyName) {
        Hotel runningHotel = Hotel.getInstance();
        Partnership newPartnership = new Partnership();
//        Partnership currentPartnership = getPartnerById(idAgency.trim());
        Response response = new Response();



        // Check if the username already exists
        for (int i = 0; i < runningHotel.getHotelPartnership().size(); i++) {
            if (runningHotel.getHotelPartnership().get(i).getLoginId().contains(userName.trim())) {
                response.setCode(404);
                response.setExplaination("Nom d'utilisateur deja existant");
                return response;
            }
        }

        // Registring new service user
        String newId = generateUUID();
        newPartnership.setIdAgency(newId);
        newPartnership.setAgencyName(agencyName.trim());
        newPartnership.setLoginId(userName.trim());
        newPartnership.setPassword(password.trim());
        newPartnership.setDiscountRate(generateRandomDouble(5.0, 15.0));

        if (this.partenershipDAO.add(newPartnership)) {
            List<Partnership> updatedList = runningHotel.getHotelPartnership();
            updatedList.add(newPartnership);
            runningHotel.setHotelPartnership(updatedList);
            response.setCode(200);
            response.setExplaination(newId);
            return response;
        }

        // id not exist but registration also failed
        response.setCode(404);
        response.setExplaination("Une Erreur s'est produite");
        return response;
    }


    public Response updateCredentials(String idAgency, String userName, String password) {
        Hotel runningHotel = Hotel.getInstance();
        Partnership newPartnership = new Partnership();
        Partnership currentPartnership = getPartnerById(idAgency.trim());
        Response response = new Response();

        newPartnership.setAgencyName(currentPartnership.getAgencyName());
        newPartnership.setIdAgency(currentPartnership.getIdAgency());
        newPartnership.setDiscountRate(currentPartnership.getDiscountRate());

        newPartnership.setLoginId(userName.trim());
        newPartnership.setPassword(password.trim());

        if (this.partenershipDAO.update(newPartnership)) {
            response.setCode(200);
            response.setExplaination("Mise a jour complete");
            return response;
        }
        else {
            response.setCode(404);
            response.setExplaination("Mise a jour echouee");
            return response;
        }

    }





    public boolean isPartner(String idAgency, String password) {
        Hotel runningHotel = Hotel.getInstance();
        for (int i = 0; i < runningHotel.getHotelPartnership().size(); i++) {
            if (runningHotel.getHotelPartnership().get(i).getIdAgency().contains(idAgency.trim())
                    && runningHotel.getHotelPartnership().get(i).getPassword().contains(password.trim())) return true;
        }
        return false;
    }

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public Partnership getPartnerById(String idAgency) {
        Hotel runningHotel = Hotel.getInstance();
        System.out.println("getPartnerById called with: "+idAgency);
        for (int i = 0; i < runningHotel.getHotelPartnership().size(); i++) {
            if (runningHotel.getHotelPartnership().get(i).getIdAgency().contains(idAgency))
                return runningHotel.getHotelPartnership().get(i);
        }
        return null;
    }


    public static double generateRandomDouble(double min, double max) {
        // Generate a random number between 0 and 1
        double random = Math.random();

        // Scale the random number to fit within the specified range
        double range = max - min;
        double scaledRandom = random * range;

        // Shift the scaled random number to the correct starting point
        double shiftedRandom = scaledRandom + min;

        // Return the final random number
        return shiftedRandom;
    }

}
