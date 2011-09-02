<%@ page import="general.Imagen" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'imagen.label', default: 'Imagen')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-imagen" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
<<<<<<< HEAD
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
=======
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
>>>>>>> upstream/master
			</ul>
		</div>
		<div id="create-imagen" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
<<<<<<< HEAD
			<g:hasErrors bean="${imagen}">
			<ul class="errors" role="alert">
				<g:eachError bean="${imagen}" var="error">
=======
			<g:hasErrors bean="${imagenInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${imagenInstance}" var="error">
>>>>>>> upstream/master
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
<<<<<<< HEAD
			<g:form action="crea"  enctype="multipart/form-data">
=======
			<g:form action="save"  enctype="multipart/form-data">
>>>>>>> upstream/master
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
