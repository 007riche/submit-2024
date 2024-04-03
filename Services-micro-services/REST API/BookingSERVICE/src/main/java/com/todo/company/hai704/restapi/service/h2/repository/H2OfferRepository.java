package com.todo.company.hai704.restapi.service.h2.repository;

import com.todo.company.hai704.restapi.service.entity.Offer;
import com.todo.company.hai704.restapi.service.h2.entities.H2Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2OfferRepository extends JpaRepository<H2Offer, Long> {
    H2Offer findFirstByOfferId(String offerId);
}
