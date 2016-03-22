package es.esmartpoint.strutspoc.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.springframework.web.context.WebApplicationContext;

public abstract class OnExitActionBase<T> {
	
	protected static Logger log;

	public OnExitActionBase() {
		 log = Logger.getLogger(this.getClass());
	}
	
	abstract public void execute(WebApplicationContext context, T model, 
			HttpServletRequest request, HttpServletResponse response, ActionErrors errors) throws Exception;
}
