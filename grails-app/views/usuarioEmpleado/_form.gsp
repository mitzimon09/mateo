<%@ page import="general.UsuarioEmpleado" %>



<div class="fieldcontain ${hasErrors(bean: usuarioEmpleado, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="usuarioEmpleado.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${general.Usuario.list()}" optionKey="id" required="" value="${usuarioEmpleado?.usuario?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioEmpleado, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="usuarioEmpleado.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${usuarioEmpleado?.empleado?.id}" class="many-to-one"/>
</div>

