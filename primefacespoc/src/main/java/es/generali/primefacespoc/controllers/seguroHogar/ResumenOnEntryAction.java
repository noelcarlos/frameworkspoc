package es.generali.primefacespoc.controllers.seguroHogar;

import java.io.Serializable;

import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class ResumenOnEntryAction implements Serializable  {

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		
		model.setPrecio(3811.99);
		
	}
}
