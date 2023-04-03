package com.example.demo.claim.controller;

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

import com.example.demo.claim.entity.Claim;
import com.example.demo.claim.service.ClaimService;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {
    @Autowired
    private ClaimService claimService;

    @GetMapping
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
    }

    @GetMapping("/{id}")
    public Claim getClaimById(@PathVariable(value = "id") Long id) {
        return claimService.getClaimById(id);
    }

    @PostMapping
    public ResponseEntity<Claim> createClaim(@RequestBody Claim claim) {
        Claim createdClaim = claimService.createClaim(claim);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClaim);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClaim(@PathVariable(value = "id") Long id, @RequestBody Claim claimDetails) {
        Claim updatedClaim = claimService.updateClaim(id, claimDetails);
        String message = "Claim updated successfully with ID: " + updatedClaim.getId();
        return ResponseEntity.ok().body(message);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClaim(@PathVariable(value = "id") Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.ok("Claim deleted successfully");
    }
}
