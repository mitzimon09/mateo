<%@ page import="mx.edu.um.rh.Seccion" %>



<div class="fieldcontain ${hasErrors(bean: seccion, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="seccion.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" maxlength="200" value="${seccion?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seccion, field: 'maximo', 'error')} required">
	<label for="maximo">
		<g:message code="seccion.maximo.label" default="Maximo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="maximo" required="" value="${fieldValue(bean: seccion, field: 'maximo')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seccion, field: 'minimo', 'error')} required">
	<label for="minimo">
		<g:message code="seccion.minimo.label" default="Minimo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="minimo" required="" value="${fieldValue(bean: seccion, field: 'minimo')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seccion, field: 'rango_academico', 'error')} required">
	<label for="rango_academico">
		<g:message code="seccion.rango_academico.label" default="Rangoacademico" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="rango_academico" required="" value="${fieldValue(bean: seccion, field: 'rango_academico')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seccion, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="seccion.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoria" name="categoria.id" from="${mx.edu.um.rh.Categoria.list()}" optionKey="id" required="" value="${seccion?.categoria?.id}" class="many-to-one"/>
</div>

