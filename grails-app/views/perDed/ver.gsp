
<%@ page import="mx.edu.um.rh.PerDed" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'perDed.label', default: 'PerDed')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#show-perDed" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>
  <div id="show-perDed" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list perDed">

      <g:if test="${perDed?.clave}">
        <li class="fieldcontain">
          <span id="clave-label" class="property-label"><g:message code="perDed.clave.label" default="Clave" /></span>

          <span class="property-value" aria-labelledby="clave-label"><g:fieldValue bean="${perDed}" field="clave"/></span>

        </li>
      </g:if>

      <g:if test="${perDed?.nombre}">
        <li class="fieldcontain">
          <span id="nombre-label" class="property-label"><g:message code="perDed.nombre.label" default="Nombre" /></span>

          <span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${perDed}" field="nombre"/></span>

        </li>
      </g:if>

      <g:if test="${perDed?.naturaleza}">
        <li class="fieldcontain">
          <span id="naturaleza-label" class="property-label"><g:message code="perDed.naturaleza.label" default="Naturaleza" /></span>

          <span class="property-value" aria-labelledby="naturaleza-label"><g:fieldValue bean="${perDed}" field="naturaleza"/></span>

        </li>
      </g:if>

      <g:if test="${perDed?.status}">
        <li class="fieldcontain">
          <span id="status-label" class="property-label"><g:message code="perDed.status.label" default="Status" /></span>

          <span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${perDed}" field="status"/></span>

        </li>
      </g:if>

      <g:if test="${perDed?.formula}">
        <li class="fieldcontain">
          <span id="formula-label" class="property-label"><g:message code="perDed.formula.label" default="Formula" /></span>

          <span class="property-value" aria-labelledby="formula-label"><g:fieldValue bean="${perDed}" field="formula"/></span>

        </li>
      </g:if>

       
      <g:if test="${perDed?.frecuenciaPago}">
        <li class="fieldcontain">
          <span id="frecuenciaPago-label" class="property-label"><g:message code="perDed.frecuenciaPago.label" default="Frecuencia Pago" /></span>

          <span class="property-value" aria-labelledby="frecuenciaPago-label"><g:fieldValue bean="${perDed}" field="frecuenciaPago"/></span>

        </li>
      </g:if>

    </ol>
    <g:form>
      <fieldset class="buttons">
        <g:hiddenField name="id" value="${perDed?.id}" />
        <g:link class="edit" action="edit" id="${perDed?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
      </fieldset>
    </g:form>
  </div>
</body>
</html>
