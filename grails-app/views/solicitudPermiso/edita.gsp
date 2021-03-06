<%@ page import="mx.edu.um.rh.SolicitudPermiso" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-solicitudPermiso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-solicitudPermiso" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${solicitudPermiso}">
			<ul class="errors" role="alert">
				<g:eachError bean="${solicitudPermiso}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${solicitudPermiso?.id}" />
				<g:hiddenField name="version" value="${solicitudPermiso?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="actualiza" value="${message(code: 'default.button.update.label', default: 'Actualizar')}" />
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Eliminar')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					<g:if test="${(permisos == 1 || permisos == 5)}">
						<g:if test="${solicitudPermiso?.status.equals('CR') || solicitudPermiso?.status.equals('SU')}">
				  			<g:actionSubmit class="enviar" action="enviar" value="${message(code: 'default.button.enviar.label', default: 'Enviar')}" />
						</g:if>
					</g:if>
					<g:if test="${(permisos == 2 || permisos == 5)}">
						<g:if test="${solicitudPermiso?.status.equals('EN')}">
				  			<g:actionSubmit class="aprobar" action="aprobar" value="${message(code: 'default.button.aprobar.label', default: 'Aprobar')}" />
  							<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
  							<g:actionSubmit class="suspender" action="suspender" value="${message(code: 'default.button.suspender.label', default: 'Suspender')}" />
						</g:if>
					</g:if>
					<g:if test="${(permisos == 3 || permisos == 5)}">
						<g:if test="${solicitudPermiso?.status.equals('AP')}">
				  			<g:actionSubmit class="revisar" action="revisar" value="${message(code: 'default.button.revisar.label', default: 'Aprobar')}" />
  							<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
						</g:if>
						<g:if test="${solicitudPermiso?.status.equals('RV')}">
							<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
							<g:actionSubmit class="autorizar" action="autorizar" value="${message(code: 'default.button.autorizar.label', default: 'Autorizar')}" />
						</g:if>
					</g:if>
					<g:if test="${(permisos == 4 || permisos == 0)}">
						<g:actionSubmit class="cancelar" action="cancelar" value="${message(code: 'default.button.cancelar.label', default: 'Cancelar')}" />
						<g:if test="${!solicitudPermiso?.status.equals('RV')}">
				  			<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
							<g:actionSubmit class="autorizar" action="autorizar" value="${message(code: 'default.button.autorizar.label', default: 'Autorizar')}" />
						</g:if>
					</g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
