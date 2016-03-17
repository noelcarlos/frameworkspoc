package es.generali.primefacespoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.generali.primefacespoc.controllers.FlowScope;
import es.generali.primefacespoc.services.LookupService;
import es.generali.primefacespoc.support.OnEntryActionBase;
import es.generali.segurohogar.models.SeguroViviendaBean;

@Controller
@RequestMapping(value="/seguroHogar")
public class LocalizacionOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	@Autowired transient LookupService lookupService;

	@RequestMapping(value="/localizacion/entry")
	public ModelAndView entry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FlowScope flowScope = FlowScope.createOrResume(request);
		
		flowScope.put("provincias", mapListToSelect(lookupService.getProvincias()));
		flowScope.put("localizacionesViviendas", mapListToSelect(lookupService.getLocalizacionesViviendas()));
		
		log.info("Before Step 2");

		flowScope.put("executionUrl", "seguroHogar/localizacion/submit?execution=" + flowScope.getExecutionId());
		return new ModelAndView("/seguroHogar/localizacion", flowScope);
	}
}