package es.generali.primefacespoc.controllers.seguroHogar;

import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.support.OnEntryActionBase;
import es.generali.segurohogar.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class MedidasDeSeguridadDeTuViviendaOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 5");
	}
}
