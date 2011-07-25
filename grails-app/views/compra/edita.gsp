<%@ page import="general.Compra" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'compra.label', default: 'Compra')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-compra" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-compra" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
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
			<g:form method="post" >
				<g:hiddenField name="id" value="${compra?.id}" />
				<g:hiddenField name="version" value="${compra?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
					<div class="fieldcontain" >
				    <g:if test="${compra.status.equals('CREADA') && permisos == 1 || permisos == 4}">
              <g:link controller="articulo" action="nuevo" params="['compra.id': compra?.id]">${message(code: 'default.add.label', args: [message(code: 'articulo.label', default: 'Articulo')])}</g:link>
            </g:if>
            <g:if test="${compra.status.equals('ENVIADA') && permisos == 2 || permisos == 4}">
              <div class="fieldcontain ${hasErrors(bean: compra, field: 'observaciones', 'error')} required">
                <label for="codigo">
                  <g:message code="compra.observaciones.label" default="Observaciones" />
                </label>
                <g:textField name="observaciones" maxlength="64" value="${compra?.observaciones}"/>
              </div>
            </g:if>
            <g:if test="${compra.status.equals('RECHAZADA')}">
              <div class="fieldcontain ${hasErrors(bean: compra, field: 'observaciones', 'error')} required">
                <label for="codigo">
                  <g:message code="compra.observaciones.label" default="Observaciones" />
                </label>
                  ${fieldValue(bean: compra, field: "observaciones")}
              </div>
            </g:if>
          </div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="actualiza" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					<g:if test="${(permisos == 1 || permisos == 4)}">
						<g:if test="${compra.status.equals('CREADA')}">
				  		<g:actionSubmit class="enviar" action="enviar" value="${message(code: 'default.button.enviar.label', default: 'Enviar')}" />
						</g:if>
					</g:if>
					<g:if test="${permisos == 2 || permisos == 4}">
						<g:if test="${compra.status.equals('ENVIADA')}">
				  		<g:actionSubmit class="aprobar" action="aprobar" value="${message(code: 'default.button.aprobar.label', default: 'Aprobar')}" />
  						<g:actionSubmit class="rechazar" action="rechazar" value="${message(code: 'default.button.rechazar.label', default: 'Rechazar')}" />
						</g:if>
					</g:if>
					<g:if test="${permisos == 3 || permisos == 4}">
						<g:if test="${compra.status.equals('APROBADA')}">
    					<g:actionSubmit class="comprar" action="comprar" value="${message(code: 'default.button.comprar.label', default: 'Comprar')}" />
						</g:if>
						<g:if test="${compra.status.equals('COMPRADA')}">
  						<g:actionSubmit class="entregar" action="entregar" value="${message(code: 'default.button.entregar.label', default: 'Entregar')}" />
  					</g:if>
					</g:if>
					<g:if test="${(permisos == 2 || permisos == 4)}">
						<g:if test="${!compra.status.equals('CREADA')}">
				  		<g:actionSubmit class="cancelar" action="cancelar" value="${message(code: 'default.button.cancelar.label', default: 'Cancelar')}" />
						</g:if>
					</g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
