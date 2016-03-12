package es.generali.primefacespoc.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("serial")
public class LookupService implements Serializable {
	@Autowired transient NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getTiposUsosViviendas() {
		return jdbcTemplate.queryForList("SELECT * FROM lk_tipos_usos_viviendas", new HashMap<String, Object>());
	}
	
	public List<Map<String, Object>> getProvincias() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_provincias", new HashMap<String, Object>());
	}

	public List<Map<String, Object>> getLocalizacionesViviendas() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_localizaciones_viviendas", new HashMap<String, Object>());
	}
	
	public List<Map<String, Object>> getTiposDeConstrucciones() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_tipos_construcciones", new HashMap<String, Object>());
	}
	
	public List<Map<String, Object>> getCalidadesDeLasConstrucciones() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_calidades_construcciones", new HashMap<String, Object>());
	}
	
	public List<Map<String, Object>> getTipologiasDeLasViviendas() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_tipologias_viviendas", new HashMap<String, Object>());
	}
	
	public List<Map<String, Object>> getOpcionesDePersonalizacionParaSeguroDeVivienda() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_opciones_personalizacion_seguro_vivienda", new HashMap<String, Object>());
	}
	
	public List<Map<String, Object>> getPeriodosDeContrataciones() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_periodos_contrataciones", new HashMap<String, Object>());
	}
	
	public List<Map<String, Object>> getSexos() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_sexos", new HashMap<String, Object>());
	}
	
	public List<Map<String, Object>> getTiposDeDocumentosDeIndentidad() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_tipos_documentos_identidad", new HashMap<String, Object>());
	}
	
	public List<Map<String, Object>> getTiposDeVias() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_tipos_vias", new HashMap<String, Object>());
	}
	
}
