package es.generali.segurohogar.models;

import java.io.Serializable;

public class ConfiguracionBean implements Serializable {
	private static final long serialVersionUID = 6707938793386834406L;
	
	Boolean queQuieresProtegerInicializar;
	Boolean localizacionInicializar;
	Boolean sobreLaConstruccionInicializar;
	Boolean caracteristicasDeLaViviendaInicializar;
	Boolean medidasDeSeguridadDeTuViviendaInicializar;
	Boolean personalizarPaqueteInicializar;
	Boolean datosDelTitularInicializar;
	Boolean datosDeLaViviendaAAsegurarInicializar;
	Boolean datosDePagoInicializar;
	Boolean resumenInicializar;

	Boolean queQuieresProtegerExterno;
	Boolean localizacionExterno;
	Boolean sobreLaConstruccionExterno;
	Boolean caracteristicasDeLaViviendaExterno;
	Boolean medidasDeSeguridadDeTuViviendaExterno;
	Boolean personalizarPaqueteExterno;
	Boolean datosDelTitularExterno;
	Boolean datosDeLaViviendaAAsegurarExterno;
	Boolean datosDePagoExterno;
	Boolean resumenExterno;
	
	Integer kbContrato;
	Boolean useDistributedCache;
	
	public Boolean getQueQuieresProtegerInicializar() {
		return queQuieresProtegerInicializar;
	}

	public void setQueQuieresProtegerInicializar(Boolean queQuieresProtegerInicializar) {
		this.queQuieresProtegerInicializar = queQuieresProtegerInicializar;
	}

	public Boolean getLocalizacionInicializar() {
		return localizacionInicializar;
	}

	public void setLocalizacionInicializar(Boolean localizacionInicializar) {
		this.localizacionInicializar = localizacionInicializar;
	}

	public Boolean getSobreLaConstruccionInicializar() {
		return sobreLaConstruccionInicializar;
	}

	public void setSobreLaConstruccionInicializar(Boolean sobreLaConstruccionInicializar) {
		this.sobreLaConstruccionInicializar = sobreLaConstruccionInicializar;
	}

	public Boolean getCaracteristicasDeLaViviendaInicializar() {
		return caracteristicasDeLaViviendaInicializar;
	}

	public void setCaracteristicasDeLaViviendaInicializar(Boolean caracteristicasDeLaViviendaInicializar) {
		this.caracteristicasDeLaViviendaInicializar = caracteristicasDeLaViviendaInicializar;
	}

	public Boolean getMedidasDeSeguridadDeTuViviendaInicializar() {
		return medidasDeSeguridadDeTuViviendaInicializar;
	}

	public void setMedidasDeSeguridadDeTuViviendaInicializar(Boolean medidasDeSeguridadDeTuViviendaInicializar) {
		this.medidasDeSeguridadDeTuViviendaInicializar = medidasDeSeguridadDeTuViviendaInicializar;
	}

	public Boolean getPersonalizarPaqueteInicializar() {
		return personalizarPaqueteInicializar;
	}

	public void setPersonalizarPaqueteInicializar(Boolean personalizarPaqueteInicializar) {
		this.personalizarPaqueteInicializar = personalizarPaqueteInicializar;
	}

	public Boolean getDatosDelTitularInicializar() {
		return datosDelTitularInicializar;
	}

	public void setDatosDelTitularInicializar(Boolean datosDelTitularInicializar) {
		this.datosDelTitularInicializar = datosDelTitularInicializar;
	}

	public Boolean getDatosDeLaViviendaAAsegurarInicializar() {
		return datosDeLaViviendaAAsegurarInicializar;
	}

	public void setDatosDeLaViviendaAAsegurarInicializar(Boolean datosDeLaViviendaAAsegurarInicializar) {
		this.datosDeLaViviendaAAsegurarInicializar = datosDeLaViviendaAAsegurarInicializar;
	}

	public Boolean getDatosDePagoInicializar() {
		return datosDePagoInicializar;
	}

	public void setDatosDePagoInicializar(Boolean datosDePagoInicializar) {
		this.datosDePagoInicializar = datosDePagoInicializar;
	}

	public Boolean getResumenInicializar() {
		return resumenInicializar;
	}

	public void setResumenInicializar(Boolean resumenInicializar) {
		this.resumenInicializar = resumenInicializar;
	}

