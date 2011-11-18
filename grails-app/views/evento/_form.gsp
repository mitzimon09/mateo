<%@ page import="mx.edu.um.rh.Evento" %>



<div class="fieldcontain ${hasErrors(bean: evento, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="evento.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" maxlength="50" value="${evento?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: evento, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="evento.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" maxlength="250" value="${evento?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: evento, field: 'prorroga', 'error')} required">
	<label for="prorroga">
		<g:message code="evento.prorroga.label" default="Prorroga" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="prorroga" required="" maxlength="50" value="${evento?.prorroga}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: evento, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="evento.status.label" default="Status" />
		
	</label>
	<g:select name="status" from="${evento.constraints.status.inList}" value="${evento?.status}" valueMessagePrefix="evento.status" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: evento, field: 'hora_inicio', 'error')} required">
	<label for="hora_inicio">
		<g:message code="evento.hora_inicio.label" default="Horainicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="hora_inicio" precision="minute" value="${evento?.hora_inicio}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: evento, field: 'hora_final', 'error')} required">
	<label for="hora_final">
		<g:message code="evento.hora_final.label" default="Horafinal" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="hora_final" precision="minute" value="${evento?.hora_final}"  />
</div>

