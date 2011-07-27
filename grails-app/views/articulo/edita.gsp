<%@ page import="general.Articulo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'articulo.label', default: 'Articulo')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-articulo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-articulo" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${articulo}">
			<ul class="errors" role="alert">
				<g:eachError bean="${articulo}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${articulo?.id}" />
				<g:hiddenField name="version" value="${articulo?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="actualiza" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					<g:if test="${permisos == 1 || permisos == 4}">
						<g:if test="${articulo.status.equals('AGREGADO')}">
  					  		<g:actionSubmit class="comprar" action="comprar" value="${message(code: 'default.button.comprar.label', default: 'Comprar')}" />
						</g:if>
					</g:if>
					<g:if test="${permisos == 3 || permisos == 4}">
						<g:if test="${articulo.status.equals('COMPRADO')}">
  					  		<g:actionSubmit class="entregar" action="entregar" value="${message(code: 'default.button.entregar.label', default: 'Entregado')}" />
						</g:if>
					</g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
