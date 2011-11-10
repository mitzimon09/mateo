
<%@ page import="mx.edu.um.rh.SolicitudVacaciones" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudVacaciones" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudVacaciones" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="solicitudVacaciones.empleado.label" default="Empleado" /></th>
					
						<g:sortableColumn property="fechaInicial" title="${message(code: 'solicitudVacaciones.fechaInicial.label', default: 'Fecha Inicial')}" />
					
						<g:sortableColumn property="fechaFinal" title="${message(code: 'solicitudVacaciones.fechaFinal.label', default: 'Fecha Final')}" />
					
						<th><g:message code="solicitudVacaciones.usuarioCrea.label" default="Usuario Crea" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'solicitudVacaciones.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="solicitudVacaciones.usuarioRecibe.label" default="Usuario Recibe" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudVacacionesList}" status="i" var="solicitudVacaciones">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudVacaciones.id}">${fieldValue(bean: solicitudVacaciones, field: "empleado")}</g:link></td>
					
						<td><g:formatDate date="${solicitudVacaciones.fechaInicial}" /></td>
					
						<td><g:formatDate date="${solicitudVacaciones.fechaFinal}" /></td>
					
						<td>${fieldValue(bean: solicitudVacaciones, field: "usuarioCrea")}</td>
					
						<td><g:formatDate date="${solicitudVacaciones.dateCreated}" /></td>
					
						<td>${fieldValue(bean: solicitudVacaciones, field: "usuarioRecibe")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudVacacionesTotal}" />
			</div>
		</div>
	</body>
</html>
