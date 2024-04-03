CREATE TABLE IF NOT EXISTS  IMAGES    
                ( Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,  
                imgName VARCHAR(255)  NOT NULL UNIQUE,
                IMG BLOB(4194304)  NOT NULL);

CREATE TABLE IF NOT EXISTS ROOMS (
                    Id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                    roomNumber INT NOT NULL UNIQUE, 
                    numberBed INT NOT NULL, 
                    basePrice DECIMAL(7,2) NOT NULL,
                    imgName VARCHAR(255) NOT NULL,
                    imgId INT,
                    CONSTRAINT fk_image FOREIGN KEY (imgId) REFERENCES IMAGES(Id) ON DELETE SET NULL
                );

CREATE TABLE IF NOT EXISTS PARTNERS ( 
                    Id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                    idAgency VARCHAR(255) NOT NULL UNIQUE,
                    agencyName VARCHAR(255) NOT NULL, 
                    loginId VARCHAR(255) NOT NULL UNIQUE, 
                    password VARCHAR(255) NOT NULL, 
                    discountRate DECIMAL(5,2) NOT NULL  
                );

CREATE TABLE IF NOT EXISTS BOOKINGS ( 
                    Id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                    bookingReference VARCHAR(255) NOT NULL,
                    clientBookingLastName VARCHAR(255) NOT NULL,
                    clientBookingFirstName VARCHAR(255) NOT NULL, 
                    arrivalDate DATE NOT NULL, 
                    checkoutDate DATE NOT NULL, 
                    numberPersons INT NOT NULL,
                    numberBed INT NOT NULL,
                    roomId BIGINT,
                    CONSTRAINT fk_booking FOREIGN KEY (roomId) REFERENCES ROOMS(Id) ON DELETE SET NULL
                );

CREATE TABLE IF NOT EXISTS OFFERS (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      offerId VARCHAR(255) NOT NULL UNIQUE,
                                      availabilityBegin DATETIME NOT NULL,
                                      checkoutDate DATETIME NOT NULL,
                                      numberPerson INT NOT NULL,
                                      numberBed INT NOT NULL,
                                      price DECIMAL(7,2) NOT NULL,
                                      roomUrl VARCHAR(255),
                                      room_id BIGINT,
                                      CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES ROOMS(Id) ON DELETE SET NULL
);


CREATE TABLE IF NOT EXISTS SERVICES (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        stars DOUBLE NOT NULL,
                                        country VARCHAR(255) NOT NULL,
                                        city VARCHAR(255) NOT NULL,
                                        street VARCHAR(255) NOT NULL,
                                        streetNumber INT NOT NULL,
                                        gpsPosition VARCHAR(255) NOT NULL,
                                        hotelImgUrl VARCHAR(255)NOT NULL,
                                        HOTEL_DOMAIN VARCHAR(255)NOT NULL,
                                        BOOKING_URL VARCHAR(255)NOT NULL,
                                        BROWSING_URL VARCHAR(255)NOT NULL,
                                        PARTNERS_URL VARCHAR(255)NOT NULL
);


