
<%@ page import="mx.edu.um.rh.Vacaciones" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vacaciones.label', default: 'Vacaciones')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-vacaciones" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-vacaciones" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'vacaciones.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="dias" title="${message(code: 'vacaciones.dias.label', default: 'Dias')}" />
					
						<th><g:message code="vacaciones.usuario.label" default="Usuario" /></th>
					
						<th><g:message code="vacaciones.empleado.label" default="Empleado" /></th>
					
						<g:sortableColumn property="status" title="${message(code: 'vacaciones.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${vacacionesList}" status="i" var="vacaciones">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${vacaciones.id}">${fieldValue(bean: vacaciones, field: "descripcion")}</g:link></td>
					
						<td>${fieldValue(bean: vacaciones, field: "dias")}</td>
					
						<td>${fieldValue(bean: vacaciones, field: "usuario")}</td>
					
						<td>${fieldValue(bean: vacaciones, field: "empleado")}</td>
					
						<td>${fieldValue(bean: vacaciones, field: "status")}</td>
					
						<td>${fieldValue(bean: vacaciones, field: "solicitudVacaciones")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeVacaciones}" />
			</div>
		</div>
	</body>
</html>
