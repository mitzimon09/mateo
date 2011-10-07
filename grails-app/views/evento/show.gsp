
<%@ page import="mx.edu.um.rh.Evento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evento.label', default: 'Evento')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-evento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-evento" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list evento">
			
				<g:if test="${eventoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="evento.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${eventoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="evento.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${eventoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventoInstance?.prorroga}">
				<li class="fieldcontain">
					<span id="prorroga-label" class="property-label"><g:message code="evento.prorroga.label" default="Prorroga" /></span>
					
						<span class="property-value" aria-labelledby="prorroga-label"><g:fieldValue bean="${eventoInstance}" field="prorroga"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventoInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="evento.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${eventoInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventoInstance?.hora_final}">
				<li class="fieldcontain">
					<span id="hora_final-label" class="property-label"><g:message code="evento.hora_final.label" default="Horafinal" /></span>
					
						<span class="property-value" aria-labelledby="hora_final-label"><g:formatDate date="${eventoInstance?.hora_final}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventoInstance?.hora_inicio}">
				<li class="fieldcontain">
					<span id="hora_inicio-label" class="property-label"><g:message code="evento.hora_inicio.label" default="Horainicio" /></span>
					
						<span class="property-value" aria-labelledby="hora_inicio-label"><g:formatDate date="${eventoInstance?.hora_inicio}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${eventoInstance?.id}" />
					<g:link class="edit" action="edit" id="${eventoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
