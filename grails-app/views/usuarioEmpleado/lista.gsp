
<%@ page import="general.UsuarioEmpleado" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usuarioEmpleado.label', default: 'UsuarioEmpleado')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-usuarioEmpleado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-usuarioEmpleado" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="usuarioEmpleado.usuario.label" default="Usuario" /></th>
					
						<th><g:message code="usuarioEmpleado.empleado.label" default="Empleado" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${usuarioEmpleados}" status="i" var="usuarioEmpleado">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${usuarioEmpleado.id}">${fieldValue(bean: usuarioEmpleado, field: "usuario")}</g:link></td>
					
						<td>${fieldValue(bean: usuarioEmpleado, field: "empleado")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeUsuarioEmpleados}" />
			</div>
		</div>
	</body>
</html>
