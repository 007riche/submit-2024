package com.todo.company.hai704.restapi.service.services;


import com.todo.company.hai704.restapi.service.BookingServiceApplication;
import com.todo.company.hai704.restapi.service.entity.*;
import com.todo.company.hai704.restapi.service.h2.entities.*;
import com.todo.company.hai704.restapi.service.h2.repository.*;
import com.todo.company.hai704.restapi.service.repository.*;
import com.todo.company.hai704.restapi.service.utilpack.EntityConverter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PersistanceService {

    @Autowired
    private ResourceLoader resourceLoader;

    private Logger logger = LoggerFactory.getLogger(BookingServiceApplication.class);

    // Primary (Mysql) Repositories
    @Autowired
    private IPartnershipRepository partnershipRepository;

    @Autowired
    private IBookingRepository bookingRepository;

    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IHotelNodeRepository hotelNodeRepository;

    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    private IOfferRepository offerRepository;


    // H2 Repositories
    @Autowired
    private H2PartnershipRepository h2partnershipRepository;

    @Autowired
    private H2BookingRepository h2bookingRepository;

    @Autowired
    private H2RoomRepository h2roomRepository;

    @Autowired
    private H2HotelNodeRepository h2HotelNodeRepository;

    @Autowired
    private H2ImageRepository h2ImageRepository;

    @Autowired
    private H2OfferRepository h2OfferRepository;

    // Image
//    @Transactional
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

//    @Transactional
    public Image saveImage(String imgName, String imgPath)
            throws IOException
    {
        // Load the file as a resource
        File file = resourceLoader.getResource("classpath:" + imgPath).getFile();

        // Convert file to byte array
        byte[] imgData = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(imgData);
        } catch (IOException e) {
            throw new IOException("Error reading image file", e);
        }

        // Create and populate the Image entity
        Image image = new Image();
        image.setImgName(imgName);
        image.setImg(imgData);

        // Save the image entity using the repository
        return imageRepository.save(image);
    }

//    @Transactional
    public URL saveImageToFile(String imgName, String outputPath, String outputFileName) throws IOException {
        // Retrieve the image entity by its name
        Image image = imageRepository.findImageByImgName(imgName);
        if (image == null) {
            throw new IllegalStateException("Image not found with name: " + imgName);
        }

        URL retURL = null;

//        resourceLoader.getResource("classpath:" + outputPath+outputFileName).getFile();
        logger.info("From persist: Retrived image name: "+image.getImgName());

        logger.warn("Recup et creation de nouvelle image Dans persistance: outputPath: "+outputPath+"  outputFileName:"+outputFileName);

        // Prepare the output directory
        File outputDir = new File(outputPath);
        if (!outputDir.exists()) {
            boolean dirCreated = outputDir.mkdirs();
            if (!dirCreated) {
                logger.error("Failed to create the output directory: {}", outputPath);
//                return false;
            }
        }

        logger.info( outputPath+ " dir exists");
        logger.info( "Path: "+outputDir.getAbsolutePath());

        // Prepare the output file
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            logger.info("trying to create file "+ outputPath);
            File file = new File(Objects.requireNonNull(classLoader.getResource(outputPath)).toURI());
            String dirPath = file.getAbsolutePath();
            String fullPath = dirPath+ outputFileName;

            logger.info("Full path "+ fullPath);
            File outputFile = new File(fullPath);
            logger.info("file output stream: ", outputFile);
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            // Write the image byte array to the file
            logger.error("From persist, Trying to open file writing stream");
            fileOutputStream.write(image.getImg());
            fileOutputStream.close();
            URI fileUri = outputFile.toURI();

            URL fileUrl = fileUri.toURL();
            retURL = fileUrl;
        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
        }

        return retURL;


//        try {
//
//        } catch (IOException e) {
//            logger.error("Error writing image file: {}", e.getMessage());
//            return false;
//        }
//        logger.error("Wanting to return true");
//        return true;
    }

    public Image findImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public List<Image> findAllImages() {
        return imageRepository.findAll();
    }

    public Image findImageByImgName(String imgName) {
        return imageRepository.findImageByImgName(imgName);
    }

