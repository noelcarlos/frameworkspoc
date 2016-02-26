package es.generali.strutspoc.controllers.seguroHogar;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;

import es.generali.segurohogar.models.ConfiguracionBean;
import es.generali.segurohogar.models.SeguroViviendaBean;
import es.generali.strutspoc.support.GeneratorHelper;

public class InitFlowAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		setup(model, (ConfiguracionBean)session.getAttribute("config"));
		session.setAttribute("model", model);
		
	}
	
	void setup(SeguroViviendaBean model, ConfiguracionBean config) {
		if (config.getQueQuieresProtegerInicializar()) {
			model.setTipoDeUsoViviendaId(1);
			model.setNumPersonasQueVivenEnLaVivienda(4);
		}
		if (config.getLocalizacionInicializar()) {
			model.setProvinciaId(2);
			model.setLocalizacionId(1);
		}
		if (config.getSobreLaConstruccionInicializar()) {
			model.setTipoDeConstruccionId(1);
			model.setCalidadDeLaConstruccionId(1);
			model.setTipologiaDeLaViviendaId(1);
		}
		if (config.getCaracteristicasDeLaViviendaInicializar()) {
			model.setMetrosCuadradosConstruidos(120.0);
			model.setNumeroDeDormitorios(4);
			model.setAnyoDeConstruccion(1990);
			model.setAnyoDeLaUltimaRehabilitacion(2014);
		}
		if (config.getMedidasDeSeguridadDeTuViviendaInicializar()) {
			model.setRejasEnVentanasOSimilares(true);
			model.setPuertaDeSeguridad(true);
			model.setAlarmaConectada(true);
			model.setCajaFuerte(true);
		}
		if (config.getPersonalizarPaqueteInicializar()) {
			model.setOpcionRotura(true);
			model.setOpcionRobo(true);
			model.setOpcionAsistenciaInformatica(true);
			model.setOpcionDefensaJuridica(true);
			model.setOpcionInhabilidad(true);
			model.setCapitalAseguradoJoyas(20000);
			model.setCapitalAseguradoObjetosDeValor(15000);
			model.setCapitalAseguradoRecomposicionEstetica(10000);
		}
		if (config.getDatosDelTitularInicializar()) {
			model.setNombre("Thomas");
			model.setApellido1("Anderson");
			model.setTipoDeDocumentoDeIdentidadId(1);
			model.setDocumentoIdentidad("1238123D");
			model.setFechaDeNacimiento(new GregorianCalendar(1971, 10, 13).getTime());
			model.setSexoId(1);
			model.setTelefonoMovil("932929212");
			model.setEmail("neo.matrix@google.com");
			model.setTipoDeViaTitularId(5);
			model.setDomicilioTitular("SION");
			model.setNumeroYPisoTitular("9/11");
			model.setCodigoPostalTitular("2001");
			model.setLocalidadTitular("CAPITAL CITY");
			model.setProvinciaTitularId(2);
		}
		if (config.getDatosDeLaViviendaAAsegurarInicializar()) {
			model.setTipoDeViaViviendaId(4);
			model.setDomicilioVivienda("SION");
			model.setNumeroYPisoVivienda("9/11");
			model.setCodigoPostalVivienda("2001");
			model.setLocalidadVivienda("CAPITAL CITY");
			model.setProvinciaViviendaId(2);
		}
		if (config.getDatosDePagoInicializar()) {
			model.setCuentaIBAN("ES76");
			model.setCuentaEntidad("2001");
			model.setCuentaOficina("0911");
			model.setCuentaDigitoControl("93");
			model.setCuentaNumero("1317332222");
			model.setFechaDeEfecto(new GregorianCalendar(2016, 11, 16).getTime());
		}
		
		model.setContrato(new GeneratorHelper().randomText(config.getKbContrato()*1024, config.getKbContrato()*1024, "\r\n\r\n", 256, 1024,
				50, 200, 2, 10));
	}
}
