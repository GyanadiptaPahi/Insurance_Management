package com.example.demo.insurancePolicy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.entity.Client;
import com.example.demo.client.repository.ClientRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.insurancePolicy.entity.InsurancePolicy;
import com.example.demo.insurancePolicy.repository.InsurancePolicyRepository;

@Service
public class InsurancePolicyService {
	@Autowired
	private InsurancePolicyRepository policyRepository;

	@Autowired
	private ClientRepository clientRepository;

	public List<InsurancePolicy> getAllPolicies() {
		return policyRepository.findAll();
	}

	public InsurancePolicy getPolicyById(Long id) {
		return policyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Policy", "id", id));
	}

	public InsurancePolicy createPolicy(InsurancePolicy policy) {

		Client client = clientRepository.findById(policy.getClient().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", policy.getClient().getId()));
		policy.setClient(client);

		return policyRepository.save(policy);
	}

	public InsurancePolicy updatePolicy(Long id, InsurancePolicy policyDetails) {
		InsurancePolicy policy = policyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Policy", "id", id));

		policy.setPolicyNumber(policyDetails.getPolicyNumber());
		policy.setType(policyDetails.getType());
		policy.setCoverageAmount(policyDetails.getCoverageAmount());
		policy.setPremium(policyDetails.getPremium());
		policy.setStartDate(policyDetails.getStartDate());
		policy.setEndDate(policyDetails.getEndDate());
		Client client = clientRepository.findById(policyDetails.getClient().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", policyDetails.getClient().getId()));
		policy.setClient(client);

		return policyRepository.save(policy);
	}

	public void deletePolicy(Long id) {
		InsurancePolicy policy = policyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Policy", "id", id));

		policyRepository.delete(policy);
	}
}
