package com.todo.company.hai704.restapi.service.h2.repository;

import com.todo.company.hai704.restapi.service.entity.Room;
import com.todo.company.hai704.restapi.service.h2.entities.H2Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2RoomRepository extends JpaRepository<H2Room, Integer> {
    H2Room findFirstByRoomNumber(int roomNumber);
}
