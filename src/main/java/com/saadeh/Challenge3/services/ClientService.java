package com.saadeh.Challenge3.services;

import com.saadeh.Challenge3.dto.ClientDTO;
import com.saadeh.Challenge3.entities.Client;
import com.saadeh.Challenge3.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found."));
        return new ClientDTO(client);
    }
}
