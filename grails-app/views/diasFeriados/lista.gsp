
<%@ page import="mx.edu.um.rh.DiasFeriados" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'diasFeriados.label', default: 'DiasFeriados')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-diasFeriados" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
                <li><g:link class="home" controller="RH"><g:message code="rh.list.label" default="RH" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-diasFeriados" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'diasFeriados.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="diadado" title="${message(code: 'diasFeriados.diadado.label', default: 'Diadado')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'diasFeriados.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="fecharegistro" title="${message(code: 'diasFeriados.fecharegistro.label', default: 'Fecharegistro')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${diasFeriados}" status="i" var="diaFeriado">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="ver" id="${diaFeriado.id}">${fieldValue(bean: diaFeriado, field: "descripcion")}</g:link></td>

						<td><g:checkBox name="diadado" disabled="true"  value="${diaFeriado?.diadado}" /></td>
					
						<td><g:formatDate date="${diaFeriado.fecha}" /></td>
					
						<td><g:formatDate date="${diaFeriado.fecharegistro}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeDiasFeriados}" />
			</div>
		</div>
	</body>
</html>
