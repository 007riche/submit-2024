package com.todo.company.hai704.restapi.Client.services.persistence;


import com.todo.company.hai704.restapi.Client.entity.Agency;
import com.todo.company.hai704.restapi.Client.entity.BookingAgency;
import com.todo.company.hai704.restapi.Client.entity.Client;
import com.todo.company.hai704.restapi.Client.entity.NodeService;
import com.todo.company.hai704.restapi.Client.repository.IAgenceRepository;
import com.todo.company.hai704.restapi.Client.repository.IBookingAgencyRepository;
import com.todo.company.hai704.restapi.Client.repository.IClientRepository;
import com.todo.company.hai704.restapi.Client.repository.IPublishedServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@DependsOnDatabaseInitialization
public class PersistenecService {
    @Autowired
    private IAgenceRepository agenceRepository;

    @Autowired
    private IBookingAgencyRepository bookingAgencyRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IPublishedServicesRepository publishedServicesRepository;



    // Agency
    // Create or Update operation
    public Agency saveAgency(Agency agency) {
        return agenceRepository.save(agency);
    }

    public Agency saveOrUpdateAgency(Agency agency, String discoveryURL) {
        agency.setDiscoveryURL(discoveryURL); // Set the discovery URL as provided or modify as per your logic
        return agenceRepository.save(agency);
    }


    // Read operation for a single agency by ID
    public Optional<Agency> findAgencyById(int id) {
        return agenceRepository.findById(id);
    }

    // Read operation for all agencies
    public List<Agency> findAllAgencies() {
        return agenceRepository.findAll();
    }

    // Delete operation
    public void deleteAgency(int id) {
        agenceRepository.deleteById(id);
    }



    // Cilent
    // Create or Update operation
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    // Read operation for a single client by ID
    public Optional<Client> findClientById(Integer id) {
        return clientRepository.findById(id);
    }

    // Read operation for all clients
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    // Delete operation
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    // Update operation (Note: save method is used for both creating and updating)

    public Client findClientByFirstNameAndLastNameAndCardNumber(String firstName, String lastName, String cardNumber) {
       return clientRepository.findClientByFirstNameAndLastNameAndCardNumber(firstName, lastName, cardNumber);
    }
    public Client updateClient(Client client) {
        return saveClient(client); // This assumes the client object has an ID set to an existing entity
    }


    // PublishedServices
    public NodeService saveNodeService(NodeService nodeService) {
        return publishedServicesRepository.save(nodeService);
    }

    public Optional<NodeService> findNodeServiceById(Long id) {
        return publishedServicesRepository.findById(id);
    }

    public List<NodeService> findAllNodeServices() {
        return publishedServicesRepository.findAll();
    }

    public void deleteNodeService(Long id) {
        publishedServicesRepository.deleteById(id);
    }

    public NodeService updateNodeService(NodeService nodeService) {
        return publishedServicesRepository.save(nodeService); // Assuming you handle the update logic before calling this
    }

     public NodeService findFirstByHotelName(String hotelName) {
            return publishedServicesRepository.findFirstByHotelName(hotelName);
     }

    public NodeService findNodeServiceByHotelNameAndHotelAddress(String hotelName,  String hotelAddress) {
        return publishedServicesRepository.findNodeServiceByHotelNameAndHotelAddress( hotelName, hotelAddress);
    }

    public NodeService findFirstByHotelNameAndIdAgencyAndHotelBookingServiceURL(String hotelName, String idAgency, String hotelBookingServiceURL) {
        return publishedServicesRepository.findFirstByHotelNameAndIdAgencyAndHotelBookingServiceURL(hotelName, idAgency, hotelBookingServiceURL);
    }


    public NodeService findNodeServiceByHotelNameAndHotelBookingServiceURL(String hotelName, String hotelBookingServiceURL) {
        return publishedServicesRepository.findNodeServiceByHotelNameAndHotelBookingServiceURL(hotelName,  hotelBookingServiceURL);
    }


    // BookingAgency
    public BookingAgency saveBookingAgency(BookingAgency bookingAgency) {
        return bookingAgencyRepository.save(bookingAgency);
    }

    public List<BookingAgency> findAllBookingAgencies() {
        return bookingAgencyRepository.findAll();
    }

    public Optional<BookingAgency> findBookingAgencyById(int id) {
        return bookingAgencyRepository.findById(id);
    }

    public BookingAgency updateBookingAgency(BookingAgency bookingAgency) {
        return bookingAgencyRepository.save(bookingAgency);
    }

    public void deleteBookingAgencyById(int id) {
        bookingAgencyRepository.deleteById(id);
    }

}
