<%@ page import="mx.edu.um.rh.SolicitudSalida" %>



<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="solicitudSalida.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${solicitudSalidaInstance?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'diasVacaciones', 'error')} required">
	<label for="diasVacaciones">
		<g:message code="solicitudSalida.diasVacaciones.label" default="Dias Vacaciones" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="diasVacaciones" required="" value="${fieldValue(bean: solicitudSalidaInstance, field: 'diasVacaciones')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'primaVacacional', 'error')} required">
	<label for="primaVacacional">
		<g:message code="solicitudSalida.primaVacacional.label" default="Prima Vacacional" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="primaVacacional" required="" value="${fieldValue(bean: solicitudSalidaInstance, field: 'primaVacacional')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'fechaInicial', 'error')} required">
	<label for="fechaInicial">
		<g:message code="solicitudSalida.fechaInicial.label" default="Fecha Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaInicial" precision="day" value="${solicitudSalidaInstance?.fechaInicial}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'destino', 'error')} required">
	<label for="destino">
		<g:message code="solicitudSalida.destino.label" default="Destino" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="destino" required="" value="${solicitudSalidaInstance?.destino}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'kilometros', 'error')} required">
	<label for="kilometros">
		<g:message code="solicitudSalida.kilometros.label" default="Kilometros" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="kilometros" required="" value="${fieldValue(bean: solicitudSalidaInstance, field: 'kilometros')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'contacto', 'error')} ">
	<label for="contacto">
		<g:message code="solicitudSalida.contacto.label" default="Contacto" />
		
	</label>
	<g:textField name="contacto" value="${solicitudSalidaInstance?.contacto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="solicitudSalida.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${general.Usuario.list()}" optionKey="id" required="" value="${solicitudSalidaInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'fechaCaptura', 'error')} required">
	<label for="fechaCaptura">
		<g:message code="solicitudSalida.fechaCaptura.label" default="Fecha Captura" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCaptura" precision="day" value="${solicitudSalidaInstance?.fechaCaptura}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'recibeUser', 'error')} ">
	<label for="recibeUser">
		<g:message code="solicitudSalida.recibeUser.label" default="Recibe User" />
		
	</label>
	<g:select id="recibeUser" name="recibeUser.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudSalidaInstance?.recibeUser?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'fechaRecibeRh', 'error')} ">
	<label for="fechaRecibeRh">
		<g:message code="solicitudSalida.fechaRecibeRh.label" default="Fecha Recibe Rh" />
		
	</label>
	<g:datePicker name="fechaRecibeRh" precision="day" value="${solicitudSalidaInstance?.fechaRecibeRh}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'rhUser', 'error')} ">
	<label for="rhUser">
		<g:message code="solicitudSalida.rhUser.label" default="Rh User" />
		
	</label>
	<g:select id="rhUser" name="rhUser.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudSalidaInstance?.rhUser?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'fechaAutorizacionRh', 'error')} ">
	<label for="fechaAutorizacionRh">
		<g:message code="solicitudSalida.fechaAutorizacionRh.label" default="Fecha Autorizacion Rh" />
		
	</label>
	<g:datePicker name="fechaAutorizacionRh" precision="day" value="${solicitudSalidaInstance?.fechaAutorizacionRh}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="solicitudSalida.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${solicitudSalidaInstance?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'visitaPadres', 'error')} required">
	<label for="visitaPadres">
		<g:message code="solicitudSalida.visitaPadres.label" default="Visita Padres" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="visitaPadres" required="" value="${fieldValue(bean: solicitudSalidaInstance, field: 'visitaPadres')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'nacional', 'error')} required">
	<label for="nacional">
		<g:message code="solicitudSalida.nacional.label" default="Nacional" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="nacional" required="" value="${fieldValue(bean: solicitudSalidaInstance, field: 'nacional')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'userPrimaVacacional', 'error')} ">
	<label for="userPrimaVacacional">
		<g:message code="solicitudSalida.userPrimaVacacional.label" default="User Prima Vacacional" />
		
	</label>
	<g:field type="number" name="userPrimaVacacional" value="${fieldValue(bean: solicitudSalidaInstance, field: 'userPrimaVacacional')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'fechaPrimaVacacional', 'error')} ">
	<label for="fechaPrimaVacacional">
		<g:message code="solicitudSalida.fechaPrimaVacacional.label" default="Fecha Prima Vacacional" />
		
	</label>
	<g:datePicker name="fechaPrimaVacacional" precision="day" value="${solicitudSalidaInstance?.fechaPrimaVacacional}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'folio', 'error')} ">
	<label for="folio">
		<g:message code="solicitudSalida.folio.label" default="Folio" />
		
	</label>
	<g:textField name="folio" value="${solicitudSalidaInstance?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'observacionesEmpleado', 'error')} ">
	<label for="observacionesEmpleado">
		<g:message code="solicitudSalida.observacionesEmpleado.label" default="Observaciones Empleado" />
		
	</label>
	<g:textField name="observacionesEmpleado" value="${solicitudSalidaInstance?.observacionesEmpleado}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'folioPago', 'error')} ">
	<label for="folioPago">
		<g:message code="solicitudSalida.folioPago.label" default="Folio Pago" />
		
	</label>
	<g:textField name="folioPago" value="${solicitudSalidaInstance?.folioPago}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'fechaFinal', 'error')} required">
	<label for="fechaFinal">
		<g:message code="solicitudSalida.fechaFinal.label" default="Fecha Final" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaFinal" precision="day" value="${solicitudSalidaInstance?.fechaFinal}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'contactoTelefono', 'error')} ">
	<label for="contactoTelefono">
		<g:message code="solicitudSalida.contactoTelefono.label" default="Contacto Telefono" />
		
	</label>
	<g:textField name="contactoTelefono" value="${solicitudSalidaInstance?.contactoTelefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'contactoEmail', 'error')} ">
	<label for="contactoEmail">
		<g:message code="solicitudSalida.contactoEmail.label" default="Contacto Email" />
		
	</label>
	<g:textField name="contactoEmail" value="${solicitudSalidaInstance?.contactoEmail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'fechaRecibeJefe', 'error')} ">
	<label for="fechaRecibeJefe">
		<g:message code="solicitudSalida.fechaRecibeJefe.label" default="Fecha Recibe Jefe" />
		
	</label>
	<g:datePicker name="fechaRecibeJefe" precision="day" value="${solicitudSalidaInstance?.fechaRecibeJefe}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'jefeUserId', 'error')} ">
	<label for="jefeUserId">
		<g:message code="solicitudSalida.jefeUserId.label" default="Jefe User Id" />
		
	</label>
	<g:select id="jefeUserId" name="jefeUserId.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudSalidaInstance?.jefeUserId?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'observacionesRh', 'error')} ">
	<label for="observacionesRh">
		<g:message code="solicitudSalida.observacionesRh.label" default="Observaciones Rh" />
		
	</label>
	<g:textField name="observacionesRh" value="${solicitudSalidaInstance?.observacionesRh}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudSalidaInstance, field: 'furlough', 'error')} ">
	<label for="furlough">
		<g:message code="solicitudSalida.furlough.label" default="Furlough" />
		
	</label>
	<g:textField name="furlough" value="${solicitudSalidaInstance?.furlough}"/>
</div>

