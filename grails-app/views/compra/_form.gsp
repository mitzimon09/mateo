<%@ page import="general.Compra" %>



<div class="fieldcontain ${hasErrors(bean: compraInstance, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="compra.total.label" default="Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="total" required="" value="${fieldValue(bean: compraInstance, field: 'total')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: compraInstance, field: 'articulos', 'error')} ">
	<label for="articulos">
		<g:message code="compra.articulos.label" default="Articulos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${compraInstance?.articulos?}" var="a">
    <li><g:link controller="articulo" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="articulo" action="create" params="['compra.id': compraInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'articulo.label', default: 'Articulo')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: compraInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="compra.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${compraInstance?.status}"/>
</div>

