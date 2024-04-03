package com.todo.company.hai704.restapi.service.bookingpublisherservice.controller;

import com.todo.company.hai704.restapi.service.bookingpublisherservice.entity.Agency;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.models.BrowsingInfo;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.models.OfferResponse;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.persistence.NodeServiceUsage;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.persistence.PersistenecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/publisher/api/comparison")
public class ComparisonController {

    @Autowired
    private PersistenecService persistenecService;



//    private final NodeServiceUsage nodeService = NodeServiceUsage.getInstance(persistenecService);
    private static Logger logger = LoggerFactory.getLogger(ComparisonController.class);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/agency")
    public boolean signUp(@RequestBody Agency agency) {

        logger.info("NWE AGENCY PUBLICATION: "+agency);
        Agency saved = persistenecService.saveAgency(agency);
        if (saved.getAgencyName().contains(agency.getAgencyName())) {
            return true;
        }
        return false;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/compare")
    public List<OfferResponse> getOfferCompare(@RequestBody BrowsingInfo browsingInfo) {

        logger.info("NEW COMPARISON BROWSING REQUEST: "+browsingInfo);

        List<OfferResponse> offerResponseList = new ArrayList<>();


        // Expecting persistenecService to be not null
        NodeServiceUsage nodeService = NodeServiceUsage.getInstance(persistenecService);

        offerResponseList = nodeService.getCompareOffer(browsingInfo);

        return offerResponseList;
    }

}
