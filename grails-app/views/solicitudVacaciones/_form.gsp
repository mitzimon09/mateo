<%@ page import="mx.edu.um.rh.SolicitudVacaciones" %>



<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="solicitudVacaciones.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${solicitudVacaciones?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="solicitudVacaciones.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${solicitudVacaciones?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'fechaInicial', 'error')} required">
	<label for="fechaInicial">
		<g:message code="solicitudVacaciones.fechaInicial.label" default="Fecha Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaInicial" precision="day"  value="${solicitudVacaciones?.fechaInicial}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'fechaFinal', 'error')} required">
	<label for="fechaFinal">
		<g:message code="solicitudVacaciones.fechaFinal.label" default="Fecha Final" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaFinal" precision="day"  value="${solicitudVacaciones?.fechaFinal}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'nacional', 'error')} ">
	<label for="nacional">
		<g:message code="solicitudVacaciones.nacional.label" default="Nacional" />
		
	</label>
	<g:checkBox name="nacional" value="${solicitudVacaciones?.nacional}" checked = "true"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'destino', 'error')} required">
	<label for="destino">
		<g:message code="solicitudVacaciones.destino.label" default="Destino" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="destino" required="" value="${solicitudVacaciones?.destino}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'kilometros', 'error')} required">
	<label for="kilometros">
		<g:message code="solicitudVacaciones.kilometros.label" default="Kilometros" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="kilometros" required="" value="${fieldValue(bean: solicitudVacaciones, field: 'kilometros')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'telContacto', 'error')} ">
	<label for="telContacto">
		<g:message code="solicitudVacaciones.telContacto.label" default="Contacto Telefono" />
		
	</label>
	<g:textField name="telContacto" value="${solicitudVacaciones?.telContacto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="solicitudVacaciones.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="email" required="" value="${solicitudVacaciones?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'folio', 'error')} ">
	<label for="folio">
		<g:message code="solicitudVacaciones.folio.label" default="Folio" />
		
	</label>
	<g:textField name="folio" value="${solicitudVacaciones?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'folioPago', 'error')} ">
	<label for="folioPago">
		<g:message code="solicitudVacaciones.folioPago.label" default="Folio Pago" />
		
	</label>
	<g:textField name="folioPago" value="${solicitudVacaciones?.folioPago}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'furlough', 'error')} ">
	<label for="furlough">
		<g:message code="solicitudVacaciones.furlough.label" default="Furlough" />
		
	</label>
	<g:textField name="furlough" value="${solicitudVacaciones?.furlough}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'userPrimaVacacional', 'error')} ">
	<label for="userPrimaVacacional">
		<g:message code="solicitudVacaciones.userPrimaVacacional.label" default="Cantidad Prima Vacacional" />
		
	</label>
	<g:field type="number" name="userPrimaVacacional" value="${fieldValue(bean: solicitudVacaciones, field: 'userPrimaVacacional')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'fechaPrimaVacacional', 'error')} ">
	<label for="fechaPrimaVacacional">
		<g:message code="solicitudVacaciones.fechaPrimaVacacional.label" default="Fecha Prima Vacacional" />
		
	</label>
	<g:datePicker name="fechaPrimaVacacional" precision="day"  value="${solicitudVacaciones?.fechaPrimaVacacional}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'visitaPadres', 'error')} ">
	<label for="visitaPadres">
		<g:message code="solicitudVacaciones.visitaPadres.label" default="Visita Padres" />
		
	</label>
	<g:checkBox name="visitaPadres" value="${solicitudVacaciones?.visitaPadres}" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'observacion', 'error')} ">
	<label for="observacion">
		<g:message code="solicitudVacaciones.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textField name="observacion"/>
</div>
