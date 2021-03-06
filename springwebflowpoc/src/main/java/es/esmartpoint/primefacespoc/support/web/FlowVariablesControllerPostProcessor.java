package es.esmartpoint.primefacespoc.support.web;

import java.beans.PropertyDescriptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.webflow.execution.RequestContextHolder;

import es.esmartpoint.primefacespoc.controllers.BaseWebFlowController;

public class FlowVariablesControllerPostProcessor extends AutowiredAnnotationBeanPostProcessor {
 
	 static FlowVariablesControllerPostProcessor instance;
	 
     FlowVariablesControllerPostProcessor() {
         super();
     }
     
     static public FlowVariablesControllerPostProcessor getInstance() {
    	 if (instance == null) {
    		 instance = new FlowVariablesControllerPostProcessor();
    	 }
    	 return instance; 
     }
 
     // Before, instantiate the Bean call interface method
     @Override
     @SuppressWarnings("rawtypes")
     public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
         return null;
     }
 
     // After the call, instantiate the Bean interface method
     @Override
     public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
         return bean;
     }
 
     // Interface methods, setting a property
     @Override
     public PropertyValues postProcessPropertyValues(PropertyValues pvs,
             PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
         if (bean instanceof BaseWebFlowController) {
        	 BaseWebFlowController bfc = (BaseWebFlowController)bean;
        	 bfc.requestContext = RequestContextHolder.getRequestContext();
        	 bfc.flowScope = bfc.requestContext.getFlowScope();
        	 bfc.session = ((HttpServletRequest)bfc.requestContext.getExternalContext().getNativeRequest()).getSession();
        	 bfc.appContext = bfc.requestContext.getActiveFlow().getApplicationContext();
         }
         
         return pvs;
     }
 }