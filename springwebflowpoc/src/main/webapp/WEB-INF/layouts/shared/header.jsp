<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div class="row">
	<div class="col-md-12">
		<div class="h3 text-center"><strong>Spring WebFlow POC</strong></div>
	</div>
</div>	

<div class="row">
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="${pageContext.request.contextPath}/es/home">
			<img class="logo" src='${pageContext.request.contextPath}/web/resources/images/logo.jpg' />
			<h1>Spring WebFlow POC</h1>
	      </a>
	    </div>
	    <div id="navbar1" class="navbar-collapse collapse">
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="${pageContext.request.contextPath}/es/home">Inicio</a></li>
	        <li><a href="${pageContext.request.contextPath}/es/seguroHogar">Hogar</a></li>
	      </ul>
	    </div>
	    <!--/.nav-collapse -->
	  </div>
	  <!--/.container-fluid -->
	</nav>
</div>

