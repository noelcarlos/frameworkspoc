package es.esmartpoint.primefacespoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.esmartpoint.primefacespoc.controllers.FlowScope;
import es.esmartpoint.primefacespoc.support.OnEntryActionBase;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean;

@Controller
@RequestMapping(value="/seguroHogar")
@SessionAttributes("model")
public class ResumenOnEntryAction extends OnEntryActionBase<SeguroViviendaBean>  {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value="/resumen/entry")
	public ModelAndView entry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FlowScope flowScope = FlowScope.createOrResume(request);

		log.info("Before Step 10");
		
		SeguroViviendaBean model = (SeguroViviendaBean)flowScope.get("model");
		
		model.setPrecio(3811.99);
		
		flowScope.put("executionUrl", "seguroHogar/resumen/submit?execution=" + flowScope.getExecutionId());
		return new ModelAndView("/seguroHogar/resumen", flowScope);
	}
}
