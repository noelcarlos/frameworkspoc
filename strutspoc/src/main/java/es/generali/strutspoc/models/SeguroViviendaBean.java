package es.generali.strutspoc.models;

import java.util.Date;
import java.util.HashMap;

public class SeguroViviendaBean extends HashMap<String, Object>{
	private static final long serialVersionUID = 705547732312798668L;
	
	/* Que quieres proteger */
	public Integer getTipoDeUsoVivienda() {
		return (Integer)get("tipoDeUsoVivienda");
	}
	public void setTipoDeUsoViviendaId(Integer tipoDeUsoViviendaId) {
		put("tipoDeUsoViviendaId", tipoDeUsoViviendaId);
	}
	public Integer getNumPersonasQueVivenEnLaViviendaId() {
		return (Integer)get("numPersonasQueVivenEnLaViviendaId");
	}
	public void setNumPersonasQueVivenEnLaVivienda(Integer numPersonasQueVivenEnLaVivienda) {
		put("numPersonasQueVivenEnLaVivienda", numPersonasQueVivenEnLaVivienda);
	}
	
	/* Localizacion */
	public Integer getProvinciaId() {
		return (Integer)get("provinciaId");
	}
	public void setProvinciaId(Integer provinciaId) {
		put("provinciaId", provinciaId);
	}
	public Integer getLocalizacionId() {
		return (Integer)get("localizacionId");
	}
	public void setLocalizacionId(Integer localizacionId) {
		put("localizacionId", localizacionId);
	}
	
	/* Sobre la construcción */
	public Integer getTipoDeConstruccionId() {
		return (Integer)get("tipoDeConstruccionId");
	}
	public void setTipoDeConstruccionId(Integer tipoDeConstruccionId) {
		put("tipoDeConstruccionId", tipoDeConstruccionId);
	}
	public Integer getCalidadDeLaConstruccionId() {
		return (Integer)get("calidadDeLaConstruccionId");
	}
	public void setCalidadDeLaConstruccionId(Integer calidadDeLaConstruccionId) {
		put("calidadDeLaConstruccionId", calidadDeLaConstruccionId);
	}
	public Integer getTipologiaDeLaViviendaId() {
		return (Integer)get("tipologiaDeLaViviendaId");
	}
	public void setTipologiaDeLaViviendaId(Integer tipologiaDeLaViviendaId) {
		put("tipologiaDeLaViviendaId", tipologiaDeLaViviendaId);
	}
	
	/* Datos de la vivienda */
	public Double getMetrosCuadradosConstruidos() {
		return (Double)get("metrosCuadradosConstruidos");
	}
	public void setMetrosCuadradosConstruidos(Double metrosCuadradosConstruidos) {
		put("metrosCuadradosConstruidos", metrosCuadradosConstruidos);
	}
	public Integer getNumeroDeDormitorios() {
		return (Integer)get("numeroDeDormitorios");
	}
	public void setNumeroDeDormitorios(Integer numeroDeDormitorios) {
		put("numeroDeDormitorios", numeroDeDormitorios);
	}
	public Integer getAnyoDeConstruccion() {
		return (Integer)get("anyoDeConstruccion");
	}
	public void setAnyoDeConstruccion(Integer anyoDeConstruccion) {
		put("anyoDeConstruccion", anyoDeConstruccion);
	}
	public Integer getAnyoDeLaUltimaRehabilitacion() {
		return (Integer)get("anyoDeLaUltimaRehabilitacion");
	}
	public void setAnyoDeLaUltimaRehabilitacion(Integer anyoDeLaUltimaRehabilitacion) {
		put("anyoDeLaUltimaRehabilitacion", anyoDeLaUltimaRehabilitacion);
	}
	
	/* Medidas de seguridad de tu vivienda */
	
	/* Personalizar paquete */
	
