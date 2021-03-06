<%@ page import="general.Compra" %>
<!doctype html>
<html>
  <head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'compra.label', default: 'Compra')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-compra" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-compra" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
    		<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${compra}">
			  <ul class="errors" role="alert">
				  <g:eachError bean="${compra}" var="error">
      			<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	    		</g:eachError>
			  </ul>
			</g:hasErrors>
			<g:form action="crea" >
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: compra, field: 'folio', 'error')} ">
	          <label for="folio">
		          <g:message code="compra.folio.label" default="Folio" />
	          </label>
	          <g:fieldValue bean="${compra}" field="folio"/>
          </div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
