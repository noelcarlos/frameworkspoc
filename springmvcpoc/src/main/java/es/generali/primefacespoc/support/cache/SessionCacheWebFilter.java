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
package es.generali.primefacespoc.support.cache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/* This filter allows the mapping of SEA control flow variable 'action' to 
 * SWF control variable '_eventId' on request scope. With the help of this filter
 * we don't need to modify the JSP files that send this request variable using JavaScript,
 * Scriptlets, Tags, JQuery o Ajax. 
 */
public class SessionCacheWebFilter implements Filter {

	static boolean inmediateSynchronization = false;

	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsrHttpServletRequest = (HttpServletRequest) req;
		
		// Continue filter chain with filtered request
		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		
		try {
			chain.doFilter(hsrHttpServletRequest, httpResponse);
			
			//System.out.println("PRIMEFACES Session size:" + getSessionSize(hsrHttpServletRequest.getSession()));
			
		} finally {
		}
	}
	
	protected long getSessionSize(HttpSession hsSession) {
		Enumeration<String> aNames = hsSession.getAttributeNames();
		long size = 0;
		while (aNames.hasMoreElements()) {
			String key = aNames.nextElement();
			Object obj = hsSession.getAttribute(key);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(baos);
				oos.writeObject(obj);
				oos.flush();
				oos.close();
				size += baos.size();
				System.out.println("\t" + key + "=" + baos.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
	
	public void destroy() {
	}
}