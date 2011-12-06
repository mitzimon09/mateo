
<%@ page import="mx.edu.um.rh.Observaciones" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'observaciones.label', default: 'Observaciones')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-observaciones" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-observaciones" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="observaciones" title="${message(code: 'observaciones.observaciones.label', default: 'Observaciones')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'observaciones.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="observaciones.solicitudRH.label" default="Solicitud RH" /></th>
					
						<th><g:message code="observaciones.usuario.label" default="Usuario" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${observacionesInstanceList}" status="i" var="observaciones">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${observaciones.id}">${fieldValue(bean: observaciones, field: "observaciones")}</g:link></td>
					
						<td><g:formatDate date="${observaciones.dateCreated}" /></td>
					
						<td>${fieldValue(bean: observaciones, field: "solicitudRH")}</td>
					
						<td>${fieldValue(bean: observaciones, field: "usuario")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${observacionesTotal}" />
			</div>
		</div>
	</body>
</html>
