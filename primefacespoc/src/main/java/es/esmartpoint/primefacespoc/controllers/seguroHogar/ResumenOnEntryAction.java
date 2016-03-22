package es.esmartpoint.primefacespoc.controllers.seguroHogar;

import org.springframework.webflow.execution.RequestContext;

import es.esmartpoint.primefacespoc.support.OnEntryActionBase;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class ResumenOnEntryAction extends OnEntryActionBase<SeguroViviendaBean>  {

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 10");
		
		model.setPrecio(3811.99);
		
	}
}
