
<%@ page import="mx.edu.um.rh.DiasFeriados" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'diasFeriados.label', default: 'DiasFeriados')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-diasFeriados" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
                <li><g:link class="home" controller="RH"><g:message code="rh.list.label" default="RH" /></g:link></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-diasFeriados" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list diasFeriados">
			
				<g:if test="${diasFeriados?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="diasFeriados.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${diasFeriados}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${diasFeriados?.diadado}">
				<li class="fieldcontain">
					<span id="diadado-label" class="property-label"><g:message code="diasFeriados.diadado.label" default="Diadado" /></span>
					
						<span class="property-value" aria-labelledby="diadado-label"><g:checkBox name="diadado" disabled="true"  value="${diasFeriados?.diadado}" /></span>
				</li>
				</g:if>
			
				<g:if test="${diasFeriados?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="diasFeriados.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${diasFeriados?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${diasFeriados?.fecharegistro}">
				<li class="fieldcontain">
					<span id="fecharegistro-label" class="property-label"><g:message code="diasFeriados.fecharegistro.label" default="Fecharegistro" /></span>
					
						<span class="property-value" aria-labelledby="fecharegistro-label"><g:formatDate date="${diasFeriados?.fecharegistro}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${diasFeriados?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="diasFeriados.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="usuario" action="show" id="${diasFeriados?.user?.id}">${diasFeriados?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${diasFeriados?.id}" />
					<g:link class="edit" action="edita" id="${diasFeriados?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
