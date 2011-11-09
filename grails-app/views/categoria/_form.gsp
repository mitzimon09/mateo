<%@ page import="mx.edu.um.rh.Categoria" %>



<div class="fieldcontain ${hasErrors(bean: categoria, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="categoria.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" maxlength="50" required="" value="${categoria?.nombre}"/>
</div>

