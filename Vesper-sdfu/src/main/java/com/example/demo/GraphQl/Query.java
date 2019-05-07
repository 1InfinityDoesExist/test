package com.example.demo.GraphQl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.model.Agencies;
import com.example.demo.model.Agents;
import com.example.demo.repository.AgencyRepository;
import com.example.demo.repository.AgentRepository;

@Component
public class Query implements GraphQLQueryResolver {
    private static final Logger logger = LogManager.getLogger(Query.class);

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    public List<Agents> getAllAgents() {
	return agentRepository.findAll();
    }

    public List<Agencies> getAllAgencies() {
	return agencyRepository.findAll();
    }

}
