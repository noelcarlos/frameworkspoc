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
import es.generali.segurohogar.models.SeguroViviendaBean.DatosDelTitular;

@Controller
@RequestMapping(value="/seguroHogar")
@SessionAttributes("model")
public class DatosDelTitularOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	@RequestMapping(value="/datosDelTitular/submit")
	public ModelAndView submit(@ModelAttribute("model") SeguroViviendaBean model, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FlowScope flowScope = FlowScope.createOrResume(request);
		log.info("After Step 7");

		validationService.validate(model, result, DatosDelTitular.class);
		
		flowScope.put("model", model);

		if (result.hasErrors()) {
			flowScope.put("executionUrl", "seguroHogar/datosDelTitular/submit?execution=" + flowScope.getExecutionId());
			return new ModelAndView("/seguroHogar/datosDelTitular", flowScope);
		} else {
			return new ModelAndView("redirect:/es/seguroHogar/datosDeLaViviendaAAsegurar/entry?execution=" + flowScope.getExecutionId());
		}
	}
	
}
