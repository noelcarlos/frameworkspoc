package com.allianz.drdc24.controllers;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

@SuppressWarnings("serial")
public abstract class BaseWebFlowController implements Serializable {
	public transient RequestContext requestContext;
	public transient HttpSession session;
	public transient MutableAttributeMap<Object> flowScope;
	public transient ApplicationContext appContext;
}
