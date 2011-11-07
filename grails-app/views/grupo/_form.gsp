<%@ page import="mx.edu.um.rh.Grupo" %>



<div class="fieldcontain ${hasErrors(bean: grupo, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="grupo.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="2" required="" value="${grupo?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: grupo, field: 'maximo', 'error')} required">
	<label for="maximo">
		<g:message code="grupo.maximo.label" default="Maximo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="maximo" required="" value="${fieldValue(bean: grupo, field: 'maximo')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: grupo, field: 'minimo', 'error')} required">
	<label for="minimo">
		<g:message code="grupo.minimo.label" default="Minimo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="minimo" required="" value="${fieldValue(bean: grupo, field: 'minimo')}"/>
</div>

