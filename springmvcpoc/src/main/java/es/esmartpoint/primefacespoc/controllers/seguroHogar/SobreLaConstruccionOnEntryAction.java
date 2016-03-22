package es.esmartpoint.primefacespoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.esmartpoint.primefacespoc.controllers.FlowScope;
import es.esmartpoint.primefacespoc.services.LookupService;
import es.esmartpoint.primefacespoc.support.OnEntryActionBase;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean;

@Controller
@RequestMapping(value="/seguroHogar")
@SessionAttributes("model")
public class SobreLaConstruccionOnEntryAction extends OnEntryActionBase<SeguroViviendaBean>  {
	private static final long serialVersionUID = 1L;

	@Autowired LookupService lookupService;

	@RequestMapping(value="/sobreLaConstruccion/entry")
	public ModelAndView entry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FlowScope flowScope = FlowScope.createOrResume(request);
		log.info("Before Step 3");

		flowScope.put("tiposDeConstrucciones", mapListToSelect(lookupService.getTiposDeConstrucciones()));
		flowScope.put("calidadesDeLasConstrucciones", mapListToSelect(lookupService.getCalidadesDeLasConstrucciones()));
		flowScope.put("tipologiasDeLasViviendas", mapListToSelect(lookupService.getTipologiasDeLasViviendas()));

		flowScope.put("executionUrl", "seguroHogar/sobreLaConstruccion/submit?execution=" + flowScope.getExecutionId());
		return new ModelAndView("/seguroHogar/sobreLaConstruccion", flowScope);
	}
}
