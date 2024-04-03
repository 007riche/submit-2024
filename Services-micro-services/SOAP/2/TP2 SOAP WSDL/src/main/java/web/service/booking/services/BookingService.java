package web.service.booking.services;



import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import web.service.booking.models.Response;
import web.service.booking.models.Room;

import java.util.List;

@WebService
public interface BookingService {


    @WebMethod
    Response makeBooking(@WebParam(name = "idAgency") String idAgency,
                         @WebParam(name = "loginId") String loginId,
                         @WebParam(name = "password") String password,
                         @WebParam(name = "FirstName") String FirstName,
                         @WebParam(name = "LastName") String LastName,
                         @WebParam(name = "chosenRooms") List<String> chosenRooms,
                         @WebParam(name = "token") String token);
}


//List<Hotel> searchHotel(String city, float minStars, float minPrice, float maxPrice, Date arrivalDate, Date departureDate) ;

//    @WebMethod
//    List<Hotel> searchHotel(@WebParam(name = "city") String city,
//                            @WebParam(name = "minStars") int minStars,
//                            @WebParam(name = "minPrice") double minPrice,
//                            @WebParam(name = "maxPrice") double maxPrice,
//                            @WebParam(name = "arrivalDate") String arrivalDate,
//                            @WebParam(name = "departureDate") String departureDate,
//                            @WebParam(name = "numberPerson") int numberPerson
//    );

//    @WebMethod
//    List<Hotel> allHotels();
//    @WebMethod
//    List<Room> searchRoom(@WebParam(name = "tempHotel") Hotel tempHotel);