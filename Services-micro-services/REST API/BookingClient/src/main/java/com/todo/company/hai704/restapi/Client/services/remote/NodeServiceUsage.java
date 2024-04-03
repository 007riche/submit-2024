package com.todo.company.hai704.restapi.Client.services.remote;

import com.todo.company.hai704.restapi.Client.entity.Agency;
import com.todo.company.hai704.restapi.Client.entity.BookingAgency;
import com.todo.company.hai704.restapi.Client.entity.Client;
import com.todo.company.hai704.restapi.Client.entity.NodeService;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.BrowseRequest;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.BrowsingInfo;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.RetrievedHotel;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.RetrievedRoom;
import com.todo.company.hai704.restapi.Client.models.restpayloadmodels.ListNodeServiceWrapper;
import com.todo.company.hai704.restapi.Client.models.restpayloadmodels.ListOfferResponseWrapper;
import com.todo.company.hai704.restapi.Client.models.restpayloadmodels.OfferResponse;
import com.todo.company.hai704.restapi.Client.models.restpayloadmodels.UserRequestBody;
import com.todo.company.hai704.restapi.Client.services.persistence.PersistenecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

@Service
public class NodeServiceUsage {
    private static NodeServiceUsage instance;
    private static RestTemplate publisherProxy = new RestTemplate();
    private static  RestTemplate anyNodeProxy = new RestTemplate();
    static String baseUrl = "http://localhost:";  // baseUrl+"8085"+"/publisher/api"+"/services";
    private static String PUB_URI_SERVICES= Agency.getInstance().getDiscoveryURL()+"/publisher/api"+"/services";
    private static String PUB_URI_SERVICE_BY_CITY= Agency.getInstance().getDiscoveryURL()+"/publisher/api"+"/services/city/{city}";
    private static String PUB_URI_SERVICE_BY_STARS= Agency.getInstance().getDiscoveryURL()+"/publisher/api"+"/services/stars/{stars}";

    private static String NODE_URI_PARTNER;  // "/api/partners" + "/"  -> @UserRequestBody
    private static String NODE_URI_BROWSE_ROOMS;  // "/api/browsing" + "/rooms" ->  @BrowseRequest
    private static String NODE_URI_BOOK;        // "/api/bookings"+ "/book"  -> @BrowseRequest

    private static String NODE_URI_BROWSE_COMPARE;  // "/api/browsing" + "/compare/room" ->  @BrowseRequest


    @Autowired
    public NodeServiceUsage(PersistenecService persistenecService) {
        this.persistenecService = persistenecService;
    }


//    @Autowired
    private final PersistenecService persistenecService;

    @Autowired
    static public synchronized NodeServiceUsage getInstance( PersistenecService persistenecService) {
        if (instance == null) {
            instance = new NodeServiceUsage(persistenecService);
        }
        return instance;
    }


