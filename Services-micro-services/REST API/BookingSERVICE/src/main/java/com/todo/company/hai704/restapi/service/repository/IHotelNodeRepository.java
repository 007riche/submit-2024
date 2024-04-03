package com.todo.company.hai704.restapi.service.repository;

import com.todo.company.hai704.restapi.service.entity.Hotel;
import com.todo.company.hai704.restapi.service.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelNodeRepository extends JpaRepository<Hotel, Long> {
}
