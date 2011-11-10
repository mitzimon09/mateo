<%@ page import="mx.edu.um.rh.Nacionalidades" %>



<div class="fieldcontain ${hasErrors(bean: nacionalidades, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="nacionalidades.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" maxlength="60" value="${nacionalidades?.nombre}"/>
</div>

