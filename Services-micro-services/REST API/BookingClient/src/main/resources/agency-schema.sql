
CREATE TABLE IF NOT EXISTS CLIENTS (
    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    lastName VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    cardNumber VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS AGENCYBOOKINGS (
    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    bookingReference VARCHAR(255) NOT NULL,
    totalPrice DECIMAL(7, 2) NOT NULL,
    arrivalDate DATE NOT NULL,
    checkoutDate DATE NOT NULL,
    numberPersons INT NOT NULL,
    mainPersonne VARCHAR(255) NOT NULL,
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES CLIENTS(Id) ON DELETE SET NULL
    );


CREATE TABLE IF NOT EXISTS SERVICES ( 
                    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
                    hotelName VARCHAR(50) NOT NULL, 
                    loginId VARCHAR(255),
                    idAgency VARCHAR(255),
                    userName VARCHAR(255),
                    Password VARCHAR(255),
                    hotelStars DECIMAL(7, 2) NOT NULL,
                    hotelImageURL VARCHAR(255) NOT NULL,
                    hotelAddress VARCHAR(255) NOT NULL,
                    hotelBookingServiceURL VARCHAR(255) NOT NULL, 
                    hotelBrowsingServiceURL VARCHAR(255) NOT NULL, 
                    hotelPartnersServiceURL VARCHAR(255) NOT NULL  
                );

CREATE TABLE IF NOT EXISTS AGENCY (
                    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                    agencyName VARCHAR(255) NOT NULL,
                    city VARCHAR(255) NOT NULL,
                    AGENCY_DB_NAME VARCHAR(255) NOT NULL,
                    port INT NOT NULL,
                    discoveryURL VARCHAR(255) NOT NULL
);