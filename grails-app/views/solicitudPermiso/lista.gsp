
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
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="rango" action="rango"><g:message code="Encontrar por rango de fecha" /></g:link></li>
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
					
						<th><g:message code="solicitudPermiso.id.label" default="Solicitud" /></th>
						
						<th><g:message code="solicitudPermiso.id.label" default="Folio" /></th>
						
						<th><g:message code="solicitudPermiso.empleado.label" default="Empleado" /></th>
					
						<g:sortableColumn property="fechaInicial" title="${message(code: 'solicitudPermiso.fechaInicial.label', default: 'Fecha Inicial')}" />
					
						<g:sortableColumn property="fechaFinal" title="${message(code: 'solicitudPermiso.fechaFinal.label', default: 'Fecha Final')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudesPermiso}" status="i" var="solicitudPermiso">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${solicitudPermiso.id}">${fieldValue(bean: solicitudPermiso, field: "id")}</g:link></td>
						
						<td><g:link action="ver" id="${solicitudPermiso.id}">${fieldValue(bean: solicitudPermiso, field: "folio")}</g:link></td>
					
						<td><g:link controller="empleado" action="ver" id="${solicitudPermiso.empleado.id}">${fieldValue(bean: solicitudPermiso, field: "empleado")}</g:link></td>
					
						<td><g:formatDate date="${solicitudPermiso.fechaInicial}" /></td>
					
						<td><g:formatDate date="${solicitudPermiso.fechaFinal}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalSolicitudesPermiso}" />
			</div>
		</div>
	</body>
</html>
