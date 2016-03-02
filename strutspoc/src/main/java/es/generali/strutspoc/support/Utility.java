package es.generali.strutspoc.support;

import java.lang.reflect.Method;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;

public class Utility {
	
	public static Method findFirst(Class<?> cl, String name) {
		Method[] methods = cl.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(name))
				return method;
		} 
		return null;
	}
	
	public static Locale getUserLocale(HttpServletRequest request, String locale) {

        Locale userLocale = null;
        HttpSession session = request.getSession(false);

        if (locale == null) {
            locale = Globals.LOCALE_KEY;
        }

        // Only check session if sessions are enabled
        if (session != null) {
            userLocale = (Locale) session.getAttribute(locale);
        }

        if (userLocale == null) {
            // Returns Locale based on Accept-Language header or the server default
            userLocale = request.getLocale();
        }

        return userLocale;

    }
}
