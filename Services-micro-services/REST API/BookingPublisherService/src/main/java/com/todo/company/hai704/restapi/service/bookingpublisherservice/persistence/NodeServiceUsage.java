package com.todo.company.hai704.restapi.service.bookingpublisherservice.persistence;


import com.todo.company.hai704.restapi.service.bookingpublisherservice.controller.PublisherController;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.entity.Agency;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.entity.NodeService;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.models.BrowsingInfo;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.models.OfferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class NodeServiceUsage {
    private static NodeServiceUsage instance;
    private static Logger logger = LoggerFactory.getLogger(NodeServiceUsage.class);

    private static String AGENCY_URI_BROWSE_COMPARE;  // "/compare" + "/"  -> @BrowsingInfo
    private static  RestTemplate agencyNodeProxy = new RestTemplate();


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


    // Q3 for Comparator
    public List<OfferResponse>  getCompareOffer(BrowsingInfo browsingInfo) {
            List<OfferResponse> comparisonOffers = new ArrayList<OfferResponse>();

            List<Agency> agencies = new ArrayList<>();

            agencies = persistenecService.findAllAgencies();


            // NumberOfAgency * AverageTimeOfSingleReq = time ?
            agencies.forEach(agency -> {
                // "/compare" + "/"  -> @BrowsingInfo
                 AGENCY_URI_BROWSE_COMPARE = agency.getDiscoveryURL() + "/compare";

                 logger.info("From PublisherService, AGENCY_URI_BROWSE_COMPARE: "+AGENCY_URI_BROWSE_COMPARE);
                logger.info("From PublisherService, Browsing payload: "+browsingInfo);

                OfferResponse[] reponsePayload = agencyNodeProxy
                        .postForObject(AGENCY_URI_BROWSE_COMPARE, browsingInfo, OfferResponse[].class);


                assert reponsePayload != null;
                List<OfferResponse> offerResponses= Arrays.stream(reponsePayload).toList();

                offerResponses.forEach(offerResponse -> {
                    comparisonOffers.add(offerResponse);
                });
            });

            return comparisonOffers;
    }

}
