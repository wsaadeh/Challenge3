package com.saadeh.Challenge3.repositories;

import com.saadeh.Challenge3.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