	public Boolean getQueQuieresProtegerExterno() {
		return queQuieresProtegerExterno;
	}

	public void setQueQuieresProtegerExterno(Boolean queQuieresProtegerExterno) {
		this.queQuieresProtegerExterno = queQuieresProtegerExterno;
	}

	public Boolean getLocalizacionExterno() {
		return localizacionExterno;
	}

	public void setLocalizacionExterno(Boolean localizacionExterno) {
		this.localizacionExterno = localizacionExterno;
	}

	public Boolean getSobreLaConstruccionExterno() {
		return sobreLaConstruccionExterno;
	}

	public void setSobreLaConstruccionExterno(Boolean sobreLaConstruccionExterno) {
		this.sobreLaConstruccionExterno = sobreLaConstruccionExterno;
	}

	public Boolean getCaracteristicasDeLaViviendaExterno() {
		return caracteristicasDeLaViviendaExterno;
	}

	public void setCaracteristicasDeLaViviendaExterno(Boolean caracteristicasDeLaViviendaExterno) {
		this.caracteristicasDeLaViviendaExterno = caracteristicasDeLaViviendaExterno;
	}

	public Boolean getMedidasDeSeguridadDeTuViviendaExterno() {
		return medidasDeSeguridadDeTuViviendaExterno;
	}

	public void setMedidasDeSeguridadDeTuViviendaExterno(Boolean medidasDeSeguridadDeTuViviendaExterno) {
		this.medidasDeSeguridadDeTuViviendaExterno = medidasDeSeguridadDeTuViviendaExterno;
	}

	public Boolean getPersonalizarPaqueteExterno() {
		return personalizarPaqueteExterno;
	}

	public void setPersonalizarPaqueteExterno(Boolean personalizarPaqueteExterno) {
		this.personalizarPaqueteExterno = personalizarPaqueteExterno;
	}

	public Boolean getDatosDelTitularExterno() {
		return datosDelTitularExterno;
	}

	public void setDatosDelTitularExterno(Boolean datosDelTitularExterno) {
		this.datosDelTitularExterno = datosDelTitularExterno;
	}

	public Boolean getDatosDeLaViviendaAAsegurarExterno() {
		return datosDeLaViviendaAAsegurarExterno;
	}

	public void setDatosDeLaViviendaAAsegurarExterno(Boolean datosDeLaViviendaAAsegurarExterno) {
		this.datosDeLaViviendaAAsegurarExterno = datosDeLaViviendaAAsegurarExterno;
	}

	public Boolean getDatosDePagoExterno() {
		return datosDePagoExterno;
	}

	public void setDatosDePagoExterno(Boolean datosDePagoExterno) {
		this.datosDePagoExterno = datosDePagoExterno;
	}

	public Boolean getResumenExterno() {
		return resumenExterno;
	}

	public void setResumenExterno(Boolean resumenExterno) {
		this.resumenExterno = resumenExterno;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getKbContrato() {
		return kbContrato;
	}
	public void setKbContrato(Integer kbContrato) {
		this.kbContrato = kbContrato;
	}

	public Boolean getUseDistributedCache() {
		return useDistributedCache;
	}

	public void setUseDistributedCache(Boolean useDistributedCache) {
		this.useDistributedCache = useDistributedCache;
	}
	
	public ConfiguracionBean() {
		queQuieresProtegerInicializar = false;
		localizacionInicializar = false;
		sobreLaConstruccionInicializar = false;
		caracteristicasDeLaViviendaInicializar = false;
		medidasDeSeguridadDeTuViviendaInicializar = false;
		personalizarPaqueteInicializar = false;
		datosDelTitularInicializar = false;
		datosDeLaViviendaAAsegurarInicializar = false;
		datosDePagoInicializar = false;
		resumenInicializar = false;
		
		queQuieresProtegerExterno = false;
		localizacionExterno = false;
		sobreLaConstruccionExterno = false;
		caracteristicasDeLaViviendaExterno = false;
		medidasDeSeguridadDeTuViviendaExterno = false;
		personalizarPaqueteExterno = false;
		datosDelTitularExterno = false;
		datosDeLaViviendaAAsegurarExterno = false;
		datosDePagoExterno = false;
		resumenExterno = false;
		
		kbContrato = 100;
		useDistributedCache = false;
	}
}
