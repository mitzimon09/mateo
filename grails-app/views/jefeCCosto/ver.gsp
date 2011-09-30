
<%@ page import="mx.edu.um.rh.JefeCCosto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'jefeCCosto.label', default: 'JefeCCosto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-jefeCCosto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nuevo"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-jefeCCosto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list jefeCCosto">
			
				<g:if test="${jefeCCosto?.ccosto}">
				<li class="fieldcontain">
					<span id="ccosto-label" class="property-label"><g:message code="jefeCCosto.ccosto.label" default="Ccosto" /></span>
					
						<span class="property-value" aria-labelledby="ccosto-label"><g:fieldValue bean="${jefeCCosto}" field="ccosto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${jefeCCosto?.ejercicio}">
				<li class="fieldcontain">
					<span id="ejercicio-label" class="property-label"><g:message code="jefeCCosto.ejercicio.label" default="Ejercicio" /></span>
					
						<span class="property-value" aria-labelledby="ejercicio-label"><g:fieldValue bean="${jefeCCosto}" field="ejercicio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${jefeCCosto?.jefe}">
				<li class="fieldcontain">
					<span id="jefe-label" class="property-label"><g:message code="jefeCCosto.jefe.label" default="Jefe" /></span>
					
						<span class="property-value" aria-labelledby="jefe-label"><g:link controller="empleado" action="ver" id="${jefeCCosto?.jefe?.id}">${jefeCCosto?.jefe?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${jefeCCosto?.subjefe}">
				<li class="fieldcontain">
					<span id="subjefe-label" class="property-label"><g:message code="jefeCCosto.subjefe.label" default="Subjefe" /></span>
					
						<span class="property-value" aria-labelledby="subjefe-label"><g:link controller="empleado" action="ver" id="${jefeCCosto?.subjefe?.id}">${jefeCCosto?.subjefe?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${jefeCCosto?.userCaptura}">
				<li class="fieldcontain">
					<span id="userCaptura-label" class="property-label"><g:message code="jefeCCosto.userCaptura.label" default="User Captura" /></span>
					
						<span class="property-value" aria-labelledby="userCaptura-label"><g:link controller="usuario" action="ver" id="${jefeCCosto?.userCaptura?.id}">${jefeCCosto?.userCaptura?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${jefeCCosto?.fechaCaptura}">
				<li class="fieldcontain">
					<span id="fechaCaptura-label" class="property-label"><g:message code="jefeCCosto.fechaCaptura.label" default="Fecha Captura" /></span>
					
						<span class="property-value" aria-labelledby="fechaCaptura-label"><g:formatDate date="${jefeCCosto?.fechaCaptura}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${jefeCCosto?.miJefe}">
				<li class="fieldcontain">
					<span id="miJefe-label" class="property-label"><g:message code="jefeCCosto.miJefe.label" default="Mi Jefe" /></span>
					
						<span class="property-value" aria-labelledby="miJefe-label"><g:link controller="empleado" action="ver" id="${jefeCCosto?.miJefe?.id}">${jefeCCosto?.miJefe?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${jefeCCosto?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="jefeCCosto.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${jefeCCosto}" field="status"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${jefeCCosto?.id}" />
					<g:link class="edit" action="edita" id="${jefeCCosto?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
