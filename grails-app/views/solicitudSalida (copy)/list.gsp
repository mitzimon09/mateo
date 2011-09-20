
<%@ page import="mx.edu.um.rh.SolicitudSalida" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudSalida.label', default: 'SolicitudSalida')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudSalida" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudSalida" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="solicitudSalida.empleado.label" default="Empleado" /></th>
					
						<g:sortableColumn property="diasVacaciones" title="${message(code: 'solicitudSalida.diasVacaciones.label', default: 'Dias Vacaciones')}" />
					
						<g:sortableColumn property="primaVacacional" title="${message(code: 'solicitudSalida.primaVacacional.label', default: 'Prima Vacacional')}" />
					
						<g:sortableColumn property="fechaInicial" title="${message(code: 'solicitudSalida.fechaInicial.label', default: 'Fecha Inicial')}" />
					
						<g:sortableColumn property="destino" title="${message(code: 'solicitudSalida.destino.label', default: 'Destino')}" />
					
						<g:sortableColumn property="kilometros" title="${message(code: 'solicitudSalida.kilometros.label', default: 'Kilometros')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudSalidaInstanceList}" status="i" var="solicitudSalidaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudSalidaInstance.id}">${fieldValue(bean: solicitudSalidaInstance, field: "empleado")}</g:link></td>
					
						<td>${fieldValue(bean: solicitudSalidaInstance, field: "diasVacaciones")}</td>
					
						<td>${fieldValue(bean: solicitudSalidaInstance, field: "primaVacacional")}</td>
					
						<td><g:formatDate date="${solicitudSalidaInstance.fechaInicial}" /></td>
					
						<td>${fieldValue(bean: solicitudSalidaInstance, field: "destino")}</td>
					
						<td>${fieldValue(bean: solicitudSalidaInstance, field: "kilometros")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudSalidaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