    // checked
    public static  List<NodeService> browseHotel(BrowsingInfo browsingInfo) {
        List<NodeService> returnedHotels = new ArrayList<>();

//        System.out.println(" Inside NodeUsage, Browse request: "+browsingInfo);
        PUB_URI_SERVICE_BY_CITY= Agency.getInstance().getDiscoveryURL()+"/publisher/api"+"/services/city/{city}";
//        ListNodeServiceWrapper listNodeServiceWrapperByCity
//        System.out.println(" Inside NodeUsage, PUB_URI_SERVICE_BY_CITY: "+PUB_URI_SERVICE_BY_CITY);

        NodeService [] listNodeServiceWrapperByCity = publisherProxy.getForObject(PUB_URI_SERVICE_BY_CITY, NodeService[].class, browsingInfo.getCity());

        PUB_URI_SERVICE_BY_STARS= Agency.getInstance().getDiscoveryURL()+"/publisher/api"+"/services/stars/{stars}";
//        ListNodeServiceWrapper listNodeServiceWrapperByStars =

        NodeService [] listNodeServiceWrapperByStars= publisherProxy.getForObject(PUB_URI_SERVICE_BY_STARS, NodeService[].class, browsingInfo.getStars());

//        System.out.println("Inside NodeUsage, listNodeServiceWrapperByStars:"+listNodeServiceWrapperByStars);
//        List<NodeService> nodeServiceWrapperByCity = listNodeServiceWrapperByCity.getNodeServiceList();

//        System.out.println(" Inside NodeUsage, PUB_URI_SERVICE_BY_STARS: "+PUB_URI_SERVICE_BY_STARS);
        List<NodeService> nodeServiceWrapperByCity = Arrays.stream(listNodeServiceWrapperByCity).toList();

//        System.out.println("Inside NodeUsage, nodeServiceWrapperByCity:"+nodeServiceWrapperByCity);
//        List<NodeService> nodeServiceWrapperByStars = listNodeServiceWrapperByStars.getNodeServiceList();
        assert listNodeServiceWrapperByStars != null;
        List<NodeService> nodeServiceWrapperByStars = Arrays.stream(listNodeServiceWrapperByStars).toList();

//        System.out.println("Inside NodeUsage, nodeServiceWrapperByStars:"+nodeServiceWrapperByStars);

        if (!nodeServiceWrapperByStars.isEmpty() && !nodeServiceWrapperByCity.isEmpty()) {

            for (int i = 0; i < nodeServiceWrapperByCity.size(); i++) {
//                System.out.println("After browsing Hotel by city: "+ nodeServiceWrapperByCity.get(i) );
                for (int j = 0; j < nodeServiceWrapperByStars.size(); j++) {

//                    System.out.println("After browsing Hotel by stars: "+ nodeServiceWrapperByStars.get(j) );
                    if (nodeServiceWrapperByCity.get(i).getHotelName()
                            .contentEquals(nodeServiceWrapperByStars.get(j).getHotelName())) {
                        returnedHotels.add(nodeServiceWrapperByCity.get(i));
                    }
                }
            }

        }

//        return null;
        return returnedHotels;
    }


    // in progress (checked normally)
    public  List<RetrievedHotel> getHotelsAvalaible(BrowsingInfo browsingInfo) {

        List<RetrievedHotel> hotelList = new ArrayList<>();
        List<NodeService> services = new ArrayList<>();
        services=  browseHotel(browsingInfo);

        assert services != null;
        System.out.println("Size of avail hotel: "+services.size());
        if (!services.isEmpty()) {
            for (int i = 0; i < services.size(); i++) {
                NodeService currentService = services.get(i);


                // To set the address before anything
                currentService.setHotelAddress();

                NodeService found = this.persistenecService
                        .findNodeServiceByHotelNameAndHotelBookingServiceURL(currentService.getHotelName(),
                                currentService.getHotelBookingServiceURL());

                if (found==null) {
                    this.persistenecService.saveNodeService(currentService);
                }

                RetrievedHotel retrievedHotel = new RetrievedHotel();

                List<RetrievedRoom> retrievedRooms = browseToNode(currentService,
                        currentService.getIdAgency(), currentService.getPassword(),
                        browsingInfo.getFrom(), browsingInfo.getTo(), browsingInfo.getNumberPerson());

                double minPrice = -1.0;
                int minPriceRoomNumberBed = -1;

                if (!retrievedRooms.isEmpty())
                {
                    minPrice = retrievedRooms.get(0).getPrice();
                    minPriceRoomNumberBed = retrievedRooms.get(0).getNumberBed();

                    for (int j = 0; j < retrievedRooms.size(); j++) {
                        RetrievedRoom currentRetrievedRoom = retrievedRooms.get(j);
                        if (currentRetrievedRoom.getPrice()<minPrice) {
                            minPrice=currentRetrievedRoom.getPrice();
                            minPriceRoomNumberBed=currentRetrievedRoom.getNumberBed();
                        }
                    }

                    retrievedHotel.setStars(currentService.getHotelStars());
                    retrievedHotel.setHotelAdresse(currentService.getHotelAddress());
                    retrievedHotel.setHotelName(currentService.getHotelName());
                    retrievedHotel.setPriceMin(minPrice);
                    retrievedHotel.setRoombeds(minPriceRoomNumberBed);
                    retrievedHotel.setImgUrl(currentService.getHotelImageURL());

                    hotelList.add(retrievedHotel);
                }

            }
        }


        return hotelList;
    }

