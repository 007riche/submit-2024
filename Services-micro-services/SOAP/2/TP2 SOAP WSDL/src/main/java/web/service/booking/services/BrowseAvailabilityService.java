package web.service.booking.services;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import web.service.booking.models.Offer;
import web.service.booking.models.Response;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService
public interface BrowseAvailabilityService {


//    List<Offer>
//Map<String, List<Offer>>
    @WebMethod
      List<Offer> browseAvailableRooms(
                                             @WebParam(name = "idAgency") String idAgency,
                                             @WebParam(name = "password") String password,
                                             @WebParam(name = "arrivalDate") Date arrivalDate,
                                             @WebParam(name = "departureDate") Date departureDate,
                                             @WebParam(name = "numberPerson") int numberPerson
                                     );

    @WebMethod
    URL getImageURL(@WebParam(name = "roomImName") String roomImName);
}
