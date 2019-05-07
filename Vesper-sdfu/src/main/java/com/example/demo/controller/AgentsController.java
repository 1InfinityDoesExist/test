
package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Agents;
import com.example.demo.service.AgentService;
import com.example.demo.util.ReflectionUtil;
import com.example.demo.util.ValidationUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/objects/agents")
public class AgentsController {
    private static final Logger logger = LogManager.getLogger(AgentsController.class);

    @Autowired
    private AgentService agentdao;

    ReflectionUtil refUtil = ReflectionUtil.getInstance();

    @PostMapping(path = "/create")
    @ApiOperation(value = "Insert New Agent Details")
    public Agents createAgent(@Valid @RequestBody Agents agent) {
	ValidationUtils.validateAgents(agent);
	return agentdao.saveAgent(agent);
    }

    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation(value = "Delete Agent Identification")
    public ResponseEntity<?> deleteAgent(@PathVariable(value = "id") Long agentId) {
	Optional<Agents> agents = agentdao.findByAgentId(agentId);
	if (agents == null) {
	    return ResponseEntity.notFound().build();
	} else {
	    agentdao.deleteAgentRecord(agentId);
	    return ResponseEntity.ok().body(null);
	}
    }

    @GetMapping(path = "/get/{id}")
    @ApiOperation(value = "Get Agent by His/Her AgentId")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
	Optional<Agents> agents = agentdao.findByAgentId(id);
	if (agents == null) {
	    return ResponseEntity.notFound().build();
	} else {
	    return ResponseEntity.ok().body(agents);
	}
    }

    @GetMapping(path = "/get")
    @ApiOperation(value = "Update The Agent Details")
    public List<Agents> findAllAgents() {
	return agentdao.findAllAgents();
    }

    @PatchMapping(path = "/update/{id}")
    @ApiOperation(value = "Update Agent Details")
    public ResponseEntity<?> updateAgentsDetials(@Valid @PathVariable(value = "id") Long id, @RequestBody String agent)
	    throws IllegalAccessException, InvocationTargetException {
	Optional<Agents> agents = agentdao.findByAgentId(id);
	if (agents == null)
	    ResponseEntity.notFound().build();

	JSONParser parser = new JSONParser();
	Agents agen = agentdao.findByAgentId(id).orElseThrow(() -> new ResourceNotFoundException("Agents", "id", "id"));
	try {
	    JSONObject agentObject = (JSONObject) parser.parse(agent);
	    for (Iterator iterator = agentObject.keySet().iterator(); iterator.hasNext();) {
		String propName = (String) iterator.next();

		refUtil.getSetterMethod("Agents", propName).invoke(agen, agentObject.get(propName));
	    }

	} catch (ParseException e) {
	}

	agentdao.saveAgent(agen);
	return ResponseEntity.ok().body(agen);

    }

}