    public  String signUPToNode(NodeService service,
                                      String username, String password,
                                      String agencyName) {
        String Id = "";

        // "/api/partners" + "/"  -> @UserRequestBody

        UserRequestBody requestBody = new UserRequestBody();

        requestBody.setUserName(username);
        requestBody.setPassword(password);
        requestBody.setAgencyName(agencyName);

        NODE_URI_PARTNER = service.getHotelPartnersServiceURL()+"/"; // + "/api/partners" + "/";

       NodeService found = persistenecService.findNodeServiceByHotelNameAndHotelBookingServiceURL(service.getHotelName(), service.getHotelBookingServiceURL());

       if(found.getIdAgency()==null) {
           System.out.println("In NodeUsage, in signUp, NODE_URI_PARTNER: "+NODE_URI_PARTNER);
           System.out.println("In NodeUsage, in signUp, RequestBody: "+requestBody);

           Id = anyNodeProxy.postForObject(NODE_URI_PARTNER, requestBody, String.class);
           found.setLoginId(username);
           found.setPassword(password);
           found.setIdAgency(Id);

           // Updattion
           this.persistenecService.updateNodeService(found);
       }

        return Id;
    }

    public  String makeBookingOnNode(NodeService service,
                                           Client client, List<String> chosen) {
        String UnifiedID = "";

        // "/api/bookings"+ "/book"  -> @BrowseRequest

         NODE_URI_BOOK= service.getHotelBookingServiceURL()+ "/book";

        BrowseRequest request = new BrowseRequest();

        request.setIdAgency(service.getIdAgency());
        request.setLoginId(service.getLoginId());
        request.setPassword(service.getPassword());
        request.setFirstName(client.getFirstName().trim().toLowerCase());
        request.setLastName(client.getLastName().trim().toLowerCase());
        request.setChosenRooms(chosen);

        UnifiedID= anyNodeProxy.postForObject(NODE_URI_BOOK, request, String.class);

       Client foundClient =  this.persistenecService.findClientByFirstNameAndLastNameAndCardNumber(
                client.getFirstName(), client.getLastName(), client.getCardNumber());
       if (foundClient == null) {
           this.persistenecService.saveClient(client);
       }

//        for (int i = 0; i < chosen.size(); i++) {
//            BookingAgency booking = new BookingAgency();
//            booking.setBookingReference(UnifiedID);
//        }

            return UnifiedID;

    }

