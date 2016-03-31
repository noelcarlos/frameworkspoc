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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "apps")
public class App implements java.io.Serializable {
	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Boolean isActive;

	private LkActionType locAction;
	private Integer locActionId;
	
	private LkActionType devAction;
	private Integer devActionId;
	
	private LkActionType intAction;
	private Integer intActionId;
	
	private LkActionType fdgAction;
	private Integer fdgActionId;
	
	private LkActionType preAction;
	private Integer preActionId;
	
	private LkActionType proAction;
	private Integer proActionId;
	
	//// BEGIN CUSTOM BLOCK ////
	//// END CUSTOM BLOCK ////

	
	// Constructor
	public App() {
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
	
	
	@NotNull
	@Column(name = "is_active", nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(Boolean isActive) {
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
		if (!(obj instanceof App))
			return false;
		if (getId() == null || !((App) obj).getId().equals(getId())) {
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
		return "/apps/view/" + getId();
	}
	
	@Transient
	public String getUITitle() {
		return getName();
	}
	
	//// END CUSTOM BLOCK ////
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loc_action_id", nullable = false)
	public LkActionType getLocAction() {
		return locAction;
	}
	
	public void setLocAction(LkActionType locAction) {
		this.locAction = locAction;
        if (locAction != null) {
            setLocActionId(locAction.getId());
        } else {
            setLocActionId(null);
        }
	}
	
	@Column(name = "loc_action_id", insertable = false, updatable = false)
	public Integer getLocActionId() {
		return locActionId;
	}

	public void setLocActionId(Integer locActionId) {
		this.locActionId = locActionId;
	}
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dev_action_id", nullable = false)
	public LkActionType getDevAction() {
		return devAction;
	}
	
	public void setDevAction(LkActionType devAction) {
		this.devAction = devAction;
        if (devAction != null) {
            setDevActionId(devAction.getId());
        } else {
            setDevActionId(null);
        }
	}
	
	@Column(name = "dev_action_id", insertable = false, updatable = false)
	public Integer getDevActionId() {
		return devActionId;
	}

	public void setDevActionId(Integer devActionId) {
		this.devActionId = devActionId;
	}
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "int_action_id", nullable = false)
	public LkActionType getIntAction() {
		return intAction;
	}
	
	public void setIntAction(LkActionType intAction) {
		this.intAction = intAction;
        if (intAction != null) {
            setIntActionId(intAction.getId());
        } else {
        	setIntActionId(null);
        }
	}
	
	@Column(name = "int_action_id", insertable = false, updatable = false)
	public Integer getIntActionId() {
		return intActionId;
	}

	public void setIntActionId(Integer intActionId) {
		this.intActionId = intActionId;
	}
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fdg_action_id", nullable = false)
	public LkActionType getFdgAction() {
		return fdgAction;
	}
	
	public void setFdgAction(LkActionType fdgAction) {
		this.fdgAction = fdgAction;
        if (fdgAction != null) {
            setFdgActionId(fdgAction.getId());
        } else {
        	setFdgActionId(null);
        }
	}
	
	@Column(name = "fdg_action_id", insertable = false, updatable = false)
	public Integer getFdgActionId() {
		return fdgActionId;
	}

	public void setFdgActionId(Integer fdgActionId) {
		this.fdgActionId = fdgActionId;
	}
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pre_action_id", nullable = false)
	public LkActionType getPreAction() {
		return preAction;
	}
	
	public void setPreAction(LkActionType preAction) {
		this.preAction = preAction;
        if (preAction != null) {
            setPreActionId(preAction.getId());
        } else {
        	setPreActionId(null);
        }
	}
	
	@Column(name = "pre_action_id", insertable = false, updatable = false)
	public Integer getPreActionId() {
		return preActionId;
	}

	public void setPreActionId(Integer preActionId) {
		this.preActionId = preActionId;
	}
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pro_action_id", nullable = false)
	public LkActionType getProAction() {
		return proAction;
	}
	
	public void setProAction(LkActionType proAction) {
		this.proAction = proAction;
        if (proAction != null) {
            setProActionId(proAction.getId());
        } else {
        	setProActionId(null);
        }
	}
	
	@Column(name = "pro_action_id", insertable = false, updatable = false)
	public Integer getProActionId() {
		return proActionId;
	}

	public void setProActionId(Integer proActionId) {
		this.proActionId = proActionId;
	}

}
