package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.demo.mode.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Agent")
@Table(name = "Agents")
public class Agents extends BaseEntity {
    private static final Logger logger = LogManager.getLogger(Agents.class);
    @Column(name = "name")
    @ApiModelProperty(notes = "Agent Name")
    private String name;

    @Column(name = "date_of_joining", nullable = false, updatable = true)
    @ApiModelProperty(notes = "Day Agent Joined")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateOfJoining;

    @Column(name = "alloted_MG")
    @ApiModelProperty(notes = "The Particular MG Alloted to the particular Agent")
    private String allotedMG;

    public Agents() {
	super();
    }

    public Agents(String name, LocalDateTime dateOfJoining, String allotedMG) {
	super();
	this.name = name;
	this.dateOfJoining = dateOfJoining;
	this.allotedMG = allotedMG;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public LocalDateTime getDateOfJoining() {
	return dateOfJoining;
    }

    public void setDateOfJoining(LocalDateTime dateOfJoining) {
	this.dateOfJoining = dateOfJoining;
    }

    public String getAllotedMG() {
	return allotedMG;
    }

    public void setAllotedMG(String allotedMG) {
	this.allotedMG = allotedMG;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((allotedMG == null) ? 0 : allotedMG.hashCode());
	result = prime * result + ((dateOfJoining == null) ? 0 : dateOfJoining.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Agents other = (Agents) obj;
	if (allotedMG == null) {
	    if (other.allotedMG != null)
		return false;
	} else if (!allotedMG.equals(other.allotedMG))
	    return false;
	if (dateOfJoining == null) {
	    if (other.dateOfJoining != null)
		return false;
	} else if (!dateOfJoining.equals(other.dateOfJoining))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Agencies [name=" + name + ", dateOfJoining=" + dateOfJoining + ", allotedMG=" + allotedMG + "]";
    }
}
