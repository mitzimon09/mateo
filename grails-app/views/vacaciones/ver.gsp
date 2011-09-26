
<%@ page import="mx.edu.um.rh.Vacaciones" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vacaciones.label', default: 'Vacaciones')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-vacaciones" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-vacaciones" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list vacaciones">
			
				<g:if test="${vacaciones?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="vacaciones.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${vacaciones}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacaciones?.dias}">
				<li class="fieldcontain">
					<span id="dias-label" class="property-label"><g:message code="vacaciones.dias.label" default="Dias" /></span>
					
						<span class="property-value" aria-labelledby="dias-label"><g:fieldValue bean="${vacaciones}" field="dias"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacaciones?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="vacaciones.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:link controller="usuario" action="ver" id="${vacaciones?.usuario?.id}">${vacaciones?.usuario?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacaciones?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="vacaciones.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="ver" id="${vacaciones?.empleado?.id}">${vacaciones?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacaciones?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="vacaciones.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${vacaciones}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacaciones?.solicitudSalida}">
				<li class="fieldcontain">
					<span id="solicitudSalida-label" class="property-label"><g:message code="vacaciones.solicitudSalida.label" default="Solicitud Salida" /></span>
					
						<span class="property-value" aria-labelledby="solicitudSalida-label"><g:link controller="solicitudSalida" action="ver" id="${vacaciones?.solicitudSalida?.id}">${vacaciones?.solicitudSalida?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacaciones?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="vacaciones.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${vacaciones?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacaciones?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="vacaciones.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="ver" id="${vacaciones?.empresa?.id}">${vacaciones?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${vacaciones?.id}" />
					<g:link class="edit" action="edita" id="${vacaciones?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
