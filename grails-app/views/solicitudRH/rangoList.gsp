
<%@ page import="mx.edu.um.rh.SolicitudRH" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudRH.label', default: 'SolicitudRH')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudRH" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="rango" action="rango"><g:message code="Encontrar por rango de fecha" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudRH" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="solicitudRH.id.label" default="Solicitud" /></th>
						
						<th><g:message code="solicitudRH.empleado.label" default="Empleado" /></th>
					
						<g:sortableColumn property="fechaInicial" title="${message(code: 'solicitudRH.fechaInicial.label', default: 'Fecha Inicial')}" />
					
						<g:sortableColumn property="fechaFinal" title="${message(code: 'solicitudRH.fechaFinal.label', default: 'Fecha Final')}" />
					
						<g:sortableColumn property="folio" title="${message(code: 'solicitudRH.folio.label', default: 'Folio')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudesRH}" status="i" var="solicitudRH">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${solicitudRH.id}">${fieldValue(bean: solicitudRH, field: "id")}</g:link></td>
					
						<td><g:link controller="empleado" action="ver" id="${solicitudRH.empleado.id}">${fieldValue(bean: solicitudRH, field: "empleado")}</g:link></td>
					
						<td><g:formatDate date="${solicitudRH.fechaInicial}" /></td>
					
						<td><g:formatDate date="${solicitudRH.fechaFinal}" /></td>
					
						<td><g:formatDate date="${solicitudRH.folio}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeSolicitudesRH}" />
			</div>
		</div>
	</body>
</html>
