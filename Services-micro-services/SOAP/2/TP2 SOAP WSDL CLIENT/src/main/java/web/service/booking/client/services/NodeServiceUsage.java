package web.service.booking.client.services;

import remote.booking.MakeBooking;
import web.service.booking.client.data.ServiceInfoDAO;
import web.service.booking.client.models.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class NodeServiceUsage {
    private static ServiceInfoDAO serviceInfoDAO;

    private static NodeServiceUsage instance;
    private NodeServiceUsage(ServiceInfoDAO serviceInfoDAO) {
        this.serviceInfoDAO = serviceInfoDAO;
    }

    static public synchronized NodeServiceUsage getInstance(ServiceInfoDAO serviceInfoDAO) {
        if (instance == null) {
            instance = new NodeServiceUsage(serviceInfoDAO);
        }
        return instance;
    }

    static public synchronized NodeServiceUsage getInstance() {
        return instance;
    }

    public static String signUPToNode(Service service,
                                      String username, String password,
                                      String agencyName) {
        String Id = "";
            try {
                System.out.println("Recieved hotel service:"+service.getHotelName());
                URL partnerUrl = new URL(service.getHotelPartnersServiceURL()+"?WSDL");
                System.out.println(partnerUrl);

                // PartnersWS
                remote.partners.PartnerShipManagementServcieImplService servcieImplService =
                        new remote.partners.PartnerShipManagementServcieImplService(partnerUrl);

                remote.partners.PartnerShipManagementService partnerProxy =
                        servcieImplService.getPartnerShipManagementServcieImplPort();

                remote.partners.Response response =
                        partnerProxy.signUpUser(username, password, agencyName);

                if (response.getCode()==200)
                {
                    Id=response.getExplaination();
                }

                System.out.println("New signup user: "+ Id);


            } catch (MalformedURLException e) {
            }

        return Id;
    }

    public static String makeBookingOnNode(Service service, String agencyID,
                                           String username, String password,
    Client client, List<String> chosen, String token) {
        String UnifiedID = "";
        try {
            URL bookingUrl = new URL(service.getHotelBookingServiceURL()+"?WSDL");
            remote.booking.BookingServiceImplementationService bookingServiceImplementationService =
                    new remote.booking.BookingServiceImplementationService(bookingUrl);

            remote.booking.BookingService bookingService =
                    bookingServiceImplementationService.getBookingServiceImplementationPort();


            remote.booking.Response response = bookingService.makeBooking(agencyID, username, password,
                    client.getFirstName(), client.getLastName(), chosen, token);

            System.out.println("From nodeServiceUsage: response: "+response.getExplaination());

            if(response.getCode() != 404) {
                UnifiedID = response.getExplaination();
                System.out.println("From nodeServiceUsage: response: "+UnifiedID);
            }

            return UnifiedID;

        } catch (MalformedURLException e) {

        }
        return "";
    }

    public static List<RetrievedRoom> browseToNode(Service service,
                                                   String agencyID, String password,
                                                   Date from, Date to,
                                                   int numberOfPerson) {
        List<RetrievedRoom> retrievedRooms = new ArrayList<>();

        System.out.println("Received service Info: id="+agencyID+ " : pass="+password+ " : from="+
                from+" : to="+to+" : nmbp="+numberOfPerson);
        System.out.println("Received URLs: "+service.getHotelBrowsingServiceURL()+"\n"+
                service.getHotelBookingServiceURL()+"\n"+
                service.getHotelPartnersServiceURL());
        System.out.println("Retrieved Hotel: "+service.getHotelName());

        String uui = agencyID;
        String passString = "password";

        String username = Agency.getInstance().getAgencyName()+Agency.getInstance().getCity();
        username=username.trim().toLowerCase().replaceAll(" ", "_");

        if (agencyID==null) {
            uui=signUPToNode(service, username, passString, Agency.getInstance().getAgencyName().trim());

            service.setPassword(passString);
            service.setIdAgency(uui);
            service.setUserName(username);
            service.setLoginId(username);

            serviceInfoDAO.add(service);
        }



        try {
            URL browsingUrl = new URL(service.getHotelBrowsingServiceURL()+"?WSDL");
            System.out.println(browsingUrl);
            // brosingWS
            remote.browse.BrowseAvailabilityServiceImplService browseAvailabilityServiceImplService =
                    new remote.browse.BrowseAvailabilityServiceImplService(browsingUrl);

            remote.browse.BrowseAvailabilityService browseAvailabilityService =
                    browseAvailabilityServiceImplService.getBrowseAvailabilityServiceImplPort();

            List<remote.browse.Offer> offerList = browseAvailabilityService.browseAvailableRooms(
                    service.getIdAgency(), service.getPassword(),
                    convertDateToXMLGregorianCalendar(from), convertDateToXMLGregorianCalendar(to), numberOfPerson);

            offerList.forEach(offer -> {
                RetrievedRoom retrievedRoom = new RetrievedRoom();

                retrievedRoom.setOfferId(offer.getOfferId());
                retrievedRoom.setFrom(from);
                retrievedRoom.setTo(to);
                retrievedRoom.setNumberBed(offer.getNumberBed());
                retrievedRoom.setPrice(offer.getPrice());
                System.out.println("Retrieved offer price: "+offer.getPrice());
                retrievedRoom.setImgName("roomNumber"+offer.getRoomNumber());

                String imgUrl = browseAvailabilityService.getImageURL(retrievedRoom.getImgName());
                retrievedRoom.setImgUrl(imgUrl);
                retrievedRoom.setSearchedNumberPerson(offer.getNumberPerson());

                retrievedRooms.add(retrievedRoom);
            });
        } catch (MalformedURLException e) {
        } catch (DatatypeConfigurationException e) {
        }


        Agency runningNode = Agency.getInstance();
        Services internalService = Services.getInstance();
        internalService.loadAvailableServices(serviceInfoDAO); // Calls running.setAllServices(getAllServices(this.serviceDBConnection, serviceInfoDAO));
//        runningNode.setAllServices(internalService.);
        return retrievedRooms;
    }

    public static List<RetrievedHotel> getHotelsAvalaible(BrowsingInfo browsingInfo) {

        List<RetrievedHotel> hotelList = new ArrayList<>();
        Agency agency = Agency.getInstance();

        List<Service> loadservice =  agency.getAllServices();
        List<Service> local= new ArrayList<>();
        List<Service> toUse= new ArrayList<>();

        loadservice.forEach(service -> {
            if (service.getHotelCity().trim().toLowerCase()
                    .contentEquals(browsingInfo.getCity().trim().toLowerCase())) {
                local.add(service);
            }
        });

        local.forEach(service -> {
            List<RetrievedRoom> retrievedRooms  = browseToNode(service, service.getIdAgency(), service.getPassword(),
                    browsingInfo.getFrom(), browsingInfo.getTo(), browsingInfo.getNumberPerson());
            if(retrievedRooms.size() > 0) {
                int rooms = 0;
                double minPrice = 0.0;
                for (int i = 0; i < retrievedRooms.size(); i++) {
                    System.out.println("Browse Retrieved room size:"+retrievedRooms.size());
                    System.out.println("Roo number "+retrievedRooms.get(i).getNumberBed()+" price: "+retrievedRooms.get(i).getPrice());
                    rooms += retrievedRooms.get(i).getNumberBed();
                    if (retrievedRooms.get(i).getPrice() != null ) {
                        if (i==0)
                            minPrice = retrievedRooms.get(i).getPrice();
                        else
                        {
                            minPrice = (retrievedRooms.get(i).getPrice()<minPrice && minPrice>0.0)?retrievedRooms.get(i).getPrice():minPrice;
                        }

                    }
                    System.out.println("Browse Retrieved min price: "+minPrice);
                }
                service.getHotelName();
                service.setCurrentRoomAvail(rooms);
                service.setCurrentMinPrice(minPrice);

                toUse.add(service);
            }
        });

        toUse.forEach(service -> {
            RetrievedHotel hotel = new RetrievedHotel();

            hotel.setHotelName(service.getHotelName());
            hotel.setHotelAdresse(service.getHotelCity());
            hotel.setImgUrl(service.getHotelImageUrl());
            hotel.setStars(service.getHotelStars());
            hotel.setRoombeds(service.getCurrentRoomAvail());
            hotel.setPriceMin(service.getCurrentMinPrice());

            hotelList.add(hotel);
        });


        return hotelList;
    }

    public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
        // Create a GregorianCalendar instance and set the time with the given Date object
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        // Convert the GregorianCalendar instance to XMLGregorianCalendar
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }
}


