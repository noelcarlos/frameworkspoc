package es.generali.primefacespoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.generali.primefacespoc.controllers.FlowScope;
import es.generali.primefacespoc.support.OnExitActionBase;
import es.generali.segurohogar.models.SeguroViviendaBean;
import es.generali.segurohogar.models.SeguroViviendaBean.SobreLaConstruccion;

@Controller
@RequestMapping(value="/seguroHogar")
@SessionAttributes("model")
public class SobreLaConstruccionOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	@RequestMapping(value="/sobreLaConstruccion/submit")
	public ModelAndView submit(@ModelAttribute("model") SeguroViviendaBean model, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FlowScope flowScope = FlowScope.createOrResume(request);
		log.info("After Step 3");

		validationService.validate(model, result, SobreLaConstruccion.class);
		
		flowScope.put("model", model);
		
		if (result.hasErrors()) {
			flowScope.put("executionUrl", "seguroHogar/sobreLaConstruccion/submit?execution=" + flowScope.getExecutionId());
			return new ModelAndView("/seguroHogar/sobreLaConstruccion", flowScope);
		} else {
			return new ModelAndView("redirect:/es/seguroHogar/caracteristicasDeLaVivienda/entry?execution=" + flowScope.getExecutionId());
		}
	}
	
}
