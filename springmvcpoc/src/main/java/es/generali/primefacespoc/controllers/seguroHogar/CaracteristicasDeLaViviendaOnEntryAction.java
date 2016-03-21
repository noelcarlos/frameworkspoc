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
public class CaracteristicasDeLaViviendaOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 4336353642143485232L;

	@RequestMapping(value="/caracteristicasDeLaVivienda/entry")
	public ModelAndView entry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FlowScope flowScope = FlowScope.createOrResume(request);
		
		log.info("Before Step 4");
		
		flowScope.put("executionUrl", "seguroHogar/caracteristicasDeLaVivienda/submit?execution=" + flowScope.getExecutionId());
		return new ModelAndView("/seguroHogar/caracteristicasDeLaVivienda", flowScope);
	}
	
}
