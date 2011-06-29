<%@ page import="general.Articulo" %>



<div class="fieldcontain ${hasErrors(bean: articuloInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="articulo.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="256" required="" value="${articuloInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloInstance, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="articulo.cantidad.label" default="Cantidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="cantidad" min="1" required="" value="${fieldValue(bean: articuloInstance, field: 'cantidad')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articuloInstance, field: 'precioUnitario', 'error')} required">
	<label for="precioUnitario">
		<g:message code="articulo.precioUnitario.label" default="Precio Unitario" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="precioUnitario" min="0" required="" value="${fieldValue(bean: articuloInstance, field: 'precioUnitario')}"/>
</div>
<!--
<div class="fieldcontain ${hasErrors(bean: articuloInstance, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="articulo.total.label" default="Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="total" required="" value="${fieldValue(bean: articuloInstance, field: 'total')}"/>
</div>
-->
