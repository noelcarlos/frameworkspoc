package es.esmartpoint.primefacespoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.esmartpoint.primefacespoc.controllers.FlowScope;
import es.esmartpoint.primefacespoc.support.OnExitActionBase;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean.Localizacion;

@Controller
@RequestMapping(value="/seguroHogar")
@SessionAttributes("model")
public class LocalizacionOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	@RequestMapping(value="/localizacion/submit")
	public ModelAndView submit(@ModelAttribute("model") SeguroViviendaBean model, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FlowScope flowScope = FlowScope.createOrResume(request);
		log.info("After Step 2");

		validationService.validate(model, result, Localizacion.class);
		
		flowScope.put("model", model);
		
		if (result.hasErrors()) {
			flowScope.put("executionUrl", "seguroHogar/localizacion/submit?execution=" + flowScope.getExecutionId());
			return new ModelAndView("/seguroHogar/localizacion", flowScope);
		} else {
			return new ModelAndView("redirect:/es/seguroHogar/sobreLaConstruccion/entry?execution=" + flowScope.getExecutionId());
		}
	}
	
}
