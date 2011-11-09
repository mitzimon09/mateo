<%@ page import="mx.edu.um.rh.Universidad" %>



<div class="fieldcontain ${hasErrors(bean: universidad, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="universidad.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" maxlength="60" value="${universidad?.nombre}"/>
</div>

