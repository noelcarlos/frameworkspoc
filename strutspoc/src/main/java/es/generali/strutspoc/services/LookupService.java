package es.generali.strutspoc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.context.WebApplicationContext;

public class LookupService {
	WebApplicationContext context;
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public LookupService(WebApplicationContext context) {
		this.context = context;
		jdbcTemplate = context.getBean(NamedParameterJdbcTemplate.class);
	}
	
	public List<Map<String, Object>> getTiposUsosViviendas() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_tipos_usos_viviendas", new HashMap<String, Object>());
	}
	
}
