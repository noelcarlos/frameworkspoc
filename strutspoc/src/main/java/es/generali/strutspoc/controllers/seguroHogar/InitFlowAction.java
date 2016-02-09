package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import es.generali.strutspoc.models.SeguroViviendaBean;
import es.generali.strutspoc.support.GeneratorHelper;

public class InitFlowAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		model.setTipoDeUsoViviendaId(1);
		model.setNumPersonasQueVivenEnLaVivienda(4);
		
		model.setRejasEnVentanasOSimilares(true);
		model.setCapitalAseguradoJoyas(20000);
		
		model.setContrato(new GeneratorHelper().randomText(100000, 100000, "\r\n\r\n", 256, 1024,
				50, 200, 2, 10));
	}
}
