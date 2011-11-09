<%@ page import="mx.edu.um.rh.NivelEstudios" %>



<div class="fieldcontain ${hasErrors(bean: nivelEstudios, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="nivelEstudios.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" maxlength="60" value="${nivelEstudios?.nombre}"/>
</div>

