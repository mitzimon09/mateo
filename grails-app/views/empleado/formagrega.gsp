<%@ page import="mx.edu.um.rh.EmpleadoPerded" %>
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
    <g:form action="grabapercepcion" >
      <fieldset class="form">

        <div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'empleado', 'error')} required">
          <label for="empleado">
            <g:message code="empleadoPerded.empleado.label" default="Empleado" />
            <span class="required-indicator">*</span>
          </label>
          <g:hiddenField name="empleado.id" value="${empleadoPerded?.empleado.id}" />
          <g:fieldValue name="empleado"bean="${empleadoPerded}" field="empleado"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'perded', 'error')} required">
          <label for="perded">
            <g:message code="empleadoPerded.perded.label" default="Perded" />
            <span class="required-indicator">*</span>
          </label>
          <g:select id="perded" name="perded.id" from="${mx.edu.um.rh.PerDed.list()}" optionKey="id" required="" value="${empleadoPerded?.perded?.id}" class="many-to-one"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'importe', 'error')} required">
          <label for="importe">
            <g:message code="empleadoPerded.importe.label" default="Importe" />
            <span class="required-indicator">*</span>
          </label>
          <g:field type="number" name="importe" required="" value="${fieldValue(bean: empleadoPerded, field: 'importe')}"/>
        </div>
        
        <div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'tipoImporte', 'error')}  required">
          <label for="tipoImporte">
            <g:message code="empleadoPerded.tipoImporte.label" default="Tipo Importe" />
           <span class="required-indicator">*</span>
          </label>
         
           <g:select name="tipoImporte" from="${empleadoPerded?.constraints.tipoImporte.inList}" required="" value="${empleadoPerded?.tipoImporte}" valueMessagePrefix="EmpleadoPerded.tipoImporte"/>
    

    
    
        </div>

        <div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'atributos', 'error')} ">
          <label for="atributos">
            <g:message code="empleadoPerded.atributos.label" default="Atributos" />

          </label>
          <g:textField name="atributos" value="${empleadoPerded?.atributos}"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'otorgado', 'error')} ">
          <label for="otorgado">
            <g:message code="empleadoPerded.otorgado.label" default="Otorgado" />

          </label>
          <g:checkBox name="otorgado" value="${empleadoPerded?.otorgado}" />
        </div>
        
        <div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'isEditableByNOM', 'error')} ">
          <label for="isEditableByNOM">
            <g:message code="empleadoPerded.isEditableByNOM.label" default="Is Editable By NOM" />

          </label>
          <g:checkBox name="isEditableByNOM" value="${empleadoPerded?.isEditableByNOM}" />
        </div>

      </fieldset>
      <fieldset class="buttons">
        <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        <g:actionSubmit class="edit" action="grabapercepcion" controller="empleado" value="${message(code:'default.button.percepcion.label', default:'Grabar Percepcion')}" />
      </fieldset>
    </g:form>
  </div>
</body>
</html>
