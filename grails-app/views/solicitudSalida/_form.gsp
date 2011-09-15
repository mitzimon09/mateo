<%@ page import="mx.edu.um.rh.SolicitudSalida" %>



<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="solicitudSalida.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${solicitudSalida?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'diasVacaciones', 'error')} required">
	<label for="diasVacaciones">
		<g:message code="solicitudSalida.diasVacaciones.label" default="Dias Vacaciones" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="diasVacaciones" required="" value="${fieldValue(bean: solicitudSalida, field: 'diasVacaciones')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'primaVacacional', 'error')} required">
	<label for="primaVacacional">
		<g:message code="solicitudSalida.primaVacacional.label" default="Prima Vacacional" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="primaVacacional" required="" value="${fieldValue(bean: solicitudSalida, field: 'primaVacacional')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'fechaInicial', 'error')} required">
	<label for="fechaInicial">
		<g:message code="solicitudSalida.fechaInicial.label" default="Fecha Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaInicial" precision="day" value="${solicitudSalida?.fechaInicial}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'destino', 'error')} required">
	<label for="destino">
		<g:message code="solicitudSalida.destino.label" default="Destino" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="destino" required="" value="${solicitudSalida?.destino}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'kilometros', 'error')} required">
	<label for="kilometros">
		<g:message code="solicitudSalida.kilometros.label" default="Kilometros" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="kilometros" required="" value="${fieldValue(bean: solicitudSalida, field: 'kilometros')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'contacto', 'error')} ">
	<label for="contacto">
		<g:message code="solicitudSalida.contacto.label" default="Contacto" />
		
	</label>
	<g:textField name="contacto" value="${solicitudSalida?.contacto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="solicitudSalida.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${general.Usuario.list()}" optionKey="id" required="" value="${solicitudSalida?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'fechaCaptura', 'error')} required">
	<label for="fechaCaptura">
		<g:message code="solicitudSalida.fechaCaptura.label" default="Fecha Captura" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCaptura" precision="day" value="${solicitudSalida?.fechaCaptura}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'recibeUser', 'error')} ">
	<label for="recibeUser">
		<g:message code="solicitudSalida.recibeUser.label" default="Recibe User" />
		
	</label>
	<g:select id="recibeUser" name="recibeUser.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudSalida?.recibeUser?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'fechaRecibeRh', 'error')} ">
	<label for="fechaRecibeRh">
		<g:message code="solicitudSalida.fechaRecibeRh.label" default="Fecha Recibe Rh" />
		
	</label>
	<g:datePicker name="fechaRecibeRh" precision="day" value="${solicitudSalida?.fechaRecibeRh}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'rhUser', 'error')} ">
	<label for="rhUser">
		<g:message code="solicitudSalida.rhUser.label" default="Rh User" />
		
	</label>
	<g:select id="rhUser" name="rhUser.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudSalida?.rhUser?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'fechaAutorizacionRh', 'error')} ">
	<label for="fechaAutorizacionRh">
		<g:message code="solicitudSalida.fechaAutorizacionRh.label" default="Fecha Autorizacion Rh" />
		
	</label>
	<g:datePicker name="fechaAutorizacionRh" precision="day" value="${solicitudSalida?.fechaAutorizacionRh}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="solicitudSalida.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${solicitudSalida?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'visitaPadres', 'error')} required">
	<label for="visitaPadres">
		<g:message code="solicitudSalida.visitaPadres.label" default="Visita Padres" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="visitaPadres" required="" value="${fieldValue(bean: solicitudSalida, field: 'visitaPadres')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'nacional', 'error')} required">
	<label for="nacional">
		<g:message code="solicitudSalida.nacional.label" default="Nacional" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="nacional" required="" value="${fieldValue(bean: solicitudSalida, field: 'nacional')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'userPrimaVacacional', 'error')} ">
	<label for="userPrimaVacacional">
		<g:message code="solicitudSalida.userPrimaVacacional.label" default="User Prima Vacacional" />
		
	</label>
	<g:field type="number" name="userPrimaVacacional" value="${fieldValue(bean: solicitudSalida, field: 'userPrimaVacacional')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'fechaPrimaVacacional', 'error')} ">
	<label for="fechaPrimaVacacional">
		<g:message code="solicitudSalida.fechaPrimaVacacional.label" default="Fecha Prima Vacacional" />
		
	</label>
	<g:datePicker name="fechaPrimaVacacional" precision="day" value="${solicitudSalida?.fechaPrimaVacacional}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'folio', 'error')} ">
	<label for="folio">
		<g:message code="solicitudSalida.folio.label" default="Folio" />
		
	</label>
	<g:textField name="folio" value="${solicitudSalida?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'observacionesEmpleado', 'error')} ">
	<label for="observacionesEmpleado">
		<g:message code="solicitudSalida.observacionesEmpleado.label" default="Observaciones Empleado" />
		
	</label>
	<g:textField name="observacionesEmpleado" value="${solicitudSalida?.observacionesEmpleado}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'folioPago', 'error')} ">
	<label for="folioPago">
		<g:message code="solicitudSalida.folioPago.label" default="Folio Pago" />
		
	</label>
	<g:textField name="folioPago" value="${solicitudSalida?.folioPago}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'fechaFinal', 'error')} required">
	<label for="fechaFinal">
		<g:message code="solicitudSalida.fechaFinal.label" default="Fecha Final" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaFinal" precision="day" value="${solicitudSalida?.fechaFinal}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'contactoTelefono', 'error')} ">
	<label for="contactoTelefono">
		<g:message code="solicitudSalida.contactoTelefono.label" default="Contacto Telefono" />
		
	</label>
	<g:textField name="contactoTelefono" value="${solicitudSalida?.contactoTelefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'contactoEmail', 'error')} ">
	<label for="contactoEmail">
		<g:message code="solicitudSalida.contactoEmail.label" default="Contacto Email" />
		
	</label>
	<g:textField name="contactoEmail" value="${solicitudSalida?.contactoEmail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'fechaRecibeJefe', 'error')} ">
	<label for="fechaRecibeJefe">
		<g:message code="solicitudSalida.fechaRecibeJefe.label" default="Fecha Recibe Jefe" />
		
	</label>
	<g:datePicker name="fechaRecibeJefe" precision="day" value="${solicitudSalida?.fechaRecibeJefe}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'jefeUserId', 'error')} ">
	<label for="jefeUserId">
		<g:message code="solicitudSalida.jefeUserId.label" default="Jefe User Id" />
		
	</label>
	<g:select id="jefeUserId" name="jefeUserId.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudSalida?.jefeUserId?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'observacionesRh', 'error')} ">
	<label for="observacionesRh">
		<g:message code="solicitudSalida.observacionesRh.label" default="Observaciones Rh" />
		
	</label>
	<g:textField name="observacionesRh" value="${solicitudSalida?.observacionesRh}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalida, field: 'furlough', 'error')} ">
	<label for="furlough">
		<g:message code="solicitudSalida.furlough.label" default="Furlough" />
		
	</label>
	<g:textField name="furlough" value="${solicitudSalida?.furlough}"/>
</div>

