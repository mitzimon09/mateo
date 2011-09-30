<%@ page import="mx.edu.um.rh.EmpleadoPuesto" %>



<div class="fieldcontain ${hasErrors(bean: empleadoPuesto, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="empleadoPuesto.status.label" default="Status" />
		
	</label>
	<g:select name="status" from="${empleadoPuesto.constraints.status.inList}" value="${empleadoPuesto?.status}" valueMessagePrefix="empleadoPuesto.status" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoPuesto, field: 'cCosto', 'error')} ">
	<label for="cCosto">
		<g:message code="empleadoPuesto.cCosto.label" default="CC osto" />
		
	</label>
	<g:textField name="cCosto" value="${empleadoPuesto?.cCosto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoPuesto, field: 'ejercicio', 'error')} ">
	<label for="ejercicio">
		<g:message code="empleadoPuesto.ejercicio.label" default="Ejercicio" />
		
	</label>
	<g:textField name="ejercicio" value="${empleadoPuesto?.ejercicio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoPuesto, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="empleadoPuesto.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${empleadoPuesto?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoPuesto, field: 'puesto', 'error')} required">
	<label for="puesto">
		<g:message code="empleadoPuesto.puesto.label" default="Puesto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="puesto" name="puesto.id" from="${mx.edu.um.rh.Puesto.list()}" optionKey="id" required="" value="${empleadoPuesto?.puesto?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoPuesto, field: 'turno', 'error')} required">
	<label for="turno">
		<g:message code="empleadoPuesto.turno.label" default="Turno" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="turno" required="" value="${fieldValue(bean: empleadoPuesto, field: 'turno')}"/>
</div>

