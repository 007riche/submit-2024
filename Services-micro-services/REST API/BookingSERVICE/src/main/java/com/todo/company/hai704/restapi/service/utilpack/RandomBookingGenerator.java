package com.todo.company.hai704.restapi.service.utilpack;



import com.todo.company.hai704.restapi.service.entity.Booking;
import com.todo.company.hai704.restapi.service.entity.Room;
import com.todo.company.hai704.restapi.service.services.PersistanceService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.todo.company.hai704.restapi.service.utilpack.CustomUtility.generateRandomInt;

public class RandomBookingGenerator {

    private static final String[] FIRST_NAMES = {"John", "Mary", "David", "Sarah", "Michael", "Elizabeth", "Christopher", "Emily", "William", "Ava"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Garcia", "Miller", "Davis", "Martinez", "Anderson"};
    private static final int[] NUM_PERSONS_OPTIONS = {1, 2, 3, 4};
    private static final int BOOKING_DATE_RANGE = 365; // number of days in the future to generate bookings

    private static final Random rand = new Random();

    public static Booking generateRandomBooking(PersistanceService service, int room) {
        Booking booking = new Booking();

        // generate random booking reference
        String bookingRef =   generateUUID(); // "BOOK-" + rand.nextInt(1000000);
        booking.setBookingReference(bookingRef);

        // generate random client name
        String firstName = FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
        booking.setClientBookingFirstName(firstName);
        booking.setClientBookingLastName(lastName);

        // generate random arrival and checkout dates
        LocalDate today = LocalDate.now();
        LocalDate arrivalDate = today.plus(rand.nextInt(BOOKING_DATE_RANGE), ChronoUnit.DAYS);
        LocalDate checkoutDate = arrivalDate.plus(rand.nextInt(7) + 1, ChronoUnit.DAYS);
        booking.setArrivalDate(java.sql.Date.valueOf(arrivalDate));
        booking.setCheckoutDate(java.sql.Date.valueOf(checkoutDate));

        // generate random number of persons
        int numPersons = NUM_PERSONS_OPTIONS[rand.nextInt(NUM_PERSONS_OPTIONS.length)];
        booking.setNumberPersons(numPersons);


        // generate random room id
        int roomId = rand.nextInt(10) + 1; // assume 10 rooms available
//        booking.setRoomId(roomId);
        Room room1 = service.findRoomById(room);

        booking.setRoom(room1);

        return booking;
    }

    public static List<Booking> generateRandomBookings(PersistanceService service, int numBookings, int roomNumberLimit) {
        List<Booking> bookings = new ArrayList<>();
        for (int i = 0; i < numBookings; i++) {
            bookings.add(generateRandomBooking(service, generateRandomInt(1, roomNumberLimit)));
        }
        return bookings;
    }



    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
