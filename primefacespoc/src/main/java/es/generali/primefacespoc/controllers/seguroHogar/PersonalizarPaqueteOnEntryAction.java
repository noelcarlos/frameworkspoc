package es.generali.primefacespoc.controllers.seguroHogar;

import java.io.Serializable;

import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class PersonalizarPaqueteOnEntryAction implements Serializable  {

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {

		model.setCapitalAseguradoVivienda(67156);
		model.setCapitalAseguradoEnseres(22365);
		model.setValorResponsabilidadCivil(100000);
		model.setPrecio(99.99);
		
	}
}
