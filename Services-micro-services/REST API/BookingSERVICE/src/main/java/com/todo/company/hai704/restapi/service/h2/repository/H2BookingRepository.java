package com.todo.company.hai704.restapi.service.h2.repository;

import com.todo.company.hai704.restapi.service.h2.entities.H2Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface H2BookingRepository extends JpaRepository<H2Booking, Long> {
    H2Booking findBookingByBookingReference(String bookingReference);
    List<H2Booking> findBookingsByCheckoutDate(Date checkoutDate);
    List<H2Booking> findBookingByClientBookingFirstName(String clientBookingFirstName);
    List<H2Booking> findBookingsByClientBookingLastName(String clientBookingLastName);
}