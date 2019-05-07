
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
import com.example.demo.model.Agencies;
import com.example.demo.service.AgencyService;
import com.example.demo.util.ReflectionUtil;
import com.example.demo.util.ValidationUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/objects/agencies")
public class AgenciesController {
    private static final Logger logger = LogManager.getLogger(AgenciesController.class);

    @Autowired
    private AgencyService agencyDao;

    ReflectionUtil refUtil = ReflectionUtil.getInstance();

    @PostMapping(path = "/create")
    @ApiOperation(value = "Create a New Agency")
    public Agencies createAgency(@Valid @RequestBody Agencies agency) {
	ValidationUtils.validateAgencies(agency);
	return agencyDao.createAgencies(agency);
    }

    @GetMapping(path = "/getAll")
    @ApiOperation(value = "Find All Agencies")
    public List<Agencies> findAllAgencies() {
	return agencyDao.findAllAgencies();
    }

    @GetMapping(path = "/get/{id}")
    @ApiOperation(value = "Get Agency By Id")
    public ResponseEntity<?> getAcencyById(@PathVariable(value = "id") Long id) {
	Optional<Agencies> agency = agencyDao.findAgencyById(id);
	if (agency == null) {
	    return ResponseEntity.notFound().build();
	} else {
	    return ResponseEntity.ok().body(agency);
	}
    }

    @DeleteMapping(path = "/delete/{id}")

    @ApiOperation(value = "Delete Agency By Id")
    public ResponseEntity<?> deleteAgencyById(@PathVariable(value = "id") Long id) {
	Optional<Agencies> agency = agencyDao.findAgencyById(id);
	if (agency == null) {
	    return ResponseEntity.notFound().build();
	} else {
	    agencyDao.closeAgency(id);
	    return ResponseEntity.ok().body(null);
	}
    }

    @PatchMapping(path = "/update/{id}")
    @ApiOperation(value = "Update The Agency")
    public ResponseEntity<?> updateAgency(@Valid @PathVariable(value = "id") Long id, @RequestBody String agencyDetails)
	    throws IllegalAccessException, InvocationTargetException {
	Optional<Agencies> agency = agencyDao.findAgencyById(id);
	if (agency == null) {
	    return ResponseEntity.notFound().build();
	}
	Agencies acy = agencyDao.findAgencyById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Agencies", "id", "id"));
	JSONParser parser = new JSONParser();

	try {
	    JSONObject agencyObject = (JSONObject) parser.parse(agencyDetails);
	    for (Iterator iterator = agencyObject.keySet().iterator(); iterator.hasNext();) {
		String propName = (String) iterator.next();
		try {
		    System.out.println(propName.toString());
		    refUtil.getSetterMethod("Agencies", propName).invoke(acy, agencyObject.get(propName));
		} catch (IllegalArgumentException e) {
		    // TODO Auto-generated catch block
		    System.out.println("catch ke aandar...hu");
		}
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	}

	agencyDao.createAgencies(acy);
	return ResponseEntity.ok().body(acy);

    }
}
