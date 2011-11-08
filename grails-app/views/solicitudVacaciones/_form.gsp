<%@ page import="mx.edu.um.rh.SolicitudVacaciones" %>



<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="solicitudVacaciones.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${solicitudesVacaciones?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="solicitudVacaciones.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${solicitudesVacaciones?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'fechaInicial', 'error')} required">
	<label for="fechaInicial">
		<g:message code="solicitudVacaciones.fechaInicial.label" default="Fecha Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaInicial" precision="day"  value="${solicitudesVacaciones?.fechaInicial}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'fechaFinal', 'error')} required">
	<label for="fechaFinal">
		<g:message code="solicitudVacaciones.fechaFinal.label" default="Fecha Final" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaFinal" precision="day"  value="${solicitudesVacaciones?.fechaFinal}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'nacional', 'error')} ">
	<label for="nacional">
		<g:message code="solicitudVacaciones.nacional.label" default="Nacional" />
		
	</label>
	<g:checkBox name="nacional" value="${solicitudesVacaciones?.nacional}" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'destino', 'error')} required">
	<label for="destino">
		<g:message code="solicitudVacaciones.destino.label" default="Destino" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="destino" required="" value="${solicitudesVacaciones?.destino}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'kilometros', 'error')} required">
	<label for="kilometros">
		<g:message code="solicitudVacaciones.kilometros.label" default="Kilometros" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="kilometros" required="" value="${fieldValue(bean: solicitudesVacaciones, field: 'kilometros')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'contactoTelefono', 'error')} ">
	<label for="contactoTelefono">
		<g:message code="solicitudVacaciones.contactoTelefono.label" default="Contacto Telefono" />
		
	</label>
	<g:textField name="contactoTelefono" value="${solicitudesVacaciones?.contactoTelefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="solicitudVacaciones.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${solicitudesVacaciones?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="solicitudVacaciones.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textField name="observaciones" value="${solicitudesVacaciones?.observaciones}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'folio', 'error')} ">
	<label for="folio">
		<g:message code="solicitudVacaciones.folio.label" default="Folio" />
		
	</label>
	<g:textField name="folio" value="${solicitudesVacaciones?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'folioPago', 'error')} ">
	<label for="folioPago">
		<g:message code="solicitudVacaciones.folioPago.label" default="Folio Pago" />
		
	</label>
	<g:textField name="folioPago" value="${solicitudesVacaciones?.folioPago}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'furlough', 'error')} ">
	<label for="furlough">
		<g:message code="solicitudVacaciones.furlough.label" default="Furlough" />
		
	</label>
	<g:textField name="furlough" value="${solicitudesVacaciones?.furlough}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'primaVacacional', 'error')} ">
	<label for="primaVacacional">
		<g:message code="solicitudVacaciones.primaVacacional.label" default="Prima Vacacional" />
		
	</label>
	<g:checkBox name="primaVacacional" value="${solicitudesVacaciones?.primaVacacional}" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'userPrimaVacacional', 'error')} ">
	<label for="userPrimaVacacional">
		<g:message code="solicitudVacaciones.userPrimaVacacional.label" default="User Prima Vacacional" />
		
	</label>
	<g:field type="number" name="userPrimaVacacional" value="${fieldValue(bean: solicitudesVacaciones, field: 'userPrimaVacacional')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'fechaPrimaVacacional', 'error')} ">
	<label for="fechaPrimaVacacional">
		<g:message code="solicitudVacaciones.fechaPrimaVacacional.label" default="Fecha Prima Vacacional" />
		
	</label>
	<g:datePicker name="fechaPrimaVacacional" precision="day"  value="${solicitudesVacaciones?.fechaPrimaVacacional}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudesVacaciones, field: 'visitaPadres', 'error')} ">
	<label for="visitaPadres">
		<g:message code="solicitudVacaciones.visitaPadres.label" default="Visita Padres" />
		
	</label>
	<g:checkBox name="visitaPadres" value="${solicitudesVacaciones?.visitaPadres}" />
</div>

