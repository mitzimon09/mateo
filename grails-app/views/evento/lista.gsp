
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
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-evento" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'evento.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'evento.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="prorroga" title="${message(code: 'evento.prorroga.label', default: 'Prorroga')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'evento.status.label', default: 'Status')}" />
					
						<g:sortableColumn property="hora_final" title="${message(code: 'evento.hora_final.label', default: 'Horafinal')}" />
					
						<g:sortableColumn property="hora_inicio" title="${message(code: 'evento.hora_inicio.label', default: 'Horainicio')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${eventos}" status="i" var="evento">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${evento.id}">${fieldValue(bean: evento, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: evento, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: evento, field: "prorroga")}</td>
					
						<td>${fieldValue(bean: evento, field: "status")}</td>
					
						<td><g:formatDate date="${evento.hora_final}" /></td>
					
						<td><g:formatDate date="${evento.hora_inicio}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeEventos}" />
			</div>
		</div>
	</body>
</html>