//    @Transactional
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    public Image updateImage(Integer id, Image updatedImage) {
        Image image = imageRepository.findById(Long.valueOf(id)).orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        image.setImgName(updatedImage.getImgName());
        image.setImg(updatedImage.getImg());
        // Assuming `setRoom` method exists and you want to update it as well.
        image.setRoom(updatedImage.getRoom());
        h2ImageRepository.save(EntityConverter.convertToH2Image(image));
        return imageRepository.save(image);
    }

    @Transactional
    public int updateImageByName(String imgName, byte[] img) {
        return imageRepository.updateImageByName(imgName, img);
    }


    // Room
    @Transactional
    public Room saveRoom(Room room) {
        Image image = room.getImage();
        Image found = imageRepository.findImageByImgName(image.getImgName());

        // First, handle the image - either save a new one or use an existing one
        if (image != null && found == null) {
            image = imageRepository.save(room.getImage());
            // Also save the image in H2 database
            H2Image h2Image = EntityConverter.convertToH2Image(image);
//            h2ImageRepository.save(h2Image);
        } else {
            room.setImage(found);
        }

        // Save the room in both MySQL and H2 databases
        Room savedRoom = roomRepository.save(room);
        H2Room h2Room = EntityConverter.convertToH2Room(room);
        h2roomRepository.save(h2Room);

        return savedRoom;
    }


    public Room findRoomById(Integer id) {
        return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));
    }

    public Room findFirstByRoomNumber(int roomNumber) {
        return roomRepository.findFirstByRoomNumber(roomNumber);
    }

    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

//    @Transactional
    public void deleteRoom(Integer id) {
        h2roomRepository.deleteById(id);
        roomRepository.deleteById(id);
    }

//    @Transactional
    public int updateRoomByRoomNumber(Integer roomNumber, Integer numberBed) {
        return roomRepository.updateRoomByRoomNumber(roomNumber, numberBed);
    }

    @Transactional
    public Room updateRoom(Integer id, Room updatedRoom) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));
        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setNumberBed(updatedRoom.getNumberBed());
        room.setBasePrice(updatedRoom.getBasePrice());
        room.setImgName(updatedRoom.getImgName());
        // Update relationships if necessary, e.g., `setImage`, `setBooking`, `setOffers`
        return roomRepository.save(room);
    }



    // Booking
//    @Transactional
    public Booking saveBooking(Booking booking) {
        h2bookingRepository.save(EntityConverter.convertToH2Booking(booking));
        return bookingRepository.save(booking);
    }

    public List<Booking> findAllBooking() {
        return  bookingRepository.findAll();
    }

    public Booking findBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));
    }


    public Booking findBookingByBookingReference(String bookingReference) {
        return bookingRepository.findBookingByBookingReference(bookingReference);
    }


    public List<Booking> findBookingsByCheckoutDate(Date checkoutDate) {
        return bookingRepository.findBookingsByCheckoutDate(checkoutDate);
    }

    public List<Booking> findBookingByClientBookingFirstName(String clientBookingFirstName) {
        return bookingRepository.findBookingByClientBookingFirstName(clientBookingFirstName);
    }


    public List<Booking> findBookingsByClientBookingLastName(String clientBookingLastName) {
        return bookingRepository.findBookingsByClientBookingLastName(clientBookingLastName);
    }

//    @Transactional
    public void deleteBooking(Long id) {
        h2bookingRepository.deleteById(id);
        bookingRepository.deleteById(id);
    }

    @Transactional
    public int updateBookingByReference(String bookingReference, String firstName, String lastName) {
        return bookingRepository.updateBookingByReference(bookingReference, firstName, lastName);
    }


//    @Transactional
    public Booking updateBooking(Long id, Booking updatedBooking) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));
        booking.setBookingReference(updatedBooking.getBookingReference());
        booking.setClientBookingLastName(updatedBooking.getClientBookingLastName());
        booking.setClientBookingFirstName(updatedBooking.getClientBookingFirstName());
        booking.setArrivalDate(updatedBooking.getArrivalDate());
        booking.setCheckoutDate(updatedBooking.getCheckoutDate());
        booking.setNumberPersons(updatedBooking.getNumberPersons());
        // Update relationships if necessary, e.g., `setRooms`
        return bookingRepository.save(booking);
    }


    // Offer

    public List<Offer> findAllOffer() {
        return offerRepository.findAll();
    }

