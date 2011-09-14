
<%@ page import="mx.edu.um.rh.SolicitudSalida" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudSalida.label', default: 'SolicitudSalida')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudSalida" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudSalida" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudSalida">
			
				<g:if test="${solicitudSalida?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="solicitudSalida.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${solicitudSalida}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="solicitudSalida.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${solicitudSalida}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="solicitudSalida.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="ver" id="${solicitudSalida?.empresa?.id}">${solicitudSalida?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.vacaciones}">
				<li class="fieldcontain">
					<span id="vacaciones-label" class="property-label"><g:message code="solicitudSalida.vacaciones.label" default="Vacaciones" /></span>
					
						<span class="property-value" aria-labelledby="vacaciones-label"><g:link controller="vacaciones" action="ver" id="${solicitudSalida?.vacaciones?.id}">${solicitudSalida?.vacaciones?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="solicitudSalida.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="ver" id="${solicitudSalida?.empleado?.id}">${solicitudSalida?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="solicitudSalida.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${solicitudSalida?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="solicitudSalida.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:link controller="usuario" action="ver" id="${solicitudSalida?.usuario?.id}">${solicitudSalida?.usuario?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudSalida?.id}" />
					<g:link class="edit" action="edita" id="${solicitudSalida?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
