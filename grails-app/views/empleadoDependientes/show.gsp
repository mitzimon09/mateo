
<%@ page import="mx.edu.um.rh.EmpleadoDependientes" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empleadoDependientes.label', default: 'EmpleadoDependientes')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-empleadoDependientes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-empleadoDependientes" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list empleadoDependientes">
			
				<g:if test="${empleadoDependientes?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="empleadoDependientes.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${empleadoDependientes}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoDependientes?.estudios}">
				<li class="fieldcontain">
					<span id="estudios-label" class="property-label"><g:message code="empleadoDependientes.estudios.label" default="Estudios" /></span>
					
						<span class="property-value" aria-labelledby="estudios-label"><g:fieldValue bean="${empleadoDependientes}" field="estudios"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoDependientes?.grado}">
				<li class="fieldcontain">
					<span id="grado-label" class="property-label"><g:message code="empleadoDependientes.grado.label" default="Grado" /></span>
					
						<span class="property-value" aria-labelledby="grado-label"><g:fieldValue bean="${empleadoDependientes}" field="grado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoDependientes?.matricula}">
				<li class="fieldcontain">
					<span id="matricula-label" class="property-label"><g:message code="empleadoDependientes.matricula.label" default="Matricula" /></span>
					
						<span class="property-value" aria-labelledby="matricula-label"><g:fieldValue bean="${empleadoDependientes}" field="matricula"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoDependientes?.bday}">
				<li class="fieldcontain">
					<span id="bday-label" class="property-label"><g:message code="empleadoDependientes.bday.label" default="Bday" /></span>
					
						<span class="property-value" aria-labelledby="bday-label"><g:formatDate date="${empleadoDependientes?.bday}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoDependientes?.colegio}">
				<li class="fieldcontain">
					<span id="colegio-label" class="property-label"><g:message code="empleadoDependientes.colegio.label" default="Colegio" /></span>
					
						<span class="property-value" aria-labelledby="colegio-label"><g:link controller="colegio" action="show" id="${empleadoDependientes?.colegio?.id}">${empleadoDependientes?.colegio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoDependientes?.empleado}">
				<li class="fieldcontain">
					<span id="empleado-label" class="property-label"><g:message code="empleadoDependientes.empleado.label" default="Empleado" /></span>
					
						<span class="property-value" aria-labelledby="empleado-label"><g:link controller="empleado" action="show" id="${empleadoDependientes?.empleado?.id}">${empleadoDependientes?.empleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${empleadoDependientes?.relacion}">
				<li class="fieldcontain">
					<span id="relacion-label" class="property-label"><g:message code="empleadoDependientes.relacion.label" default="Relacion" /></span>
					
						<span class="property-value" aria-labelledby="relacion-label"><g:link controller="relacion" action="show" id="${empleadoDependientes?.relacion?.id}">${empleadoDependientes?.relacion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${empleadoDependientes?.id}" />
					<g:link class="edit" action="edit" id="${empleadoDependientes?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
