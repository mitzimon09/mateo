
<%@ page import="mx.edu.um.rh.SolicitudSalida" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudSalida.label', default: 'SolicitudSalida')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudSalida" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudSalida" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="status" title="${message(code: 'solicitudSalida.status.label', default: 'Status')}" />
					
						<g:sortableColumn property="observaciones" title="${message(code: 'solicitudSalida.observaciones.label', default: 'Observaciones')}" />
					
						<th><g:message code="solicitudSalida.empresa.label" default="Empresa" /></th>
					
						<th><g:message code="solicitudSalida.vacaciones.label" default="Vacaciones" /></th>
					
						<th><g:message code="solicitudSalida.empleado.label" default="Empleado" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'solicitudSalida.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudSalidas}" status="i" var="solicitudSalida">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${solicitudSalida.id}">${fieldValue(bean: solicitudSalida, field: "status")}</g:link></td>
					
						<td>${fieldValue(bean: solicitudSalida, field: "observacionesRh")}</td>
					
						<td>${fieldValue(bean: solicitudSalida, field: "folio")}</td>
					
						<td>${fieldValue(bean: solicitudSalida, field: "destino")}</td>
					
					
						<td><g:formatDate date="${solicitudSalida.fechaAutorizacionRh}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeSolicitudSalidas}" />
			</div>
		</div>
	</body>
</html>
