package com.allianz.drdc24.support;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.execution.RequestContext;

public abstract class OnExitActionBase<T> implements Serializable {
	private static final long serialVersionUID = 1641245228042874793L;
	
	protected static Logger log;

	@Autowired protected transient ValidationService validationService;
	
	public OnExitActionBase() {
		 log = Logger.getLogger(this.getClass());
	}
	
	abstract public boolean execute(RequestContext requestContext, T model) throws Exception;
}
