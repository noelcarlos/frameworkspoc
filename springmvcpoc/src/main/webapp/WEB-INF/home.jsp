<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

		<div class="row">
			<div class="col-md-6">
				<h2>CONFIGURACIÓN</h2>
			</div>
		</div>
		<h:form id="setupForm">
			<p:messages showDetail="false" showSummary="true"/>
			
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
							<ui:repeat id="checkLeftInicializar" var="result" value="#{mainController.flow.selectNodes('//flow/step')}" varStatus="iter">
								<ui:fragment rendered='#{result.valueOf("@name") lt 6}'>
									<div class="checkbox checkbox-primary">
										<h:selectBooleanCheckbox id="menu" value="#{flowScope.config[result.valueOf('@view').concat('Inicializar')]}"
											styleClass="chkComponentInicializar" />
										<p:outputLabel>#{result.valueOf("@name")} - #{result.valueOf("@menu")}</p:outputLabel>
									</div>
								</ui:fragment>
							</ui:repeat>
						</div>
						<div class="col-md-6">
							<ui:repeat id="checkRightInicializar" var="result" value="#{mainController.flow.selectNodes('//flow/step')}" varStatus="iter">
								<ui:fragment rendered='#{!(result.valueOf("@name") lt 6)}'>
									<div class="checkbox checkbox-primary">
										<h:selectBooleanCheckbox id="menu" value="#{flowScope.config[result.valueOf('@view').concat('Inicializar')]}"
											styleClass="chkComponentInicializar" />
										<p:outputLabel>#{result.valueOf("@name")} - #{result.valueOf("@menu")}</p:outputLabel>
									</div>
								</ui:fragment>
							</ui:repeat>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<div class="checkbox checkbox-primary">
								<input type="checkbox" id="allInicializar" name="allInicializar" onClick="selectAllSetupChecksInicializar();"></input>
								<label for="allInicializar">TODO</label>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<div class="col-md-6">
							<ui:repeat id="checkLeftExterno" var="result" value="#{mainController.flow.selectNodes('//flow/step')}" varStatus="iter">
								<ui:fragment rendered='#{result.valueOf("@name") lt 6}'>
									<div class="checkbox checkbox-primary">
										<h:selectBooleanCheckbox id="menu" value="#{flowScope.config[result.valueOf('@view').concat('Externo')]}"
											styleClass="chkComponentExterno" />
										<p:outputLabel>#{result.valueOf("@name")} - #{result.valueOf("@menu")}</p:outputLabel>
									</div>
								</ui:fragment>
							</ui:repeat>
						</div>
						<div class="col-md-6">
							<ui:repeat id="checkRightExterno" var="result" value="#{mainController.flow.selectNodes('//flow/step')}" varStatus="iter">
								<ui:fragment rendered='#{!(result.valueOf("@name") lt 6)}'>
									<div class="checkbox checkbox-primary">
										<h:selectBooleanCheckbox id="menu" value="#{flowScope.config[result.valueOf('@view').concat('Externo')]}"
											styleClass="chkComponentExterno" />
										<p:outputLabel>#{result.valueOf("@name")} - #{result.valueOf("@menu")}</p:outputLabel>
									</div>
								</ui:fragment>
							</ui:repeat>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<div class="checkbox checkbox-primary">
								<input type="checkbox" id="allExterno" name="allExterno" onClick="selectAllSetupChecksExterno();"></input>
								<label for="allExterno">TODO</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				<div class="form-group ">
					<h:outputLabel for="kbContrato" styleClass="control-label">
						Longitud del contrato en KB:
					</h:outputLabel>
					<p:inputText id="kbContrato" style="color:#000;" value="#{flowScope.config.kbContrato}" 
						styleClass="form-control" label="Longitud del contrato en KB"/>
				</div>
				</div>
				<div class="col-md-6">
					<div class="checkbox checkbox-primary">
						<h:selectBooleanCheckbox id="useDistributedCache" value="#{flowScope.config.useDistributedCache}" />
						<p:outputLabel>Usar cache distribuido</p:outputLabel>
					</div>
				</div>
			</div>
			<div class="separator-sm"></div>
			<div class="row">
				<div class="form-group">
					<div class="col-md-6">
						<p:commandLink id="performSetup" action="aplicar" 
							styleClass="btn btn-default" style="color:#000;" ajax="false">
							Aplicar
						</p:commandLink>
					</div>
				</div>
			</div>
			<div class="separator-sm"></div>
		</h:form>
		
    </tiles:putAttribute>
</tiles:insertDefinition>