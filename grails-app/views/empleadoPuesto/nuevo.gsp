<%@ page import="mx.edu.um.rh.EmpleadoPuesto" %>
<!doctype html>
<html>
	<head>
		
		<g:set var="entityName" value="${message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<div id="create-empleadoPuesto" class="content scaffold-create" role="main">
		
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${empleadoPuesto}">
			<ul class="errors" role="alert">
				<g:eachError bean="${empleadoPuesto}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="crea" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
