package com.todo.company.hai704.restapi.service.h2.repository;

import com.todo.company.hai704.restapi.service.entity.Image;
import com.todo.company.hai704.restapi.service.h2.entities.H2Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2ImageRepository extends JpaRepository<H2Image, Long> {
    H2Image findImageByImgName(String imgName);
}
