<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
	<div class="row">
		<div class="col-md-12">
			<spring:hasBindErrors name="model">
				<div class="alert alert-danger">
					<form:errors path="*" cssClass="errorBlock" element="div" />
				</div>
			</spring:hasBindErrors>
		</div>
	</div>

