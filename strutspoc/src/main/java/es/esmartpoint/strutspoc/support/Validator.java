package es.esmartpoint.strutspoc.support;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;

public class Validator {
	Object model;
	List<Record> params = new ArrayList<Record>();
	
	public static interface ErrorFunction {
		void onError(String fieldname, String message);
	}
	
	public Validator() {
	}
	
	public Validator model(Object model) {
		this.model = model;
		return this;
	}
	
	public Validator mandatory(String fieldname) {
		params.add(new Record()
			.set("type", "MANDATORY")
			.set("fieldname", fieldname)
		);
		return this;
	}
	
	public boolean validate(HttpServletRequest request, ErrorFunction errorFn) throws Exception {
		for (Record param : params) {
			String type = param.get("type");
			String fieldname = param.get("fieldname");

			if (type.equals("MANDATORY")) {
				
				Method getter = model.getClass().getMethod("get" + StringUtils.capitalize(fieldname));
			    Object value = getter.invoke(model);
			    String className = model.getClass().getName();
			    
			    String key = className + ".field." + fieldname;
			    		
			    if (value == null || StringUtils.isEmpty(value.toString())) {
					errorFn.onError(fieldname, "Debe de introducir el campo '" + getMessage(request, key) + "'");
			    }
			}
		}
		
		return true;
	}
	
	protected String getMessage(HttpServletRequest request, String resourceKey){
		MessageResources messageResources = ((MessageResources) request.getAttribute(Globals.MESSAGES_KEY));
		return messageResources.getMessage(Utility.getUserLocale(request, null), resourceKey);
	}
}
