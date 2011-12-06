<%@ page import="mx.edu.um.rh.EmpleadoDependientes" %>



<div class="fieldcontain ${hasErrors(bean: empleadoDependientes, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="empleadoDependientes.nombre.label" default="Nombre" />
		
	</label>
	<g:textArea name="nombre" cols="40" rows="5" maxlength="255" value="${empleadoDependientes?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoDependientes, field: 'estudios', 'error')} ">
	<label for="estudios">
		<g:message code="empleadoDependientes.estudios.label" default="Estudios" />
		
	</label>
	<g:textField name="estudios" maxlength="100" value="${empleadoDependientes?.estudios}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoDependientes, field: 'grado', 'error')} ">
	<label for="grado">
		<g:message code="empleadoDependientes.grado.label" default="Grado" />
		
	</label>
	<g:field type="number" name="grado" value="${fieldValue(bean: empleadoDependientes, field: 'grado')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoDependientes, field: 'matricula', 'error')} ">
	<label for="matricula">
		<g:message code="empleadoDependientes.matricula.label" default="Matricula" />
		
	</label>
	<g:textField name="matricula" maxlength="7" value="${empleadoDependientes?.matricula}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoDependientes, field: 'bday', 'error')} required">
	<label for="bday">
		<g:message code="empleadoDependientes.bday.label" default="Bday" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="bday" precision="day"  value="${empleadoDependientes?.bday}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoDependientes, field: 'colegio', 'error')} required">
	<label for="colegio">
		<g:message code="empleadoDependientes.colegio.label" default="Colegio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="colegio" name="colegio.id" from="${mx.edu.um.rh.Colegio.list()}" optionKey="id" required="" value="${empleadoDependientes?.colegio?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoDependientes, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="empleadoDependientes.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${empleadoDependientes?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleadoDependientes, field: 'relacion', 'error')} required">
	<label for="relacion">
		<g:message code="empleadoDependientes.relacion.label" default="Relacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="relacion" name="relacion.id" from="${mx.edu.um.rh.Relacion.list()}" optionKey="id" required="" value="${empleadoDependientes?.relacion?.id}" class="many-to-one"/>
</div>

