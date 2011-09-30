
<%@ page import="mx.edu.um.rh.JefeCCosto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'jefeCCosto.label', default: 'JefeCCosto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-jefeCCosto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-jefeCCosto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="ccosto" title="${message(code: 'jefeCCosto.ccosto.label', default: 'Ccosto')}" />
					
						<g:sortableColumn property="ejercicio" title="${message(code: 'jefeCCosto.ejercicio.label', default: 'Ejercicio')}" />
					
						<th><g:message code="jefeCCosto.status.label" default="Status" /></th>
					
						<g:sortableColumn property="fechaCaptura" title="${message(code: 'jefeCCosto.fechaCaptura.label', default: 'Fecha Captura')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${jefesCCosto}" status="i" var="jefeCCosto">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${jefeCCosto.id}">${fieldValue(bean: jefeCCosto, field: "ccosto")}</g:link></td>
					
						<td>${fieldValue(bean: jefeCCosto, field: "ejercicio")}</td>
					
						<td>${fieldValue(bean: jefeCCosto, field: "status")}</td>
					
						<td><g:formatDate date="${jefeCCosto.fechaCaptura}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDejefesCCosto}" />
			</div>
		</div>
	</body>
</html>
