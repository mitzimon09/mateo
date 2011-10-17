<%@ page import="mx.edu.um.rh.TipoEmpleado" %>



<div class="fieldcontain ${hasErrors(bean: tipoEmpleado, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="tipoEmpleado.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="40" required="" value="${tipoEmpleado?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoEmpleado, field: 'prefijo', 'error')} required">
	<label for="prefijo">
		<g:message code="tipoEmpleado.prefijo.label" default="Prefijo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="prefijo" maxlength="3" required="" value="${tipoEmpleado?.prefijo}"/>
</div>

