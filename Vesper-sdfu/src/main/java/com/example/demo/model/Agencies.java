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

@Entity(name = "Agencies")
@Table(name = "Agencies")
public class Agencies extends BaseEntity {
    private static final Logger logger = LogManager.getLogger(Agencies.class);

    @Column(name = "agency_name", nullable = false, updatable = true)
    @ApiModelProperty(notes = "Name of Agenccy")
    private String agencyName;

    @Column(name = "agency_address")
    @ApiModelProperty(notes = "Address of The Agency")
    private String agencyAddress;

    @Column(name = "number_of_agents")
    @ApiModelProperty(notes = "Number of Agents Working in the Agency")
    private String numberOfAgents;

    @Column(name = "agency_creation_date", nullable = false, updatable = false)
    @ApiModelProperty(notes = "The Day Agency Was Established")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime agencyCreationDate;

    public Agencies() {
	super();
    }

    public Agencies(String agencyName, String agencyAddress, String numberOfAgents, LocalDateTime agencyCreationDate) {
	super();
	this.agencyName = agencyName;
	this.agencyAddress = agencyAddress;
	this.numberOfAgents = numberOfAgents;
	this.agencyCreationDate = agencyCreationDate;
    }

    public String getAgencyName() {
	return agencyName;
    }

    public void setAgencyName(String agencyName) {
	this.agencyName = agencyName;
    }

    public String getAgencyAddress() {
	return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
	this.agencyAddress = agencyAddress;
    }

    public String getNumberOfAgents() {
	return numberOfAgents;
    }

    public void setNumberOfAgents(String numberOfAgents) {
	this.numberOfAgents = numberOfAgents;
    }

    public LocalDateTime getAgencyCreationDate() {
	return agencyCreationDate;
    }

    public void setAgencyCreationDate(LocalDateTime agencyCreationDate) {
	this.agencyCreationDate = agencyCreationDate;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((agencyAddress == null) ? 0 : agencyAddress.hashCode());
	result = prime * result + ((agencyCreationDate == null) ? 0 : agencyCreationDate.hashCode());
	result = prime * result + ((agencyName == null) ? 0 : agencyName.hashCode());
	result = prime * result + ((numberOfAgents == null) ? 0 : numberOfAgents.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Agencies other = (Agencies) obj;
	if (agencyAddress == null) {
	    if (other.agencyAddress != null)
		return false;
	} else if (!agencyAddress.equals(other.agencyAddress))
	    return false;
	if (agencyCreationDate == null) {
	    if (other.agencyCreationDate != null)
		return false;
	} else if (!agencyCreationDate.equals(other.agencyCreationDate))
	    return false;
	if (agencyName == null) {
	    if (other.agencyName != null)
		return false;
	} else if (!agencyName.equals(other.agencyName))
	    return false;
	if (numberOfAgents == null) {
	    if (other.numberOfAgents != null)
		return false;
	} else if (!numberOfAgents.equals(other.numberOfAgents))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Agencies [agencyName=" + agencyName + ", agencyAddress=" + agencyAddress + ", numberOfAgents="
		+ numberOfAgents + ", agencyCreationDate=" + agencyCreationDate + "]";
    }

}
