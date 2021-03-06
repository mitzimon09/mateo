<%@ page import="mx.edu.um.rh.Evento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evento.label', default: 'Evento')}" />
		<title><g:message code="Pase de Lista para {0}" args="[evento.nombre]"/></title>
	    <meta name="layout" content="main" />
	</head>
	<body>
		<a href="#create-evento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-evento" class="content scaffold-create" role="main">
			<h1><g:message code="Pase de Lista para {0}" args="[evento.nombre]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${evento}">
			<ul class="errors" role="alert">
				<g:eachError bean="${evento}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="paseLista" >
				<fieldset class="form">
	  				<g:hiddenField name="id" value="${evento?.id}" />
				    //todo aqui <br>
				    campo para capturar # nomina
				    <div class="fieldcontain required">
                        <label for="clave">
                            <g:message code="evento.clave.label" default="# Clave" />
                            <span class="required-indicator">*</span>
                        </label>
                        <input type="text" id="clave" name="clave" value="" required="" />
                    </div>
				</fieldset>
				<fieldset class="buttons">
				    <span class="button">
                        <sec:ifLoggedIn>
					        <g:submitButton name="paseLista" class="list" value="Pase De Lista" />
                            <g:link class="list" action="cerrarEvento" id="${evento?.id}" ><g:message code="default.button.cerrarEvento.label" default="Cerrar Evento" /></g:link>
                            <g:link class="list" action="preparaSubir" id="${evento?.id}" ><g:message code="default.button.cerrarEvento.label" default="Subir Archivo" /></g:link>
                        </sec:ifLoggedIn>
                    <span>
				</fieldset>
			</g:form>
		</div>
		<g:javascript>
          $(document).ready(function() {
            $('#clave').focus();
          });
        </g:javascript>
	</body>
</html>
