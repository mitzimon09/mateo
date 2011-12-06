
<%@ page import="mx.edu.um.rh.Seccion" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'seccion.label', default: 'Puesto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-seccion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="home" controller="RH"><g:message code="rh.list.label" default="RH" /></g:link></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-seccion" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list seccion">
			
				<g:if test="${seccion?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="seccion.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${seccion}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${seccion?.maximo}">
				<li class="fieldcontain">
					<span id="maximo-label" class="property-label"><g:message code="seccion.maximo.label" default="Maximo" /></span>
					
						<span class="property-value" aria-labelledby="maximo-label"><g:fieldValue bean="${seccion}" field="maximo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${seccion?.minimo}">
				<li class="fieldcontain">
					<span id="minimo-label" class="property-label"><g:message code="seccion.minimo.label" default="Minimo" /></span>
					
						<span class="property-value" aria-labelledby="minimo-label"><g:fieldValue bean="${seccion}" field="minimo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${seccion?.rango_academico}">
				<li class="fieldcontain">
					<span id="rango_academico-label" class="property-label"><g:message code="seccion.rango_academico.label" default="Rangoacademico" /></span>
					
						<span class="property-value" aria-labelledby="rango_academico-label"><g:fieldValue bean="${seccion}" field="rango_academico"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${seccion?.categoria}">
				<li class="fieldcontain">
					<span id="categoria-label" class="property-label"><g:message code="seccion.categoria.label" default="Categoria" /></span>
					
						<span class="property-value" aria-labelledby="categoria-label"><g:link controller="categoria" action="show" id="${seccion?.categoria?.id}">${seccion?.categoria?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${seccion?.id}" />
					<g:link class="edit" action="edita" id="${seccion?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
