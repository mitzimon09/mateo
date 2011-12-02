
<%@ page import="mx.edu.um.rh.EmpleadoPerded" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empleadoPerded.label', default: 'EmpleadoPerded')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-empleadoPerded" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-empleadoPerded" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="tipoImporte" title="${message(code: 'empleadoPerded.tipoImporte.label', default: 'Tipo Importe')}" />
					
						<g:sortableColumn property="atributos" title="${message(code: 'empleadoPerded.atributos.label', default: 'Atributos')}" />
					
						<th><g:message code="empleadoPerded.empleado.label" default="Empleado" /></th>
					
						<g:sortableColumn property="importe" title="${message(code: 'empleadoPerded.importe.label', default: 'Importe')}" />
					
						<g:sortableColumn property="isEditableByNOM" title="${message(code: 'empleadoPerded.isEditableByNOM.label', default: 'Is Editable By NOM')}" />
					
						<g:sortableColumn property="otorgado" title="${message(code: 'empleadoPerded.otorgado.label', default: 'Otorgado')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${empleadoPerdedList}" status="i" var="empleadoPerded">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${empleadoPerded.id}">${fieldValue(bean: empleadoPerded, field: "tipoImporte")}</g:link></td>
					
						<td>${fieldValue(bean: empleadoPerded, field: "atributos")}</td>
					
						<td>${fieldValue(bean: empleadoPerded, field: "empleado")}</td>
					
						<td>${fieldValue(bean: empleadoPerded, field: "importe")}</td>
					
						<td><g:formatBoolean boolean="${empleadoPerded.isEditableByNOM}" /></td>
					
						<td><g:formatBoolean boolean="${empleadoPerded.otorgado}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${empleadoPerdedTotal}" />
			</div>
		</div>
	</body>
</html>
