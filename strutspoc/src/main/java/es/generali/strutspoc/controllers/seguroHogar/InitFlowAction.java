package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import es.generali.strutspoc.models.SeguroViviendaBean;

public class InitFlowAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		model.setTipoDeUsoViviendaId(1);
		model.setNumPersonasQueVivenEnLaVivienda(4);
		
	}
}