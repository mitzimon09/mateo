
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
			
				<g:if test="${solicitudSalida?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="solicitudSalida.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="ver" id="${solicitudSalida?.empleado?.id}">${solicitudSalida?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.diasVacaciones}">
				<li class="fieldcontain">
					<span id="diasVacaciones-label" class="property-label"><g:message code="solicitudSalida.diasVacaciones.label" default="Dias Vacaciones" /></span>
					
						<span class="property-value" aria-labelledby="diasVacaciones-label"><g:fieldValue bean="${solicitudSalida}" field="diasVacaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.primaVacacional}">
				<li class="fieldcontain">
					<span id="primaVacacional-label" class="property-label"><g:message code="solicitudSalida.primaVacacional.label" default="Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="primaVacacional-label"><g:fieldValue bean="${solicitudSalida}" field="primaVacacional"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.fechaInicial}">
				<li class="fieldcontain">
					<span id="fechaInicial-label" class="property-label"><g:message code="solicitudSalida.fechaInicial.label" default="Fecha Inicial" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicial-label"><g:formatDate date="${solicitudSalida?.fechaInicial}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.destino}">
				<li class="fieldcontain">
					<span id="destino-label" class="property-label"><g:message code="solicitudSalida.destino.label" default="Destino" /></span>
					
						<span class="property-value" aria-labelledby="destino-label"><g:fieldValue bean="${solicitudSalida}" field="destino"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.kilometros}">
				<li class="fieldcontain">
					<span id="kilometros-label" class="property-label"><g:message code="solicitudSalida.kilometros.label" default="Kilometros" /></span>
					
						<span class="property-value" aria-labelledby="kilometros-label"><g:fieldValue bean="${solicitudSalida}" field="kilometros"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.contacto}">
				<li class="fieldcontain">
					<span id="contacto-label" class="property-label"><g:message code="solicitudSalida.contacto.label" default="Contacto" /></span>
					
						<span class="property-value" aria-labelledby="contacto-label"><g:fieldValue bean="${solicitudSalida}" field="contacto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="solicitudSalida.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="usuario" action="show" id="${solicitudSalida?.user?.id}">${solicitudSalida?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.fechaCaptura}">
				<li class="fieldcontain">
					<span id="fechaCaptura-label" class="property-label"><g:message code="solicitudSalida.fechaCaptura.label" default="Fecha Captura" /></span>
					
						<span class="property-value" aria-labelledby="fechaCaptura-label"><g:formatDate date="${solicitudSalida?.fechaCaptura}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.recibeUser}">
				<li class="fieldcontain">
					<span id="recibeUser-label" class="property-label"><g:message code="solicitudSalida.recibeUser.label" default="Recibe User" /></span>
					
						<span class="property-value" aria-labelledby="recibeUser-label"><g:link controller="usuario" action="ver" id="${solicitudSalida?.recibeUser?.id}">${solicitudSalida?.recibeUser?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.fechaRecibeRh}">
				<li class="fieldcontain">
					<span id="fechaRecibeRh-label" class="property-label"><g:message code="solicitudSalida.fechaRecibeRh.label" default="Fecha Recibe Rh" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibeRh-label"><g:formatDate date="${solicitudSalida?.fechaRecibeRh}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.rhUser}">
				<li class="fieldcontain">
					<span id="rhUser-label" class="property-label"><g:message code="solicitudSalida.rhUser.label" default="Rh User" /></span>
					
						<span class="property-value" aria-labelledby="rhUser-label"><g:link controller="usuario" action="ver" id="${solicitudSalida?.rhUser?.id}">${solicitudSalida?.rhUser?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.fechaAutorizacionRh}">
				<li class="fieldcontain">
					<span id="fechaAutorizacionRh-label" class="property-label"><g:message code="solicitudSalida.fechaAutorizacionRh.label" default="Fecha Autorizacion Rh" /></span>
					
						<span class="property-value" aria-labelledby="fechaAutorizacionRh-label"><g:formatDate date="${solicitudSalida?.fechaAutorizacionRh}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="solicitudSalida.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${solicitudSalida}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.visitaPadres}">
				<li class="fieldcontain">
					<span id="visitaPadres-label" class="property-label"><g:message code="solicitudSalida.visitaPadres.label" default="Visita Padres" /></span>
					
						<span class="property-value" aria-labelledby="visitaPadres-label"><g:fieldValue bean="${solicitudSalida}" field="visitaPadres"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.nacional}">
				<li class="fieldcontain">
					<span id="nacional-label" class="property-label"><g:message code="solicitudSalida.nacional.label" default="Nacional" /></span>
					
						<span class="property-value" aria-labelledby="nacional-label"><g:fieldValue bean="${solicitudSalida}" field="nacional"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.userPrimaVacacional}">
				<li class="fieldcontain">
					<span id="userPrimaVacacional-label" class="property-label"><g:message code="solicitudSalida.userPrimaVacacional.label" default="User Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="userPrimaVacacional-label"><g:fieldValue bean="${solicitudSalida}" field="userPrimaVacacional"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.fechaPrimaVacacional}">
				<li class="fieldcontain">
					<span id="fechaPrimaVacacional-label" class="property-label"><g:message code="solicitudSalida.fechaPrimaVacacional.label" default="Fecha Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="fechaPrimaVacacional-label"><g:formatDate date="${solicitudSalida?.fechaPrimaVacacional}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="solicitudSalida.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${solicitudSalida}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.observacionesEmpleado}">
				<li class="fieldcontain">
					<span id="observacionesEmpleado-label" class="property-label"><g:message code="solicitudSalida.observacionesEmpleado.label" default="Observaciones Empleado" /></span>
					
						<span class="property-value" aria-labelledby="observacionesEmpleado-label"><g:fieldValue bean="${solicitudSalida}" field="observacionesEmpleado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.folioPago}">
				<li class="fieldcontain">
					<span id="folioPago-label" class="property-label"><g:message code="solicitudSalida.folioPago.label" default="Folio Pago" /></span>
					
						<span class="property-value" aria-labelledby="folioPago-label"><g:fieldValue bean="${solicitudSalida}" field="folioPago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.fechaFinal}">
				<li class="fieldcontain">
					<span id="fechaFinal-label" class="property-label"><g:message code="solicitudSalida.fechaFinal.label" default="Fecha Final" /></span>
					
						<span class="property-value" aria-labelledby="fechaFinal-label"><g:formatDate date="${solicitudSalida?.fechaFinal}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.contactoTelefono}">
				<li class="fieldcontain">
					<span id="contactoTelefono-label" class="property-label"><g:message code="solicitudSalida.contactoTelefono.label" default="Contacto Telefono" /></span>
					
						<span class="property-value" aria-labelledby="contactoTelefono-label"><g:fieldValue bean="${solicitudSalida}" field="contactoTelefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.contactoEmail}">
				<li class="fieldcontain">
					<span id="contactoEmail-label" class="property-label"><g:message code="solicitudSalida.contactoEmail.label" default="Contacto Email" /></span>
					
						<span class="property-value" aria-labelledby="contactoEmail-label"><g:fieldValue bean="${solicitudSalida}" field="contactoEmail"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.fechaRecibeJefe}">
				<li class="fieldcontain">
					<span id="fechaRecibeJefe-label" class="property-label"><g:message code="solicitudSalida.fechaRecibeJefe.label" default="Fecha Recibe Jefe" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibeJefe-label"><g:formatDate date="${solicitudSalida?.fechaRecibeJefe}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.jefeUserId}">
				<li class="fieldcontain">
					<span id="jefeUserId-label" class="property-label"><g:message code="solicitudSalida.jefeUserId.label" default="Jefe User Id" /></span>
					
						<span class="property-value" aria-labelledby="jefeUserId-label"><g:link controller="usuario" action="show" id="${solicitudSalida?.jefeUserId?.id}">${solicitudSalida?.jefeUserId?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.observacionesRh}">
				<li class="fieldcontain">
					<span id="observacionesRh-label" class="property-label"><g:message code="solicitudSalida.observacionesRh.label" default="Observaciones Rh" /></span>
					
						<span class="property-value" aria-labelledby="observacionesRh-label"><g:fieldValue bean="${solicitudSalida}" field="observacionesRh"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudSalida?.furlough}">
				<li class="fieldcontain">
					<span id="furlough-label" class="property-label"><g:message code="solicitudSalida.furlough.label" default="Furlough" /></span>
					
						<span class="property-value" aria-labelledby="furlough-label"><g:fieldValue bean="${solicitudSalida}" field="furlough"/></span>
					
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
