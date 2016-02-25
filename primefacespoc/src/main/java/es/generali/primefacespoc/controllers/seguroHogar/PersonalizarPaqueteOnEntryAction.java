package es.generali.primefacespoc.controllers.seguroHogar;

import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.support.OnEntryActionBase;
import es.generali.segurohogar.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class PersonalizarPaqueteOnEntryAction extends OnEntryActionBase<SeguroViviendaBean>  {

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 6");

		model.setCapitalAseguradoVivienda(67156);
		model.setCapitalAseguradoEnseres(22365);
		model.setValorResponsabilidadCivil(100000);
		model.setPrecio(99.99);
		
	}
}