//    public static String getImageURLOnNode(Service service,
//                                           String roomImage) {
//        String imgUrl = "";
//        try {
//            URL browsingUrl = new URL(service.getHotelBrowsingServiceURL()+"?WSDL");
//            System.out.println(browsingUrl);
//
//            // brosingWS
//            remote.browse.BrowseAvailabilityServiceImplService browseAvailabilityServiceImplService =
//                    new remote.browse.BrowseAvailabilityServiceImplService(browsingUrl);
//
//            remote.browse.BrowseAvailabilityService browseAvailabilityService =
//                    browseAvailabilityServiceImplService.getBrowseAvailabilityServiceImplPort();
//
//            browseAvailabilityService.getImageURL()
//
//        } catch (MalformedURLException e) {
//
//        }
//
//        return "";
//    }

//        Services reloaded = new Services("SOAPSERVICEDB", "soap", "password");
//        System.out.println("From HotelDisplay subsection\n" +
//                "Search Params: "+browsingInfo.toString());
//        reloaded.loadAvailableServices();
//        System.err.println("To use, as per resquest: "+service.getHotelName());
//        System.err.println("Browsing url: "+service.getHotelBrowsingServiceURL());
//        System.err.println("Booking url: "+service.getHotelBookingServiceURL());
//        System.err.println("Partner url: "+service.getHotelPartnersServiceURL());