//    @Transactional
    public Offer saveOffer(Offer offer) {
//        h2OfferRepository.save(EntityConverter.convertToH2Offer(offer));
        return offerRepository.save(offer);
    }

    public Offer findOfferById(Long id) {
        return offerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + id));
    }

    public Offer findFirstByOfferId(String offerId) {
        return offerRepository.findFirstByOfferId(offerId);
    }

//    @Transactional
    public void deleteOffer(Long id) {
        h2OfferRepository.deleteById(id);
        offerRepository.deleteById(id);
    }

//    @Transactional
    public Offer updateOffer(Long id, Offer updatedOffer) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + id));
        offer.setOfferId(updatedOffer.getOfferId());
        offer.setAvailabilityBegin(updatedOffer.getAvailabilityBegin());
        offer.setCheckoutDate(updatedOffer.getCheckoutDate());
        offer.setNumberPerson(updatedOffer.getNumberPerson());
        // Assuming `setRoom` method exists and you want to update it as well.
        offer.setRoom(updatedOffer.getRoom());
        return offerRepository.save(offer);
    }

    @Transactional
    public int updateOfferByOfferId(String offerId, Date availabilityBegin, Date checkoutDate) {
        return offerRepository.updateOfferByOfferId(offerId, availabilityBegin, checkoutDate);
    }


    // Partnership

//    @Transactional
    public Partnership savePartnership(Partnership partnership) {
        h2partnershipRepository.save(EntityConverter.convertToH2Partnership(partnership));
        return partnershipRepository.save(partnership);
    }

    public Partnership findPartnershipById(Long id) {
        return partnershipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Partnership not found with id: " + id));
    }

    public Partnership findPartnershipByIdAgency(String idAgency) {
        return partnershipRepository.findPartnershipByIdAgency(idAgency);
    }

    @Transactional
    public int updatePartnershipByIdAgency(String idAgency, String agencyName, String password) {
        return partnershipRepository.updatePartnershipByIdAgency(idAgency, agencyName, password);
    }

//    @Transactional
    public void deletePartnership(Long id) {
        h2partnershipRepository.deleteById(id);
        partnershipRepository.deleteById(id);
    }

//    @Transactional
    public Partnership updatePartnership(Long id, Partnership updatedPartnership) {
        Partnership partnership = partnershipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Partnership not found with id: " + id));
        partnership.setIdAgency(updatedPartnership.getIdAgency());
        partnership.setAgencyName(updatedPartnership.getAgencyName());
        partnership.setLoginId(updatedPartnership.getLoginId());
        partnership.setPassword(updatedPartnership.getPassword());
        partnership.setDiscountRate(updatedPartnership.getDiscountRate());
        return partnershipRepository.save(partnership);
    }

    // Hotel

//    @Transactional
    public Hotel saveHotel(Hotel hotel) {
        h2HotelNodeRepository.save(EntityConverter.convertToH2Hotel(hotel));
        return hotelNodeRepository.save(hotel);
    }

    public Hotel findHotelById(Long id) {
        return hotelNodeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel not found with id: " + id));
    }

    public List<Hotel> findAllHotels() {
        return hotelNodeRepository.findAll();
    }


    // @Transactional
    public void deleteHotel(Long id) {
        h2HotelNodeRepository.deleteById(id);
        hotelNodeRepository.deleteById(id);
    }


    // @Transactional
    public Hotel updateHotel(Long id, Hotel updatedHotel) {
        Hotel hotel = hotelNodeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel not found with id: " + id));
        hotel.setName(updatedHotel.getName());
        hotel.setStars(updatedHotel.getStars());
        hotel.setCountry(updatedHotel.getCountry());
        hotel.setCity(updatedHotel.getCity());
        hotel.setStreet(updatedHotel.getStreet());
        hotel.setStreetNumber(updatedHotel.getStreetNumber());
        hotel.setGpsPosition(updatedHotel.getGpsPosition());
        hotel.setHotelImgUrl(updatedHotel.getHotelImgUrl());
        // Update URLs if necessary
        hotel.setBOOKING_URL(updatedHotel.getBOOKING_URL());
        hotel.setBROWSING_URL(updatedHotel.getBROWSING_URL());
        hotel.setPARTNERS_URL(updatedHotel.getPARTNERS_URL());
        return hotelNodeRepository.save(hotel);
    }


    // H2
    // H2 Booking Repository Methods

    // @Transactional
    public H2Booking saveH2Booking(H2Booking booking) {
        return h2bookingRepository.save(booking);
    }

    public H2Booking findH2BookingById(Long id) {
        return h2bookingRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("H2 Booking not found with id: " + id));
    }

