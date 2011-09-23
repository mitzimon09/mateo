<%@ page import="general.Documento" %>



<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'concepto', 'error')} ">
	<label for="concepto">
		<g:message code="documento.concepto.label" default="Concepto" />
		
	</label>
	<g:textField name="concepto" value="${documentoInstance?.concepto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="documento.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="128" required="" value="${documentoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'naturaleza', 'error')} ">
	<label for="naturaleza">
		<g:message code="documento.naturaleza.label" default="Naturaleza" />
		
	</label>
	<g:textField name="naturaleza" maxlength="10" value="${documentoInstance?.naturaleza}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="documento.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textField name="observaciones" maxlength="128" value="${documentoInstance?.observaciones}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="documento.status.label" default="Status" />
		
	</label>
	<g:select name="status" from="${documentoInstance.constraints.status.inList}" value="${documentoInstance?.status}" valueMessagePrefix="documento.status" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'cheque', 'error')} ">
	<label for="cheque">
		<g:message code="documento.cheque.label" default="Cheque" />
		
	</label>
	<g:textField name="cheque" value="${documentoInstance?.cheque}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="documento.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day" value="${documentoInstance?.fecha}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'importe', 'error')} required">
	<label for="importe">
		<g:message code="documento.importe.label" default="Importe" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="importe" required="" value="${fieldValue(bean: documentoInstance, field: 'importe')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'iva', 'error')} required">
	<label for="iva">
		<g:message code="documento.iva.label" default="Iva" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="iva" required="" value="${fieldValue(bean: documentoInstance, field: 'iva')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="documento.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${general.Usuario.list()}" optionKey="id" required="" value="${documentoInstance?.usuario?.id}" class="many-to-one"/>
</div>

