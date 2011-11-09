<%@ page import="mx.edu.um.rh.TipoDependiente" %>



<div class="fieldcontain ${hasErrors(bean: tipoDependiente, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="tipoDependiente.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" maxlength="10" value="${tipoDependiente?.nombre}"/>
</div>

