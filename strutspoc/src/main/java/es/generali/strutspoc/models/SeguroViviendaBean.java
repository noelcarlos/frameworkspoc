package es.generali.strutspoc.models;

import java.util.Date;
import java.util.HashMap;

import javax.validation.constraints.NotNull;

import org.apache.struts.action.ActionForm;

public class SeguroViviendaBean extends ActionForm {
	HashMap<String, Object> map = new HashMap<String, Object>(); 
	
	private static final long serialVersionUID = 705547732312798668L;
	
	/* Que quieres proteger */
	
	@NotNull
	public Integer getTipoDeUsoViviendaId() {
		return (Integer)map.get("tipoDeUsoViviendaId");
	}
	public void setTipoDeUsoViviendaId(Integer tipoDeUsoViviendaId) {
		map.put("tipoDeUsoViviendaId", tipoDeUsoViviendaId);
	}
	
	@NotNull
	public Integer getNumPersonasQueVivenEnLaVivienda() {
		return (Integer)map.get("numPersonasQueVivenEnLaVivienda");
	}
	public void setNumPersonasQueVivenEnLaVivienda(Integer numPersonasQueVivenEnLaVivienda) {
		map.put("numPersonasQueVivenEnLaVivienda", numPersonasQueVivenEnLaVivienda);
	}
	
	/* Localizacion */
	
	@NotNull
	public Integer getProvinciaId() {
		return (Integer)map.get("provinciaId");
	}
	public void setProvinciaId(Integer provinciaId) {
		map.put("provinciaId", provinciaId);
	}
	
	@NotNull
	public Integer getLocalizacionId() {
		return (Integer)map.get("localizacionId");
	}
	public void setLocalizacionId(Integer localizacionId) {
		map.put("localizacionId", localizacionId);
	}
	
	/* Sobre la construcción */
	public Integer getTipoDeConstruccionId() {
		return (Integer)map.get("tipoDeConstruccionId");
	}
	public void setTipoDeConstruccionId(Integer tipoDeConstruccionId) {
		map.put("tipoDeConstruccionId", tipoDeConstruccionId);
	}
	public Integer getCalidadDeLaConstruccionId() {
		return (Integer)map.get("calidadDeLaConstruccionId");
	}
	public void setCalidadDeLaConstruccionId(Integer calidadDeLaConstruccionId) {
		map.put("calidadDeLaConstruccionId", calidadDeLaConstruccionId);
	}
	public Integer getTipologiaDeLaViviendaId() {
		return (Integer)map.get("tipologiaDeLaViviendaId");
	}
	public void setTipologiaDeLaViviendaId(Integer tipologiaDeLaViviendaId) {
		map.put("tipologiaDeLaViviendaId", tipologiaDeLaViviendaId);
	}
	
	/* Datos de la vivienda */
	public Double getMetrosCuadradosConstruidos() {
		return (Double)map.get("metrosCuadradosConstruidos");
	}
	public void setMetrosCuadradosConstruidos(Double metrosCuadradosConstruidos) {
		map.put("metrosCuadradosConstruidos", metrosCuadradosConstruidos);
	}
	public Integer getNumeroDeDormitorios() {
		return (Integer)map.get("numeroDeDormitorios");
	}
	public void setNumeroDeDormitorios(Integer numeroDeDormitorios) {
		map.put("numeroDeDormitorios", numeroDeDormitorios);
	}
	public Integer getAnyoDeConstruccion() {
		return (Integer)map.get("anyoDeConstruccion");
	}
	public void setAnyoDeConstruccion(Integer anyoDeConstruccion) {
		map.put("anyoDeConstruccion", anyoDeConstruccion);
	}
	public Integer getAnyoDeLaUltimaRehabilitacion() {
		return (Integer)map.get("anyoDeLaUltimaRehabilitacion");
	}
	public void setAnyoDeLaUltimaRehabilitacion(Integer anyoDeLaUltimaRehabilitacion) {
		map.put("anyoDeLaUltimaRehabilitacion", anyoDeLaUltimaRehabilitacion);
	}
	
	/* Medidas de seguridad de tu vivienda */
	
	/* Personalizar paquete */
	
