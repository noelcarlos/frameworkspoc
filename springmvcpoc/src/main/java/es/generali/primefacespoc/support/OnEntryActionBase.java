package es.generali.primefacespoc.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;

public abstract class OnEntryActionBase<T> implements Serializable {
	private static final long serialVersionUID = 1641245228042874793L;
	
	protected static Logger log;

	//@Autowired protected transient ValidationService validationService;
	
	public OnEntryActionBase() {
		 log = Logger.getLogger(this.getClass());
	}
	
	//abstract public void execute(RequestContext requestContext, T model) throws Exception;
	
	public HashMap<String, String> mapListToSelect(List<Map<String, Object>> src) {
		HashMap<String, String> res = new HashMap<String, String>();
		for (Map<String, Object> val : src) {
			res.put(val.get("ID").toString(), val.get("NOMBRE").toString());
		}
		return res;
	}
}
