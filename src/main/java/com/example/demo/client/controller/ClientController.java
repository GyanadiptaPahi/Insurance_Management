package com.example.demo.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.entity.Client;
import com.example.demo.client.service.ClientService;
import com.example.demo.insurancePolicy.entity.InsurancePolicy;
import com.example.demo.insurancePolicy.repository.InsurancePolicyRepository;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    
    
    private InsurancePolicyRepository policyRepository;
    
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }
    
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable(value = "id") Long id) {
        return clientService.getClientById(id);
    }
    
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long id, @RequestBody Client clientDetails) {
        Client updatedClient = clientService.updateClient(id, clientDetails);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable(value = "id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client with ID " + id + " has been deleted successfully.");
    }
    
   


}
