<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="flowMenu.jsp" />

<div class="row">
	<div class="col-md-6">
		<html:form action="/seguroHogar?method=onSubmit">
			<html:errors />
			<input type="hidden" name="flowEvent" />
			<div class="form-group">
				<label class="control-label" for="provinciaId">Provincia:</label>
				<html:select property="provinciaId" styleClass="form-control">
					<html:option value="" ></html:option>
					<html:options collection="provincias" property="id" labelProperty="nombre" />
				</html:select>
			</div>	
			<div class="form-group">
				<label class="control-label" for="localizacionId">¿Donde se encuentra la vivienda?:</label>
				<html:select property="localizacionId" styleClass="form-control">
					<html:option value="" ></html:option>
					<html:options collection="localizacionesViviendas" property="id" labelProperty="nombre" />
				</html:select>
			</div>		
		</html:form>
	</div>	
</div>
 
<jsp:include page="flowButtons.jsp" />
 