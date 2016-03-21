package es.generali.primefacespoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.generali.primefacespoc.controllers.FlowScope;
import es.generali.primefacespoc.services.LookupService;
import es.generali.primefacespoc.support.OnEntryActionBase;
import es.generali.segurohogar.models.SeguroViviendaBean;

@Controller
@RequestMapping(value="/seguroHogar")
@SessionAttributes("model")
public class QueQuieresProtegerOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;

	@Autowired transient LookupService lookupService;

	@RequestMapping(value="/queQuieresProteger/entry")
	public ModelAndView entry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FlowScope flowScope = FlowScope.createOrResume(request);
		
		flowScope.put("tiposUsosViviendas", mapListToSelect(lookupService.getTiposUsosViviendas()));
		
		log.info("Before Step 1");

		flowScope.put("executionUrl", "seguroHogar/queQuieresProteger/submit?execution=" + flowScope.getExecutionId());
		return new ModelAndView("/seguroHogar/queQuieresProteger", flowScope);
	}
	
}
