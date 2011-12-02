<%@ page import="mx.edu.um.rh.EmpleadoPerded" %>



<div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'tipoImporte', 'error')} ">
	<label for="tipoImporte">
		<g:message code="empleadoPerded.tipoImporte.label" default="Tipo Importe" />
		
	</label>
	<g:textField name="tipoImporte" maxlength="1" value="${empleadoPerded?.tipoImporte}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'atributos', 'error')} ">
	<label for="atributos">
		<g:message code="empleadoPerded.atributos.label" default="Atributos" />
		
	</label>
	<g:textField name="atributos" value="${empleadoPerded?.atributos}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="empleadoPerded.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${empleadoPerded?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'importe', 'error')} required">
	<label for="importe">
		<g:message code="empleadoPerded.importe.label" default="Importe" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="importe" required="" value="${fieldValue(bean: empleadoPerded, field: 'importe')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'isEditableByNOM', 'error')} ">
	<label for="isEditableByNOM">
		<g:message code="empleadoPerded.isEditableByNOM.label" default="Is Editable By NOM" />
		
	</label>
	<g:checkBox name="isEditableByNOM" value="${empleadoPerded?.isEditableByNOM}" />
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'otorgado', 'error')} ">
	<label for="otorgado">
		<g:message code="empleadoPerded.otorgado.label" default="Otorgado" />
		
	</label>
	<g:checkBox name="otorgado" value="${empleadoPerded?.otorgado}" />
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoPerded, field: 'perded', 'error')} required">
	<label for="perded">
		<g:message code="empleadoPerded.perded.label" default="Perded" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="perded" name="perded.id" from="${mx.edu.um.rh.PerDed.list()}" optionKey="id" required="" value="${empleadoPerded?.perded?.id}" class="many-to-one"/>
</div>

