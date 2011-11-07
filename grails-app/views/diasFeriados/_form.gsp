<%@ page import="mx.edu.um.rh.DiasFeriados" %>



<div class="fieldcontain ${hasErrors(bean: diasFeriados, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="diasFeriados.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" maxlength="100" value="${diasFeriados?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: diasFeriados, field: 'diadado', 'error')} required">
	<label for="diadado">
		<g:message code="diasFeriados.diadado.label" default="Diadado" />
		<span class="required-indicator">*</span>
	</label>
	<g:checkBox name="diadado" value="${diasFeriados?.diadado}" />
</div>

<div class="fieldcontain ${hasErrors(bean: diasFeriados, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="diasFeriados.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${diasFeriados?.fecha}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: diasFeriados, field: 'fecharegistro', 'error')} required">
	<label for="fecharegistro">
		<g:message code="diasFeriados.fecharegistro.label" default="Fecharegistro" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecharegistro" precision="day"  value="${diasFeriados?.fecharegistro}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: diasFeriados, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="diasFeriados.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${general.Usuario.list()}" optionKey="id" required="" value="${diasFeriados?.user?.id}" class="many-to-one"/>
</div>

