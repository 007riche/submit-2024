package com.todo.company.hai704.restapi.service.h2.repository;

import com.todo.company.hai704.restapi.service.entity.Hotel;
import com.todo.company.hai704.restapi.service.h2.entities.H2Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2HotelNodeRepository extends JpaRepository<H2Hotel, Long> {
}
