<%@ page import="general.TipoCliente" %>



<div class="fieldcontain ${hasErrors(bean: tipoCliente, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoCliente.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="16" required="" value="${tipoCliente?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoCliente, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="tipoCliente.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" maxlength="128" value="${tipoCliente?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoCliente, field: 'margenUtilidad', 'error')} required">
	<label for="margenUtilidad">
		<g:message code="tipoCliente.margenUtilidad.label" default="Margen Utilidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="margenUtilidad" required="" value="${fieldValue(bean: tipoCliente, field: 'margenUtilidad')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoCliente, field: 'base', 'error')} ">
	<label for="base">
		<g:message code="tipoCliente.base.label" default="Base" />
		
	</label>
	<g:checkBox name="base" value="${tipoCliente?.base}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tipoCliente, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="tipoCliente.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${tipoCliente?.empresa?.id}" class="many-to-one"/>
</div>

