package com.example.demo.insurancePolicy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.insurancePolicy.entity.InsurancePolicy;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {
}
