<%@ page import="mx.edu.um.rh.SolicitudVacaciones" %>



<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="solicitudVacaciones.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${solicitudVacacionesInstance?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="solicitudVacaciones.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${solicitudVacacionesInstance?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'fechaCaptura', 'error')} required">
	<label for="fechaCaptura">
		<g:message code="solicitudVacaciones.fechaCaptura.label" default="Fecha Captura" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCaptura" precision="day"  value="${solicitudVacacionesInstance?.fechaCaptura}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'fechaInicial', 'error')} required">
	<label for="fechaInicial">
		<g:message code="solicitudVacaciones.fechaInicial.label" default="Fecha Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaInicial" precision="day"  value="${solicitudVacacionesInstance?.fechaInicial}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'fechaFinal', 'error')} required">
	<label for="fechaFinal">
		<g:message code="solicitudVacaciones.fechaFinal.label" default="Fecha Final" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaFinal" precision="day"  value="${solicitudVacacionesInstance?.fechaFinal}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'fechaRecibeJefe', 'error')} ">
	<label for="fechaRecibeJefe">
		<g:message code="solicitudVacaciones.fechaRecibeJefe.label" default="Fecha Recibe Jefe" />
		
	</label>
	<g:datePicker name="fechaRecibeJefe" precision="day"  value="${solicitudVacacionesInstance?.fechaRecibeJefe}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'fechaRecibeRh', 'error')} ">
	<label for="fechaRecibeRh">
		<g:message code="solicitudVacaciones.fechaRecibeRh.label" default="Fecha Recibe Rh" />
		
	</label>
	<g:datePicker name="fechaRecibeRh" precision="day"  value="${solicitudVacacionesInstance?.fechaRecibeRh}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'fechaAutorizacionRh', 'error')} ">
	<label for="fechaAutorizacionRh">
		<g:message code="solicitudVacaciones.fechaAutorizacionRh.label" default="Fecha Autorizacion Rh" />
		
	</label>
	<g:datePicker name="fechaAutorizacionRh" precision="day"  value="${solicitudVacacionesInstance?.fechaAutorizacionRh}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'diasVacaciones', 'error')} required">
	<label for="diasVacaciones">
		<g:message code="solicitudVacaciones.diasVacaciones.label" default="Dias Vacaciones" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="diasVacaciones" required="" value="${fieldValue(bean: solicitudVacacionesInstance, field: 'diasVacaciones')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'userPrimaVacacional', 'error')} ">
	<label for="userPrimaVacacional">
		<g:message code="solicitudVacaciones.userPrimaVacacional.label" default="User Prima Vacacional" />
		
	</label>
	<g:field type="number" name="userPrimaVacacional" value="${fieldValue(bean: solicitudVacacionesInstance, field: 'userPrimaVacacional')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'fechaPrimaVacacional', 'error')} ">
	<label for="fechaPrimaVacacional">
		<g:message code="solicitudVacaciones.fechaPrimaVacacional.label" default="Fecha Prima Vacacional" />
		
	</label>
	<g:datePicker name="fechaPrimaVacacional" precision="day"  value="${solicitudVacacionesInstance?.fechaPrimaVacacional}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'destino', 'error')} required">
	<label for="destino">
		<g:message code="solicitudVacaciones.destino.label" default="Destino" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="destino" required="" value="${solicitudVacacionesInstance?.destino}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'kilometros', 'error')} required">
	<label for="kilometros">
		<g:message code="solicitudVacaciones.kilometros.label" default="Kilometros" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="kilometros" required="" value="${fieldValue(bean: solicitudVacacionesInstance, field: 'kilometros')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'contactoTelefono', 'error')} ">
	<label for="contactoTelefono">
		<g:message code="solicitudVacaciones.contactoTelefono.label" default="Contacto Telefono" />
		
	</label>
	<g:textField name="contactoTelefono" value="${solicitudVacacionesInstance?.contactoTelefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="solicitudVacaciones.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${solicitudVacacionesInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="solicitudVacaciones.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textField name="observaciones" value="${solicitudVacacionesInstance?.observaciones}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'folio', 'error')} ">
	<label for="folio">
		<g:message code="solicitudVacaciones.folio.label" default="Folio" />
		
	</label>
	<g:textField name="folio" value="${solicitudVacacionesInstance?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'folioPago', 'error')} ">
	<label for="folioPago">
		<g:message code="solicitudVacaciones.folioPago.label" default="Folio Pago" />
		
	</label>
	<g:textField name="folioPago" value="${solicitudVacacionesInstance?.folioPago}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="solicitudVacaciones.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${general.Usuario.list()}" optionKey="id" required="" value="${solicitudVacacionesInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'recibeUser', 'error')} ">
	<label for="recibeUser">
		<g:message code="solicitudVacaciones.recibeUser.label" default="Recibe User" />
		
	</label>
	<g:select id="recibeUser" name="recibeUser.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudVacacionesInstance?.recibeUser?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'rhUser', 'error')} ">
	<label for="rhUser">
		<g:message code="solicitudVacaciones.rhUser.label" default="Rh User" />
		
	</label>
	<g:select id="rhUser" name="rhUser.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudVacacionesInstance?.rhUser?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="solicitudVacaciones.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${solicitudVacacionesInstance?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'jefeUserId', 'error')} ">
	<label for="jefeUserId">
		<g:message code="solicitudVacaciones.jefeUserId.label" default="Jefe User Id" />
		
	</label>
	<g:select id="jefeUserId" name="jefeUserId.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudVacacionesInstance?.jefeUserId?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'furlough', 'error')} ">
	<label for="furlough">
		<g:message code="solicitudVacaciones.furlough.label" default="Furlough" />
		
	</label>
	<g:textField name="furlough" value="${solicitudVacacionesInstance?.furlough}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'nacional', 'error')} ">
	<label for="nacional">
		<g:message code="solicitudVacaciones.nacional.label" default="Nacional" />
		
	</label>
	<g:checkBox name="nacional" value="${solicitudVacacionesInstance?.nacional}" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'primaVacacional', 'error')} ">
	<label for="primaVacacional">
		<g:message code="solicitudVacaciones.primaVacacional.label" default="Prima Vacacional" />
		
	</label>
	<g:checkBox name="primaVacacional" value="${solicitudVacacionesInstance?.primaVacacional}" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacacionesInstance, field: 'visitaPadres', 'error')} ">
	<label for="visitaPadres">
		<g:message code="solicitudVacaciones.visitaPadres.label" default="Visita Padres" />
		
	</label>
	<g:checkBox name="visitaPadres" value="${solicitudVacacionesInstance?.visitaPadres}" />
</div>

