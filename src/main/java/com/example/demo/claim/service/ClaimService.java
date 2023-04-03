package com.example.demo.claim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.claim.entity.Claim;
import com.example.demo.claim.repository.ClaimRepository;
import com.example.demo.client.entity.Client;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.insurancePolicy.entity.InsurancePolicy;
import com.example.demo.insurancePolicy.repository.InsurancePolicyRepository;

@Service
public class ClaimService {
    @Autowired
    private ClaimRepository claimRepository;
    
    @Autowired
    private InsurancePolicyRepository policyRepository;

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Claim getClaimById(Long id) {
        return claimRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Claim", "id", id));
    }

    public Claim createClaim(Claim claim) {
    	 InsurancePolicy policy = policyRepository.findById(claim.getInsurancePolicy().getId())
                 .orElseThrow(() -> new ResourceNotFoundException("Policy", "id", claim.getInsurancePolicy().getId()));
         claim.setInsurancePolicy(policy);
        return claimRepository.save(claim);
    }

    public Claim updateClaim(Long id, Claim claimDetails) {
        Claim claim = claimRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Claim", "id", id));

        claim.setClaimNumber(claimDetails.getClaimNumber());
        claim.setDescription(claimDetails.getDescription());
        claim.setClaimDate(claimDetails.getClaimDate());
        claim.setClaimStatus(claimDetails.getClaimStatus());
        claim.setInsurancePolicy(claimDetails.getInsurancePolicy());
   
        InsurancePolicy policy = policyRepository.findById(claimDetails.getInsurancePolicy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Policy", "id", claimDetails.getInsurancePolicy().getId()));
        claim.setInsurancePolicy(policy);
        
        return claimRepository.save(claim);
    }

    public void deleteClaim(Long id) {
        Claim claim = claimRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Claim", "id", id));

        claimRepository.delete(claim);
    }
}
