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
package com.allianz.drdc24.controllers.app;


import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.allianz.drdc24.controllers.BaseWebFlowController;
import com.allianz.drdc24.models.App;
import com.allianz.drdc24.services.AppService;
import com.allianz.drdc24.services.LookupService;
import com.allianz.drdc24.support.ValidationService;
import com.allianz.drdc24.support.message.MessageService;

//// BEGIN CUSTOM BLOCK ////
//// END CUSTOM BLOCK ////

/**
 * The User entity controller allow the UI screens and the entity flows to perform list, search, view, 
 * delete, edit, create, select and multiselect operations. 
 * 
 * @author Noel Hernández Pérez
 * @version 1.0
 * 
 */
@ViewScoped
public class AppEditController extends BaseWebFlowController implements Serializable {
    private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(AppEditController.class);
	
	@Autowired private transient MessageService messageService;
	@Autowired private transient ValidationService validationService;
	@Autowired private transient LookupService lookupService;
	/**
	 * Main entity service.
	 */
	@Autowired protected transient AppService appService;

	private App formModel;

	public App getFormModel() {
		return formModel;
	}
	
	/**
	 * Called to perform the formModel initialization from a subflow, url, or by using data model defaults.
	 *
	 * @param refEntity when the flow is entered from a subflow, indicates the form model to be edited otherwise is null
	 * @param id when the flow is entered from a URL, indicates the form model id to be read from the persistence layer. Null to allows entity creation from defaults.
	 */ 
	public void onStart(App refEntity, Integer id) throws IOException {
		if (refEntity != null)
			formModel = refEntity;
		else if (id != null)
			formModel = appService.loadFull(id);
		else
			formModel = appService.createNew();
		
		flowScope.put("lkActionTypes", lookupService.listLkActionTypes());
	}
	
	/**
	 * Called to perform the validation of lookup fields and backend field crossvalidation.
	 *
	 * @param formModel form model to be validated
	 * @return returns true if there is not validation error messages in order to continue the flow execution
	 * or false to stay in the same page and show the validation error messages. 
	 */ 	
	public Boolean crossValidation(App formModel) {
		return !messageService.hasErrorMessages();
	}	

	public Boolean onSave() throws IOException {
		if (!validationService.validate(formModel)) {
			return false;
		}
		if (!crossValidation(formModel)) {
			return false;
		}
		appService.saveOrMerge(formModel);
		
		return true;
	}
	
	public Boolean onDeleteSelected() throws IOException {
		appService.delete(formModel.getId());
		return true;
	}
	
//// BEGIN CUSTOM BLOCK ////
	
//// END CUSTOM BLOCK ////

}
