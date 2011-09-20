
<%@ page import="mx.edu.um.rh.EmpleadoPuesto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-empleadoPuesto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-empleadoPuesto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list empleadoPuesto">
			
				<g:if test="${empleadoPuesto?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="empleadoPuesto.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${empleadoPuesto}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoPuesto?.cCosto}">
				<li class="fieldcontain">
					<span id="cCosto-label" class="property-label"><g:message code="empleadoPuesto.cCosto.label" default="CC osto" /></span>
					
						<span class="property-value" aria-labelledby="cCosto-label"><g:fieldValue bean="${empleadoPuesto}" field="cCosto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoPuesto?.ejercicio}">
				<li class="fieldcontain">
					<span id="ejercicio-label" class="property-label"><g:message code="empleadoPuesto.ejercicio.label" default="Ejercicio" /></span>
					
						<span class="property-value" aria-labelledby="ejercicio-label"><g:fieldValue bean="${empleadoPuesto}" field="ejercicio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoPuesto?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="empleadoPuesto.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="show" id="${empleadoPuesto?.empleado?.id}">${empleadoPuesto?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoPuesto?.puesto}">
				<li class="fieldcontain">
					<span id="puesto-label" class="property-label"><g:message code="empleadoPuesto.puesto.label" default="Puesto" /></span>
					
						<span class="property-value" aria-labelledby="puesto-label"><g:link controller="puesto" action="show" id="${empleadoPuesto?.puesto?.id}">${empleadoPuesto?.puesto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoPuesto?.turno}">
				<li class="fieldcontain">
					<span id="turno-label" class="property-label"><g:message code="empleadoPuesto.turno.label" default="Turno" /></span>
					
						<span class="property-value" aria-labelledby="turno-label"><g:fieldValue bean="${empleadoPuesto}" field="turno"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${empleadoPuesto?.id}" />
					<g:link class="edit" action="edit" id="${empleadoPuesto?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
