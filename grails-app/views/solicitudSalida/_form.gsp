<%@ page import="mx.edu.um.rh.SolicitudSalida" %>



<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="solicitudSalida.status.label" default="Status" />
		
	</label>
	<g:select name="status" from="${solicitudSalida.constraints.status.inList}" value="${solicitudSalida?.status}" valueMessagePrefix="solicitudSalida.status" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="solicitudSalida.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textField name="observaciones" maxlength="128" value="${solicitudSalida?.observaciones}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="solicitudSalida.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${solicitudSalida?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'vacaciones', 'error')} required">
	<label for="vacaciones">
		<g:message code="solicitudSalida.vacaciones.label" default="Vacaciones" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="vacaciones" name="vacaciones.id" from="${mx.edu.um.rh.Vacaciones.list()}" optionKey="id" required="" value="${solicitudSalida?.vacaciones?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="solicitudSalida.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${solicitudSalida?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="solicitudSalida.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${general.Usuario.list()}" optionKey="id" required="" value="${solicitudSalida?.usuario?.id}" class="many-to-one"/>
</div>

