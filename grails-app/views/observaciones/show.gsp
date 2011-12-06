
<%@ page import="mx.edu.um.rh.Observaciones" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'observaciones.label', default: 'Observaciones')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-observaciones" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-observaciones" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list observaciones">
			
				<g:if test="${observaciones?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="observaciones.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${observaciones}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${observaciones?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="observaciones.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${observaciones?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${observaciones?.solicitudRH}">
				<li class="fieldcontain">
					<span id="solicitudRH-label" class="property-label"><g:message code="observaciones.solicitudRH.label" default="Solicitud RH" /></span>
					
						<span class="property-value" aria-labelledby="solicitudRH-label"><g:link controller="solicitudRH" action="show" id="${observaciones?.solicitudRH?.id}">${observaciones?.solicitudRH?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${observaciones?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="observaciones.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:link controller="usuario" action="show" id="${observaciones?.usuario?.id}">${observaciones?.usuario?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${observaciones?.id}" />
					<g:link class="edit" action="edit" id="${observaciones?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
