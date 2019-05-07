
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Agencies;
import com.example.demo.repository.AgencyRepository;

@Service
public class AgencyService {

	@Autowired
	private AgencyRepository agencyRepository;

	public Agencies createAgencies(Agencies agencies) {
		return agencyRepository.save(agencies);
	}

	public List<Agencies> findAllAgencies() {
		return agencyRepository.findAll();
	}

	public Optional<Agencies> findAgencyById(Long id) {
		return agencyRepository.findById(id);
	}

	public void closeAgency(Long id) {
		agencyRepository.closeAgency(id);
	}
}
