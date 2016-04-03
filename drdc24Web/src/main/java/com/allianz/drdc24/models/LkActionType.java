/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.allianz.drdc24.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

//// BEGIN CUSTOM BLOCK ////
//// END CUSTOM BLOCK ////

/**
 * The User entity is part of the domain data representing 
 * the data rows of users table. 
 * 
 * @author Noel Hernández Pérez
 * @version 1.0
 * 
 */  
@Entity
@Table(name = "lk_action_types")
public class LkActionType implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Boolean isActive;

	//// BEGIN CUSTOM BLOCK ////
	//// END CUSTOM BLOCK ////

	
	// Constructor
	public LkActionType() {
	}

	
	/**
	 * Getter for field userId.
	 *
	 * @return the userId field value
	 */ 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	/**
	 * Setter for field userId.
	 *
	 * @param userIdNew the new userId field value
	 */ 	
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Getter for field screenName.
	 *
	 * @return the screenName field value
	 */ 
	@NotEmpty
	@Length(max = 64)
	@Column(name = "name", nullable = false, length = 64)
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for field screenName.
	 *
	 * @param screenNameNew the new screenName field value
	 */ 	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for field isEnabled.
	 *
	 * @return the isEnabled field value
	 */ 
	@NotNull
	@Column(name = "is_active", nullable = false)
	public Boolean getIsEnabled() {
		return isActive;
	}
	
	/**
	 * Setter for field isEnabled.
	 *
	 * @param isEnabledNew the new isEnabled field value
	 */ 	
	public void setIsEnabled(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * The equal method must be correctly overriden.
	 *
	 * @param obj foreign object
	 * @return true if obj and this ave equal content 
	 */ 	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this)
			return true;		
		if (!(obj instanceof LkActionType))
			return false;
		if (getId() == null || !((LkActionType) obj).getId().equals(getId())) {
			return false;
		}
		return true;
	}
	
	/**
	 * The hash method must be correctly overriden.
	 *
	 * @return hash the entity value 
	 */ 	
	@Override
	public int hashCode() {
		int result = 17;
		if (this.getId() == null) {
			return 0;
		} else {
			result = 37 * result + this.getId().hashCode();
		}
		return result;
	}	


	@Transient
	public Object getEntityPrimaryKey() {
		return getId();
	}
	
	@Transient
	public Integer getEntityPrimaryKeyId() {
		return getId();
	}

	//// BEGIN CUSTOM BLOCK ////
	
	@Transient
	public String getUIName() {
		return getName();
	}
	
	@Transient
	public String getUIURL() {
		return "/catalogs/lk-action-types/" + getId();
	}
	
	@Transient
	public String getUITitle() {
		return getName();
	}
	
	//// END CUSTOM BLOCK ////
}
