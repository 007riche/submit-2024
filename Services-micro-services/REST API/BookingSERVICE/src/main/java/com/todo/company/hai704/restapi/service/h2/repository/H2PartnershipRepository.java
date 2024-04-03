package com.todo.company.hai704.restapi.service.h2.repository;

import com.todo.company.hai704.restapi.service.entity.Partnership;
import com.todo.company.hai704.restapi.service.h2.entities.H2Partnership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2PartnershipRepository extends JpaRepository<H2Partnership, Long> {
    H2Partnership findPartnershipByIdAgency(String idAgency);
}
