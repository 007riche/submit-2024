package com.todo.company.hai704.restapi.Client.models.restpayloadmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListOfferResponseWrapper {

    List<OfferResponse> offerResponses;

    public ListOfferResponseWrapper() {
    }

    public List<OfferResponse> getOfferResponses() {
        return offerResponses;
    }

    public void setOfferResponses(List<OfferResponse> offerResponses) {
        this.offerResponses = offerResponses;
    }
}
