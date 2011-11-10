
<%@ page import="mx.edu.um.rh.Grupo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'grupo.label', default: 'Grupo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-grupo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="home" controller="RH"><g:message code="rh.list.label" default="RH" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-grupo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'grupo.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="maximo" title="${message(code: 'grupo.maximo.label', default: 'Maximo')}" />
					
						<g:sortableColumn property="minimo" title="${message(code: 'grupo.minimo.label', default: 'Minimo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${grupos}" status="i" var="grupo">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${grupo.id}">${fieldValue(bean: grupo, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: grupo, field: "maximo")}</td>
					
						<td>${fieldValue(bean: grupo, field: "minimo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeGrupos}" />
			</div>
		</div>
	</body>
</html>
