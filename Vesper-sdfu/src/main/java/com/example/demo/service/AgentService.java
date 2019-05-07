
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.Agents;
import com.example.demo.repository.AgentRepository;

@Service
public class AgentService {

	@Autowired
	private AgentRepository agentRepository;

	public Agents saveAgent(Agents agent) {
		return agentRepository.save(agent);
	}

	public List<Agents> findAllAgents() {
		return agentRepository.findAll();
	}

	public Optional<Agents> findByAgentId(Long id) {
		return agentRepository.findById(id);
	}

	public void deleteAgentRecord(Long agentId) {
		agentRepository.terminateAgent(agentId);
	}

	/*
	 * public void deleteAgentRecord(Long agentId) {
	 * agentRepository.deleteById(agentId); }
	 */
}
