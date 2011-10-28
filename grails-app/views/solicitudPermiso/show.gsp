
<%@ page import="mx.edu.um.rh.SolicitudPermiso" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudPermiso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudPermiso" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudPermiso">
			
				<g:if test="${solicitudPermisoInstance?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="solicitudPermiso.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="show" id="${solicitudPermisoInstance?.empleado?.id}">${solicitudPermisoInstance?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.fechaInicial}">
				<li class="fieldcontain">
					<span id="fechaInicial-label" class="property-label"><g:message code="solicitudPermiso.fechaInicial.label" default="Fecha Inicial" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicial-label"><g:formatDate date="${solicitudPermisoInstance?.fechaInicial}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.fechaFinal}">
				<li class="fieldcontain">
					<span id="fechaFinal-label" class="property-label"><g:message code="solicitudPermiso.fechaFinal.label" default="Fecha Final" /></span>
					
						<span class="property-value" aria-labelledby="fechaFinal-label"><g:formatDate date="${solicitudPermisoInstance?.fechaFinal}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.usuarioCrea}">
				<li class="fieldcontain">
					<span id="usuarioCrea-label" class="property-label"><g:message code="solicitudPermiso.usuarioCrea.label" default="Usuario Crea" /></span>
					
						<span class="property-value" aria-labelledby="usuarioCrea-label"><g:link controller="usuario" action="show" id="${solicitudPermisoInstance?.usuarioCrea?.id}">${solicitudPermisoInstance?.usuarioCrea?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="solicitudPermiso.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${solicitudPermisoInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.usuarioRecibe}">
				<li class="fieldcontain">
					<span id="usuarioRecibe-label" class="property-label"><g:message code="solicitudPermiso.usuarioRecibe.label" default="Usuario Recibe" /></span>
					
						<span class="property-value" aria-labelledby="usuarioRecibe-label"><g:link controller="usuario" action="show" id="${solicitudPermisoInstance?.usuarioRecibe?.id}">${solicitudPermisoInstance?.usuarioRecibe?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.fechaRecibe}">
				<li class="fieldcontain">
					<span id="fechaRecibe-label" class="property-label"><g:message code="solicitudPermiso.fechaRecibe.label" default="Fecha Recibe" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibe-label"><g:formatDate date="${solicitudPermisoInstance?.fechaRecibe}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.usuarioAutoriza}">
				<li class="fieldcontain">
					<span id="usuarioAutoriza-label" class="property-label"><g:message code="solicitudPermiso.usuarioAutoriza.label" default="Usuario Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="usuarioAutoriza-label"><g:link controller="usuario" action="show" id="${solicitudPermisoInstance?.usuarioAutoriza?.id}">${solicitudPermisoInstance?.usuarioAutoriza?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.fechaAutoriza}">
				<li class="fieldcontain">
					<span id="fechaAutoriza-label" class="property-label"><g:message code="solicitudPermiso.fechaAutoriza.label" default="Fecha Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="fechaAutoriza-label"><g:formatDate date="${solicitudPermisoInstance?.fechaAutoriza}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="solicitudPermiso.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:link controller="observaciones" action="show" id="${solicitudPermisoInstance?.observaciones?.id}">${solicitudPermisoInstance?.observaciones?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.telContacto}">
				<li class="fieldcontain">
					<span id="telContacto-label" class="property-label"><g:message code="solicitudPermiso.telContacto.label" default="Tel Contacto" /></span>
					
						<span class="property-value" aria-labelledby="telContacto-label"><g:fieldValue bean="${solicitudPermisoInstance}" field="telContacto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="solicitudPermiso.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${solicitudPermisoInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.solicitudSalida}">
				<li class="fieldcontain">
					<span id="solicitudSalida-label" class="property-label"><g:message code="solicitudPermiso.solicitudSalida.label" default="Solicitud Salida" /></span>
					
						<span class="property-value" aria-labelledby="solicitudSalida-label"><g:link controller="solicitudSalida" action="show" id="${solicitudPermisoInstance?.solicitudSalida?.id}">${solicitudPermisoInstance?.solicitudSalida?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.vacaciones}">
				<li class="fieldcontain">
					<span id="vacaciones-label" class="property-label"><g:message code="solicitudPermiso.vacaciones.label" default="Vacaciones" /></span>
					
						<span class="property-value" aria-labelledby="vacaciones-label"><g:link controller="vacaciones" action="show" id="${solicitudPermisoInstance?.vacaciones?.id}">${solicitudPermisoInstance?.vacaciones?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="solicitudPermiso.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${solicitudPermisoInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.jefeCCosto}">
				<li class="fieldcontain">
					<span id="jefeCCosto-label" class="property-label"><g:message code="solicitudPermiso.jefeCCosto.label" default="Jefe CC osto" /></span>
					
						<span class="property-value" aria-labelledby="jefeCCosto-label"><g:link controller="jefeCCosto" action="show" id="${solicitudPermisoInstance?.jefeCCosto?.id}">${solicitudPermisoInstance?.jefeCCosto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="solicitudPermiso.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${solicitudPermisoInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.motivo}">
				<li class="fieldcontain">
					<span id="motivo-label" class="property-label"><g:message code="solicitudPermiso.motivo.label" default="Motivo" /></span>
					
						<span class="property-value" aria-labelledby="motivo-label"><g:fieldValue bean="${solicitudPermisoInstance}" field="motivo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.actualizacion}">
				<li class="fieldcontain">
					<span id="actualizacion-label" class="property-label"><g:message code="solicitudPermiso.actualizacion.label" default="Actualizacion" /></span>
					
						<span class="property-value" aria-labelledby="actualizacion-label"><g:formatBoolean boolean="${solicitudPermisoInstance?.actualizacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="solicitudPermiso.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${solicitudPermisoInstance?.empresa?.id}">${solicitudPermisoInstance?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.hospedaje}">
				<li class="fieldcontain">
					<span id="hospedaje-label" class="property-label"><g:message code="solicitudPermiso.hospedaje.label" default="Hospedaje" /></span>
					
						<span class="property-value" aria-labelledby="hospedaje-label"><g:fieldValue bean="${solicitudPermisoInstance}" field="hospedaje"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.otros}">
				<li class="fieldcontain">
					<span id="otros-label" class="property-label"><g:message code="solicitudPermiso.otros.label" default="Otros" /></span>
					
						<span class="property-value" aria-labelledby="otros-label"><g:fieldValue bean="${solicitudPermisoInstance}" field="otros"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.totalDeGastos}">
				<li class="fieldcontain">
					<span id="totalDeGastos-label" class="property-label"><g:message code="solicitudPermiso.totalDeGastos.label" default="Total De Gastos" /></span>
					
						<span class="property-value" aria-labelledby="totalDeGastos-label"><g:fieldValue bean="${solicitudPermisoInstance}" field="totalDeGastos"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermisoInstance?.viaticos}">
				<li class="fieldcontain">
					<span id="viaticos-label" class="property-label"><g:message code="solicitudPermiso.viaticos.label" default="Viaticos" /></span>
					
						<span class="property-value" aria-labelledby="viaticos-label"><g:fieldValue bean="${solicitudPermisoInstance}" field="viaticos"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudPermisoInstance?.id}" />
					<g:link class="edit" action="edit" id="${solicitudPermisoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
