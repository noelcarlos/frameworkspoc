package es.generali.strutspoc.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.generali.strutspoc.services.LookupService;

public class InitAction extends es.generali.strutspoc.support.BaseAction {
	
	public ActionForward onEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		NamedParameterJdbcTemplate jdbcTemplate = context.getBean(NamedParameterJdbcTemplate.class);
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT id, nombre, mail FROM usuarios u", new HashMap<String, Object>());
		
		LookupService lookupService = new LookupService(context);
		
		request.setAttribute("tiposUsosViviendas", lookupService.getTiposUsosViviendas());
		
		return mapping.findForward("app.init");
	}
	
}
