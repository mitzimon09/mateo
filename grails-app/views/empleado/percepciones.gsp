<%@ page import="mx.edu.um.rh.Empleado" %>
<%@ page import="mx.edu.um.rh.PerDed" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empleado.label', default: 'Empleado')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-empleado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-empleado" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${empleado}">
			<ul class="errors" role="alert">
				<g:eachError bean="${empleado}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="crea" >
				<fieldset class="form">
                                 <g:hiddenField name="empleado.id" value="${empleado?.id}" />
                    <div class="fieldcontain ${hasErrors(bean: empleado, field: 'nombre', 'error')} required">
	                    <label for="nombre">
		                    <g:message code="empleado.nombre.label" default="Nombre" />
		                    <span class="required-indicator">*</span>
	                    </label>
	                    <g:textField name="nombre" maxlength="50" required="" value="${empleado?.nombre}"/>
                    </div>
                                  
                    <g:each in="${percepciones}" status="i" var="percepcion">
                      <ol> 
                      <g:link action="formagrega" id="${percepcion.id}">${fieldValue(bean: percepcion, field: "nombre")}</g:link>
                      <input type="checkbox" name="persepciones" value="1">
                      </ol> 
                    </g:each>

				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
