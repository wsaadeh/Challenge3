package com.saadeh.Challenge3.services;

import com.saadeh.Challenge3.dto.ClientDTO;
import com.saadeh.Challenge3.entities.Client;
import com.saadeh.Challenge3.repositories.ClientRepository;
import com.saadeh.Challenge3.services.exceptions.ClientException;
import com.saadeh.Challenge3.services.exceptions.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(()-> new ClientException("Client not found."));
        return new ClientDTO(client);
    }

    @Transactional
    public Page<ClientDTO> findAll(Pageable pageable){
        return clientRepository.findAll(pageable).map(
                x-> new ClientDTO(x)
        );
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client client = new Client();
        copyDtoToEntity(dto,client);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try {
            Client client = clientRepository.getReferenceById(id);
            copyDtoToEntity(dto,client);
            client = clientRepository.save(client);
            return new ClientDTO(client);
        }catch (EntityNotFoundException e){
            throw new ClientException("Client not found.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!clientRepository.existsById(id)){
            throw new ClientException("Client not found.");
        }
        try {
            clientRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Data integrity violation.");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}
