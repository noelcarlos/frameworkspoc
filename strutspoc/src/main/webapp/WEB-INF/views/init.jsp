<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 
<div class="row">
	<div class="col-md-6">
		<h2>CONFIGURACIÓN INICIAL</h2>
	</div>
</div>

<form name="setupForm" action="${pageContext.request.contextPath}/init.do?method=onSetup" method="post">
	<input id="_setupEvent" type="hidden" name="_setupEvent" />
	<div class="row">
		<div class="col-md-6">
			<h3>Datos a inicializar</h3>
		</div>
		<div class="col-md-6">
			<h3>Pasos disponibles en PrimefacesPOC</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<div class="col-md-6">
					<c:forEach var="result" items="${sessionScope.get('flow').selectNodes('//flow/step')}" varStatus="iter">
						<c:if test='${result.valueOf("@name") <= 5}'>
							<div class="checkbox checkbox-primary">
								<input type="checkbox" id='${result.valueOf("@view").concat('Inicializar')}' value="on"
									class="chkComponent" name='${result.valueOf("@view").concat('Inicializar')}'
									${sessionScope.config[result.valueOf("@view").concat('Inicializar')] ? "checked='checked'" : ""} >
								</input>
								<label for='${result.valueOf("@view").concat('Inicializar')}'>${result.valueOf("@name")} - ${result.valueOf("@menu")}</label>
								<input type="hidden" value="false" name='${result.valueOf("@view").concat('Inicializar')}' />
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="col-md-6">
					<c:forEach var="result" items="${sessionScope.get('flow').selectNodes('//flow/step')}" varStatus="iter">
						<c:if test='${result.valueOf("@name") > 5}'>
							<div class="checkbox checkbox-primary">
								<input type="checkbox" id='${result.valueOf("@view").concat('Inicializar')}' value="on"
									class="chkComponent" name='${result.valueOf("@view").concat('Inicializar')}'
									${sessionScope.config[result.valueOf("@view").concat('Inicializar')] ? "checked='checked'" : ""} >
								</input>
								<label for='${result.valueOf("@view").concat('Inicializar')}'>${result.valueOf("@name")} - ${result.valueOf("@menu")}</label>
								<input type="hidden" value="false" name='${result.valueOf("@view").concat('Inicializar')}' />
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-6">
					<div class="checkbox checkbox-primary">
						<input type="checkbox" id="all" name="all" onClick="selectAllSetupChecks();"></input>
						<label for="all">TODO</label>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<div class="col-md-6">
					<c:forEach var="result" items="${sessionScope.get('flow').selectNodes('//flow/step')}" varStatus="iter">
						<c:if test='${result.valueOf("@name") <= 5}'>
							<div class="checkbox checkbox-primary">
								<input type="checkbox" id='${result.valueOf("@view").concat('Externo')}' value="on"
									class="chkComponentExterno" name='${result.valueOf("@view").concat('Externo')}'
									${sessionScope.config[result.valueOf("@view").concat('Externo')] ? "checked='checked'" : ""} >
								</input>
								<label for='${result.valueOf("@view").concat('Externo')}'>${result.valueOf("@name")} - ${result.valueOf("@menu")}</label>
								<input type="hidden" value="false" name='${result.valueOf("@view").concat('Externo')}' />
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="col-md-6">
					<c:forEach var="result" items="${sessionScope.get('flow').selectNodes('//flow/step')}" varStatus="iter">
						<c:if test='${result.valueOf("@name") > 5}'>
							<div class="checkbox checkbox-primary">
								<input type="checkbox" id='${result.valueOf("@view").concat('Externo')}' value="on"
									class="chkComponentExterno" name='${result.valueOf("@view").concat('Externo')}'
									${sessionScope.config[result.valueOf("@view").concat('Externo')] ? "checked='checked'" : ""} >
								</input>
								<label for='${result.valueOf("@view").concat('Externo')}'>${result.valueOf("@name")} - ${result.valueOf("@menu")}</label>
								<input type="hidden" value="false" name='${result.valueOf("@view").concat('Externo')}' />
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-6">
					<div class="checkbox checkbox-primary">
						<input type="checkbox" id="allExterno" name="allExterno" onClick="selectAllSetupExternoChecks();"></input>
						<label for="allExterno">TODO</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label for="kbContrato">Longitud del contrato en KB:</label>
				<input type="text" id="kbContrato" name="kbContrato" style="color:#000;" 
					value="${sessionScope.config.kbContrato}"></input>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">	
				<div class="checkbox checkbox-primary">
					<input type="checkbox" id='useDistributedCache' value="on" name='useDistributedCache'
						${sessionScope.config.useDistributedCache ? "checked='checked'" : ""} >
					</input>
					<label for='useDistributedCache'>Usar cache distribuido</label>
					<input type="hidden" value="false" name='useDistributedCache' />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<div class="col-md-6">
				<button type="button" class="btn btn-default" onClick="setupEvent('pg-${ result.valueOf('@name')}');">
					Aplicar
				</button>
			</div>
		</div>
	</div>
	<div class="separator-sm"></div>
</form>