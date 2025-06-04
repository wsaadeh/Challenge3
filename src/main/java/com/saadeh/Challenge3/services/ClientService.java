package com.saadeh.Challenge3.services;

import com.saadeh.Challenge3.dto.ClientDTO;
import com.saadeh.Challenge3.entities.Client;
import com.saadeh.Challenge3.repositories.ClientRepository;
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
        Client client = clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found."));
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
            throw new RuntimeException("Recurso não encontrado.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!clientRepository.existsById(id)){
            throw new RuntimeException("Recurso não encontrado.");
        }
        try {
            clientRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Falha de integridade referencial.");
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
