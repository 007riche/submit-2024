package com.todo.company.hai704.restapi.service.repository;

import com.todo.company.hai704.restapi.service.entity.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRoomRepository extends JpaRepository<Room, Integer> {
    Room findFirstByRoomNumber(int roomNumber);


    @Modifying
    @Transactional
    @Query("UPDATE Room r SET r.numberBed = :numberBed WHERE r.roomNumber = :roomNumber")
    int updateRoomByRoomNumber(@Param("roomNumber") Integer roomNumber, @Param("numberBed") Integer numberBed);

}
