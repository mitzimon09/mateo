
<%@ page import="mx.edu.um.rh.Puesto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'puesto.label', default: 'Puesto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-puesto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="home" controller="RH"><g:message code="rh.list.label" default="RH" /></g:link></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-puesto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list puesto">
			
				<g:if test="${puesto?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="puesto.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${puesto}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${puesto?.maximo}">
				<li class="fieldcontain">
					<span id="maximo-label" class="property-label"><g:message code="puesto.maximo.label" default="Maximo" /></span>
					
						<span class="property-value" aria-labelledby="maximo-label"><g:fieldValue bean="${puesto}" field="maximo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${puesto?.minimo}">
				<li class="fieldcontain">
					<span id="minimo-label" class="property-label"><g:message code="puesto.minimo.label" default="Minimo" /></span>
					
						<span class="property-value" aria-labelledby="minimo-label"><g:fieldValue bean="${puesto}" field="minimo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${puesto?.seccion}">
				<li class="fieldcontain">
					<span id="seccion-label" class="property-label"><g:message code="puesto.seccion.label" default="Seccion" /></span>
					
						<span class="property-value" aria-labelledby="seccion-label"><g:link controller="seccion" action="show" id="${puesto?.seccion?.id}">${puesto?.seccion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${puesto?.id}" />
					<g:link class="edit" action="edita" id="${puesto?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
