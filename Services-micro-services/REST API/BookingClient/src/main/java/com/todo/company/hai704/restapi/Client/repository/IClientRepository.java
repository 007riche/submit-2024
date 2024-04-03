package com.todo.company.hai704.restapi.Client.repository;

import com.todo.company.hai704.restapi.Client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByFirstNameAndLastNameAndCardNumber(String firstName, String lastName, String cardNumber);
}
