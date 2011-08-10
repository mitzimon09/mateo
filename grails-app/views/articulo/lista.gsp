
<%@ page import="general.Articulo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'articulo.label', default: 'Articulo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-articulo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-articulo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'articulo.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="cantidad" title="${message(code: 'articulo.cantidad.label', default: 'Cantidad')}" />
					
						<g:sortableColumn property="precioUnitario" title="${message(code: 'articulo.precioUnitario.label', default: 'Precio Unitario')}" />
					
						<g:sortableColumn property="total" title="${message(code: 'articulo.total.label', default: 'Total')}" />
						
						<g:sortableColumn property="status" title="${message(code: 'articulo.status.label', default: 'Status')}" />
					
						<th><g:message code="articulo.compra.label" default="Compra" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${articulos}" status="i" var="articulo">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edita" id="${articulo.id}">${fieldValue(bean: articulo, field: "descripcion")}</g:link></td>
					
						<td>${fieldValue(bean: articulo, field: "cantidad")}</td>
					
						<td>${fieldValue(bean: articulo, field: "precioUnitario")}</td>
					
						<td>${fieldValue(bean: articulo, field: "total")}</td>
					
						<td>${fieldValue(bean: articulo, field: "compra")}</td>
						
						<td>${fieldValue(bean: articulo, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeArticulos}" />
			</div>
		</div>
	</body>
</html>
