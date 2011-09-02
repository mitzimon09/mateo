
<%@ page import="general.Documento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-documento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-documento" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list documento">
			
				<g:if test="${documentoInstance?.concepto}">
				<li class="fieldcontain">
					<span id="concepto-label" class="property-label"><g:message code="documento.concepto.label" default="Concepto" /></span>
					
						<span class="property-value" aria-labelledby="concepto-label"><g:fieldValue bean="${documentoInstance}" field="concepto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="documento.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${documentoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.naturaleza}">
				<li class="fieldcontain">
					<span id="naturaleza-label" class="property-label"><g:message code="documento.naturaleza.label" default="Naturaleza" /></span>
					
						<span class="property-value" aria-labelledby="naturaleza-label"><g:fieldValue bean="${documentoInstance}" field="naturaleza"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="documento.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${documentoInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="documento.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${documentoInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.cheque}">
				<li class="fieldcontain">
					<span id="cheque-label" class="property-label"><g:message code="documento.cheque.label" default="Cheque" /></span>
					
						<span class="property-value" aria-labelledby="cheque-label"><g:fieldValue bean="${documentoInstance}" field="cheque"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="documento.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${documentoInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.importe}">
				<li class="fieldcontain">
					<span id="importe-label" class="property-label"><g:message code="documento.importe.label" default="Importe" /></span>
					
						<span class="property-value" aria-labelledby="importe-label"><g:fieldValue bean="${documentoInstance}" field="importe"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.iva}">
				<li class="fieldcontain">
					<span id="iva-label" class="property-label"><g:message code="documento.iva.label" default="Iva" /></span>
					
						<span class="property-value" aria-labelledby="iva-label"><g:fieldValue bean="${documentoInstance}" field="iva"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="documento.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:link controller="usuario" action="show" id="${documentoInstance?.usuario?.id}">${documentoInstance?.usuario?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${documentoInstance?.id}" />
					<g:link class="edit" action="edit" id="${documentoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
