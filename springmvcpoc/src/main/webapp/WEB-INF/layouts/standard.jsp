<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %> 
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Spring WebFlow POC</title>
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/icons/favicon.ico?v2" />
	
    <link href="${pageContext.request.contextPath}/web/resources/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/web/resources/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/web/resources/plugins/awesome-bootstrap/awesome-bootstrap-checkbox.css" rel="stylesheet" />
	
 	<!-- App Style -->
	<link href="${pageContext.request.contextPath}/web/resources/style.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/web/resources/app.css" rel="stylesheet" />
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
    <script src="${pageContext.request.contextPath}/web/resources/jquery-1.11.3/jquery-1.11.3.min.js"></script>
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/web/resources/bootstrap-3.3.6/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/resources/plugins/bootstrap-datepicker-1.5.1/js/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/resources/plugins/bootstrap-datepicker-1.5.1/locales/bootstrap-datepicker.es.min.js"></script>

	<SCRIPT type="text/javascript">
    	function selectAllSetupChecksInicializar() {
   			$( ".chkComponentInicializar" ).each(function() {
    			if ($("#allInicializar").is(':checked')) {
    			 	$(this).prop('checked', true);
    			} else {
    			  	$(this).prop('checked', false);
    			}
    		});
    	}
    	function selectAllSetupChecksExterno() {
   			$( ".chkComponentExterno" ).each(function() {
    			if ($("#allExterno").is(':checked')) {
    			 	$(this).prop('checked', true);
    			} else {
    			  	$(this).prop('checked', false);
    			}
    		});
    	}
    	$(function() {
	    	$('input.date-picker').datepicker({
	    	    language: "es"
	    	});
    	});
    </SCRIPT>	
	
</head>
<body>

	<body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" style="dark-gray-back">
		<div class="header-block dark-gray-back">
			<div class="container">
				<tiles:insertAttribute name="header" />
			</div>
		</div>
	
		<div class="container" style="background: #fff;min-height: 600px;">
			<tiles:insertAttribute name="content" />
		</div>
		
		<div class="footer-block dark-gray-back">
			<div class="container">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</body>
	
</body>

</html>
