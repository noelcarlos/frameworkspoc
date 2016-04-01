package com.allianz.drdc24.services;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allianz.drdc24.models.App;

@Service
@SuppressWarnings("serial")
@Transactional(readOnly = true)
public class AppService implements Serializable {
	@PersistenceContext(unitName="mainPersistenceUnit") protected EntityManager entityManager;
	
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
	
	@SuppressWarnings("unchecked")
	public App loadFull(Serializable id) {
		Criteria c = getSession().createCriteria(App.class);
		c.add(Restrictions.eq("id", id));

		List<App> res = c.list();
		if (res.size() != 1)
			return null;
		else {
			App element = res.get(0);
			return element;
		}
	}
	
	public App createNew() {
		App formModel = new App();
		return formModel;
	}
	
	@Transactional(readOnly = false)
	public App save(App app) {
		getSession().save(app);
		return app;
	}
	
	@Transactional(readOnly = false)
	public App merge(App app) {
		return (App)getSession().merge(app);
	}
	
	@Transactional(readOnly = false)
	public App saveOrMerge(App app) {
		if (app.getId() == null) {
			getSession().save(app);
			return app;
		} else {
			return (App)getSession().merge(app);
		}
	}

}
