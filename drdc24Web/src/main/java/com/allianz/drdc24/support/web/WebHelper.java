/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.allianz.drdc24.support.web;

import java.util.HashMap;
import java.util.regex.Pattern;

import javax.el.ELException;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.webflow.core.collection.MutableAttributeMap;

public class WebHelper {
	
	@SuppressWarnings("hiding")
	static public <Object> Object eval(String expression, Class<? extends Object> expectedType) throws ELException {
		FacesContext context = FacesContext.getCurrentInstance();
	    return context.getApplication().evaluateExpressionGet(context, expression, expectedType);
	}
	
	static public Object getAttributeOfCurrentComponent(String attrName) {
		FacesContext context = FacesContext.getCurrentInstance();
		return UIComponent.getCurrentComponent(context).getAttributes().get(attrName);
	}
	
	static private boolean testMatching(String patternArray[], int posPattern, String uriArray[], int posUri) {
		if (posPattern == patternArray.length && posUri == uriArray.length)
			return true;
		if (posUri >= uriArray.length || posPattern >= patternArray.length)
			return false;
		for (; posPattern < patternArray.length; ) {
			if (patternArray[posPattern].startsWith("{*")) {
				if (testMatching(patternArray, posPattern+1, uriArray, posUri) ||
					testMatching(patternArray, posPattern+1, uriArray, posUri+1)) {
					return true;
				}
				return false;
			}
			else if (patternArray[posPattern].startsWith("{")) {
				posPattern++;
				posUri++;
			}
			else if (patternArray[posPattern].equals(uriArray[posUri])) {
				posPattern++;
				posUri++;
			} else {
				return false;
			}
		}
		if (posPattern == patternArray.length && posUri == uriArray.length)
			return true;
		else
			return false;
	}
	
	private static Pattern integerPattern = Pattern.compile("-?\\d+(\\.\\d*)?");

	public static boolean isInteger(String string) {
	    return integerPattern.matcher(string).matches();
	}
	
	/*
	 * Pattern  /{id}/{date}/{otherId}?ignoreparams
	 * 	
	 * Read     /145/20100102/752
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	static public boolean extractParamsFromURL(MutableAttributeMap scopeMap, String pattern) {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		String realURI = request.getRequestURI();
		String currentURI = realURI.substring(request.getContextPath().length());
		HashMap<String, Object> attrMap = new HashMap<String, Object>(); 
		
		String patternArray[] = pattern.split("[/-]");
		String uriArray[] = currentURI.split("[/-]");
		
		int posUri = 0;
		
		for (int posPattern = 0; posPattern < patternArray.length; posPattern++) {
			if (patternArray[posPattern].startsWith("{*")) {
				String key = patternArray[posPattern].substring(2, patternArray[posPattern].indexOf('}'));
				if (testMatching(patternArray, posPattern+1, uriArray, posUri+1)) {
					if (!isInteger(uriArray[posUri]))
						attrMap.put(key, uriArray[posUri]);
					else
						attrMap.put(key, Integer.parseInt(uriArray[posUri]));
					posUri++;
				} else if (!testMatching(patternArray, posPattern+1, uriArray, posUri)) {
					return false;
				}
			} else if (patternArray[posPattern].startsWith("{")) {
				String key = patternArray[posPattern].substring(1, patternArray[posPattern].indexOf('}'));
				if (!isInteger(uriArray[posUri]))
					attrMap.put(key, uriArray[posUri]);
				else
					attrMap.put(key, Integer.parseInt(uriArray[posUri]));
				posUri++;
			} else {
				if (posUri >= uriArray.length || !uriArray[posUri].equals(patternArray[posPattern]))
					return false;
				posUri++;
			}
		}
		
		for (String key : attrMap.keySet()) {
			scopeMap.put(key, attrMap.get(key));
		} 
		return true;
	}	
}
