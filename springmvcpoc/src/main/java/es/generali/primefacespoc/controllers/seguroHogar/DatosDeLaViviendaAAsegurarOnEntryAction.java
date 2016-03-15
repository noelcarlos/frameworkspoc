package es.generali.primefacespoc.controllers.seguroHogar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;

import es.generali.primefacespoc.services.LookupService;
import es.generali.primefacespoc.support.OnEntryActionBase;
import es.generali.segurohogar.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class DatosDeLaViviendaAAsegurarOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {
	@Autowired LookupService lookupService;

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 8");

		MutableAttributeMap<Object> flowScope = requestContext.getFlowScope(); 

		flowScope.put("tiposDeVias", mapListToSelect(lookupService.getTiposDeVias()));
		flowScope.put("provincias", mapListToSelect(lookupService.getProvincias()));
	}
}
