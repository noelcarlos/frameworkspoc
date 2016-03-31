package com.allianz.drdc24.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allianz.drdc24.models.App;

@Service
@SuppressWarnings("serial")
public class LookupService implements Serializable {
	@Autowired transient NamedParameterJdbcTemplate jdbcTemplate;
	@PersistenceContext(unitName="mainPersistenceUnit") protected EntityManager entityManager;
	
	//@Cacheable("getTiposUsosViviendas")
	@Cacheable(value="oneDayCache", key="#root.targetClass+#root.methodName")
	public List<Map<String, Object>> getTiposUsosViviendas() {
		System.out.println("Rebuild cache:getTiposUsosViviendas");
		 return jdbcTemplate.queryForList("SELECT * FROM lk_tipos_usos_viviendas", new HashMap<String, Object>());
	}
	
	@Cacheable(value="oneDayCache", key="#root.targetClass+#root.methodName")
	public List<Map<String, Object>> getProvincias() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_provincias", new HashMap<String, Object>());
	}

	@Cacheable(value="oneDayCache", key="#root.targetClass+#root.methodName")
	public List<Map<String, Object>> getLocalizacionesViviendas() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_localizaciones_viviendas", new HashMap<String, Object>());
	}
	
	@Cacheable(value="oneDayCache", key="#root.targetClass+#root.methodName")
	public List<Map<String, Object>> getTiposDeConstrucciones() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_tipos_construcciones", new HashMap<String, Object>());
	}
	
	@Cacheable(value="oneDayCache", key="#root.targetClass+#root.methodName")
	public List<Map<String, Object>> getCalidadesDeLasConstrucciones() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_calidades_construcciones", new HashMap<String, Object>());
	}
	
	@Cacheable(value="oneDayCache", key="#root.targetClass+#root.methodName")
	public List<Map<String, Object>> getTipologiasDeLasViviendas() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_tipologias_viviendas", new HashMap<String, Object>());
	}
	
	@Cacheable(value="oneDayCache", key="#root.targetClass+#root.methodName")
	public List<Map<String, Object>> getOpcionesDePersonalizacionParaSeguroDeVivienda() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_opciones_personalizacion_seguro_vivienda", new HashMap<String, Object>());
	}
	
	@Cacheable(value="oneDayCache", key="#root.targetClass+#root.methodName")
	public List<Map<String, Object>> getPeriodosDeContrataciones() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_periodos_contrataciones", new HashMap<String, Object>());
	}
	
	@Cacheable(value="oneDayCache", key="#root.targetClass+#root.methodName")
	public List<Map<String, Object>> getSexos() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_sexos", new HashMap<String, Object>());
	}
	
	@Cacheable(value="oneDayCache", key="#root.targetClass+#root.methodName")
	public List<Map<String, Object>> getTiposDeDocumentosDeIndentidad() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_tipos_documentos_identidad", new HashMap<String, Object>());
	}
	
	@Cacheable(value="oneDayCache", key="#root.targetClass+#root.methodName")
	public List<Map<String, Object>> getTiposDeVias() {
		 return jdbcTemplate.queryForList("SELECT * FROM lk_tipos_vias", new HashMap<String, Object>());
	}
	
	public List<Map<String, Object>> userList() {
		 return jdbcTemplate.queryForList("SELECT * FROM users", new HashMap<String, Object>());
	}
	
	public List<Map<String, Object>> appList(String searchStr, int first, int size, String sortField, boolean sortAscending) {
		 return jdbcTemplate.queryForList("SELECT * FROM apps", new HashMap<String, Object>());
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	 
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public List<App> appListAll(String searchStr, Integer firstResult, Integer maxResults, String orderBy, Boolean isAscending) {
		Criteria c = getSession().createCriteria(App.class);
		if (searchStr != null && searchStr.trim().length() > 0) {
    		c.add(Restrictions.ilike("name", searchStr, MatchMode.ANYWHERE));
		}
		if (orderBy != null && isAscending != null) {
			if (isAscending) {
				c.addOrder(Order.asc(orderBy));
			} else {
				c.addOrder(Order.desc(orderBy));
			}
		}
		if (firstResult != null) {
			c.setFirstResult(firstResult);
		}
		if (maxResults != null) {
			c.setMaxResults(maxResults);
		}
		return (List<App>)c.list();
	}
	
	@Transactional(readOnly = false)
	public Integer appCount(String searchStr) {
		Criteria c = getSession().createCriteria(App.class);
		if (searchStr != null && searchStr.trim().length() > 0) {
    		c.add(Restrictions.ilike("name", searchStr, MatchMode.ANYWHERE));
		}
		c.setProjection(Projections.rowCount());
		Number count = (Number) c.uniqueResult();
		return count.intValue();
	}
}
