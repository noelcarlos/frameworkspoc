<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<div class="col-md-12">
		<div class="separator-sm"></div>
		<div class="btn-group" role="group" aria-label="...">
			<button type="button" class="btn btn-default" onclick="sendEvent('goFirst');" 
				${(sessionScope.get('currentStep') != "1") ? '' : 'disabled="disabled"'}>
				Inicio
			</button>
			<button type="button" class="btn btn-default" onclick="sendEvent('goPrevious');"
				${(sessionScope.get('currentStep') != "1") ? '' : 'disabled="disabled"'}>
				Anterior
			</button>
			<button type="button" class="btn btn-primary" onclick="sendEvent('goNext');"
				${(!sessionScope.get('currentStep').equals(sessionScope.get('lastPageNumber'))) ? '' : 'disabled="disabled"'}>
				Siguiente
			</button>
			<button type="button" class="btn btn-default" onclick="sendEvent('goLast');"
				${(!sessionScope.get('currentStep').equals(sessionScope.get('lastPageNumber'))) ? '' : 'disabled="disabled"'}>
				Última
			</button>
		</div>
		<div class="separator-sm"></div>
	</div>
</div>
