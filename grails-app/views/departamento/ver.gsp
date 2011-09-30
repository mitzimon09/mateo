
<%@ page import="contabilidad.Departamento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'departamento.label', default: 'Departamento')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-departamento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-departamento" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list departamento">
			
				<g:if test="${departamento?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="departamento.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${departamento}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${departamento?.cuenta}">
				<li class="fieldcontain">
					<span id="cuenta-label" class="property-label"><g:message code="departamento.cuenta.label" default="Cuenta" /></span>
					
						<span class="property-value" aria-labelledby="cuenta-label"><g:link controller="cuenta" action="ver" id="${departamento?.cuenta?.id}">${departamento?.cuenta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${departamento?.organizacion}">
				<li class="fieldcontain">
					<span id="organizacion-label" class="property-label"><g:message code="departamento.organizacion.label" default="Organizacion" /></span>
					
						<span class="property-value" aria-labelledby="organizacion-label"><g:link controller="organizacion" action="ver" id="${departamento?.organizacion?.id}">${departamento?.organizacion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
                          
                                <g:if test="${departamento?.cuenta?.status}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="departamento.status" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:formatBoolean boolean="${departamento?.cuenta?.status}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${departamento?.id}" />
					<g:link class="edit" action="edita" id="${departamento?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
