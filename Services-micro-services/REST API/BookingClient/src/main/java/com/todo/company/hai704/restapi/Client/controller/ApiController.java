package com.todo.company.hai704.restapi.Client.controller;

import com.todo.company.hai704.restapi.Client.models.guidatamodels.BrowsingInfo;
import com.todo.company.hai704.restapi.Client.models.restpayloadmodels.OfferResponse;
import com.todo.company.hai704.restapi.Client.services.persistence.PersistenecService;
import com.todo.company.hai704.restapi.Client.services.remote.NodeServiceUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class ApiController {

    @Autowired
    private PersistenecService persistenecService;


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/compare")
    public List<OfferResponse> getOfferCompare(@RequestBody BrowsingInfo browsingInfo) {
        List<OfferResponse> offerResponseList = new ArrayList<OfferResponse>();

        NodeServiceUsage nodeService = new NodeServiceUsage(persistenecService);

        offerResponseList = nodeService.getCompareOffer(browsingInfo);
        return offerResponseList;
    }
}
