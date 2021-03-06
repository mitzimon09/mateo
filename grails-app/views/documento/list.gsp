
<%@ page import="general.Documento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-documento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-documento" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="concepto" title="${message(code: 'documento.concepto.label', default: 'Concepto')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'documento.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="naturaleza" title="${message(code: 'documento.naturaleza.label', default: 'Naturaleza')}" />
					
						<g:sortableColumn property="observaciones" title="${message(code: 'documento.observaciones.label', default: 'Observaciones')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'documento.status.label', default: 'Status')}" />
					
						<g:sortableColumn property="cheque" title="${message(code: 'documento.cheque.label', default: 'Cheque')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${documentoInstanceList}" status="i" var="documentoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${documentoInstance.id}">${fieldValue(bean: documentoInstance, field: "concepto")}</g:link></td>
					
						<td>${fieldValue(bean: documentoInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: documentoInstance, field: "naturaleza")}</td>
					
						<td>${fieldValue(bean: documentoInstance, field: "observaciones")}</td>
					
						<td>${fieldValue(bean: documentoInstance, field: "status")}</td>
					
						<td>${fieldValue(bean: documentoInstance, field: "cheque")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${documentoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
