package es.generali.primefacespoc.controllers.seguroHogar;

import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.support.OnEntryActionBase;
import es.generali.segurohogar.models.SeguroViviendaBean;

public class CaracteristicasDeLaViviendaOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 4336353642143485232L;

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 4");
	}
	
}
