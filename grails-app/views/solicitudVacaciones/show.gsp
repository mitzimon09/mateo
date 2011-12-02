
<%@ page import="mx.edu.um.rh.SolicitudVacaciones" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudVacaciones" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudVacaciones" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudVacaciones">
			
				<g:if test="${solicitudVacaciones?.usuarioRecibe}">
				<li class="fieldcontain">
					<span id="usuarioRecibe-label" class="property-label"><g:message code="solicitudVacaciones.usuarioRecibe.label" default="Usuario Recibe" /></span>
					
						<span class="property-value" aria-labelledby="usuarioRecibe-label"><g:link controller="usuario" action="show" id="${solicitudVacaciones?.usuarioRecibe?.id}">${solicitudVacaciones?.usuarioRecibe?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.fechaRecibe}">
				<li class="fieldcontain">
					<span id="fechaRecibe-label" class="property-label"><g:message code="solicitudVacaciones.fechaRecibe.label" default="Fecha Recibe" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibe-label"><g:formatDate date="${solicitudVacaciones?.fechaRecibe}" /></span>
					
				</li>
				</g:if>
			
			<g:if test="${(permisos >= 3)}">
				<g:if test="${solicitudVacaciones?.usuarioAutoriza}">
				<li class="fieldcontain">
					<span id="usuarioAutoriza-label" class="property-label"><g:message code="solicitudVacaciones.usuarioAutoriza.label" default="Usuario Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="usuarioAutoriza-label"><g:link controller="usuario" action="show" id="${solicitudVacaciones?.usuarioAutoriza?.id}">${solicitudVacaciones?.usuarioAutoriza?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			</g:if>
			
				<g:if test="${solicitudVacaciones?.fechaAutoriza}">
				<li class="fieldcontain">
					<span id="fechaAutoriza-label" class="property-label"><g:message code="solicitudVacaciones.fechaAutoriza.label" default="Fecha Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="fechaAutoriza-label"><g:formatDate date="${solicitudVacaciones?.fechaAutoriza}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="solicitudVacaciones.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:link controller="observaciones" action="show" id="${solicitudVacaciones?.observaciones?.id}">${solicitudVacaciones?.observaciones?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			
				<g:if test="${solicitudVacaciones?.solicitudSalida}">
				<li class="fieldcontain">
					<span id="solicitudSalida-label" class="property-label"><g:message code="solicitudVacaciones.solicitudSalida.label" default="Solicitud Salida" /></span>
					
						<span class="property-value" aria-labelledby="solicitudSalida-label"><g:link controller="solicitudSalida" action="show" id="${solicitudVacaciones?.solicitudSalida?.id}">${solicitudVacaciones?.solicitudSalida?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.vacaciones}">
				<li class="fieldcontain">
					<span id="vacaciones-label" class="property-label"><g:message code="solicitudVacaciones.vacaciones.label" default="Vacaciones" /></span>
					
						<span class="property-value" aria-labelledby="vacaciones-label"><g:link controller="vacaciones" action="show" id="${solicitudVacaciones?.vacaciones?.id}">${solicitudVacaciones?.vacaciones?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="solicitudVacaciones.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${solicitudVacaciones}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.jefeCCosto}">
				<li class="fieldcontain">
					<span id="jefeCCosto-label" class="property-label"><g:message code="solicitudVacaciones.jefeCCosto.label" default="Jefe CC osto" /></span>
					
						<span class="property-value" aria-labelledby="jefeCCosto-label"><g:link controller="jefeCCosto" action="show" id="${solicitudVacaciones?.jefeCCosto?.id}">${solicitudVacaciones?.jefeCCosto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="solicitudVacaciones.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${solicitudVacaciones}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.fechaRecibeJefe}">
				<li class="fieldcontain">
					<span id="fechaRecibeJefe-label" class="property-label"><g:message code="solicitudVacaciones.fechaRecibeJefe.label" default="Fecha Recibe Jefe" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibeJefe-label"><g:formatDate date="${solicitudVacaciones?.fechaRecibeJefe}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.fechaRecibeRh}">
				<li class="fieldcontain">
					<span id="fechaRecibeRh-label" class="property-label"><g:message code="solicitudVacaciones.fechaRecibeRh.label" default="Fecha Recibe Rh" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibeRh-label"><g:formatDate date="${solicitudVacaciones?.fechaRecibeRh}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.fechaAutorizacionRh}">
				<li class="fieldcontain">
					<span id="fechaAutorizacionRh-label" class="property-label"><g:message code="solicitudVacaciones.fechaAutorizacionRh.label" default="Fecha Autorizacion Rh" /></span>
					
						<span class="property-value" aria-labelledby="fechaAutorizacionRh-label"><g:formatDate date="${solicitudVacaciones?.fechaAutorizacionRh}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.diasVacaciones}">
				<li class="fieldcontain">
					<span id="diasVacaciones-label" class="property-label"><g:message code="solicitudVacaciones.diasVacaciones.label" default="Dias Vacaciones" /></span>
					
						<span class="property-value" aria-labelledby="diasVacaciones-label"><g:fieldValue bean="${solicitudVacaciones}" field="diasVacaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.userPrimaVacacional}">
				<li class="fieldcontain">
					<span id="userPrimaVacacional-label" class="property-label"><g:message code="solicitudVacaciones.userPrimaVacacional.label" default="User Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="userPrimaVacacional-label"><g:fieldValue bean="${solicitudVacaciones}" field="userPrimaVacacional"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.fechaPrimaVacacional}">
				<li class="fieldcontain">
					<span id="fechaPrimaVacacional-label" class="property-label"><g:message code="solicitudVacaciones.fechaPrimaVacacional.label" default="Fecha Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="fechaPrimaVacacional-label"><g:formatDate date="${solicitudVacaciones?.fechaPrimaVacacional}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.destino}">
				<li class="fieldcontain">
					<span id="destino-label" class="property-label"><g:message code="solicitudVacaciones.destino.label" default="Destino" /></span>
					
						<span class="property-value" aria-labelledby="destino-label"><g:fieldValue bean="${solicitudVacaciones}" field="destino"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.kilometros}">
				<li class="fieldcontain">
					<span id="kilometros-label" class="property-label"><g:message code="solicitudVacaciones.kilometros.label" default="Kilometros" /></span>
					
						<span class="property-value" aria-labelledby="kilometros-label"><g:fieldValue bean="${solicitudVacaciones}" field="kilometros"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.folioPago}">
				<li class="fieldcontain">
					<span id="folioPago-label" class="property-label"><g:message code="solicitudVacaciones.folioPago.label" default="Folio Pago" /></span>
					
						<span class="property-value" aria-labelledby="folioPago-label"><g:fieldValue bean="${solicitudVacaciones}" field="folioPago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.furlough}">
				<li class="fieldcontain">
					<span id="furlough-label" class="property-label"><g:message code="solicitudVacaciones.furlough.label" default="Furlough" /></span>
					
						<span class="property-value" aria-labelledby="furlough-label"><g:fieldValue bean="${solicitudVacaciones}" field="furlough"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="solicitudVacaciones.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${solicitudVacaciones?.empresa?.id}">${solicitudVacaciones?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.fechaCaptura}">
				<li class="fieldcontain">
					<span id="fechaCaptura-label" class="property-label"><g:message code="solicitudVacaciones.fechaCaptura.label" default="Fecha Captura" /></span>
					
						<span class="property-value" aria-labelledby="fechaCaptura-label"><g:formatDate date="${solicitudVacaciones?.fechaCaptura}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.nacional}">
				<li class="fieldcontain">
					<span id="nacional-label" class="property-label"><g:message code="solicitudVacaciones.nacional.label" default="Nacional" /></span>
					
						<span class="property-value" aria-labelledby="nacional-label"><g:formatBoolean boolean="${solicitudVacaciones?.nacional}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.primaVacacional}">
				<li class="fieldcontain">
					<span id="primaVacacional-label" class="property-label"><g:message code="solicitudVacaciones.primaVacacional.label" default="Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="primaVacacional-label"><g:formatBoolean boolean="${solicitudVacaciones?.primaVacacional}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.visitaPadres}">
				<li class="fieldcontain">
					<span id="visitaPadres-label" class="property-label"><g:message code="solicitudVacaciones.visitaPadres.label" default="Visita Padres" /></span>
					
						<span class="property-value" aria-labelledby="visitaPadres-label"><g:formatBoolean boolean="${solicitudVacaciones?.visitaPadres}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudVacaciones?.id}" />
					<g:link class="edit" action="edit" id="${solicitudVacaciones?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
