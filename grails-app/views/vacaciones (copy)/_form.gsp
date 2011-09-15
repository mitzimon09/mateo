<%@ page import="mx.edu.um.rh.Vacaciones" %>



<div class="fieldcontain ${hasErrors(bean: vacaciones, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="vacaciones.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" maxlength="100" value="${vacaciones?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vacaciones, field: 'dias', 'error')} required">
	<label for="dias">
		<g:message code="vacaciones.dias.label" default="Dias" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="dias" required="" value="${fieldValue(bean: vacaciones, field: 'dias')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vacaciones, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="vacaciones.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${general.Usuario.list()}" optionKey="id" required="" value="${vacaciones?.usuario?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vacaciones, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="vacaciones.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${vacaciones?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vacaciones, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="vacaciones.status.label" default="Status" />
		
	</label>
	<g:select name="status" from="${vacaciones.constraints.status.inList}" value="${vacaciones?.status}" valueMessagePrefix="vacaciones.status" noSelection="['': '']"/>
</div>


<div class="fieldcontain ${hasErrors(bean: vacaciones, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="vacaciones.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${vacaciones?.empresa?.id}" class="many-to-one"/>
</div>

