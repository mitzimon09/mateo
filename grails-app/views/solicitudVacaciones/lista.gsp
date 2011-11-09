
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
				<li><g:link class="create" action="nueva"><g:message code="Nueva Solicitud de Vacaciones" args="[entityName]" /></g:link></li>
				<li><g:link class="list" action="rango"><g:message code="Encontrar por rango de fecha" /></g:link></li>
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
					
						<th><g:message code="solicitudVacaciones.empresa.label" default="Empresa" /></th>
					
						<g:sortableColumn property="fechaCaptura" title="${message(code: 'solicitudVacaciones.fechaCaptura.label', default: 'Fecha Captura')}" />
					
						<g:sortableColumn property="fechaInicial" title="${message(code: 'solicitudVacaciones.fechaInicial.label', default: 'Fecha Inicial')}" />
					
						<g:sortableColumn property="fechaFinal" title="${message(code: 'solicitudVacaciones.fechaFinal.label', default: 'Fecha Final')}" />
					
						<g:sortableColumn property="fechaRecibeJefe" title="${message(code: 'solicitudVacaciones.fechaRecibeJefe.label', default: 'Fecha Recibe Jefe')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudesVacaciones}" status="i" var="solicitudVacaciones">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${solicitudesVacaciones.id}">${fieldValue(bean: solicitudVacaciones, field: "empleado")}</g:link></td>
					
						<td>${fieldValue(bean: solicitudVacaciones, field: "empresa")}</td>
					
						<td><g:formatDate date="${solicitudVacaciones.fechaCaptura}" /></td>
					
						<td><g:formatDate date="${solicitudVacaciones.fechaInicial}" /></td>
					
						<td><g:formatDate date="${solicitudVacaciones.fechaFinal}" /></td>
					
						<td><g:formatDate date="${solicitudVacaciones.fechaRecibeJefe}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeSolicitudesVacaciones}" />
			</div>
		</div>
	</body>
</html>
