package web.service.booking.services;


//import jakarta.jws.WebMethod;
//import jakarta.jws.WebParam;
//import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import web.service.booking.models.Response;

@WebService
public interface PartnerShipManagementService {

    @WebMethod
    Response signUpUser(@WebParam(name = "userName") String userName,
                        @WebParam(name = "Password") String Password,
                        @WebParam(name = "AgencyName") String AgencyName);



    @WebMethod
    Response updateCredentials(@WebParam(name = "idAgency") String idAgency,
                               @WebParam(name = "userName") String userName,
                               @WebParam(name = "Password") String Password);


}
