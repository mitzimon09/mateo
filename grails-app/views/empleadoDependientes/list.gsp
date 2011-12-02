
<%@ page import="mx.edu.um.rh.EmpleadoDependientes" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empleadoDependientes.label', default: 'EmpleadoDependientes')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-empleadoDependientes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-empleadoDependientes" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'empleadoDependientes.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="estudios" title="${message(code: 'empleadoDependientes.estudios.label', default: 'Estudios')}" />
					
						<g:sortableColumn property="grado" title="${message(code: 'empleadoDependientes.grado.label', default: 'Grado')}" />
					
						<g:sortableColumn property="matricula" title="${message(code: 'empleadoDependientes.matricula.label', default: 'Matricula')}" />
					
						<g:sortableColumn property="bday" title="${message(code: 'empleadoDependientes.bday.label', default: 'Bday')}" />
					
						<th><g:message code="empleadoDependientes.colegio.label" default="Colegio" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${empleadoDependientesList}" status="i" var="empleadoDependientes">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${empleadoDependientes.id}">${fieldValue(bean: empleadoDependientes, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: empleadoDependientes, field: "estudios")}</td>
					
						<td>${fieldValue(bean: empleadoDependientes, field: "grado")}</td>
					
						<td>${fieldValue(bean: empleadoDependientes, field: "matricula")}</td>
					
						<td><g:formatDate date="${empleadoDependientes.bday}" /></td>
					
						<td>${fieldValue(bean: empleadoDependientes, field: "colegio")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${empleadoDependientesTotal}" />
			</div>
		</div>
	</body>
</html>
