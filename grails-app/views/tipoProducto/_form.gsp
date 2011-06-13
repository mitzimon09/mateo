<%@ page import="inventario.TipoProducto" %>



<div class="fieldcontain ${hasErrors(bean: tipoProductoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="tipoProducto.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${tipoProductoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoProductoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="tipoProducto.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" maxlength="200" value="${tipoProductoInstance?.descripcion}"/>
</div>

