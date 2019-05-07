package com.example.demo.mode.common;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "objectid_generator")
    @SequenceGenerator(name = "objectid_generator", sequenceName = "objectid_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    @ApiModelProperty(notes = "Hibernated Generated Primary Key")
    protected Long id;

    @Column(name = "creation_data")
    @CreationTimestamp
    @ApiModelProperty(notes = "Date of creation of Particular Object")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime creationDate;

    @Column(name = "modification_date")
    @UpdateTimestamp
    @ApiModelProperty(notes = "Last Date of Modification of That Particular Object")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime modificationDate;

    @Column(name = "designation")
    @ApiModelProperty(notes = "Designation That Agent Holds")
    protected String designation;

    @Column(name = "delete_flag")
    @ApiModelProperty(notes = "Delete Flag")
    public boolean deleteFlag;

    public BaseEntity() {
	super();
    }

    public BaseEntity(Long id, LocalDateTime creationDate, LocalDateTime modificationDate, String designation,
	    Boolean deleteFlag) {
	super();
	this.id = id;
	this.creationDate = creationDate;
	this.modificationDate = modificationDate;
	this.designation = designation;
	this.deleteFlag = deleteFlag;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public LocalDateTime getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
	this.creationDate = creationDate;
    }

    public LocalDateTime getModificationDate() {
	return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
	this.modificationDate = modificationDate;
    }

    public String getDesignation() {
	return designation;
    }

    public void setDesignation(String designation) {
	this.designation = designation;
    }

    public boolean getDeleteFlag() {
	return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
	this.deleteFlag = deleteFlag;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
	result = prime * result + (deleteFlag ? 1231 : 1237);
	result = prime * result + ((designation == null) ? 0 : designation.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((modificationDate == null) ? 0 : modificationDate.hashCode());
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
	BaseEntity other = (BaseEntity) obj;
	if (creationDate == null) {
	    if (other.creationDate != null)
		return false;
	} else if (!creationDate.equals(other.creationDate))
	    return false;
	if (deleteFlag != other.deleteFlag)
	    return false;
	if (designation == null) {
	    if (other.designation != null)
		return false;
	} else if (!designation.equals(other.designation))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (modificationDate == null) {
	    if (other.modificationDate != null)
		return false;
	} else if (!modificationDate.equals(other.modificationDate))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "BaseEntity [id=" + id + ", creationDate=" + creationDate + ", modificationDate=" + modificationDate
		+ ", designation=" + designation + ", deleteFlag=" + deleteFlag + "]";
    }

}
