package es.esmartpoint.primefacespoc.controllers.seguroHogar;

import org.springframework.webflow.execution.RequestContext;

import es.esmartpoint.primefacespoc.support.OnEntryActionBase;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class DatosDePagoOnEntryAction extends OnEntryActionBase<SeguroViviendaBean>  {

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 9");
	}
}
