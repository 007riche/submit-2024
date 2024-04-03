package com.todo.company.hai704.restapi.service.controllers;

import com.todo.company.hai704.restapi.service.BookingServiceApplication;
import com.todo.company.hai704.restapi.service.entity.Partnership;
import com.todo.company.hai704.restapi.service.models.UserRequestBody;
import com.todo.company.hai704.restapi.service.repository.IBookingRepository;
import com.todo.company.hai704.restapi.service.repository.IPartnershipRepository;
import com.todo.company.hai704.restapi.service.repository.IRoomRepository;
import com.todo.company.hai704.restapi.service.services.PersistanceService;
import com.todo.company.hai704.restapi.service.utilpack.CustomUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partners")
@DependsOn("dataSourcesInitialization")
public class PartnershipController {

    @Autowired
    private PersistanceService persistanceService;

    private Logger logger = LoggerFactory.getLogger(BookingServiceApplication.class);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public String  signUpUser(@RequestBody UserRequestBody request) {
        Partnership partnership = new Partnership();
        partnership.setLoginId(request.getUserName());
        partnership.setPassword(request.getPassword());
        partnership.setAgencyName(request.getAgencyName());
        String newUUID = CustomUtility.generateUUID();
        double newDiscountRate = CustomUtility.generateRandomDouble(5, 20);
        partnership.setIdAgency(newUUID);
        partnership.setDiscountRate(newDiscountRate);

       Partnership retPartn= persistanceService.savePartnership(partnership);
       if (retPartn.getAgencyName()
               .contentEquals(partnership.getAgencyName())
       && retPartn.getIdAgency()
               .contentEquals(partnership.getIdAgency())) {
           return retPartn.getIdAgency();
       } else {
           return "";
       }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/update")
    public String updateCredentials(@RequestBody UserRequestBody request) {
       int affectedRow = persistanceService.updatePartnershipByIdAgency(request.getIdAgency(), request.getUserName(), request.getPassword());
       if (affectedRow == 1) return request.getIdAgency();
       else return "";
    }

    @GetMapping("/{idAgency}")
    public Partnership getPartnerById(@PathVariable String idAgency) {
        return persistanceService.findPartnershipByIdAgency(idAgency);
    }
}
