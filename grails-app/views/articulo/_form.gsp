<%@ page import="general.Articulo" %>

<div class="fieldcontain ${hasErrors(bean: articulo, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="articulo.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="256" required="" value="${articulo?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articulo, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="articulo.cantidad.label" default="Cantidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="cantidad" min="1" required="" value="${fieldValue(bean: articulo, field: 'cantidad')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articulo, field: 'precioUnitario', 'error')} required">
	<label for="precioUnitario">
		<g:message code="articulo.precioUnitario.label" default="Precio Unitario" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="precioUnitario" min="0" required="" value="${fieldValue(bean: articulo, field: 'precioUnitario')}"/>
</div>
<!--
<div class="fieldcontain ${hasErrors(bean: articulo, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="articulo.total.label" default="Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="total" required="" value="${fieldValue(bean: articulo, field: 'total')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articulo, field: 'compra', 'error')} required">
	<label for="compra">
		<g:message code="articulo.compra.label" default="Compra" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="compra" name="compra.id" from="${general.Compra.list()}" optionKey="id" required="" value="${articulo?.compra?.id}" class="many-to-one"/>
</div>
-->
