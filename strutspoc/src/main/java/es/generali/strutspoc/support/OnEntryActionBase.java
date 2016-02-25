package es.generali.strutspoc.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

public abstract class OnEntryActionBase<T> {
	
	protected static Logger log;

	public OnEntryActionBase() {
		 log = Logger.getLogger(this.getClass());
	}
	
	abstract public void execute(WebApplicationContext context, T model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;
}
