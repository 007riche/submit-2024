package com.todo.company.hai704.restapi.service.repository;

import com.todo.company.hai704.restapi.service.entity.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IImageRepository extends JpaRepository<Image, Long> {
    Image findImageByImgName(String imgName);
    @Modifying
    @Transactional
    @Query("UPDATE Image i SET i.img = :img WHERE i.imgName = :imgName")
    int updateImageByName(@Param("imgName") String imgName, @Param("img") byte[] img);

}
