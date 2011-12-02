<%@ page import="mx.edu.um.rh.Relacion" %>



<div class="fieldcontain ${hasErrors(bean: relacion, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="relacion.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" maxlength="10" value="${relacion?.nombre}"/>
</div>

