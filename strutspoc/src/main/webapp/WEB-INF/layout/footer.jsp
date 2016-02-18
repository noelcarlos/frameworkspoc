<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<div class="col-md-6">
		<h2>CONFIGURACIÓN</h2>
	</div>
</div>

<form name="setupForm" action="${pageContext.request.contextPath}/seguroHogar.do?method=onSetup" method="post">
	<input id="_setupEvent" type="hidden" name="_setupEvent" />
	<div class="row">
		<div class="col-md-6">
			<h3>Información inicial</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<div class="col-md-6">
					<c:forEach var="result" items="${sessionScope.get('flow').selectNodes('//flow/step')}" varStatus="iter">
						<c:if test='${result.valueOf("@name") <= 5}'>
							<div class="checkbox checkbox-primary">
								<input type="checkbox" id='${result.valueOf("@view")}' value="on"
									class="chkComponent" name='${result.valueOf("@view")}'
									${sessionScope.config[result.valueOf("@view")] ? "checked='checked'" : ""} >
								</input>
								<label for='${result.valueOf("@view")}'>${result.valueOf("@name")} - ${result.valueOf("@menu")}</label>
								<input type="hidden" value="false" name='${result.valueOf("@view")}' />
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="col-md-6">
					<c:forEach var="result" items="${sessionScope.get('flow').selectNodes('//flow/step')}" varStatus="iter">
						<c:if test='${result.valueOf("@name") > 5}'>
							<div class="checkbox checkbox-primary">
								<input type="checkbox" id='${result.valueOf("@view")}' value="on"
									class="chkComponent" name='${result.valueOf("@view")}'
									${sessionScope.config[result.valueOf("@view")] ? "checked='checked'" : ""} >
								</input>
								<label for='${result.valueOf("@view")}'>${result.valueOf("@name")} - ${result.valueOf("@menu")}</label>
								<input type="hidden" value="false" name='${result.valueOf("@view")}' />
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
				<label for="kbContrato">Longitud del contrato en KB:</label>
				<input type="text" id="kbContrato" name="kbContrato" style="color:#000;" 
					value="${sessionScope.config.kbContrato}"></input>
			</div>
				
			<div class="form-group">	
				<div class="checkbox checkbox-primary">
					<input type="checkbox" id='useDistributedCache' value="on"
						class="chkComponent" name='useDistributedCache'
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
	