    public  List<RetrievedRoom> browseToNode(NodeService service,
                                                   String agencyID, String password,
                                                   Date from, Date to,
                                                   int numberOfPerson) {
        List<RetrievedRoom> retrievedRooms = new ArrayList<>();

        NodeService found = this.persistenecService.findNodeServiceByHotelNameAndHotelBookingServiceURL(service.getHotelName(), service.getHotelBookingServiceURL());

        Agency running = Agency.getInstance();

        String signUpUserName = running.getAgencyName().trim().toLowerCase().replace(" ", "_");

        if (found.getIdAgency()==null) {
            signUPToNode(found, signUpUserName, "password", running.getAgencyName());
        }

        found = this.persistenecService.findNodeServiceByHotelNameAndHotelBookingServiceURL(service.getHotelName(), service.getHotelBookingServiceURL());


        BrowseRequest request = new BrowseRequest();

        request.setIdAgency(found.getIdAgency());
        request.setPassword(found.getPassword());
        request.setArrivalDate(from);
        request.setDepartureDate(to);
        request.setNumberPerson(numberOfPerson);


        NODE_URI_BROWSE_ROOMS= found.getHotelBrowsingServiceURL() + "/rooms";  // "/api/browsing" + "/rooms" ->  @BrowseRequest

        System.out.println("In NodeUsage, in browse, NODE_URI_BROWSE_ROOMS: "+NODE_URI_BROWSE_ROOMS);
        System.out.println("In NodeUsage, in browse, Request body: "+request);

//        OfferResponse[] reponsePayload = anyNodeProxy.getForObject(NODE_URI_BROWSE_ROOMS, OfferResponse[].class, request);
//        anyNodeProxy.getF
        OfferResponse[] reponsePayload = anyNodeProxy.postForObject(NODE_URI_BROWSE_ROOMS, request, OfferResponse[].class);
        assert reponsePayload != null;
        List<OfferResponse> offerResponses= Arrays.stream(reponsePayload).toList();

        for (int i = 0; i < offerResponses.size(); i++) {
            RetrievedRoom retrievedRoom = new RetrievedRoom();
            OfferResponse currentResponse = offerResponses.get(i);

            System.out.println("currentResponse: "+currentResponse);

            retrievedRoom.setOfferId(currentResponse.getOfferId());
            retrievedRoom.setFrom(currentResponse.getAvailabilityBegin());
            retrievedRoom.setTo(currentResponse.getCheckoutDate());
            retrievedRoom.setNumberBed(currentResponse.getNumberBed());
            retrievedRoom.setImgUrl(currentResponse.getRoomUrl());
            retrievedRoom.setSearchedNumberPerson(currentResponse.getNumberPerson());
            retrievedRoom.setPrice(currentResponse.getPrice());

            retrievedRooms.add(retrievedRoom);
        }


        return retrievedRooms;
    }


    // Q3 for Comparator
    public List<OfferResponse>  getCompareOffer(BrowsingInfo browsingInfo) {
            List<OfferResponse> comparisonOffers = new ArrayList<OfferResponse>();

        List<NodeService> returnedHotels = new ArrayList<>();
        returnedHotels=  browseHotel(browsingInfo);

        returnedHotels.forEach( service -> {
            NodeService found = this.persistenecService.findNodeServiceByHotelNameAndHotelBookingServiceURL(service.getHotelName(), service.getHotelBookingServiceURL());

            Agency running = Agency.getInstance();

            String signUpUserName = running.getAgencyName().trim().toLowerCase().replace(" ", "_");

            if (found==null) {
                signUPToNode(found, signUpUserName, "password", running.getAgencyName());
            }

            found = this.persistenecService.findNodeServiceByHotelNameAndHotelBookingServiceURL(service.getHotelName(), service.getHotelBookingServiceURL());

            BrowseRequest request = new BrowseRequest();

            request.setIdAgency(found.getIdAgency());
            request.setPassword(found.getPassword());
            request.setArrivalDate(browsingInfo.getFrom());
            request.setDepartureDate(browsingInfo.getTo());
            request.setNumberPerson(browsingInfo.getNumberPerson());

//            NODE_URI_BROWSE_COMPARE;  // "/api/browsing" + "/compare/room" ->  @BrowseRequest
            
            NODE_URI_BROWSE_COMPARE= found.getHotelBrowsingServiceURL() + "/compare/room";  // "/api/browsing" + "/rooms" ->  @BrowseRequest

            System.out.println("In NodeUsage, in browse, NODE_URI_BROWSE_COMPARE: "+NODE_URI_BROWSE_COMPARE);
            System.out.println("In NodeUsage, in browse, Request body: "+request);

            OfferResponse[] reponsePayload = anyNodeProxy.postForObject(NODE_URI_BROWSE_COMPARE, request, OfferResponse[].class);
            assert reponsePayload != null;
            List<OfferResponse> offerResponses= Arrays.stream(reponsePayload).toList();

            for (int i = 0; i < offerResponses.size(); i++) {
                if (i==0) {
                    offerResponses.get(i).setOfferId(running.getAgencyName()+", "+running.getCity());
                }
                comparisonOffers.add(offerResponses.get(i));
            }
        });
            return comparisonOffers;
    }

}
