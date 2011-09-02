
<%@ page import="general.TipoCliente" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoCliente.label', default: 'TipoCliente')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tipoCliente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tipoCliente" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipoCliente">
			
				<g:if test="${tipoCliente?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="tipoCliente.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${tipoCliente}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoCliente?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="tipoCliente.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${tipoCliente}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoCliente?.margenUtilidad}">
				<li class="fieldcontain">
					<span id="margenUtilidad-label" class="property-label"><g:message code="tipoCliente.margenUtilidad.label" default="Margen Utilidad" /></span>
					
						<span class="property-value" aria-labelledby="margenUtilidad-label"><g:fieldValue bean="${tipoCliente}" field="margenUtilidad"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoCliente?.base}">
				<li class="fieldcontain">
					<span id="base-label" class="property-label"><g:message code="tipoCliente.base.label" default="Base" /></span>
					
						<span class="property-value" aria-labelledby="base-label"><g:formatBoolean boolean="${tipoCliente?.base}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoCliente?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="tipoCliente.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="ver" id="${tipoCliente?.empresa?.id}">${tipoCliente?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tipoCliente?.id}" />
					<g:link class="edit" action="edita" id="${tipoCliente?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
