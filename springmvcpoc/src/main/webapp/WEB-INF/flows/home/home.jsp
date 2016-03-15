<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">

		<div class="row">
			<div class="col-md-6">
				<h2>CONFIGURACIÓN INICIAL</h2>
			</div>
		</div>
		
		<form:form id="setupForm" method="POST" commandName="config">
			<form:errors path="*" cssClass="errorblock" element="div" />
			
			<input type="hidden" name="_eventId" value="aplicar"/>			
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
							<c:forEach var="result" items="${mainController.flow.selectNodes('//flow/step')}" varStatus="iter">
								<c:if test='${result.valueOf("@name") <= 5}'>
									<div class="checkbox checkbox-primary">
										<input type="checkbox" id='${result.valueOf("@view").concat('Inicializar')}' value="true"
											class="chkComponentInicializar" name='${result.valueOf("@view").concat('Inicializar')}'
											${config[result.valueOf('@view').concat('Inicializar')] ? "checked='checked'" : ""} >
										</input>
										<label for='${result.valueOf("@view").concat('Inicializar')}'>${result.valueOf("@name")} - ${result.valueOf("@menu")}</label>
										<input type="hidden" value="false" name='${result.valueOf('@view').concat('Inicializar')}' />
									</div>
								</c:if>
							</c:forEach>
						</div>
						<div class="col-md-6">
							<c:forEach var="result" items="${mainController.flow.selectNodes('//flow/step')}" varStatus="iter">
								<c:if test='${result.valueOf("@name") > 5}'>
									<div class="checkbox checkbox-primary">
										<input type="checkbox" id='${result.valueOf("@view").concat('Inicializar')}' value="on"
											class="chkComponentInicializar" name='${result.valueOf("@view").concat('Inicializar')}'
											${config[result.valueOf('@view').concat('Inicializar')] ? "checked='checked'" : ""} >
										</input>
										<label for='${result.valueOf("@view").concat('Inicializar')}'>${result.valueOf("@name")} - ${result.valueOf("@menu")}</label>
										<input type="hidden" value="false" name='${result.valueOf('@view').concat('Inicializar')}' />
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<div class="checkbox checkbox-primary">
								<input type="checkbox" id="allInicializar" name="allInicializar" onClick="selectAllSetupChecksInicializar();" />
								<label for="allInicializar">TODO</label>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<div class="col-md-6">
							<c:forEach var="result" items="${mainController.flow.selectNodes('//flow/step')}" varStatus="iter">
								<c:if test='${result.valueOf("@name") <= 5}'>
									<div class="checkbox checkbox-primary">
										<input type="checkbox" id='${result.valueOf("@view").concat('Externo')}' value="on"
											class="chkComponentExterno" name='${result.valueOf("@view").concat('Externo')}'
											${config[result.valueOf("@view").concat('Externo')] ? "checked='checked'" : ""} >
										</input>
										<label for='${result.valueOf("@view").concat('Externo')}'>${result.valueOf("@name")} - ${result.valueOf("@menu")}</label>
										<input type="hidden" value="false" name='${result.valueOf('@view').concat('Externo')}' />
									</div>
								</c:if>
							</c:forEach>
						</div>
						<div class="col-md-6">
							<c:forEach var="result" items="${mainController.flow.selectNodes('//flow/step')}" varStatus="iter">
								<c:if test='${result.valueOf("@name") > 5}'>
									<div class="checkbox checkbox-primary">
										<input type="checkbox" id='${result.valueOf("@view").concat('Externo')}' value="on"
											class="chkComponentExterno" name='${result.valueOf("@view").concat('Externo')}'
											${config[result.valueOf("@view").concat('Externo')] ? "checked='checked'" : ""} >
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
								<input type="checkbox" id="allExterno" name="allExterno" onClick="selectAllSetupChecksExterno();" />
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
						<form:button class="btn btn-default" >Aplicar</form:button>
					</div>
				</div>
			</div>
			<div class="separator-sm"></div>
		</</form:form>
		
    </tiles:putAttribute>
</tiles:insertDefinition>