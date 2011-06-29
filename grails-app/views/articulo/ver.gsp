
<%@ page import="general.Articulo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'articulo.label', default: 'Articulo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-articulo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-articulo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list articulo">
			
				<g:if test="${articulo?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="articulo.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${articulo}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${articulo?.cantidad}">
				<li class="fieldcontain">
					<span id="cantidad-label" class="property-label"><g:message code="articulo.cantidad.label" default="Cantidad" /></span>
					
						<span class="property-value" aria-labelledby="cantidad-label"><g:fieldValue bean="${articulo}" field="cantidad"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${articulo?.precioUnitario}">
				<li class="fieldcontain">
					<span id="precioUnitario-label" class="property-label"><g:message code="articulo.precioUnitario.label" default="Precio Unitario" /></span>
					
						<span class="property-value" aria-labelledby="precioUnitario-label"><g:fieldValue bean="${articulo}" field="precioUnitario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${articulo?.total}">
				<li class="fieldcontain">
					<span id="total-label" class="property-label"><g:message code="articulo.total.label" default="Total" /></span>
					
						<span class="property-value" aria-labelledby="total-label"><g:fieldValue bean="${articulo}" field="total"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${articulo?.compra}">
				<li class="fieldcontain">
					<span id="compra-label" class="property-label"><g:message code="articulo.compra.label" default="Compra" /></span>
					
						<span class="property-value" aria-labelledby="compra-label"><g:link controller="compra" action="ver" id="${articulo?.compra?.id}">${articulo?.compra?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${articulo?.id}" />
					<g:link class="edit" action="edita" id="${articulo?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
