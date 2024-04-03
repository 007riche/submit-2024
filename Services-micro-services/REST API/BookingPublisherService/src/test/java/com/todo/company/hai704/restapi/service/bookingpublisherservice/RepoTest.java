package com.todo.company.hai704.restapi.service.bookingpublisherservice;

import com.todo.company.hai704.restapi.service.bookingpublisherservice.entity.NodeService;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.h2.entities.H2NodeService;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.h2.repository.H2NodePublishServiceRepository;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.repository.INodePublishServiceRepository;
import com.todo.company.hai704.restapi.service.bookingpublisherservice.util.NodeServiceConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RepoTest {
    @Autowired
    private INodePublishServiceRepository repo;

    @Autowired
    private H2NodePublishServiceRepository h2NodePublishServiceRepository;


    @Test
    public void testAddNew() {
        NodeService user = NodeService.createNodeService();


        user.setHotelName("Hotel 55");
        user.setHotelStars(4.5);
        user.setHotelCountry("country 1");
        user.setHotelCity("city 1");
        user.setHotelStreetNumber(25);
        user.setHotelStreet("montdage avenue");
        user.setHotelGPS("25~ 90*");
        user.setHotelImageURL("/api/img/1");
        user.setHotelBookingServiceURL("/api/booking");
        user.setHotelBrowsingServiceURL("/api/browsing");
        user.setHotelPartnersServiceURL("/api/partnering");

        H2NodeService h2user = NodeServiceConverter.convertToH2NodeService(user);

        NodeService savedUser = repo.save(user);
        h2NodePublishServiceRepository.save(h2user);
//        h2NodePublishServiceRepository.save(user);
        Assertions.assertNotNull(savedUser);
//        Assertions.
    }

    @Test
    public void testListAll() {
        Iterable<NodeService> users =   repo.findAll();

        Assertions.assertNotNull(users);

        for (NodeService user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Long userId = 1L;
        Optional<NodeService> byId = repo.findById(userId);
        NodeService user = byId.get();
        user.setHotelName("geek manga Hotel");
        repo.save(user);

        Optional<NodeService> updatedUsers = repo.findById(userId);

        NodeService updatedUser = updatedUsers.get();
        Assertions.assertEquals(updatedUser.getHotelName(), "geek manga Hotel");

        System.out.println(updatedUser);
    }

    @Test
    public void testGet() {
        Long userId =2L;
        Optional<NodeService> byId = repo.findById(userId);
        NodeService user = byId.get();

        Assertions.assertNotNull(user.getHotelName());
        System.out.println(user);

    }

    @Test
    public void testDelete() {
        Long userId =2L;
        repo.deleteById(userId);

        Optional<NodeService> byId = repo.findById(userId);
//       Assertions.assertNull(byId);
        System.out.println(byId);
    }
}
