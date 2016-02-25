package es.generali.primefacespoc.support;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.execution.RequestContext;

public abstract class OnEntryActionBase<T> implements Serializable {
	private static final long serialVersionUID = 1641245228042874793L;
	
	protected static Logger log;

	@Autowired protected transient ValidationService validationService;
	
	public OnEntryActionBase() {
		 log = Logger.getLogger(this.getClass());
	}
	
	abstract public void execute(RequestContext requestContext, T model) throws Exception;
}
