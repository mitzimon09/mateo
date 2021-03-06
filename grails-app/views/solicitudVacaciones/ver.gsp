
<%@ page import="mx.edu.um.rh.SolicitudVacaciones", import="mx.edu.um.Constantes" %>
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
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="rango" action="rango"><g:message code="Encontrar por rango de fecha" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudVacaciones" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${solicitudVacaciones?.nacional != true}">
				<div class="errors"><g:message code="${solicitudVacaciones?.empleado}, recuerde que debe comprar un seguro de accidentes." /></div>
			</g:if>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudVacaciones">
			
				<g:if test="${solicitudVacaciones?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="solicitudVacaciones.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="ver" id="${solicitudVacaciones?.empleado?.id}">${solicitudVacaciones?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
				
			
				<g:if test="${solicitudVacaciones?.fechaInicial}">
				<li class="fieldcontain">
					<span id="fechaInicial-label" class="property-label"><g:message code="solicitudVacaciones.fechaInicial.label" default="Fecha Inicial" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicial-label"><g:formatDate date="${solicitudVacaciones?.fechaInicial}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.fechaFinal}">
				<li class="fieldcontain">
					<span id="fechaFinal-label" class="property-label"><g:message code="solicitudVacaciones.fechaFinal.label" default="Fecha Final" /></span>
					
						<span class="property-value" aria-labelledby="fechaFinal-label"><g:formatDate date="${solicitudVacaciones?.fechaFinal}" /></span>
					
				</li>
				</g:if>
				
				<g:if test="${solicitudVacaciones?.diasVacaciones}">
				<li class="fieldcontain">
					<span id="diasVacaciones-label" class="property-label"><g:message code="solicitudVacaciones.diasVacaciones.label" default="Dias Vacaciones" /></span>
					
						<span class="property-value" aria-labelledby="diasVacaciones-label"><g:fieldValue bean="${solicitudVacaciones}" field="diasVacaciones"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${solicitudVacaciones?.telContacto}">
				<li class="fieldcontain">
					<span id="telContacto-label" class="property-label"><g:message code="solicitudVacaciones.telContacto.label" default="Tel Contacto" /></span>
					
						<span class="property-value" aria-labelledby="telContacto-label"><g:fieldValue bean="${solicitudVacaciones}" field="telContacto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="solicitudVacaciones.email.label" default="Email" /></span>
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${solicitudVacaciones}" field="email"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${solicitudVacaciones?.vacaciones}">
				<li class="fieldcontain">
					<span id="vacaciones-label" class="property-label"><g:message code="solicitudVacaciones.vacaciones.label" default="Vacaciones" /></span>
					
						<span class="property-value" aria-labelledby="vacaciones-label"><g:link controller="vacaciones" action="show" id="${solicitudVacaciones?.vacaciones?.id}">${solicitudVacaciones?.vacaciones?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			
				<g:if test="${solicitudVacaciones?.usuarioRecibe}">
				<li class="fieldcontain">
					<span id="usuarioRecibe-label" class="property-label"><g:message code="solicitudVacaciones.usuarioRecibe.label" default="Usuario Recibe" /></span>
					
						<span class="property-value" aria-labelledby="usuarioRecibe-label"><g:link controller="usuario" action="ver" id="${solicitudVacaciones?.usuarioRecibe?.id}">${solicitudVacaciones?.usuarioRecibe?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.fechaRecibe}">
				<li class="fieldcontain">
					<span id="fechaRecibe-label" class="property-label"><g:message code="solicitudVacaciones.fechaRecibe.label" default="Fecha Recibe" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibe-label"><g:formatDate date="${solicitudVacaciones?.fechaRecibe}" /></span>
					
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
				<g:if test="${solicitudVacaciones?.usuarioAutoriza}">
				<li class="fieldcontain">
					<span id="usuarioAutoriza-label" class="property-label"><g:message code="solicitudVacaciones.usuarioAutoriza.label" default="Usuario Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="usuarioAutoriza-label"><g:link controller="usuario" action="ver" id="${solicitudVacaciones?.usuarioAutoriza?.id}">${solicitudVacaciones?.usuarioAutoriza?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.fechaAutoriza}">
				<li class="fieldcontain">
					<span id="fechaAutoriza-label" class="property-label"><g:message code="solicitudVacaciones.fechaAutoriza.label" default="Fecha Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="fechaAutoriza-label"><g:formatDate date="${solicitudVacaciones?.fechaAutoriza}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.solicitudSalida}">
				<li class="fieldcontain">
					<span id="solicitudSalida-label" class="property-label"><g:message code="solicitudVacaciones.solicitudSalida.label" default="Solicitud Salida" /></span>
					
						<span class="property-value" aria-labelledby="solicitudSalida-label"><g:link controller="solicitudSalida" action="ver" id="${solicitudVacaciones?.solicitudSalida?.id}">${solicitudVacaciones?.solicitudSalida?.encodeAsHTML()}</g:link></span>
					
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
					
						<span class="property-value" aria-labelledby="jefeCCosto-label"><g:link controller="jefeCCosto" action="ver" id="${solicitudVacaciones?.jefeCCosto?.id}">${solicitudVacaciones?.jefeCCosto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudVacaciones?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="solicitudVacaciones.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${solicitudVacaciones}" field="folio"/></span>
					
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
			
				<li class="fieldcontain">
					<span id="nacional-label" class="property-label"><g:message code="solicitudVacaciones.nacional.label" default="Nacional" /></span>
					
						<span class="property-value" aria-labelledby="nacional-label"><g:formatBoolean boolean="${solicitudVacaciones?.nacional}" /></span>
					
				</li>
				
				<li class="fieldcontain">
					<span id="userPrimaVacacional-label" class="property-label"><g:message code="solicitudVacaciones.userPrimaVacacional.label" default="Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="userPrimaVacacional-label"><g:fieldValue bean="${solicitudVacaciones}" field="userPrimaVacacional"/></span>
					
				</li>
				<g:if test="${solicitudVacaciones?.fechaPrimaVacacional}">
				<li class="fieldcontain">
					<span id="fechaPrimaVacacional-label" class="property-label"><g:message code="solicitudVacaciones.fechaPrimaVacacional.label" default="Fecha Prima Vacacional" /></span>
					
						<span class="property-value" aria-labelledby="fechaPrimaVacacional-label"><g:formatDate date="${solicitudVacaciones?.fechaPrimaVacacional}" /></span>
					
				</li>
				<g:if test="${solicitudVacaciones?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="solicitudVacaciones.dateCreated.label" default="Fecha de creacion" /></span>
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${solicitudVacaciones?.dateCreated}" /></span>
					
				</li>
				</g:if>
				</g:if>
				
				<g:each var="book" in="${books}">
     <p>Title: ${book.title}</p>
     <p>Author: ${book.author}</p>
</g:each>
				
				<g:if test="${solicitudVacaciones?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="solicitudVacaciones.observaciones.label" default="Observaciones" /></span>
					<ul>
					<g:each var="observacion" in="${solicitudVacaciones?.observaciones}">
							<li><span class="property-value" aria-labelledby="observaciones-label"><p>${observacion.observaciones}</p></span></li>
					</g:each>
					</ul>
				</li>
				</g:if>
				
			<g:if test="${(permisos >= 2)}">
				<g:if test="${solicitudVacaciones?.fechaRecibeJefe}">
				<li class="fieldcontain">
					<span id="fechaRecibeJefe-label" class="property-label"><g:message code="solicitudVacaciones.fechaRecibeJefe.label" default="Fecha Recibe Jefe" /></span>
					
						<span class="property-value" aria-labelledby="fechaRecibeJefe-label"><g:formatDate date="${solicitudVacaciones?.fechaRecibeJefe}" /></span>
					
				</li>
				</g:if>
			</g:if>
				
				<g:if test="${(permisos >= 3)}">
				<g:if test="${solicitudVacaciones?.usuarioAutoriza}">
				<li class="fieldcontain">
					<span id="usuarioAutoriza-label" class="property-label"><g:message code="solicitudVacaciones.usuarioAutoriza.label" default="Usuario Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="usuarioAutoriza-label"><g:link controller="usuario" action="show" id="${solicitudVacaciones?.usuarioAutoriza?.id}">${solicitudVacaciones?.usuarioAutoriza?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
				
				<g:if test="${solicitudVacaciones?.fechaAutoriza}">
				<li class="fieldcontain">
					<span id="fechaAutoriza-label" class="property-label"><g:message code="solicitudVacaciones.fechaAutoriza.label" default="Fecha Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="fechaAutoriza-label"><g:formatDate date="${solicitudVacaciones?.fechaAutoriza}" /></span>
					
				</li>
				</g:if>
			</g:if>
			
			<g:if test="${solicitudPermiso?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="solicitudPermiso.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${solicitudPermiso}" field="status"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudVacaciones?.id}" />
					<g:link class="edit" action="edita" id="${solicitudVacaciones?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					<g:if test="${(permisos == 1 || permisos == 5)}">
						<g:if test="${solicitudVacaciones?.status.equals(Constantes.STATUS_CREADO) || solicitudVacaciones?.status.equals(Constantes.STATUS_SUSPENDIDO)}">
				  			<g:actionSubmit class="enviar" action="enviar" value="${message(code: 'default.button.enviar.label', default: 'Enviar')}" />
						</g:if>
					</g:if>
					<g:if test="${(permisos == 2 || permisos == 5)}">
						<g:if test="${solicitudVacaciones?.status.equals(Constantes.STATUS_ENVIADO)}">
				  			<g:actionSubmit class="aprobar" action="aprobar" value="${message(code: 'default.button.aprobar.label', default: 'Aprobar')}" />
  							<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
  							<g:actionSubmit class="suspender" action="suspender" value="${message(code: 'default.button.suspender.label', default: 'Suspender')}" />
						</g:if>
					</g:if>
					<g:if test="${(permisos == 3 || permisos == 5)}">
						<g:if test="${solicitudVacaciones?.status.equals(Constantes.STATUS_APROBADO)}">
				  			<g:actionSubmit class="revisar" action="revisar" value="${message(code: 'default.button.revisar.label', default: 'Aprobar')}" />
  							<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
						</g:if>
						<g:if test="${solicitudVacaciones?.status.equals(Constantes.STATUS_REVISADO)}">
							<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
							<g:actionSubmit class="autorizar" action="autorizar" value="${message(code: 'default.button.autorizar.label', default: 'Autorizar')}" />
						</g:if>
					</g:if>
					<g:if test="${(permisos == 4 || permisos == 0)}">
						<g:actionSubmit class="cancelar" action="cancelar" value="${message(code: 'default.button.cancelar.label', default: 'Cancelar')}" />
						<g:if test="${!solicitudVacaciones?.status.equals(Constantes.STATUS_REVISADO)}">
				  			<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
							<g:actionSubmit class="autorizar" action="autorizar" value="${message(code: 'default.button.autorizar.label', default: 'Autorizar')}" />
						</g:if>
					</g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

