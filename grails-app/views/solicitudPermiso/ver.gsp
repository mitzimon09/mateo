
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
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="rango" action="rango"><g:message code="Encontrar por rango de fecha" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudPermiso" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudPermiso">
			
				<g:if test="${solicitudPermiso?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="solicitudPermiso.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="ver" id="${solicitudPermiso?.empleado?.id}">${solicitudPermiso?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.fechaInicial}">
				<li class="fieldcontain">
					<span id="fechaInicial-label" class="property-label"><g:message code="solicitudPermiso.fechaInicial.label" default="Fecha Inicial" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicial-label"><g:formatDate date="${solicitudPermiso?.fechaInicial}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.fechaFinal}">
				<li class="fieldcontain">
					<span id="fechaFinal-label" class="property-label"><g:message code="solicitudPermiso.fechaFinal.label" default="Fecha Final" /></span>
					
						<span class="property-value" aria-labelledby="fechaFinal-label"><g:formatDate date="${solicitudPermiso?.fechaFinal}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.usuarioCrea}">
				<li class="fieldcontain">
					<span id="usuarioCrea-label" class="property-label"><g:message code="solicitudPermiso.usuarioCrea.label" default="Usuario Crea" /></span>
					
						<span class="property-value" aria-labelledby="usuarioCrea-label"><g:link controller="usuario" action="ver" id="${solicitudPermiso?.usuarioCrea?.id}">${solicitudPermiso?.usuarioCrea?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="solicitudPermiso.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${solicitudPermiso?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.usuarioRecibe}">
				<li class="fieldcontain">
					<span id="usuarioRecibe-label" class="property-label"><g:message code="solicitudPermiso.usuarioRecibe.label" default="Usuario Recibe" /></span>
					
						<span class="property-value" aria-labelledby="usuarioRecibe-label"><g:link controller="usuario" action="ver" id="${solicitudPermiso?.usuarioRecibe?.id}">${solicitudPermiso?.usuarioRecibe?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.fechaRecibe}">
				<li class="fieldcontain">
					<span id="fechaRecibe-label" class="property-label"><g:message code="solicitudPermiso.fechaRecibe.label" default="Fecha Recibe" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibe-label"><g:formatDate date="${solicitudPermiso?.fechaRecibe}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.usuarioAutoriza}">
				<li class="fieldcontain">
					<span id="usuarioAutoriza-label" class="property-label"><g:message code="solicitudPermiso.usuarioAutoriza.label" default="Usuario Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="usuarioAutoriza-label"><g:link controller="usuario" action="ver" id="${solicitudPermiso?.usuarioAutoriza?.id}">${solicitudPermiso?.usuarioAutoriza?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.fechaAutoriza}">
				<li class="fieldcontain">
					<span id="fechaAutoriza-label" class="property-label"><g:message code="solicitudPermiso.fechaAutoriza.label" default="Fecha Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="fechaAutoriza-label"><g:formatDate date="${solicitudPermiso?.fechaAutoriza}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="solicitudPermiso.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:link controller="observaciones" action="ver" id="${solicitudPermiso?.observaciones?.id}">${solicitudPermiso?.observaciones?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.telContacto}">
				<li class="fieldcontain">
					<span id="telContacto-label" class="property-label"><g:message code="solicitudPermiso.telContacto.label" default="Tel Contacto" /></span>
					
						<span class="property-value" aria-labelledby="telContacto-label"><g:fieldValue bean="${solicitudPermiso}" field="telContacto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="solicitudPermiso.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${solicitudPermiso}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.solicitudSalida}">
				<li class="fieldcontain">
					<span id="solicitudSalida-label" class="property-label"><g:message code="solicitudPermiso.solicitudSalida.label" default="Solicitud Salida" /></span>
					
						<span class="property-value" aria-labelledby="solicitudSalida-label"><g:link controller="solicitudSalida" action="ver" id="${solicitudPermiso?.solicitudSalida?.id}">${solicitudPermiso?.solicitudSalida?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.vacaciones}">
				<li class="fieldcontain">
					<span id="vacaciones-label" class="property-label"><g:message code="solicitudPermiso.vacaciones.label" default="Vacaciones" /></span>
					
						<span class="property-value" aria-labelledby="vacaciones-label"><g:link controller="vacaciones" action="ver" id="${solicitudPermiso?.vacaciones?.id}">${solicitudPermiso?.vacaciones?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="solicitudPermiso.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${solicitudPermiso}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.jefeCCosto}">
				<li class="fieldcontain">
					<span id="jefeCCosto-label" class="property-label"><g:message code="solicitudPermiso.jefeCCosto.label" default="Jefe CC osto" /></span>
					
						<span class="property-value" aria-labelledby="jefeCCosto-label"><g:link controller="jefeCCosto" action="ver" id="${solicitudPermiso?.jefeCCosto?.id}">${solicitudPermiso?.jefeCCosto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="solicitudPermiso.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${solicitudPermiso}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.motivo}">
				<li class="fieldcontain">
					<span id="motivo-label" class="property-label"><g:message code="solicitudPermiso.motivo.label" default="Motivo" /></span>
					
						<span class="property-value" aria-labelledby="motivo-label"><g:fieldValue bean="${solicitudPermiso}" field="motivo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.actualizacion}">
				<li class="fieldcontain">
					<span id="actualizacion-label" class="property-label"><g:message code="solicitudPermiso.actualizacion.label" default="Actualizacion" /></span>
					
						<span class="property-value" aria-labelledby="actualizacion-label"><g:formatBoolean boolean="${solicitudPermiso?.actualizacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="solicitudPermiso.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="ver" id="${solicitudPermiso?.empresa?.id}">${solicitudPermiso?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.hospedaje}">
				<li class="fieldcontain">
					<span id="hospedaje-label" class="property-label"><g:message code="solicitudPermiso.hospedaje.label" default="Hospedaje" /></span>
					
						<span class="property-value" aria-labelledby="hospedaje-label"><g:fieldValue bean="${solicitudPermiso}" field="hospedaje"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.otros}">
				<li class="fieldcontain">
					<span id="otros-label" class="property-label"><g:message code="solicitudPermiso.otros.label" default="Otros" /></span>
					
						<span class="property-value" aria-labelledby="otros-label"><g:fieldValue bean="${solicitudPermiso}" field="otros"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.totalDeGastos}">
				<li class="fieldcontain">
					<span id="totalDeGastos-label" class="property-label"><g:message code="solicitudPermiso.totalDeGastos.label" default="Total De Gastos" /></span>
					
						<span class="property-value" aria-labelledby="totalDeGastos-label"><g:fieldValue bean="${solicitudPermiso}" field="totalDeGastos"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudPermiso?.viaticos}">
				<li class="fieldcontain">
					<span id="viaticos-label" class="property-label"><g:message code="solicitudPermiso.viaticos.label" default="Viaticos" /></span>
					
						<span class="property-value" aria-labelledby="viaticos-label"><g:fieldValue bean="${solicitudPermiso}" field="viaticos"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudPermiso?.id}" />
					<g:link class="edit" action="edita" id="${solicitudPermiso?.id}"><g:message code="default.button.edit.label" default="Editar" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					<g:if test="${(permisos == 1 || permisos == 5)}">
						<g:if test="${solicitudPermiso?.status.equals('CR') || solicitudPermiso?.status.equals('SU')}">
				  			<g:actionSubmit class="enviar" action="enviar" value="${message(code: 'default.button.enviar.label', default: 'Enviar')}" />
						</g:if>
					</g:if>
					<g:if test="${(permisos == 2 || permisos == 5)}">
						<g:if test="${solicitudPermiso?.status.equals('EN')}">
				  			<g:actionSubmit class="aprobar" action="aprobar" value="${message(code: 'default.button.aprobar.label', default: 'Aprobar')}" />
  							<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
  							<g:actionSubmit class="suspender" action="suspender" value="${message(code: 'default.button.suspender.label', default: 'Suspender')}" />
						</g:if>
					</g:if>
					<g:if test="${(permisos == 3 || permisos == 5)}">
						<g:if test="${solicitudPermiso?.status.equals('AP')}">
				  			<g:actionSubmit class="revisar" action="revisar" value="${message(code: 'default.button.revisar.label', default: 'Aprobar')}" />
  							<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
						</g:if>
						<g:if test="${solicitudPermiso?.status.equals('RV')}">
							<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
							<g:actionSubmit class="autorizar" action="autorizar" value="${message(code: 'default.button.autorizar.label', default: 'Autorizar')}" />
						</g:if>
					</g:if>
					<g:if test="${(permisos == 4 || permisos == 0)}">
						<g:actionSubmit class="cancelar" action="cancelar" value="${message(code: 'default.button.cancelar.label', default: 'Cancelar')}" />
						<g:if test="${!solicitudPermiso?.status.equals('RV')}">
				  			<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
							<g:actionSubmit class="autorizar" action="autorizar" value="${message(code: 'default.button.autorizar.label', default: 'Autorizar')}" />
						</g:if>
					</g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
