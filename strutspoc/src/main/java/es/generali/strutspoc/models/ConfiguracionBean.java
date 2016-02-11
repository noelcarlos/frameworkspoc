package es.generali.strutspoc.models;

import java.io.Serializable;

public class ConfiguracionBean implements Serializable {
	private static final long serialVersionUID = 6707938793386834406L;
	
	Boolean queQuieresProteger;
	Boolean localizacion;
	Boolean sobreLaConstruccion;
	Boolean caracteristicasDeLaVivienda;
	Boolean medidasDeSeguridadDeTuVivienda;
	Boolean personalizarPaquete;
	Boolean datosDelTitular;
	Boolean datosDeLaViviendaAAsegurar;
	Boolean datosDePago;
	Boolean resumen;
	Integer kbContrato;
	
	public ConfiguracionBean() {
		queQuieresProteger = false;
		localizacion = false;
		sobreLaConstruccion = false;
		caracteristicasDeLaVivienda = false;
		medidasDeSeguridadDeTuVivienda = false;
		personalizarPaquete = false;
		datosDelTitular = false;
		datosDeLaViviendaAAsegurar = false;
		datosDePago = false;
		resumen = false;
		kbContrato = 100;
	}
	
	public Boolean getQueQuieresProteger() {
		return queQuieresProteger;
	}
	public void setQueQuieresProteger(Boolean queQuieresProteger) {
		this.queQuieresProteger = queQuieresProteger;
	}
	public Boolean getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(Boolean localizacion) {
		this.localizacion = localizacion;
	}
	public Boolean getSobreLaConstruccion() {
		return sobreLaConstruccion;
	}
	public void setSobreLaConstruccion(Boolean sobreLaConstruccion) {
		this.sobreLaConstruccion = sobreLaConstruccion;
	}
	public Boolean getCaracteristicasDeLaVivienda() {
		return caracteristicasDeLaVivienda;
	}
	public void setCaracteristicasDeLaVivienda(Boolean caracteristicasDeLaVivienda) {
		this.caracteristicasDeLaVivienda = caracteristicasDeLaVivienda;
	}
	public Boolean getMedidasDeSeguridadDeTuVivienda() {
		return medidasDeSeguridadDeTuVivienda;
	}
	public void setMedidasDeSeguridadDeTuVivienda(Boolean medidasDeSeguridadDeTuVivienda) {
		this.medidasDeSeguridadDeTuVivienda = medidasDeSeguridadDeTuVivienda;
	}
	public Boolean getPersonalizarPaquete() {
		return personalizarPaquete;
	}
	public void setPersonalizarPaquete(Boolean personalizarPaquete) {
		this.personalizarPaquete = personalizarPaquete;
	}
	public Boolean getDatosDelTitular() {
		return datosDelTitular;
	}
	public void setDatosDelTitular(Boolean datosDelTitular) {
		this.datosDelTitular = datosDelTitular;
	}
	public Boolean getDatosDeLaViviendaAAsegurar() {
		return datosDeLaViviendaAAsegurar;
	}
	public void setDatosDeLaViviendaAAsegurar(Boolean datosDeLaViviendaAAsegurar) {
		this.datosDeLaViviendaAAsegurar = datosDeLaViviendaAAsegurar;
	}
	public Boolean getDatosDePago() {
		return datosDePago;
	}
	public void setDatosDePago(Boolean datosDePago) {
		this.datosDePago = datosDePago;
	}
	public Boolean getResumen() {
		return resumen;
	}
	public void setResumen(Boolean resumen) {
		this.resumen = resumen;
	}
	public Integer getKbContrato() {
		return kbContrato;
	}
	public void setKbContrato(Integer kbContrato) {
		this.kbContrato = kbContrato;
	}
}
