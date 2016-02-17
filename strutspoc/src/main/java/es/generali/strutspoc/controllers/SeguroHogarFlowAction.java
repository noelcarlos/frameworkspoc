package es.generali.strutspoc.controllers;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.generali.segurohogar.models.SeguroViviendaBean;
import es.generali.strutspoc.models.ConfiguracionBean;
import es.generali.strutspoc.support.GeneratorHelper;

public class SeguroHogarFlowAction extends StrutsFlowAction {
	
	public void onEntry(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();

		ConfiguracionBean config = new ConfiguracionBean();
		session.setAttribute("config", config);

		SeguroViviendaBean model = new SeguroViviendaBean();
		session.setAttribute("model", model);
	}
	
	public ActionForward onSetup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();
		
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		
		ConfiguracionBean config = (ConfiguracionBean)session.getAttribute("config");
		convertAndValidate(request, config, errors, messages);
		session.setAttribute("config", config);
		
		SeguroViviendaBean model = (SeguroViviendaBean)session.getAttribute("model");
		setup(model, (ConfiguracionBean)session.getAttribute("config"));
		session.setAttribute("model", model);
		
		int currentStep = Integer.parseInt(session.getAttribute("currentStep").toString());
		
		Document flow = (Document)session.getAttribute("flow");
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		String title = node.valueOf("@title");
		request.setAttribute("currentPageTitle", title);
		request.setAttribute("currentPageNumber", "" + currentStep);
		
		session.setAttribute("currentStep", "" + currentStep); 
		
		response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onStep&step=" + currentStep);		
		
		return null;
	}
	
	void setup(SeguroViviendaBean model, ConfiguracionBean config) {
		if (config.getQueQuieresProteger()) {
			model.setTipoDeUsoViviendaId(1);
			model.setNumPersonasQueVivenEnLaVivienda(4);
		}
		if (config.getLocalizacion()) {
			model.setProvinciaId(2);
			model.setLocalizacionId(1);
		}
		if (config.getSobreLaConstruccion()) {
			model.setTipoDeConstruccionId(1);
			model.setCalidadDeLaConstruccionId(1);
			model.setTipologiaDeLaViviendaId(1);
		}
		if (config.getCaracteristicasDeLaVivienda()) {
			model.setMetrosCuadradosConstruidos(120.0);
			model.setNumeroDeDormitorios(4);
			model.setAnyoDeConstruccion(1990);
			model.setAnyoDeLaUltimaRehabilitacion(2014);
		}
		if (config.getMedidasDeSeguridadDeTuVivienda()) {
			model.setRejasEnVentanasOSimilares(true);
			model.setPuertaDeSeguridad(true);
			model.setAlarmaConectada(true);
			model.setCajaFuerte(true);
		}
		if (config.getPersonalizarPaquete()) {
			model.setOpcionRotura(true);
			model.setOpcionRobo(true);
			model.setOpcionAsistenciaInformatica(true);
			model.setOpcionDefensaJuridica(true);
			model.setOpcionInhabilidad(true);
			model.setCapitalAseguradoJoyas(20000);
			model.setCapitalAseguradoObjetosDeValor(15000);
			model.setCapitalAseguradoRecomposicionEstetica(10000);
		}
		if (config.getDatosDelTitular()) {
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
		if (config.getDatosDeLaViviendaAAsegurar()) {
			model.setTipoDeViaViviendaId(4);
			model.setDomicilioVivienda("SION");
			model.setNumeroYPisoVivienda("9/11");
			model.setCodigoPostalVivienda("2001");
			model.setLocalidadVivienda("CAPITAL CITY");
			model.setProvinciaViviendaId(2);
		}
		if (config.getDatosDePago()) {
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
