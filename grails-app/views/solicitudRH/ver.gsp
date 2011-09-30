
<%@ page import="mx.edu.um.rh.SolicitudRH" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudRH.label', default: 'SolicitudRH')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudRH" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudRH" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudRH">
			
				<g:if test="${solicitudRH?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="solicitudRH.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="ver" id="${solicitudRH?.empleado?.id}">${solicitudRH?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.fechaInicial}">
				<li class="fieldcontain">
					<span id="fechaInicial-label" class="property-label"><g:message code="solicitudRH.fechaInicial.label" default="Fecha Inicial" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicial-label"><g:formatDate date="${solicitudRH?.fechaInicial}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.fechaFinal}">
				<li class="fieldcontain">
					<span id="fechaFinal-label" class="property-label"><g:message code="solicitudRH.fechaFinal.label" default="Fecha Final" /></span>
					
						<span class="property-value" aria-labelledby="fechaFinal-label"><g:formatDate date="${solicitudRH?.fechaFinal}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.usuarioCrea}">
				<li class="fieldcontain">
					<span id="usuarioCrea-label" class="property-label"><g:message code="solicitudRH.usuarioCrea.label" default="Usuario Crea" /></span>
					
						<span class="property-value" aria-labelledby="usuarioCrea-label"><g:link controller="usuario" action="ver" id="${solicitudRH?.usuarioCrea?.id}">${solicitudRH?.usuarioCrea?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="solicitudRH.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${solicitudRH?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.usuarioRecibe}">
				<li class="fieldcontain">
					<span id="usuarioRecibe-label" class="property-label"><g:message code="solicitudRH.usuarioRecibe.label" default="Usuario Recibe" /></span>
					
						<span class="property-value" aria-labelledby="usuarioRecibe-label"><g:link controller="usuario" action="ver" id="${solicitudRH?.usuarioRecibe?.id}">${solicitudRH?.usuarioRecibe?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.fechaRecibe}">
				<li class="fieldcontain">
					<span id="fechaRecibe-label" class="property-label"><g:message code="solicitudRH.fechaRecibe.label" default="Fecha Recibe" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibe-label"><g:formatDate date="${solicitudRH?.fechaRecibe}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.usuarioAutoriza}">
				<li class="fieldcontain">
					<span id="usuarioAutoriza-label" class="property-label"><g:message code="solicitudRH.usuarioAutoriza.label" default="Usuario Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="usuarioAutoriza-label"><g:link controller="usuario" action="ver" id="${solicitudRH?.usuarioAutoriza?.id}">${solicitudRH?.usuarioAutoriza?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.fechaAutoriza}">
				<li class="fieldcontain">
					<span id="fechaAutoriza-label" class="property-label"><g:message code="solicitudRH.fechaAutoriza.label" default="Fecha Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="fechaAutoriza-label"><g:formatDate date="${solicitudRH?.fechaAutoriza}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="solicitudRH.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${solicitudRH}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.telContacto}">
				<li class="fieldcontain">
					<span id="telContacto-label" class="property-label"><g:message code="solicitudRH.telContacto.label" default="Tel Contacto" /></span>
					
						<span class="property-value" aria-labelledby="telContacto-label"><g:fieldValue bean="${solicitudRH}" field="telContacto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="solicitudRH.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${solicitudRH}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.solicitudSalida}">
				<li class="fieldcontain">
					<span id="solicitudSalida-label" class="property-label"><g:message code="solicitudRH.solicitudSalida.label" default="Solicitud Salida" /></span>
					
						<span class="property-value" aria-labelledby="solicitudSalida-label"><g:link controller="solicitudSalida" action="ver" id="${solicitudRH?.solicitudSalida?.id}">${solicitudRH?.solicitudSalida?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.vacaciones}">
				<li class="fieldcontain">
					<span id="vacaciones-label" class="property-label"><g:message code="solicitudRH.vacaciones.label" default="Vacaciones" /></span>
					
						<span class="property-value" aria-labelledby="vacaciones-label"><g:link controller="vacaciones" action="ver" id="${solicitudRH?.vacaciones?.id}">${solicitudRH?.vacaciones?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="solicitudRH.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${solicitudRH}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudRH?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="solicitudRH.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="ver" id="${solicitudRH?.empresa?.id}">${solicitudRH?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudRH?.id}" />
					<g:link class="edit" action="edita" id="${solicitudRH?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
