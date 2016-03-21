package es.generali.primefacespoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.generali.primefacespoc.controllers.FlowScope;
import es.generali.primefacespoc.support.OnEntryActionBase;
import es.generali.segurohogar.models.SeguroViviendaBean;

@Controller
@RequestMapping(value="/seguroHogar")
@SessionAttributes("model")
public class DatosDePagoOnEntryAction extends OnEntryActionBase<SeguroViviendaBean>  {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value="/datosDePago/entry")
	public ModelAndView entry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FlowScope flowScope = FlowScope.createOrResume(request);
		
		log.info("Before Step 9");

		flowScope.put("executionUrl", "seguroHogar/datosDePago/submit?execution=" + flowScope.getExecutionId());
		return new ModelAndView("/seguroHogar/datosDePago", flowScope);
	}
}
