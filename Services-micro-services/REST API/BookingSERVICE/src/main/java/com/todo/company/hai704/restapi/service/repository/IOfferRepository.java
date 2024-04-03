package com.todo.company.hai704.restapi.service.repository;

import com.todo.company.hai704.restapi.service.entity.Offer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface IOfferRepository extends JpaRepository<Offer, Long> {
    Offer findFirstByOfferId(String offerId);
    @Modifying
    @Transactional
    @Query("UPDATE Offer o SET o.availabilityBegin = :availabilityBegin, o.checkoutDate = :checkoutDate WHERE o.offerId = :offerId")
    int updateOfferByOfferId(@Param("offerId") String offerId, @Param("availabilityBegin") Date availabilityBegin, @Param("checkoutDate") Date checkoutDate);

}
