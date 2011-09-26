
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
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudSalida" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudSalida">
			
				<g:if test="${solicitudSalidaInstance?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="solicitudSalida.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="show" id="${solicitudSalidaInstance?.empleado?.id}">${solicitudSalidaInstance?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.diasVacaciones}">
				<li class="fieldcontain">
					<span id="diasVacaciones-label" class="property-label"><g:message code="solicitudSalida.diasVacaciones.label" default="Dias Vacaciones" /></span>
					
						<span class="property-value" aria-labelledby="diasVacaciones-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="diasVacaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.primaVacacional}">
				<li class="fieldcontain">
					<span id="primaVacacional-label" class="property-label"><g:message code="solicitudSalida.primaVacacional.label" default="Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="primaVacacional-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="primaVacacional"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.fechaInicial}">
				<li class="fieldcontain">
					<span id="fechaInicial-label" class="property-label"><g:message code="solicitudSalida.fechaInicial.label" default="Fecha Inicial" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicial-label"><g:formatDate date="${solicitudSalidaInstance?.fechaInicial}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.destino}">
				<li class="fieldcontain">
					<span id="destino-label" class="property-label"><g:message code="solicitudSalida.destino.label" default="Destino" /></span>
					
						<span class="property-value" aria-labelledby="destino-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="destino"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.kilometros}">
				<li class="fieldcontain">
					<span id="kilometros-label" class="property-label"><g:message code="solicitudSalida.kilometros.label" default="Kilometros" /></span>
					
						<span class="property-value" aria-labelledby="kilometros-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="kilometros"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.contacto}">
				<li class="fieldcontain">
					<span id="contacto-label" class="property-label"><g:message code="solicitudSalida.contacto.label" default="Contacto" /></span>
					
						<span class="property-value" aria-labelledby="contacto-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="contacto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="solicitudSalida.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="usuario" action="show" id="${solicitudSalidaInstance?.user?.id}">${solicitudSalidaInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.fechaCaptura}">
				<li class="fieldcontain">
					<span id="fechaCaptura-label" class="property-label"><g:message code="solicitudSalida.fechaCaptura.label" default="Fecha Captura" /></span>
					
						<span class="property-value" aria-labelledby="fechaCaptura-label"><g:formatDate date="${solicitudSalidaInstance?.fechaCaptura}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.recibeUser}">
				<li class="fieldcontain">
					<span id="recibeUser-label" class="property-label"><g:message code="solicitudSalida.recibeUser.label" default="Recibe User" /></span>
					
						<span class="property-value" aria-labelledby="recibeUser-label"><g:link controller="usuario" action="show" id="${solicitudSalidaInstance?.recibeUser?.id}">${solicitudSalidaInstance?.recibeUser?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.fechaRecibeRh}">
				<li class="fieldcontain">
					<span id="fechaRecibeRh-label" class="property-label"><g:message code="solicitudSalida.fechaRecibeRh.label" default="Fecha Recibe Rh" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibeRh-label"><g:formatDate date="${solicitudSalidaInstance?.fechaRecibeRh}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.rhUser}">
				<li class="fieldcontain">
					<span id="rhUser-label" class="property-label"><g:message code="solicitudSalida.rhUser.label" default="Rh User" /></span>
					
						<span class="property-value" aria-labelledby="rhUser-label"><g:link controller="usuario" action="show" id="${solicitudSalidaInstance?.rhUser?.id}">${solicitudSalidaInstance?.rhUser?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.fechaAutorizacionRh}">
				<li class="fieldcontain">
					<span id="fechaAutorizacionRh-label" class="property-label"><g:message code="solicitudSalida.fechaAutorizacionRh.label" default="Fecha Autorizacion Rh" /></span>
					
						<span class="property-value" aria-labelledby="fechaAutorizacionRh-label"><g:formatDate date="${solicitudSalidaInstance?.fechaAutorizacionRh}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="solicitudSalida.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.visitaPadres}">
				<li class="fieldcontain">
					<span id="visitaPadres-label" class="property-label"><g:message code="solicitudSalida.visitaPadres.label" default="Visita Padres" /></span>
					
						<span class="property-value" aria-labelledby="visitaPadres-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="visitaPadres"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.nacional}">
				<li class="fieldcontain">
					<span id="nacional-label" class="property-label"><g:message code="solicitudSalida.nacional.label" default="Nacional" /></span>
					
						<span class="property-value" aria-labelledby="nacional-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="nacional"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.userPrimaVacacional}">
				<li class="fieldcontain">
					<span id="userPrimaVacacional-label" class="property-label"><g:message code="solicitudSalida.userPrimaVacacional.label" default="User Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="userPrimaVacacional-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="userPrimaVacacional"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.fechaPrimaVacacional}">
				<li class="fieldcontain">
					<span id="fechaPrimaVacacional-label" class="property-label"><g:message code="solicitudSalida.fechaPrimaVacacional.label" default="Fecha Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="fechaPrimaVacacional-label"><g:formatDate date="${solicitudSalidaInstance?.fechaPrimaVacacional}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="solicitudSalida.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.observacionesEmpleado}">
				<li class="fieldcontain">
					<span id="observacionesEmpleado-label" class="property-label"><g:message code="solicitudSalida.observacionesEmpleado.label" default="Observaciones Empleado" /></span>
					
						<span class="property-value" aria-labelledby="observacionesEmpleado-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="observacionesEmpleado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.folioPago}">
				<li class="fieldcontain">
					<span id="folioPago-label" class="property-label"><g:message code="solicitudSalida.folioPago.label" default="Folio Pago" /></span>
					
						<span class="property-value" aria-labelledby="folioPago-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="folioPago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.fechaFinal}">
				<li class="fieldcontain">
					<span id="fechaFinal-label" class="property-label"><g:message code="solicitudSalida.fechaFinal.label" default="Fecha Final" /></span>
					
						<span class="property-value" aria-labelledby="fechaFinal-label"><g:formatDate date="${solicitudSalidaInstance?.fechaFinal}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.contactoTelefono}">
				<li class="fieldcontain">
					<span id="contactoTelefono-label" class="property-label"><g:message code="solicitudSalida.contactoTelefono.label" default="Contacto Telefono" /></span>
					
						<span class="property-value" aria-labelledby="contactoTelefono-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="contactoTelefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.contactoEmail}">
				<li class="fieldcontain">
					<span id="contactoEmail-label" class="property-label"><g:message code="solicitudSalida.contactoEmail.label" default="Contacto Email" /></span>
					
						<span class="property-value" aria-labelledby="contactoEmail-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="contactoEmail"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.fechaRecibeJefe}">
				<li class="fieldcontain">
					<span id="fechaRecibeJefe-label" class="property-label"><g:message code="solicitudSalida.fechaRecibeJefe.label" default="Fecha Recibe Jefe" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibeJefe-label"><g:formatDate date="${solicitudSalidaInstance?.fechaRecibeJefe}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.jefeUserId}">
				<li class="fieldcontain">
					<span id="jefeUserId-label" class="property-label"><g:message code="solicitudSalida.jefeUserId.label" default="Jefe User Id" /></span>
					
						<span class="property-value" aria-labelledby="jefeUserId-label"><g:link controller="usuario" action="show" id="${solicitudSalidaInstance?.jefeUserId?.id}">${solicitudSalidaInstance?.jefeUserId?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.observacionesRh}">
				<li class="fieldcontain">
					<span id="observacionesRh-label" class="property-label"><g:message code="solicitudSalida.observacionesRh.label" default="Observaciones Rh" /></span>
					
						<span class="property-value" aria-labelledby="observacionesRh-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="observacionesRh"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalidaInstance?.furlough}">
				<li class="fieldcontain">
					<span id="furlough-label" class="property-label"><g:message code="solicitudSalida.furlough.label" default="Furlough" /></span>
					
						<span class="property-value" aria-labelledby="furlough-label"><g:fieldValue bean="${solicitudSalidaInstance}" field="furlough"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudSalidaInstance?.id}" />
					<g:link class="edit" action="edit" id="${solicitudSalidaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
