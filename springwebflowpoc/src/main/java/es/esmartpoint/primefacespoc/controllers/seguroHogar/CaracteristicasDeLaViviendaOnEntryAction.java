package es.esmartpoint.primefacespoc.controllers.seguroHogar;

import org.springframework.webflow.execution.RequestContext;

import es.esmartpoint.primefacespoc.support.OnEntryActionBase;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean;

public class CaracteristicasDeLaViviendaOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 4336353642143485232L;

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 4");
	}
	
}
