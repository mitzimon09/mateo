
<%@ page import="mx.edu.um.rh.Grupo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'grupo.label', default: 'Grupo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-grupo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="home" controller="RH"><g:message code="rh.list.label" default="RH" /></g:link></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-grupo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list grupo">
			
				<g:if test="${grupo?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="grupo.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${grupo}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${grupo?.maximo}">
				<li class="fieldcontain">
					<span id="maximo-label" class="property-label"><g:message code="grupo.maximo.label" default="Maximo" /></span>
					
						<span class="property-value" aria-labelledby="maximo-label"><g:fieldValue bean="${grupo}" field="maximo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${grupo?.minimo}">
				<li class="fieldcontain">
					<span id="minimo-label" class="property-label"><g:message code="grupo.minimo.label" default="Minimo" /></span>
					
						<span class="property-value" aria-labelledby="minimo-label"><g:fieldValue bean="${grupo}" field="minimo"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${grupo?.id}" />
					<g:link class="edit" action="edita" id="${grupo?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
