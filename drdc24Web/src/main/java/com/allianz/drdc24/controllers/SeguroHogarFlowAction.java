package com.allianz.drdc24.controllers;

import java.util.GregorianCalendar;

import org.springframework.webflow.execution.RequestContext;

import com.allianz.drdc24.models.ConfiguracionBean;
import com.allianz.drdc24.models.SeguroViviendaBean;
import com.allianz.drdc24.support.GeneratorHelper;

public class SeguroHogarFlowAction extends BaseWebFlowController {
	private static final long serialVersionUID = 6848148192857690277L;
	
	private SeguroViviendaBean model;
	
	public void onInit(RequestContext requestContext) throws Exception {
		model = (SeguroViviendaBean)flowScope.get("model");
		
		ConfiguracionBean config = (ConfiguracionBean)session.getAttribute("config");
		
		if (session.getAttribute("config") == null) {
			config = new ConfiguracionBean();
			
			config.setQueQuieresProtegerExterno(true);
			config.setLocalizacionExterno(true);
			config.setSobreLaConstruccionExterno(true);
			config.setCaracteristicasDeLaViviendaExterno(true);
			config.setMedidasDeSeguridadDeTuViviendaExterno(true);
			config.setPersonalizarPaqueteExterno(true);
			config.setDatosDelTitularExterno(true);
			config.setDatosDeLaViviendaAAsegurarExterno(true);
			config.setDatosDePagoExterno(true);
			config.setResumenExterno(true);
			
			session.setAttribute("config", config);
			flowScope.put("config", config);
		} 
		
		setup(config);
	}
	
	public SeguroViviendaBean getModel() {
		return model;
	}
	
	public void setup(ConfiguracionBean config) {
		if (config.getQueQuieresProtegerInicializar()) {
			model.setTipoDeUsoViviendaId(1);
			model.setNumPersonasQueVivenEnLaVivienda(8);
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
			model.setMetrosCuadradosConstruidos(250.0);
			model.setNumeroDeDormitorios(6);
			model.setAnyoDeConstruccion(2012);
			model.setAnyoDeLaUltimaRehabilitacion(2012);
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

	public void setModel(SeguroViviendaBean model) {
		this.model = model;
	}

}
