package com.example.demo.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.amazonaws.services.applicationautoscaling.model.ValidationException;
import com.example.demo.model.Agencies;
import com.example.demo.model.Agents;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidationUtils {
    private static final Logger logger = LogManager.getLogger(ValidationUtils.class);

    private String getFile(String fileName) {
	StringBuilder result = new StringBuilder("");
	// Get file from resources folder
	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource(fileName).getFile());
	try (Scanner scanner = new Scanner(file)) {
	    while (scanner.hasNextLine()) {
		String line = scanner.nextLine();
		result.append(line).append("\n");
	    }
	    scanner.close();
	} catch (IOException e) {
	    logger.error("Error in reading json schema file", e);
	}
	return result.toString();
    }

    public static void validateAgencies(Agencies agencies) {
	logger.debug("Start method:: validateEmployee : Input ::::" + agencies.toString());
	ValidationUtils validationutils = new ValidationUtils();
	String schemaFile = validationutils.getFile("createJsonSchema/AgenciesSchema.json");
	InputStream is = new ByteArrayInputStream(schemaFile.getBytes());
	JSONObject jsonSchema = new JSONObject(new JSONTokener(is));

	ObjectMapper mapperObj = new ObjectMapper();
	String jsonStr = "";
	try {
	    jsonStr = mapperObj.writeValueAsString(agencies);
	    JSONObject jsonSubject = new JSONObject(new JSONTokener(jsonStr));
	    Schema schema = SchemaLoader.load(jsonSchema);
	    schema.validate(jsonSubject);
	} catch (JsonParseException ex) {
	    logger.error("Exception Occured in validateUserfeedback:: ", ex);
	}  catch (ValidationException e) {
		//e.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(System.out::println);
		throw e;
	} catch (JsonProcessingException e) {
	    logger.error("Exception Occured in validateUserfeedback:: ", e);
	}
	logger.debug("End method::validateUserfeedback");
    }

    public static void validateAgents(Agents agents) throws ValidationException {
	logger.debug("Start method:: validateContactUpdate : Input ::::" + agents.toString());
	ValidationUtils validationutils = new ValidationUtils();
	String schemaFile = validationutils.getFile("createJsonSchema/AgentsSchemaValidation.json");
	InputStream is = new ByteArrayInputStream(schemaFile.getBytes());
	JSONObject jsonSchema = new JSONObject(new JSONTokener(is));

	ObjectMapper mapperObj = new ObjectMapper();
	String jsonStr = "";
	try {

	    jsonStr = mapperObj.writeValueAsString(agents);

	    JSONObject jsonSubject = new JSONObject(new JSONTokener(jsonStr));
	    Schema schema = SchemaLoader.load(jsonSchema);
	    schema.validate(jsonSubject);
	} catch (JsonParseException ex) {
	    logger.debug(ex.getMessage());
	} catch (ValidationException e) {
	    logger.debug(e.getMessage());
	  //  e.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(System.out::println);
	    throw e;
	} catch (JsonProcessingException e) {
	    logger.debug(e.getMessage());
	}
	logger.debug("End method::validateProduct");
    }

}