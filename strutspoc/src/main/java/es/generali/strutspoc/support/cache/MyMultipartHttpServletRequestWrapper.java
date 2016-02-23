package es.generali.strutspoc.support.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class MyMultipartHttpServletRequestWrapper extends HttpServletRequestWrapper implements MultipartHttpServletRequest {
	
	/**************/
	/* ATTRIBUTES */
	/**************/

	private MultipartHttpServletRequest delegate;

	/***************/
	/* CONSTRUCTOR */
	/***************/
	
	public MyMultipartHttpServletRequestWrapper (MultipartHttpServletRequest _mhsrMultipartHttpServletRequest) {
		super(_mhsrMultipartHttpServletRequest);
		this.delegate = _mhsrMultipartHttpServletRequest;
	}
	
	/*********************/
	/* DELEGATE METHOD'S */
	/*********************/
	
	@Override
	public Iterator<String> getFileNames() {
		return delegate.getFileNames();
	}

	@Override
	public MultipartFile getFile(String name) {
		return delegate.getFile(name);
	}

	@Override
	public List<MultipartFile> getFiles(String name) {
		return delegate.getFiles(name);
	}

	@Override
	public HttpMethod getRequestMethod() {
		return delegate.getRequestMethod();
	}

	@Override
	public HttpHeaders getRequestHeaders() {
		return delegate.getRequestHeaders();
	}

	@Override
	public Map<String, MultipartFile> getFileMap() {
		return delegate.getFileMap();
	}

	@Override
	public HttpHeaders getMultipartHeaders(String paramOrFileName) {
		return delegate.getMultipartHeaders(paramOrFileName);
	}

	@Override
	public MultiValueMap<String, MultipartFile> getMultiFileMap() {
		return delegate.getMultiFileMap();
	}

	@Override
	public String getMultipartContentType(String paramOrFileName) {
		return delegate.getMultipartContentType(paramOrFileName);
	}

	@Override
	public Object getAttribute(String name) {
		return delegate.getAttribute(name);
	}

	@Override
	public String getAuthType() {
		return delegate.getAuthType();
	}

	@Override
	public Cookie[] getCookies() {
		return delegate.getCookies();
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return delegate.getAttributeNames();
	}

	@Override
	public long getDateHeader(String name) {
		return delegate.getDateHeader(name);
	}

	@Override
	public String getCharacterEncoding() {
		return delegate.getCharacterEncoding();
	}

	@Override
	public void setCharacterEncoding(String env)
			throws UnsupportedEncodingException {
		delegate.setCharacterEncoding(env);
	}

	@Override
	public String getHeader(String name) {
		return delegate.getHeader(name);
	}

	@Override
	public int getContentLength() {
		return delegate.getContentLength();
	}

	@Override
	public String getContentType() {
		return delegate.getContentType();
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		return delegate.getHeaders(name);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return delegate.getInputStream();
	}

//	public String getParameter(String name) {
//		return delegate.getParameter(name);
//	}

	@Override
	public String getParameter(String paramName) {
		return delegate.getParameter(paramName);
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		return delegate.getHeaderNames();
	}

	@Override
	public int getIntHeader(String name) {
		return delegate.getIntHeader(name);
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return delegate.getParameterNames();
	}

//	public String[] getParameterValues(String name) {
//		return delegate.getParameterValues(name);
//	}

	@Override
	public String[] getParameterValues(String paramName) {
		return delegate.getParameterValues(paramName);
	}

	@Override
	public String getMethod() {
		return delegate.getMethod();
	}

	@Override
	public String getPathInfo() {
		return delegate.getPathInfo();
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return delegate.getParameterMap();
	}

	@Override
	public String getProtocol() {
		return delegate.getProtocol();
	}

	@Override
	public String getPathTranslated() {
		return delegate.getPathTranslated();
	}

	@Override
	public String getScheme() {
		return delegate.getScheme();
	}

	@Override
	public String getServerName() {
		return delegate.getServerName();
	}

	@Override
	public String getContextPath() {
		return delegate.getContextPath();
	}

	@Override
	public int getServerPort() {
		return delegate.getServerPort();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return delegate.getReader();
	}

	@Override
	public String getQueryString() {
		return delegate.getQueryString();
	}

	@Override
	public String getRemoteAddr() {
		return delegate.getRemoteAddr();
	}

	@Override
	public String getRemoteUser() {
		return delegate.getRemoteUser();
	}

	@Override
	public String getRemoteHost() {
		return delegate.getRemoteHost();
	}

	@Override
	public boolean isUserInRole(String role) {
		return delegate.isUserInRole(role);
	}

	@Override
	public void setAttribute(String name, Object o) {
		delegate.setAttribute(name, o);
	}

	@Override
	public Principal getUserPrincipal() {
		return delegate.getUserPrincipal();
	}

	@Override
	public String getRequestedSessionId() {
		return delegate.getRequestedSessionId();
	}

	@Override
	public void removeAttribute(String name) {
		delegate.removeAttribute(name);
	}

	@Override
	public String getRequestURI() {
		return delegate.getRequestURI();
	}

	@Override
	public Locale getLocale() {
		return delegate.getLocale();
	}

	@Override
	public Enumeration<Locale> getLocales() {
		return delegate.getLocales();
	}

	@Override
	public StringBuffer getRequestURL() {
		return delegate.getRequestURL();
	}

	@Override
	public boolean isSecure() {
		return delegate.isSecure();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		return delegate.getRequestDispatcher(path);
	}

	@Override
	public String getServletPath() {
		return delegate.getServletPath();
	}

	@Override
	public HttpSession getSession(boolean create) {
		HttpSession source = delegate.getSession(create);
		return new MyHttpServletSessionWrapper(source); 
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getRealPath(String path) {
		return delegate.getRealPath(path);
	}

	@Override
	public int getRemotePort() {
		return delegate.getRemotePort();
	}

	@Override
	public String getLocalName() {
		return delegate.getLocalName();
	}

	@Override
	public String getLocalAddr() {
		return delegate.getLocalAddr();
	}

	@Override
	public HttpSession getSession() {
		HttpSession source = delegate.getSession();
		return new MyHttpServletSessionWrapper(source); 
	}

	@Override
	public int getLocalPort() {
		return delegate.getLocalPort();
	}

	@Override
	public ServletContext getServletContext() {
		return delegate.getServletContext();
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		return delegate.isRequestedSessionIdValid();
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		return delegate.startAsync();
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		return delegate.isRequestedSessionIdFromCookie();
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		return delegate.isRequestedSessionIdFromURL();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isRequestedSessionIdFromUrl() {
		return delegate.isRequestedSessionIdFromUrl();
	}

	@Override
	public boolean authenticate(HttpServletResponse response)
			throws IOException, ServletException {
		return delegate.authenticate(response);
	}

	@Override
	public AsyncContext startAsync(ServletRequest servletRequest,
			ServletResponse servletResponse) throws IllegalStateException {
		return delegate.startAsync(servletRequest, servletResponse);
	}

	@Override
	public void login(String username, String password) throws ServletException {
		delegate.login(username, password);
	}

	@Override
	public void logout() throws ServletException {
		delegate.logout();
	}

	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		return delegate.getParts();
	}

	@Override
	public boolean isAsyncStarted() {
		return delegate.isAsyncStarted();
	}

	@Override
	public Part getPart(String name) throws IOException, ServletException {
		return delegate.getPart(name);
	}

	@Override
	public boolean isAsyncSupported() {
		return delegate.isAsyncSupported();
	}

	@Override
	public AsyncContext getAsyncContext() {
		return delegate.getAsyncContext();
	}

	@Override
	public DispatcherType getDispatcherType() {
		return delegate.getDispatcherType();
	}
}