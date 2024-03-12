package com.example.mjs.repository;

import com.example.mjs.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client save(Client client);
    Client findByIdAndPw(String id,String pw);
    Client findById(String id);
}
