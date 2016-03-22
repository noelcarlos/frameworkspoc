package es.esmartpoint.segurohogar.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class SeguroViviendaBean implements Serializable {
	HashMap<String, Object> map = new HashMap<String, Object>(); 
	
	private static final long serialVersionUID = 705547732312798668L;
	
	public SeguroViviendaBean() {
	}
	
	/* Que quieres proteger */
	public interface QueQuieresProteger {};
	
	@NotNull(groups={QueQuieresProteger.class})
	public Integer getTipoDeUsoViviendaId() {
		return (Integer)map.get("tipoDeUsoViviendaId");
	}
	public void setTipoDeUsoViviendaId(Integer tipoDeUsoViviendaId) {
		map.put("tipoDeUsoViviendaId", tipoDeUsoViviendaId);
	}
	@NotNull(groups={QueQuieresProteger.class})
	public Integer getNumPersonasQueVivenEnLaVivienda() {
		return (Integer)map.get("numPersonasQueVivenEnLaVivienda");
	}
	public void setNumPersonasQueVivenEnLaVivienda(Integer numPersonasQueVivenEnLaVivienda) {
		map.put("numPersonasQueVivenEnLaVivienda", numPersonasQueVivenEnLaVivienda);
	}
	
	/* Localizacion */

	public interface Localizacion {};
	
	@NotNull(groups={Localizacion.class})
	public Integer getProvinciaId() {
		return (Integer)map.get("provinciaId");
	}
	public void setProvinciaId(Integer provinciaId) {
		map.put("provinciaId", provinciaId);
	}
	
	@NotNull(groups={Localizacion.class})
	public Integer getLocalizacionId() {
		return (Integer)map.get("localizacionId");
	}
	public void setLocalizacionId(Integer localizacionId) {
		map.put("localizacionId", localizacionId);
	}
	
	/* Sobre la construcción */

	public interface SobreLaConstruccion {};
	
	@NotNull(groups={SobreLaConstruccion.class})
	public Integer getTipoDeConstruccionId() {
		return (Integer)map.get("tipoDeConstruccionId");
	}
	public void setTipoDeConstruccionId(Integer tipoDeConstruccionId) {
		map.put("tipoDeConstruccionId", tipoDeConstruccionId);
	}
	@NotNull(groups={SobreLaConstruccion.class})
	public Integer getCalidadDeLaConstruccionId() {
		return (Integer)map.get("calidadDeLaConstruccionId");
	}
	public void setCalidadDeLaConstruccionId(Integer calidadDeLaConstruccionId) {
		map.put("calidadDeLaConstruccionId", calidadDeLaConstruccionId);
	}
	@NotNull(groups={SobreLaConstruccion.class})
	public Integer getTipologiaDeLaViviendaId() {
		return (Integer)map.get("tipologiaDeLaViviendaId");
	}
	public void setTipologiaDeLaViviendaId(Integer tipologiaDeLaViviendaId) {
		map.put("tipologiaDeLaViviendaId", tipologiaDeLaViviendaId);
	}
	
	/* Caracteristicas */
	public interface CaracteristicasDeLaVivienda {};
	
	@NotNull(groups={CaracteristicasDeLaVivienda.class})
	public Double getMetrosCuadradosConstruidos() {
		return (Double)map.get("metrosCuadradosConstruidos");
	}
	public void setMetrosCuadradosConstruidos(Double metrosCuadradosConstruidos) {
		map.put("metrosCuadradosConstruidos", metrosCuadradosConstruidos);
	}
	@NotNull(groups={CaracteristicasDeLaVivienda.class})
	public Integer getNumeroDeDormitorios() {
		return (Integer)map.get("numeroDeDormitorios");
	}
	public void setNumeroDeDormitorios(Integer numeroDeDormitorios) {
		map.put("numeroDeDormitorios", numeroDeDormitorios);
	}
	@NotNull(groups={CaracteristicasDeLaVivienda.class})
	public Integer getAnyoDeConstruccion() {
		return (Integer)map.get("anyoDeConstruccion");
	}
	public void setAnyoDeConstruccion(Integer anyoDeConstruccion) {
		map.put("anyoDeConstruccion", anyoDeConstruccion);
	}
	@NotNull(groups={CaracteristicasDeLaVivienda.class})
	public Integer getAnyoDeLaUltimaRehabilitacion() {
		return (Integer)map.get("anyoDeLaUltimaRehabilitacion");
	}
	public void setAnyoDeLaUltimaRehabilitacion(Integer anyoDeLaUltimaRehabilitacion) {
		map.put("anyoDeLaUltimaRehabilitacion", anyoDeLaUltimaRehabilitacion);
	}
	
	/* Medidas de seguridad de tu vivienda */
	
	public interface MedidasDeSeguridadDeTuVivienda {};
	
	public Boolean getRejasEnVentanasOSimilares() {
		return (Boolean)map.get("rejasEnVentanasOSimilares");
	}
	public void setRejasEnVentanasOSimilares(Boolean rejasEnVentanasOSimilares) {
		map.put("rejasEnVentanasOSimilares", rejasEnVentanasOSimilares);
	}
	public Boolean getPuertaDeSeguridad() {
		return (Boolean)map.get("puertaDeSeguridad");
	}
	public void setPuertaDeSeguridad(Boolean puertaDeSeguridad) {
		map.put("puertaDeSeguridad", puertaDeSeguridad);
	}
	public Boolean getAlarmaConectada() {
		return (Boolean)map.get("alarmaConectada");
	}
	public void setAlarmaConectada(Boolean alarmaConectada) {
		map.put("alarmaConectada", alarmaConectada);
	}
	public Boolean getCajaFuerte() {
		return (Boolean)map.get("cajaFuerte");
	}
	public void setCajaFuerte(Boolean cajaFuerte) {
		map.put("cajaFuerte", cajaFuerte);
	}
	
	/* Personalizar paquete */
	
	public interface PersonalizarPaquete {};
	
	@NotNull(groups={PersonalizarPaquete.class})
	public Integer getCapitalAseguradoVivienda() {
		return (Integer)map.get("capitalAseguradoVivienda");
	}
	public void setCapitalAseguradoVivienda(Integer capitalAseguradoVivienda) {
		map.put("capitalAseguradoVivienda", capitalAseguradoVivienda);
	}
	@NotNull(groups={PersonalizarPaquete.class})
	public Integer getCapitalAseguradoEnseres() {
		return (Integer)map.get("capitalAseguradoEnseres");
	}
	public void setCapitalAseguradoEnseres(Integer capitalAseguradoEnseres) {
		map.put("capitalAseguradoEnseres", capitalAseguradoEnseres);
	}
	@NotNull(groups={PersonalizarPaquete.class})
	public Integer getValorResponsabilidadCivil() {
		return (Integer)map.get("valorResponsabilidadCivil");
	}
	public void setValorResponsabilidadCivil(Integer valorResponsabilidadCivil) {
		map.put("valorResponsabilidadCivil", valorResponsabilidadCivil);
	}
	
	@NotNull(groups={PersonalizarPaquete.class})
	public Double getPrecio() {
		return (Double)map.get("precio");
	}
	public void setPrecio(Double precio) {
		map.put("precio", precio);
	}
	
	public Boolean getOpcionRotura() {
		return (Boolean)map.get("opcionRotura");
	}
	public void setOpcionRotura(Boolean opcionRotura) {
		map.put("opcionRotura", opcionRotura);
	}
	public Boolean getOpcionRobo() {
		return (Boolean)map.get("opcionRobo");
	}
	public void setOpcionRobo(Boolean opcionRobo) {
		map.put("opcionRobo", opcionRobo);
	}
	public Boolean getOpcionDefensaJuridica() {
		return (Boolean)map.get("opcionDefensaJuridica");
	}
	public void setOpcionDefensaJuridica(Boolean opcionDefensaJuridica) {
		map.put("opcionDefensaJuridica", opcionDefensaJuridica);
	}
	public Boolean getOpcionInhabilidad() {
		return (Boolean)map.get("opcionInhabilidad");
	}
	public void setOpcionInhabilidad(Boolean opcionInhabilidad) {
		map.put("opcionInhabilidad", opcionInhabilidad);
	}
	public Boolean getOpcionAsistenciaInformatica() {
		return (Boolean)map.get("opcionAsistenciaInformatica");
	}
	public void setOpcionAsistenciaInformatica(Boolean opcionAsistenciaInformatica) {
		map.put("opcionAsistenciaInformatica", opcionAsistenciaInformatica);
	}
	@NotNull(groups={PersonalizarPaquete.class})
	public Integer getCapitalAseguradoJoyas() {
		return (Integer)map.get("capitalAseguradoJoyas");
	}
	public void setCapitalAseguradoJoyas(Integer capitalAseguradoJoyas) {
		map.put("capitalAseguradoJoyas", capitalAseguradoJoyas);
	}
	@NotNull(groups={PersonalizarPaquete.class})
	public Integer getCapitalAseguradoObjetosDeValor() {
		return (Integer)map.get("capitalAseguradoObjetosDeValor");
	}
	public void setCapitalAseguradoObjetosDeValor(Integer capitalAseguradoObjetosDeValor) {
		map.put("capitalAseguradoObjetosDeValor", capitalAseguradoObjetosDeValor);
	}
	@NotNull(groups={PersonalizarPaquete.class})
	public Integer getCapitalAseguradoRecomposicionEstetica() {
		return (Integer)map.get("capitalAseguradoRecomposicionEstetica");
	}
	public void setCapitalAseguradoRecomposicionEstetica(Integer capitalAseguradoRecomposicionEstetica) {
		map.put("capitalAseguradoRecomposicionEstetica", capitalAseguradoRecomposicionEstetica);
	}
	
	/* Datos del titular */
	
	public interface DatosDelTitular {};
	
	@NotEmpty(groups={DatosDelTitular.class})
	public String getNombre() {
		return (String)map.get("nombre");
	}
	public void setNombre(String nombre) {
		map.put("nombre", nombre);
	}
	@NotEmpty(groups={DatosDelTitular.class})
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
	@NotNull(groups={DatosDelTitular.class})
	public Integer getTipoDeDocumentoDeIdentidadId() {
		return (Integer)map.get("tipoDeDocumentoDeIdentidadId");
	}
	public void setTipoDeDocumentoDeIdentidadId(Integer tipoDeDocumentoDeIdentidadId) {
		map.put("tipoDeDocumentoDeIdentidadId", tipoDeDocumentoDeIdentidadId);
	}
	@NotEmpty(groups={DatosDelTitular.class})
	public String getDocumentoIdentidad() {
		return (String)map.get("documentoIdentidad");
	}
	public void setDocumentoIdentidad(String documentoIdentidad) {
		map.put("documentoIdentidad", documentoIdentidad);
	}
	@NotNull(groups={DatosDelTitular.class})
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFechaDeNacimiento() {
		return (Date)map.get("fechaDeNacimiento");
	}
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		map.put("fechaDeNacimiento", fechaDeNacimiento);
	}
	@NotNull(groups={DatosDelTitular.class})
	public Integer getSexoId() {
		return (Integer)map.get("sexoId");
	}
	public void setSexoId(Integer sexoId) {
		map.put("sexoId", sexoId);
	}
	@NotEmpty(groups={DatosDelTitular.class})
	public String getTelefonoMovil() {
		return (String)map.get("telefonoMovil");
	}
	public void setTelefonoMovil(String telefonoMovil) {
		map.put("telefonoMovil", telefonoMovil);
	}
	@NotEmpty(groups={DatosDelTitular.class})
	public String getEmail() {
		return (String)map.get("email");
	}
	public void setEmail(String email) {
		map.put("email", email);
	}
	@NotNull(groups={DatosDelTitular.class})
	public Integer getTipoDeViaTitularId() {
		return (Integer)map.get("tipoDeViaTitularId");
	}
	public void setTipoDeViaTitularId(Integer tipoDeViaTitularId) {
		map.put("tipoDeViaTitularId", tipoDeViaTitularId);
	}
	@NotEmpty(groups={DatosDelTitular.class})
	public String getDomicilioTitular() {
		return (String)map.get("domicilioTitular");
	}
	public void setDomicilioTitular(String domicilioTitular) {
		map.put("domicilioTitular", domicilioTitular);
	}
	@NotEmpty(groups={DatosDelTitular.class})
	public String getNumeroYPisoTitular() {
		return (String)map.get("numeroYPisoTitular");
	}
	public void setNumeroYPisoTitular(String numeroYPisoTitular) {
		map.put("numeroYPisoTitular", numeroYPisoTitular);
	}
	@NotEmpty(groups={DatosDelTitular.class})
	public String getCodigoPostalTitular() {
		return (String)map.get("codigoPostalTitular");
	}
	public void setCodigoPostalTitular(String codigoPostalTitular) {
		map.put("codigoPostalTitular", codigoPostalTitular);
	}
	@NotEmpty(groups={DatosDelTitular.class})
	public String getLocalidadTitular() {
		return (String)map.get("localidadTitular");
	}
	public void setLocalidadTitular(String localidadTitular) {
		map.put("localidadTitular", localidadTitular);
	}
	@NotNull(groups={DatosDelTitular.class})
	public Integer getProvinciaTitularId() {
		return (Integer)map.get("provinciaTitularId");
	}
	public void setProvinciaTitularId(Integer provinciaTitularId) {
		map.put("provinciaTitularId", provinciaTitularId);
	}
	public Boolean getLaViviendaAsegurarCoincideConLaDelTitular() {
		return (Boolean)map.get("laViviendaAsegurarCoincideConLaDelTitular");
	}
	public void setLaViviendaAsegurarCoincideConLaDelTitular(Boolean laViviendaAsegurarCoincideConLaDelTitular) {
		map.put("laViviendaAsegurarCoincideConLaDelTitular", laViviendaAsegurarCoincideConLaDelTitular);
	}
	
	/* Vivienda a asegurar */
	
	public interface DatosDeLaViviendaAAsegurar {};
	
	@NotNull(groups={DatosDeLaViviendaAAsegurar.class})
	public Integer getTipoDeViaViviendaId() {
		return (Integer)map.get("tipoDeViaViviendaId");
	}
	public void setTipoDeViaViviendaId(Integer tipoDeViaViviendaId) {
		map.put("tipoDeViaViviendaId", tipoDeViaViviendaId);
	}
	@NotEmpty(groups={DatosDeLaViviendaAAsegurar.class})
	public String getDomicilioVivienda() {
		return (String)map.get("domicilioVivienda");
	}
	public void setDomicilioVivienda(String domicilioVivienda) {
		map.put("domicilioVivienda", domicilioVivienda);
	}
	@NotEmpty(groups={DatosDeLaViviendaAAsegurar.class})
	public String getNumeroYPisoVivienda() {
		return (String)map.get("numeroYPisoVivienda");
	}
	public void setNumeroYPisoVivienda(String numeroYPisoVivienda) {
		map.put("numeroYPisoVivienda", numeroYPisoVivienda);
	}
	@NotEmpty(groups={DatosDeLaViviendaAAsegurar.class})
	public String getCodigoPostalVivienda() {
		return (String)map.get("codigoPostalVivienda");
	}
	public void setCodigoPostalVivienda(String codigoPostalVivienda) {
		map.put("codigoPostalVivienda", codigoPostalVivienda);
	}
	@NotEmpty(groups={DatosDeLaViviendaAAsegurar.class})
	public String getLocalidadVivienda() {
		return (String)map.get("localidadVivienda");
	}
	public void setLocalidadVivienda(String localidadVivienda) {
		map.put("localidadVivienda", localidadVivienda);
	}
	@NotNull(groups={DatosDeLaViviendaAAsegurar.class})
	public Integer getProvinciaViviendaId() {
		return (Integer)map.get("provinciaViviendaId");
	}
	public void setProvinciaViviendaId(Integer provinciaViviendaId) {
		map.put("provinciaViviendaId", provinciaViviendaId);
	}
	
	/* Datos de pago */
	
	public interface DatosDePago {};
	
	@NotNull(groups={DatosDePago.class})
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFechaDeEfecto() {
		return (Date)map.get("fechaDeEfecto");
	}
	public void setFechaDeEfecto(Date fechaDeEfecto) {
		map.put("fechaDeEfecto", fechaDeEfecto);
	}
	@NotEmpty(groups={DatosDePago.class})
	public String getCuentaIBAN() {
		return (String)map.get("cuentaIBAN");
	}
	public void setCuentaIBAN(String cuentaIBAN) {
		map.put("cuentaIBAN", cuentaIBAN);
	}
	@NotEmpty(groups={DatosDePago.class})
	public String getCuentaEntidad() {
		return (String)map.get("cuentaEntidad");
	}
	public void setCuentaEntidad(String cuentaEntidad) {
		map.put("cuentaEntidad", cuentaEntidad);
	}
	@NotEmpty(groups={DatosDePago.class})
	public String getCuentaOficina() {
		return (String)map.get("cuentaOficina");
	}
	public void setCuentaOficina(String cuentaOficina) {
		map.put("cuentaOficina", cuentaOficina);
	}
	@NotEmpty(groups={DatosDePago.class})
	public String getCuentaDigitoControl() {
		return (String)map.get("cuentaDigitoControl");
	}
	public void setCuentaDigitoControl(String cuentaDigitoControl) {
		map.put("cuentaDigitoControl", cuentaDigitoControl);
	}
	@NotEmpty(groups={DatosDePago.class})
	public String getCuentaNumero() {
		return (String)map.get("cuentaNumero");
	}
	public void setCuentaNumero(String cuentaNumero) {
		map.put("cuentaNumero", cuentaNumero);
	}
	
	/* Entra */
	
	public String getContrato() {
		return (String)map.get("contrato");
	}
	public void setContrato(String contrato) {
		map.put("contrato", contrato);
	}
}
