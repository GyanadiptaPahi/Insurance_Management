package com.example.demo.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.entity.Client;
import com.example.demo.client.repository.ClientRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.insurancePolicy.entity.InsurancePolicy;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
    }
    
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
    
    public Client updateClient(Long id, Client clientDetails) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        
        client.setName(clientDetails.getName());
        client.setDateOfBirth(clientDetails.getDateOfBirth());
        client.setAddress(clientDetails.getAddress());
        client.setContactInformation(clientDetails.getContactInformation());
        
        return clientRepository.save(client);
    }
    
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        
        clientRepository.delete(client);
    }
}

