-- CREATE DATABASE IF NOT EXISTS TPSERVICESERVICEDB;

CREATE TABLE IF NOT EXISTS SERVICES (
    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    hotelName VARCHAR(255) NOT NULL,
    hotelStars DECIMAL(2, 1) NOT NULL,
    hotelCountry VARCHAR(255) NOT NULL,
    hotelCity VARCHAR(255) NOT NULL,
    hotelStreetNumber INTEGER,
    hotelStreet VARCHAR(255),
    hotelGPS VARCHAR(255),
    hotelImageURL TEXT(4000) NOT NULL,
    hotelBookingServiceURL TEXT(4000) NOT NULL,
    hotelBrowsingServiceURL TEXT(4000) NULL,
    hotelPartnersServiceURL TEXT(4000) NULL
    );

CREATE TABLE IF NOT EXISTS AGENCY (
    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    agencyName VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    discoveryURL VARCHAR(255) NOT NULL
);