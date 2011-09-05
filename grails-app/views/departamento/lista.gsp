
<%@ page import="contabilidad.Departamento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'departamento.label', default: 'Departamento')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-departamento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-departamento" class="content scaffold-list" role="main">
			<h1><g:message code="departamento.titulo" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'departamento.nombre')}" />
					
						<th><g:message code="departamento.cuenta" /></th>
                                                
                                                <th><g:message code="departamento.status" /></th>
					
						
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${departamentos}" status="i" var="departamentos">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${departamentos.id}">${fieldValue(bean: departamentos, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: departamentos, field: "cuenta")}</td>
                                                
                                                <td><g:formatBoolean boolean="${departamentos.cuenta.status}" /></td>
                                                
                                                
					
						
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeDepartamentos}" />
			</div>
		</div>
	</body>
</html>
