
<%@ page import="mx.edu.um.rh.Puesto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'puesto.label', default: 'Seccion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-puesto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="home" controller="RH"><g:message code="rh.list.label" default="RH" /></g:link></li>
				<li><g:link class="home" controller="RH"><g:message code="rh.list.label" default="RH" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-puesto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					    <g:sortableColumn property="nombre" title="${message(code: 'puesto.nombre.label', default: 'Numero')}" />
					    
						<g:sortableColumn property="nombre" title="${message(code: 'puesto.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="maximo" title="${message(code: 'puesto.maximo.label', default: 'Maximo')}" />
					
						<g:sortableColumn property="minimo" title="${message(code: 'puesto.minimo.label', default: 'Minimo')}" />
					
						<th><g:message code="puesto.seccion.label" default="Seccion" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${puestos}" status="i" var="puesto">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: puesto, field: "id")}</td>
					
						<td><g:link action="ver" id="${puesto.id}">${fieldValue(bean: puesto, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: puesto, field: "maximo")}</td>
					
						<td>${fieldValue(bean: puesto, field: "minimo")}</td>
					
						<td>${fieldValue(bean: puesto, field: "seccion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDePuestos}" />
			</div>
		</div>
	</body>
</html>