	/* Datos del titular */
	public String getNombre() {
		return (String)get("nombre");
	}
	public void setNombre(String nombre) {
		put("nombre", nombre);
	}
	public String getApellido1() {
		return (String)get("apellido1");
	}
	public void setApellido1(String apellido1) {
		put("apellido1", apellido1);
	}
	public String getApellido2() {
		return (String)get("apellido2");
	}
	public void setApellido2(String apellido2) {
		put("apellido2", apellido2);
	}
	public Integer getTipoDeDocumentoDeIdentidadId() {
		return (Integer)get("tipoDeDocumentoDeIdentidadId");
	}
	public void setTipoDeDocumentoDeIdentidadId(Integer tipoDeDocumentoDeIdentidadId) {
		put("tipoDeDocumentoDeIdentidadId", tipoDeDocumentoDeIdentidadId);
	}
	public String getDocumentoIdentidad() {
		return (String)get("documentoIdentidad");
	}
	public void setDocumentoIdentidad(String documentoIdentidad) {
		put("documentoIdentidad", documentoIdentidad);
	}
	public Date getFechaDeNacimiento() {
		return (Date)get("fechaDeNacimiento");
	}
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		put("fechaDeNacimiento", fechaDeNacimiento);
	}
	public Integer getSexoId() {
		return (Integer)get("sexoId");
	}
	public void setSexoId(Integer sexoId) {
		put("sexoId", sexoId);
	}
	public String getTelefonoMovil() {
		return (String)get("telefonoMovil");
	}
	public void setTelefonoMovil(String telefonoMovil) {
		put("telefonoMovil", telefonoMovil);
	}
	public String getEmail() {
		return (String)get("email");
	}
	public void setEmail(String email) {
		put("email", email);
	}
	public Integer getTipoDeVíaTitularId() {
		return (Integer)get("tipoDeVíaTitularId");
	}
	public void setTipoDeVíaTitularId(Integer tipoDeVíaTitularId) {
		put("tipoDeVíaTitularId", tipoDeVíaTitularId);
	}
	public String getDomicilioTitular() {
		return (String)get("domicilioTitular");
	}
	public void setDomicilioTitular(String domicilioTitular) {
		put("domicilioTitular", domicilioTitular);
	}
	public String getNumeroYPisoTitular() {
		return (String)get("numeroYPisoTitular");
	}
	public void setNumeroYPisoTitular(String numeroYPisoTitular) {
		put("numeroYPisoTitular", numeroYPisoTitular);
	}
	public String getCodigoPostalTitular() {
		return (String)get("codigoPostalTitular");
	}
	public void setCodigoPostalTitular(String codigoPostalTitular) {
		put("codigoPostalTitular", codigoPostalTitular);
	}
	public String getLocalidadTitular() {
		return (String)get("localidadTitular");
	}
	public void setLocalidadTitular(String localidadTitular) {
		put("localidadTitular", localidadTitular);
	}
	public Integer getProvinciaTitularId() {
		return (Integer)get("provinciaTitularId");
	}
	public void setProvinciaTitularId(Integer provinciaTitularId) {
		put("provinciaTitularId", provinciaTitularId);
	}
	
	/* Vivienda a asegurar */
	public Integer getTipoDeVíaViviendaId() {
		return (Integer)get("tipoDeVíaViviendaId");
	}
	public void setTipoDeVíaViviendaId(Integer tipoDeVíaViviendaId) {
		put("tipoDeVíaViviendaId", tipoDeVíaViviendaId);
	}
	public String getDomicilioVivienda() {
		return (String)get("domicilioVivienda");
	}
	public void setDomicilioVivienda(String domicilioVivienda) {
		put("domicilioVivienda", domicilioVivienda);
	}
	public String getNumeroYPisoVivienda() {
		return (String)get("numeroYPisoVivienda");
	}
	public void setNumeroYPisoVivienda(String numeroYPisoVivienda) {
		put("numeroYPisoVivienda", numeroYPisoVivienda);
	}
	public String getCodigoPostalVivienda() {
		return (String)get("codigoPostalVivienda");
	}
	public void setCodigoPostalVivienda(String codigoPostalVivienda) {
		put("codigoPostalVivienda", codigoPostalVivienda);
	}
	public String getLocalidadVivienda() {
		return (String)get("localidadVivienda");
	}
	public void setLocalidadVivienda(String localidadVivienda) {
		put("localidadVivienda", localidadVivienda);
	}
	public Integer getProvinciaViviendaId() {
		return (Integer)get("provinciaViviendaId");
	}
	public void setProvinciaViviendaId(Integer provinciaViviendaId) {
		put("provinciaViviendaId", provinciaViviendaId);
	}
	
	/* Datos de pago */
	public Date getFechaDeEfecto() {
		return (Date)get("fechaDeEfecto");
	}
	public void setFechaDeEfecto(Date fechaDeEfecto) {
		put("fechaDeEfecto", fechaDeEfecto);
	}
	public String getCuentaIBAN() {
		return (String)get("cuentaIBAN");
	}
	public void setCuentaIBAN(String cuentaIBAN) {
		put("cuentaIBAN", cuentaIBAN);
	}
	public String getCuentaEntidad() {
		return (String)get("cuentaEntidad");
	}
	public void setCuentaEntidad(String cuentaEntidad) {
		put("cuentaEntidad", cuentaEntidad);
	}
	public String getCuentaOficina() {
		return (String)get("cuentaOficina");
	}
	public void setCuentaOficina(String cuentaOficina) {
		put("cuentaOficina", cuentaOficina);
	}
	public String getCuentaDigitoControl() {
		return (String)get("cuentaDigitoControl");
	}
	public void setCuentaDigitoControl(String cuentaDigitoControl) {
		put("cuentaDigitoControl", cuentaDigitoControl);
	}
	public String getCuentaNumero() {
		return (String)get("cuentaNumero");
	}
	public void setCuentaNumero(String cuentaNumero) {
		put("cuentaNumero", cuentaNumero);
	}
}