//    public H2Booking findH2BookingByBookingReference(String bookingReference) {
//        return h2bookingRepository.findBookingByBookingReference(bookingReference);
//    }
//
//    public List<H2Booking> findH2BookingsByCheckoutDate(Date checkoutDate) {
//        return h2bookingRepository.findBookingsByCheckoutDate(checkoutDate);
//    }
//
//    public List<H2Booking> findH2BookingByClientBookingFirstName(String clientBookingFirstName) {
//        return h2bookingRepository.findBookingByClientBookingFirstName(clientBookingFirstName);
//    }
//
//    public List<H2Booking> findH2BookingsByClientBookingLastName(String clientBookingLastName) {
//        return h2bookingRepository.findBookingsByClientBookingLastName(clientBookingLastName);
//    }


    // @Transactional
    public void deleteH2Booking(Long id) {
        h2bookingRepository.deleteById(id);
    }


    // H2 Room Repository Operations

    // @Transactional
    public H2Room saveH2Room(H2Room room) {
        return h2roomRepository.save(room);
    }

    public H2Room findH2RoomById(Integer id) {
        return h2roomRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("H2 Room not found with id: " + id));
    }

    public H2Room findH2FirstByRoomNumber(int roomNumber) {
        return h2roomRepository.findFirstByRoomNumber(roomNumber);
    }

    public List<H2Room> findH2AllRooms() {
        return h2roomRepository.findAll();
    }


    // @Transactional
    public void deleteH2Room(Integer id) {
        h2roomRepository.deleteById(id);
    }

    // H2 Offer Repository Operations
    // @Transactional
    public H2Offer saveH2Offer(H2Offer offer) {
        return h2OfferRepository.save(offer);
    }

    public H2Offer findH2OfferById(Long id) {
        return h2OfferRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("H2 Offer not found with id: " + id));
    }

    public H2Offer findH2FirstByOfferId(String offerId) {
        return h2OfferRepository.findFirstByOfferId(offerId);
    }


    // @Transactional
    public void deleteH2Offer(Long id) {
        h2OfferRepository.deleteById(id);
    }

    // H2 Partnership Repository Operations

    // @Transactional
    public H2Partnership saveH2Partnership(H2Partnership partnership) {
        return h2partnershipRepository.save(partnership);
    }

    public H2Partnership findH2PartnershipById(Long id) {
        return h2partnershipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("H2 Partnership not found with id: " + id));
    }

    public H2Partnership findH2PartnershipByIdAgency(String idAgency) {
        return h2partnershipRepository.findPartnershipByIdAgency(idAgency);
    }


    // @Transactional
    public void deleteH2Partnership(Long id) {
        h2partnershipRepository.deleteById(id);
    }

    // H2 Hotel Node Repository Operations

    // @Transactional
    public H2Hotel saveH2Hotel(H2Hotel hotel) {
        return h2HotelNodeRepository.save(hotel);
    }

    public H2Hotel findH2HotelById(Long id) {
        return h2HotelNodeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("H2 Hotel not found with id: " + id));
    }

    public List<H2Hotel> findH2AllHotels() {
        return h2HotelNodeRepository.findAll();
    }

    // @Transactional

    public void deleteH2Hotel(Long id) {
        h2HotelNodeRepository.deleteById(id);
    }

    // H2 Image Repository Operations
    // @Transactional
    public H2Image saveH2Image(H2Image image) {
        return h2ImageRepository.save(image);
    }

    public H2Image findH2ImageById(Long id) {
        return h2ImageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("H2 Image not found with id: " + id));
    }

    public H2Image findH2ImageByImgName(String imgName) {
        return h2ImageRepository.findImageByImgName(imgName);
    }

    public List<H2Image> findH2AllImages() {
        return h2ImageRepository.findAll();
    }

    // @Transactional
    public void deleteH2Image(Long id) {
        h2ImageRepository.deleteById(id);
    }
}
