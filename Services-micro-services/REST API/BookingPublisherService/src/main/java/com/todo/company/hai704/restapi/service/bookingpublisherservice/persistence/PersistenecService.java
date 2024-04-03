package com.todo.company.hai704.restapi.service.bookingpublisherservice.persistence;

import com.todo.company.hai704.restapi.service.bookingpublisherservice.entity.Agency;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.entity.NodeService;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.exception.ServiceNotFoundException;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.h2.repository.H2NodePublishServiceRepository;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.repository.IAgenceRepository;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.repository.INodePublishServiceRepository;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.util.NodeServiceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersistenecService {

    @Autowired
    private INodePublishServiceRepository repository;

    @Autowired
    private IAgenceRepository agenceRepository;

    @Autowired
    private H2NodePublishServiceRepository h2NodePublishServiceRepository;

    public List<NodeService> findAll() {
        return  repository.findAll();
    }

    public NodeService findHotelById(Long id) throws ServiceNotFoundException {
        Optional<NodeService> results=  repository.findById(id);
        if (results.isPresent()) {
            return results.get();
        }
       else throw new ServiceNotFoundException("Could not find any Service with ID "+ id);
    }

    public NodeService save(NodeService service) {
        h2NodePublishServiceRepository.save(NodeServiceConverter.convertToH2NodeService(service));
        return repository.save(service);
    }

    public List<NodeService> findByCity(String country, String city) {
        List<NodeService> inCountry =  repository.findByHotelCountry(country);
        List<NodeService> inCity =  repository.findByHotelCity(city);

        List<NodeService> retrieved = new ArrayList<>();

        for (NodeService service: inCountry) {
            if (inCity.contains(service)) {
                retrieved.add(service);
            }
        }
        return  retrieved;
    }
    public List<NodeService> findByCityOfSameSameName(String city) {
        return    repository.findByHotelCity(city);
    }



    public List<NodeService> findByHotelCountry(String country) {
        return repository.findByHotelCountry(country);
    }

    public List<NodeService> findByHotelStars(double stars) {
        List<NodeService> allHotels = repository.findAll();
        List<NodeService> rangedStars = new ArrayList<>();

        if (!allHotels.isEmpty()) {
            for (NodeService nodeService : allHotels) {
                if (nodeService.getHotelStars()>=stars) {
                    rangedStars.add(nodeService);
                }
            }
        }

        return rangedStars;
    }



    // Agency
    // Create or Update operation
    public Agency saveAgency(Agency agency) {
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

}
