
<%@ page import="mx.edu.um.rh.SolicitudPermiso" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudPermiso.label', default: 'SolicitudPermiso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudPermiso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudPermiso" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="solicitudPermiso.empleado.label" default="Empleado" /></th>
					
						<g:sortableColumn property="fechaInicial" title="${message(code: 'solicitudPermiso.fechaInicial.label', default: 'Fecha Inicial')}" />
					
						<g:sortableColumn property="fechaFinal" title="${message(code: 'solicitudPermiso.fechaFinal.label', default: 'Fecha Final')}" />
					
						<th><g:message code="solicitudPermiso.usuarioCrea.label" default="Usuario Crea" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'solicitudPermiso.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="solicitudPermiso.usuarioRecibe.label" default="Usuario Recibe" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudPermisoInstanceList}" status="i" var="solicitudPermisoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudPermisoInstance.id}">${fieldValue(bean: solicitudPermisoInstance, field: "empleado")}</g:link></td>
					
						<td><g:formatDate date="${solicitudPermisoInstance.fechaInicial}" /></td>
					
						<td><g:formatDate date="${solicitudPermisoInstance.fechaFinal}" /></td>
					
						<td>${fieldValue(bean: solicitudPermisoInstance, field: "usuarioCrea")}</td>
					
						<td><g:formatDate date="${solicitudPermisoInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: solicitudPermisoInstance, field: "usuarioRecibe")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudPermisoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
