package es.generali.primefacespoc.support;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.Globals;
import org.apache.commons.lang.StringUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.context.MessageSource;

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
	
	public boolean validate(MessageSource msg, ErrorFunction errorFn) throws Exception {
		for (Record param : params) {
			String type = param.get("type");
			String fieldname = param.get("fieldname");

			if (type.equals("MANDATORY")) {
				
				Method getter = model.getClass().getMethod("get" + StringUtils.capitalize(fieldname));
			    Object value = getter.invoke(model);
			    String className = model.getClass().getName();
			    
			    String key = className + ".field." + fieldname;
			    		
			    if (value == null || StringUtils.isEmpty(value.toString())) {
					errorFn.onError(fieldname, "Debe de introducir el campo '" + getMessage(msg, key) + "'");
			    }
			}
		}
		
		return true;
	}
	
	//@Autowired
	//protected MessageSource resource;
	
	protected String getMessage(MessageSource msg, String resourceKey) {
		return msg.getMessage(resourceKey, null, new Locale("es", "es"));
	}
}
