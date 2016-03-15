<!DOCTYPE composition PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	    		xmlns:ui="http://java.sun.com/jsf/facelets"
	  			xmlns:h="http://java.sun.com/jsf/html"
	  			xmlns:f="http://java.sun.com/jsf/core"
	  			xmlns:p="http://primefaces.org/ui"
				template="/layouts/standard.xhtml">
<ui:define name="content">
<!-- PAGE:STATIC:Home -->
	<div class="sidebar subpanel">
		<div class="clear vs_1" />
		<h1>La página que has solicitado no existe en el servidor</h1>
		<div class="clear vs_1" />
		<p>Prueba mejor suerte en los siguientes enlaces</p>
		<div class="clear vs_1" />
		<p><a class="bigButton" href="#{request.contextPath}/es/home">Inicio</a></p>
		<div class="clear vs_1" />
	</div>
</ui:define>
</ui:composition>
