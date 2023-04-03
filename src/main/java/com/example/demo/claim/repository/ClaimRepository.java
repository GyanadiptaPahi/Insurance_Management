package com.example.demo.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.claim.entity.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
}

