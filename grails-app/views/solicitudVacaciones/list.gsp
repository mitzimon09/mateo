
<%@ page import="mx.edu.um.rh.SolicitudVacaciones" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudVacaciones" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudVacaciones" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="solicitudVacaciones.empleado.label" default="Empleado" /></th>
					
						<th><g:message code="solicitudVacaciones.empresa.label" default="Empresa" /></th>
					
						<g:sortableColumn property="fechaCaptura" title="${message(code: 'solicitudVacaciones.fechaCaptura.label', default: 'Fecha Captura')}" />
					
						<g:sortableColumn property="fechaInicial" title="${message(code: 'solicitudVacaciones.fechaInicial.label', default: 'Fecha Inicial')}" />
					
						<g:sortableColumn property="fechaFinal" title="${message(code: 'solicitudVacaciones.fechaFinal.label', default: 'Fecha Final')}" />
					
						<g:sortableColumn property="fechaRecibeJefe" title="${message(code: 'solicitudVacaciones.fechaRecibeJefe.label', default: 'Fecha Recibe Jefe')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudVacacionesInstanceList}" status="i" var="solicitudVacacionesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudVacacionesInstance.id}">${fieldValue(bean: solicitudVacacionesInstance, field: "empleado")}</g:link></td>
					
						<td>${fieldValue(bean: solicitudVacacionesInstance, field: "empresa")}</td>
					
						<td><g:formatDate date="${solicitudVacacionesInstance.fechaCaptura}" /></td>
					
						<td><g:formatDate date="${solicitudVacacionesInstance.fechaInicial}" /></td>
					
						<td><g:formatDate date="${solicitudVacacionesInstance.fechaFinal}" /></td>
					
						<td><g:formatDate date="${solicitudVacacionesInstance.fechaRecibeJefe}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudVacacionesInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
