<!doctype html>
<html>
	<head>
        <title><g:message code="seccion.lista" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
	</head>
	<body>
		<a href="#list-seccion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="home" controller="RH"><g:message code="rh.list.label" default="RH" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-seccion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="seccion.categoria.label" default="Seccion" /></th>
						<g:sortableColumn property="descripcion" title="${message(code: 'seccion.descripcion.label', default: 'Categoria')}" />
					
						<g:sortableColumn property="maximo" title="${message(code: 'seccion.maximo.label', default: 'Maximo')}" />
					
						<g:sortableColumn property="minimo" title="${message(code: 'seccion.minimo.label', default: 'Minimo')}" />
					
						<g:sortableColumn property="rango_academico" title="${message(code: 'seccion.rango_academico.label', default: 'Rangoacademico')}" />
					
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${secciones}" status="i" var="seccion">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: seccion, field: "categoria")}</td>
					
						<td><g:link action="ver" id="${seccion.id}">${fieldValue(bean: seccion, field: "descripcion")}</g:link></td>
					
						<td>${fieldValue(bean: seccion, field: "maximo")}</td>
					
						<td>${fieldValue(bean: seccion, field: "minimo")}</td>
					
						<td>${fieldValue(bean: seccion, field: "rango_academico")}</td>
					
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${totalDeSecciones}" />
			</div>
		</div>
	</body>
</html>
