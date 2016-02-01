<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<div class="row">
	<div class="col-md-12">
		<div class="h3 text-center"><strong>Generali Struts POC</strong></div>
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
	      <a class="navbar-brand" href="${pageContext.request.contextPath}//init.do?method=onEntry">
			<img class="logo" src='${pageContext.request.contextPath}/static/images/logo.jpg' />
			<h1>Struts POC</h1>
	      </a>
	    </div>
	    <div id="navbar1" class="navbar-collapse collapse">
	      <ul class="nav navbar-nav navbar-right">
	        <li class="active"><a href="${pageContext.request.contextPath}//init.do?method=onEntry">Home</a></li>
	        <li><a href="#">Configuration</a></li>
	        <li><a href="#">Contact</a></li>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
	          <ul class="dropdown-menu" role="menu">
	            <li><a href="#">Action</a></li>
	            <li><a href="#">Another action</a></li>
	            <li><a href="#">Something else here</a></li>
	            <li class="divider"></li>
	            <li class="dropdown-header">Nav header</li>
	            <li><a href="#">Separated link</a></li>
	            <li><a href="#">One more separated link</a></li>
	          </ul>
	        </li>
	      </ul>
	    </div>
	    <!--/.nav-collapse -->
	  </div>
	  <!--/.container-fluid -->
	</nav>
</div>



