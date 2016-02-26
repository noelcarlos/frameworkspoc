package org.esmartpoint.jsfkarma.web;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class FlowVariablesControllerPostProcessor extends AutowiredAnnotationBeanPostProcessor {
 
     public FlowVariablesControllerPostProcessor() {
         super();
         System.out.println("This is a InstantiationAwareBeanPostProcessorAdapter implementation class constructor！！");
     }
 
     // Before, instantiate the Bean call interface method
     @Override
     public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
         System.out.println("InstantiationAwareBeanPostProcessor calls the postProcessBeforeInstantiation method");
         return null;
     }
 
     // After the call, instantiate the Bean interface method
     @Override
     public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
         System.out.println("InstantiationAwareBeanPostProcessor calls the postProcessAfterInitialization method");
         return bean;
     }
 
     // Interface methods, setting a property
     @Override
     public PropertyValues postProcessPropertyValues(PropertyValues pvs,
             PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
         System.out.println("InstantiationAwareBeanPostProcessor calls the postProcessPropertyValues method");
         return pvs;
     }
 }