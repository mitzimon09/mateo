
<%@ page import="mx.edu.um.rh.Evento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evento.label', default: 'Evento')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-evento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-evento" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<h1>Reporte de Evento ${evento.nombre}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="nombre" title="${message(code: 'evento.nombre.label', default: 'Nombre')}" />
						<g:sortableColumn property="status" title="${message(code: 'evento.status.label', default: 'Status')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${empleadoEventos}" status="i" var="empleadoEvento">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: empleadoEvento, field: "empleado")}</td>
						
						<td>${fieldValue(bean: empleadoEvento, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>