	/* Datos del titular */
	public String getNombre() {
		return (String)map.get("nombre");
	}
	public void setNombre(String nombre) {
		map.put("nombre", nombre);
	}
	public String getApellido1() {
		return (String)map.get("apellido1");
	}
	public void setApellido1(String apellido1) {
		map.put("apellido1", apellido1);
	}
	public String getApellido2() {
		return (String)map.get("apellido2");
	}
	public void setApellido2(String apellido2) {
		map.put("apellido2", apellido2);
	}
	public Integer getTipoDeDocumentoDeIdentidadId() {
		return (Integer)map.get("tipoDeDocumentoDeIdentidadId");
	}
	public void setTipoDeDocumentoDeIdentidadId(Integer tipoDeDocumentoDeIdentidadId) {
		map.put("tipoDeDocumentoDeIdentidadId", tipoDeDocumentoDeIdentidadId);
	}
	public String getDocumentoIdentidad() {
		return (String)map.get("documentoIdentidad");
	}
	public void setDocumentoIdentidad(String documentoIdentidad) {
		map.put("documentoIdentidad", documentoIdentidad);
	}
	public Date getFechaDeNacimiento() {
		return (Date)map.get("fechaDeNacimiento");
	}
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		map.put("fechaDeNacimiento", fechaDeNacimiento);
	}
	public Integer getSexoId() {
		return (Integer)map.get("sexoId");
	}
	public void setSexoId(Integer sexoId) {
		map.put("sexoId", sexoId);
	}
	public String getTelefonoMovil() {
		return (String)map.get("telefonoMovil");
	}
	public void setTelefonoMovil(String telefonoMovil) {
		map.put("telefonoMovil", telefonoMovil);
	}
	public String getEmail() {
		return (String)map.get("email");
	}
	public void setEmail(String email) {
		map.put("email", email);
	}
	public Integer getTipoDeVíaTitularId() {
		return (Integer)map.get("tipoDeVíaTitularId");
	}
	public void setTipoDeVíaTitularId(Integer tipoDeVíaTitularId) {
		map.put("tipoDeVíaTitularId", tipoDeVíaTitularId);
	}
	public String getDomicilioTitular() {
		return (String)map.get("domicilioTitular");
	}
	public void setDomicilioTitular(String domicilioTitular) {
		map.put("domicilioTitular", domicilioTitular);
	}
	public String getNumeroYPisoTitular() {
		return (String)map.get("numeroYPisoTitular");
	}
	public void setNumeroYPisoTitular(String numeroYPisoTitular) {
		map.put("numeroYPisoTitular", numeroYPisoTitular);
	}
	public String getCodigoPostalTitular() {
		return (String)map.get("codigoPostalTitular");
	}
	public void setCodigoPostalTitular(String codigoPostalTitular) {
		map.put("codigoPostalTitular", codigoPostalTitular);
	}
	public String getLocalidadTitular() {
		return (String)map.get("localidadTitular");
	}
	public void setLocalidadTitular(String localidadTitular) {
		map.put("localidadTitular", localidadTitular);
	}
	public Integer getProvinciaTitularId() {
		return (Integer)map.get("provinciaTitularId");
	}
	public void setProvinciaTitularId(Integer provinciaTitularId) {
		map.put("provinciaTitularId", provinciaTitularId);
	}
	
	/* Vivienda a asegurar */
	public Integer getTipoDeVíaViviendaId() {
		return (Integer)map.get("tipoDeVíaViviendaId");
	}
	public void setTipoDeVíaViviendaId(Integer tipoDeVíaViviendaId) {
		map.put("tipoDeVíaViviendaId", tipoDeVíaViviendaId);
	}
	public String getDomicilioVivienda() {
		return (String)map.get("domicilioVivienda");
	}
	public void setDomicilioVivienda(String domicilioVivienda) {
		map.put("domicilioVivienda", domicilioVivienda);
	}
	public String getNumeroYPisoVivienda() {
		return (String)map.get("numeroYPisoVivienda");
	}
	public void setNumeroYPisoVivienda(String numeroYPisoVivienda) {
		map.put("numeroYPisoVivienda", numeroYPisoVivienda);
	}
	public String getCodigoPostalVivienda() {
		return (String)map.get("codigoPostalVivienda");
	}
	public void setCodigoPostalVivienda(String codigoPostalVivienda) {
		map.put("codigoPostalVivienda", codigoPostalVivienda);
	}
	public String getLocalidadVivienda() {
		return (String)map.get("localidadVivienda");
	}
	public void setLocalidadVivienda(String localidadVivienda) {
		map.put("localidadVivienda", localidadVivienda);
	}
	public Integer getProvinciaViviendaId() {
		return (Integer)map.get("provinciaViviendaId");
	}
	public void setProvinciaViviendaId(Integer provinciaViviendaId) {
		map.put("provinciaViviendaId", provinciaViviendaId);
	}
	
	/* Datos de pago */
	public Date getFechaDeEfecto() {
		return (Date)map.get("fechaDeEfecto");
	}
	public void setFechaDeEfecto(Date fechaDeEfecto) {
		map.put("fechaDeEfecto", fechaDeEfecto);
	}
	public String getCuentaIBAN() {
		return (String)map.get("cuentaIBAN");
	}
	public void setCuentaIBAN(String cuentaIBAN) {
		map.put("cuentaIBAN", cuentaIBAN);
	}
	public String getCuentaEntidad() {
		return (String)map.get("cuentaEntidad");
	}
	public void setCuentaEntidad(String cuentaEntidad) {
		map.put("cuentaEntidad", cuentaEntidad);
	}
	public String getCuentaOficina() {
		return (String)map.get("cuentaOficina");
	}
	public void setCuentaOficina(String cuentaOficina) {
		map.put("cuentaOficina", cuentaOficina);
	}
	public String getCuentaDigitoControl() {
		return (String)map.get("cuentaDigitoControl");
	}
	public void setCuentaDigitoControl(String cuentaDigitoControl) {
		map.put("cuentaDigitoControl", cuentaDigitoControl);
	}
	public String getCuentaNumero() {
		return (String)map.get("cuentaNumero");
	}
	public void setCuentaNumero(String cuentaNumero) {
		map.put("cuentaNumero", cuentaNumero);
	}
}
