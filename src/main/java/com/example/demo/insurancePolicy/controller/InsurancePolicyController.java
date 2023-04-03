package com.example.demo.insurancePolicy.controller;

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

import com.example.demo.insurancePolicy.entity.InsurancePolicy;
import com.example.demo.insurancePolicy.service.InsurancePolicyService;

@RestController
@RequestMapping("/api/policies")
public class InsurancePolicyController {
    @Autowired
    private InsurancePolicyService policyService;
    
    @GetMapping
    public List<InsurancePolicy> getAllPolicies() {
        return policyService.getAllPolicies();
    }
    
    @GetMapping("/{id}")
    public InsurancePolicy getPolicyById(@PathVariable(value = "id") Long id) {
        return policyService.getPolicyById(id);
    }
    
    @PostMapping
    public ResponseEntity<InsurancePolicy> createPolicy(@RequestBody InsurancePolicy policy) {
        InsurancePolicy createdPolicy = policyService.createPolicy(policy);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePolicy(@PathVariable(value = "id") Long id, @RequestBody InsurancePolicy policyDetails) {
        InsurancePolicy updatedPolicy = policyService.updatePolicy(id, policyDetails);
        return ResponseEntity.ok().body("Policy updated successfully with id: " + updatedPolicy.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePolicy(@PathVariable(value = "id") Long id) {
        policyService.deletePolicy(id);
        return ResponseEntity.ok().body("Policy deleted successfully with id: " + id);
    }

}
