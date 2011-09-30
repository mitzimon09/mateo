<%@ page import="mx.edu.um.rh.EmpleadoPuesto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empleadoPuesto.label', default: 'EmpleadoPuesto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-empleadoPuesto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-empleadoPuesto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="status" title="${message(code: 'empleadoPuesto.status.label', default: 'Status')}" />
						<g:sortableColumn property="cCosto" title="${message(code: 'empleadoPuesto.cCosto.label', default: 'CC osto')}" />
						<g:sortableColumn property="ejercicio" title="${message(code: 'empleadoPuesto.ejercicio.label', default: 'Ejercicio')}" />
						<th><g:message code="empleadoPuesto.empleado.label" default="Empleado" /></th>
						<th><g:message code="empleadoPuesto.puesto.label" default="Puesto" /></th>
						<g:sortableColumn property="turno" title="${message(code: 'empleadoPuesto.turno.label', default: 'Turno')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${empleadoPuestoList}" status="i" var="empleadoPuesto">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="ver" id="${empleadoPuesto.id}">${fieldValue(bean: empleadoPuesto, field: "status")}</g:link></td>
						<td>${fieldValue(bean: empleadoPuesto, field: "cCosto")}</td>
						<td>${fieldValue(bean: empleadoPuesto, field: "ejercicio")}</td>
						<td>${fieldValue(bean: empleadoPuesto, field: "empleado")}</td>
						<td>${fieldValue(bean: empleadoPuesto, field: "puesto")}</td>
						<td>${fieldValue(bean: empleadoPuesto, field: "turno")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeEmpleadoPuestos}" />
			</div>
		</div>
	</body>
</html>
