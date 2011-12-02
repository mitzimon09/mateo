
<%@ page import="mx.edu.um.rh.EmpleadoPerded" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empleadoPerded.label', default: 'EmpleadoPerded')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-empleadoPerded" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-empleadoPerded" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list empleadoPerded">
			
				<g:if test="${empleadoPerded?.tipoImporte}">
				<li class="fieldcontain">
					<span id="tipoImporte-label" class="property-label"><g:message code="empleadoPerded.tipoImporte.label" default="Tipo Importe" /></span>
					
						<span class="property-value" aria-labelledby="tipoImporte-label"><g:fieldValue bean="${empleadoPerded}" field="tipoImporte"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoPerded?.atributos}">
				<li class="fieldcontain">
					<span id="atributos-label" class="property-label"><g:message code="empleadoPerded.atributos.label" default="Atributos" /></span>
					
						<span class="property-value" aria-labelledby="atributos-label"><g:fieldValue bean="${empleadoPerded}" field="atributos"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoPerded?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="empleadoPerded.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="show" id="${empleadoPerded?.empleado?.id}">${empleadoPerded?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoPerded?.importe}">
				<li class="fieldcontain">
					<span id="importe-label" class="property-label"><g:message code="empleadoPerded.importe.label" default="Importe" /></span>
					
						<span class="property-value" aria-labelledby="importe-label"><g:fieldValue bean="${empleadoPerded}" field="importe"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoPerded?.isEditableByNOM}">
				<li class="fieldcontain">
					<span id="isEditableByNOM-label" class="property-label"><g:message code="empleadoPerded.isEditableByNOM.label" default="Is Editable By NOM" /></span>
					
						<span class="property-value" aria-labelledby="isEditableByNOM-label"><g:formatBoolean boolean="${empleadoPerded?.isEditableByNOM}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoPerded?.otorgado}">
				<li class="fieldcontain">
					<span id="otorgado-label" class="property-label"><g:message code="empleadoPerded.otorgado.label" default="Otorgado" /></span>
					
						<span class="property-value" aria-labelledby="otorgado-label"><g:formatBoolean boolean="${empleadoPerded?.otorgado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoPerded?.perded}">
				<li class="fieldcontain">
					<span id="perded-label" class="property-label"><g:message code="empleadoPerded.perded.label" default="Perded" /></span>
					
						<span class="property-value" aria-labelledby="perded-label"><g:link controller="perDed" action="show" id="${empleadoPerded?.perded?.id}">${empleadoPerded?.perded?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${empleadoPerded?.id}" />
					<g:link class="edit" action="edit" id="${empleadoPerded?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
