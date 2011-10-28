<%@ page import="mx.edu.um.rh.SolicitudVacaciones" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudVacaciones.label', default: 'SolicitudVacaciones')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-solicitudVacaciones" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudVacaciones" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:form action="rangoList">
			<fieldset class="form">
				<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'fechaInicial', 'error')} required">
					<label for="fechaEmpiezaRango">
						<g:message code="fechaEmpiezaRango.label" default="Fecha donde empieza el rango" />
						<span class="required-indicator">*</span>
					</label>
					<g:datePicker name="fechaEmpiezaRango" precision="day" value="${fechaEmpiezaRango}"  />
				</div>

				<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'fechaFinal', 'error')} required">
					<label for="fechaTerminaRango">
						<g:message code="fechaTerminaRango.label" default="Fecha donde termina el rango" />
						<span class="required-indicator">*</span>
					</label>
					<g:datePicker name="fechaTerminaRango" precision="day" value="${fechaTerminaRango}"  />
				</div>
					<g:submitButton name="rangoList" class="rangoList" value="${message(code: 'default.button.buscar.label', default: 'Buscar')}" />
			</fieldset>
			</g:form>
		</div>
	</body>
</html>
