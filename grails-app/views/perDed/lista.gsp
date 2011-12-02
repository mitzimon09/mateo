
<%@ page import="mx.edu.um.rh.PerDed" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'perDed.label', default: 'PerDed')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-perDed" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-perDed" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="clave" title="${message(code: 'perDed.clave.label', default: 'Clave')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'perDed.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="naturaleza" title="${message(code: 'perDed.naturaleza.label', default: 'Naturaleza')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'perDed.status.label', default: 'Status')}" />
					
						<g:sortableColumn property="formula" title="${message(code: 'perDed.formula.label', default: 'Formula')}" />
					
						<th><g:message code="perDed.atributos.label" default="Atributos" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${perDedList}" status="i" var="perDed">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${perDed.id}">${fieldValue(bean: perDed, field: "clave")}</g:link></td>
					
						<td>${fieldValue(bean: perDed, field: "nombre")}</td>
					
						<td>${fieldValue(bean: perDed, field: "naturaleza")}</td>
					
						<td>${fieldValue(bean: perDed, field: "status")}</td>
					
						<td>${fieldValue(bean: perDed, field: "formula")}</td>
					
						<td>${fieldValue(bean: perDed, field: "atributos")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${perDedTotal}" />
			</div>
		</div>
	</body>
</html>
