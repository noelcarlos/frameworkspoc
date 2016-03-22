<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<div class="row">
	<div class="col-md-12">
		<div class="separator-sm"></div>
		<div class="btn-group" role="group" aria-label="...">
		
			<button type="button" class="btn btn-default" onclick="sendEvent('gobackward-first');" 
				${(esmartpointWebFlowEngine.currentStep != 1) ? '' : 'disabled="disabled"'}>
				Inicio
			</button>
			<button type="button" class="btn btn-default" onclick="sendEvent('gobackward-previous');"
				${(esmartpointWebFlowEngine.currentStep != 1) ? '' : 'disabled="disabled"'}>
				Anterior
			</button>
			<button type="button" class="btn btn-primary" onclick="sendEvent('goforward-next');"
				${(esmartpointWebFlowEngine.currentStep != esmartpointWebFlowEngine.lastPageNumber) ? '' : 'disabled="disabled"'}>
				Siguiente
			</button>
			<button type="button" class="btn btn-default" onclick="sendEvent('goforward-last');"
				${(esmartpointWebFlowEngine.currentStep != esmartpointWebFlowEngine.lastPageNumber) ? '' : 'disabled="disabled"'}>
				Última
			</button>
			
		</div>
		<div class="separator-sm"></div>
	</div>
</div>

