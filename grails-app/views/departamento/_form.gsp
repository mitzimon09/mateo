<%@ page import="contabilidad.Departamento" %>



<div class="fieldcontain ${hasErrors(bean: departamento, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="departamento.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${departamento?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departamento, field: 'codigo', 'error')} required">
	<label for="codigo">
          <h2><g:message code="departamento.cuenta.label" default="Cuenta" /></h2>
		<g:message code="departamento.cuenta.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="codigo" maxlength="64" required="" value="${departamento?.cuenta?.codigo}"/>
</div> 

<div class="fieldcontain ${hasErrors(bean: departamento, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="departamento.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numero" maxlength="64" required="" value="${departamento?.cuenta?.numero}"/>
</div> 

<div class="fieldcontain ${hasErrors(bean: departamento, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="departamento.descripcion.label" default="Descripción" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="64" required="" value="${departamento?.cuenta?.descripcion}"/>
</div> 

<div class="fieldcontain ${hasErrors(bean: departamento, field: 'tieneMovimientos', 'error')}">
	<label for="tieneMovimientos">
		<g:message code="departamento.tieneMovimientos.label" default="Tiene Movimientos" />
		
	</label>
        <g:checkBox name="tieneMovimientos" value="${departamento?.cuenta?.tieneMovimientos}" />
</div> 

<div class="fieldcontain ${hasErrors(bean: departamento, field: 'tieneAuxiliares', 'error')}">
	<label for="tieneAuxiliares">
		<g:message code="departamento.tieneAuxiliares.label" default="Tiene Auxiliares" />
	</label>
        <g:checkBox name="tieneAuxiliares" value="${departamento?.cuenta?.tieneAuxiliares}" />
</div> 

<div class="fieldcontain ${hasErrors(bean: departamento, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="departamento.status" />
	</label>
	<g:checkBox name="status" value="${departamento?.cuenta?.status}" />
</div>

