
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
			
				<g:if test="${solicitudVacacionesInstance?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="solicitudVacaciones.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="show" id="${solicitudVacacionesInstance?.empleado?.id}">${solicitudVacacionesInstance?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="solicitudVacaciones.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${solicitudVacacionesInstance?.empresa?.id}">${solicitudVacacionesInstance?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.fechaCaptura}">
				<li class="fieldcontain">
					<span id="fechaCaptura-label" class="property-label"><g:message code="solicitudVacaciones.fechaCaptura.label" default="Fecha Captura" /></span>
					
						<span class="property-value" aria-labelledby="fechaCaptura-label"><g:formatDate date="${solicitudVacacionesInstance?.fechaCaptura}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.fechaInicial}">
				<li class="fieldcontain">
					<span id="fechaInicial-label" class="property-label"><g:message code="solicitudVacaciones.fechaInicial.label" default="Fecha Inicial" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicial-label"><g:formatDate date="${solicitudVacacionesInstance?.fechaInicial}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.fechaFinal}">
				<li class="fieldcontain">
					<span id="fechaFinal-label" class="property-label"><g:message code="solicitudVacaciones.fechaFinal.label" default="Fecha Final" /></span>
					
						<span class="property-value" aria-labelledby="fechaFinal-label"><g:formatDate date="${solicitudVacacionesInstance?.fechaFinal}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.fechaRecibeJefe}">
				<li class="fieldcontain">
					<span id="fechaRecibeJefe-label" class="property-label"><g:message code="solicitudVacaciones.fechaRecibeJefe.label" default="Fecha Recibe Jefe" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibeJefe-label"><g:formatDate date="${solicitudVacacionesInstance?.fechaRecibeJefe}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.fechaRecibeRh}">
				<li class="fieldcontain">
					<span id="fechaRecibeRh-label" class="property-label"><g:message code="solicitudVacaciones.fechaRecibeRh.label" default="Fecha Recibe Rh" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibeRh-label"><g:formatDate date="${solicitudVacacionesInstance?.fechaRecibeRh}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.fechaAutorizacionRh}">
				<li class="fieldcontain">
					<span id="fechaAutorizacionRh-label" class="property-label"><g:message code="solicitudVacaciones.fechaAutorizacionRh.label" default="Fecha Autorizacion Rh" /></span>
					
						<span class="property-value" aria-labelledby="fechaAutorizacionRh-label"><g:formatDate date="${solicitudVacacionesInstance?.fechaAutorizacionRh}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.diasVacaciones}">
				<li class="fieldcontain">
					<span id="diasVacaciones-label" class="property-label"><g:message code="solicitudVacaciones.diasVacaciones.label" default="Dias Vacaciones" /></span>
					
						<span class="property-value" aria-labelledby="diasVacaciones-label"><g:fieldValue bean="${solicitudVacacionesInstance}" field="diasVacaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.userPrimaVacacional}">
				<li class="fieldcontain">
					<span id="userPrimaVacacional-label" class="property-label"><g:message code="solicitudVacaciones.userPrimaVacacional.label" default="User Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="userPrimaVacacional-label"><g:fieldValue bean="${solicitudVacacionesInstance}" field="userPrimaVacacional"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.fechaPrimaVacacional}">
				<li class="fieldcontain">
					<span id="fechaPrimaVacacional-label" class="property-label"><g:message code="solicitudVacaciones.fechaPrimaVacacional.label" default="Fecha Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="fechaPrimaVacacional-label"><g:formatDate date="${solicitudVacacionesInstance?.fechaPrimaVacacional}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.destino}">
				<li class="fieldcontain">
					<span id="destino-label" class="property-label"><g:message code="solicitudVacaciones.destino.label" default="Destino" /></span>
					
						<span class="property-value" aria-labelledby="destino-label"><g:fieldValue bean="${solicitudVacacionesInstance}" field="destino"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.kilometros}">
				<li class="fieldcontain">
					<span id="kilometros-label" class="property-label"><g:message code="solicitudVacaciones.kilometros.label" default="Kilometros" /></span>
					
						<span class="property-value" aria-labelledby="kilometros-label"><g:fieldValue bean="${solicitudVacacionesInstance}" field="kilometros"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.contactoTelefono}">
				<li class="fieldcontain">
					<span id="contactoTelefono-label" class="property-label"><g:message code="solicitudVacaciones.contactoTelefono.label" default="Contacto Telefono" /></span>
					
						<span class="property-value" aria-labelledby="contactoTelefono-label"><g:fieldValue bean="${solicitudVacacionesInstance}" field="contactoTelefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="solicitudVacaciones.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${solicitudVacacionesInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="solicitudVacaciones.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${solicitudVacacionesInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="solicitudVacaciones.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${solicitudVacacionesInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.folioPago}">
				<li class="fieldcontain">
					<span id="folioPago-label" class="property-label"><g:message code="solicitudVacaciones.folioPago.label" default="Folio Pago" /></span>
					
						<span class="property-value" aria-labelledby="folioPago-label"><g:fieldValue bean="${solicitudVacacionesInstance}" field="folioPago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="solicitudVacaciones.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="usuario" action="show" id="${solicitudVacacionesInstance?.user?.id}">${solicitudVacacionesInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.recibeUser}">
				<li class="fieldcontain">
					<span id="recibeUser-label" class="property-label"><g:message code="solicitudVacaciones.recibeUser.label" default="Recibe User" /></span>
					
						<span class="property-value" aria-labelledby="recibeUser-label"><g:link controller="usuario" action="show" id="${solicitudVacacionesInstance?.recibeUser?.id}">${solicitudVacacionesInstance?.recibeUser?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.rhUser}">
				<li class="fieldcontain">
					<span id="rhUser-label" class="property-label"><g:message code="solicitudVacaciones.rhUser.label" default="Rh User" /></span>
					
						<span class="property-value" aria-labelledby="rhUser-label"><g:link controller="usuario" action="show" id="${solicitudVacacionesInstance?.rhUser?.id}">${solicitudVacacionesInstance?.rhUser?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="solicitudVacaciones.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${solicitudVacacionesInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.jefeUserId}">
				<li class="fieldcontain">
					<span id="jefeUserId-label" class="property-label"><g:message code="solicitudVacaciones.jefeUserId.label" default="Jefe User Id" /></span>
					
						<span class="property-value" aria-labelledby="jefeUserId-label"><g:link controller="usuario" action="show" id="${solicitudVacacionesInstance?.jefeUserId?.id}">${solicitudVacacionesInstance?.jefeUserId?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.furlough}">
				<li class="fieldcontain">
					<span id="furlough-label" class="property-label"><g:message code="solicitudVacaciones.furlough.label" default="Furlough" /></span>
					
						<span class="property-value" aria-labelledby="furlough-label"><g:fieldValue bean="${solicitudVacacionesInstance}" field="furlough"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.nacional}">
				<li class="fieldcontain">
					<span id="nacional-label" class="property-label"><g:message code="solicitudVacaciones.nacional.label" default="Nacional" /></span>
					
						<span class="property-value" aria-labelledby="nacional-label"><g:formatBoolean boolean="${solicitudVacacionesInstance?.nacional}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.primaVacacional}">
				<li class="fieldcontain">
					<span id="primaVacacional-label" class="property-label"><g:message code="solicitudVacaciones.primaVacacional.label" default="Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="primaVacacional-label"><g:formatBoolean boolean="${solicitudVacacionesInstance?.primaVacacional}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacacionesInstance?.visitaPadres}">
				<li class="fieldcontain">
					<span id="visitaPadres-label" class="property-label"><g:message code="solicitudVacaciones.visitaPadres.label" default="Visita Padres" /></span>
					
						<span class="property-value" aria-labelledby="visitaPadres-label"><g:formatBoolean boolean="${solicitudVacacionesInstance?.visitaPadres}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudVacacionesInstance?.id}" />
					<g:link class="edit" action="edit" id="${solicitudVacacionesInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
