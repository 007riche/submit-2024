package com.todo.company.hai704.restapi.service.bookingpublisherservice.util;

import com.todo.company.hai704.restapi.service.bookingpublisherservice.entity.NodeService;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.h2.entities.H2NodeService;

public class NodeServiceConverter {

    public static H2NodeService convertToH2NodeService(NodeService nodeService) {
        if (nodeService == null) {
            return null;
        }

        H2NodeService h2NodeService = new H2NodeService();
        h2NodeService.setId(nodeService.getId());
        h2NodeService.setHotelName(nodeService.getHotelName());
        h2NodeService.setHotelStars(nodeService.getHotelStars());
        h2NodeService.setHotelCountry(nodeService.getHotelCountry());
        h2NodeService.setHotelCity(nodeService.getHotelCity());
        h2NodeService.setHotelStreetNumber(nodeService.getHotelStreetNumber());
        h2NodeService.setHotelStreet(nodeService.getHotelStreet());
        h2NodeService.setHotelGPS(nodeService.getHotelGPS());
        h2NodeService.setHotelImageURL(nodeService.getHotelImageURL());
        h2NodeService.setHotelBookingServiceURL(nodeService.getHotelBookingServiceURL());
        h2NodeService.setHotelBrowsingServiceURL(nodeService.getHotelBrowsingServiceURL());
        h2NodeService.setHotelPartnersServiceURL(nodeService.getHotelPartnersServiceURL());

        return h2NodeService;
    }
}
