package es.generali.primefacespoc.support;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.execution.RequestContext;

public abstract class OnExitActionBase<T> implements Serializable {
	private static final long serialVersionUID = 1641245228042874793L;

	@Autowired protected transient ValidationService validationService; 
	
	abstract public boolean execute(RequestContext requestContext, T model) throws Exception;
}
