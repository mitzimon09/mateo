<%@ page import="mx.edu.um.rh.Puesto" %>



<div class="fieldcontain ${hasErrors(bean: puesto, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="puesto.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" maxlength="50" value="${puesto?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: puesto, field: 'maximo', 'error')} required">
	<label for="maximo">
		<g:message code="puesto.maximo.label" default="Maximo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="maximo" required="" value="${fieldValue(bean: puesto, field: 'maximo')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: puesto, field: 'minimo', 'error')} required">
	<label for="minimo">
		<g:message code="puesto.minimo.label" default="Minimo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="minimo" required="" value="${fieldValue(bean: puesto, field: 'minimo')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: puesto, field: 'seccion', 'error')} required">
	<label for="seccion">
		<g:message code="puesto.seccion.label" default="Seccion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="seccion" name="seccion.id" from="${mx.edu.um.rh.Seccion.list()}" optionKey="id" required="" value="${puesto?.seccion?.id}" class="many-to-one"/>
</div>

