<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../commons/flowMenu.jsp" />

<html:form action="/seguroHogar?method=onSubmit">
	<jsp:include page="../commons/flowFormErrors.jsp" />
	
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label" for="capitalAseguradoVivienda">Capital asegurado para la vivienda:</label>
				<html:text property="capitalAseguradoVivienda" styleClass="form-control"></html:text>
			</div>			
			<div class="form-group">
				<label class="control-label" for="capitalAseguradoEnseres">Capital asegurado para enseres:</label>
				<html:text property="capitalAseguradoEnseres" styleClass="form-control"></html:text>
			</div>			
			<div class="form-group">
				<label class="control-label" for="valorResponsabilidadCivil">Valor de responsabilidad civil:</label>
				<html:text property="valorResponsabilidadCivil" styleClass="form-control"></html:text>
			</div>
					
			<div class="form-group">
				<label class="control-label">Opciones</label>
				<div class="checkbox checkbox-primary">
					<html:checkbox styleId="opcionRotura" property="opcionRotura"></html:checkbox>
					<label for="opcionRotura">Rotura</label>
				</div>
				<div class="checkbox checkbox-primary">
					<html:checkbox styleId="opcionRobo" property="opcionRobo"></html:checkbox>
					<label for="opcionRobo">Robo</label>
				</div>
				<div class="checkbox checkbox-primary">
					<html:checkbox styleId="opcionDefensaJuridica" property="opcionDefensaJuridica"></html:checkbox>
					<label for="opcionDefensaJuridica">Defensa juridica</label>
				</div>
				<div class="checkbox checkbox-primary">
					<html:checkbox styleId="opcionInhabilidad" property="opcionInhabilidad"></html:checkbox>
					<label for="opcionInhabilidad">Inhabilidad</label>
				</div>
				<div class="checkbox checkbox-primary">
					<html:checkbox styleId="opcionAsistenciaInformatica" property="opcionAsistenciaInformatica"></html:checkbox>
					<label for="opcionAsistenciaInformatica">Asistencia informática</label>
				</div>
			</div>	
			
			<div class="form-group">
				<label class="control-label" for="capitalAseguradoJoyas">Capital asegurado para joyas:</label>
				<html:text property="capitalAseguradoJoyas" styleClass="form-control"></html:text>
			</div>
			<div class="form-group">
				<label class="control-label" for="capitalAseguradoObjetosDeValor">Capital asegurado para objetos de valor:</label>
				<html:text property="capitalAseguradoObjetosDeValor" styleClass="form-control"></html:text>
			</div>
			<div class="form-group">
				<label class="control-label" for="capitalAseguradoRecomposicionEstetica">Capital asegurado para recomposición estética:</label>
				<html:text property="capitalAseguradoRecomposicionEstetica" styleClass="form-control"></html:text>
			</div>
					
		</div>	
	</div>
	
</html:form>
 
<jsp:include page="../commons/flowButtons.jsp" />
 