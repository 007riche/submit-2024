package com.todo.company.hai704.restapi.service.repository;

import com.todo.company.hai704.restapi.service.entity.Booking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IBookingRepository extends JpaRepository<Booking, Long> {
    Booking findBookingByBookingReference(String bookingReference);
    List<Booking> findBookingsByCheckoutDate(Date checkoutDate);
    List<Booking> findBookingByClientBookingFirstName(String clientBookingFirstName);
    List<Booking> findBookingsByClientBookingLastName(String clientBookingLastName);
    @Modifying
    @Transactional
    @Query("UPDATE Booking b SET b.clientBookingFirstName = :firstName, b.clientBookingLastName = :lastName WHERE b.bookingReference = :bookingReference")
    int updateBookingByReference(@Param("bookingReference") String bookingReference, @Param("firstName") String firstName, @Param("lastName") String lastName);

}