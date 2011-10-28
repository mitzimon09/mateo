<%@ page import="mx.edu.um.rh.SolicitudRH" %>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'folio', 'error')} ">
	<label for="folio">
		<g:message code="solicitudRH.folio.label" default="Folio" />
		
	</label>
	<g:textField name="folio" maxlength="50" value="${solicitudRH?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="solicitudRH.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${solicitudRH?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'fechaInicial', 'error')} required">
	<label for="fechaInicial">
		<g:message code="solicitudRH.fechaInicial.label" default="Fecha Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaInicial" precision="day" value="${solicitudRH?.fechaInicial}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'fechaFinal', 'error')} required">
	<label for="fechaFinal">
		<g:message code="solicitudRH.fechaFinal.label" default="Fecha Final" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaFinal" precision="day" value="${solicitudRH?.fechaFinal}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'usuarioRecibe', 'error')} ">
	<label for="usuarioRecibe">
		<g:message code="solicitudRH.usuarioRecibe.label" default="Usuario Recibe" />
		
	</label>
	<g:select id="usuarioRecibe" name="usuarioRecibe.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudRH?.usuarioRecibe?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'fechaRecibe', 'error')} ">
	<label for="fechaRecibe">
		<g:message code="solicitudRH.fechaRecibe.label" default="Fecha Recibe" />
		
	</label>
	<g:datePicker name="fechaRecibe" precision="day" value="${solicitudRH?.fechaRecibe}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'usuarioAutoriza', 'error')} ">
	<label for="usuarioAutoriza">
		<g:message code="solicitudRH.usuarioAutoriza.label" default="Usuario Autoriza" />
		
	</label>
	<g:select id="usuarioAutoriza" name="usuarioAutoriza.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudRH?.usuarioAutoriza?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'fechaAutoriza', 'error')} ">
	<label for="fechaAutoriza">
		<g:message code="solicitudRH.fechaAutoriza.label" default="Fecha Autoriza" />
		
	</label>
	<g:datePicker name="fechaAutoriza" precision="day" value="${solicitudRH?.fechaAutoriza}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="solicitudRH.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textField name="observaciones" value="${solicitudRH?.observaciones}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'telContacto', 'error')} ">
	<label for="telContacto">
		<g:message code="solicitudRH.telContacto.label" default="Tel Contacto" />
		
	</label>
	<g:textField name="telContacto" value="${solicitudRH?.telContacto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="solicitudRH.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${solicitudRH?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'solicitudSalida', 'error')} ">
	<label for="solicitudSalida">
		<g:message code="solicitudRH.solicitudSalida.label" default="Solicitud Salida" />
		
	</label>
	<g:select id="solicitudSalida" name="solicitudSalida.id" from="${mx.edu.um.rh.SolicitudSalida.list()}" optionKey="id" value="${solicitudRH?.solicitudSalida?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'vacaciones', 'error')} ">
	<label for="vacaciones">
		<g:message code="solicitudRH.vacaciones.label" default="Vacaciones" />
		
	</label>
	<g:select id="vacaciones" name="vacaciones.id" from="${mx.edu.um.rh.Vacaciones.list()}" optionKey="id" value="${solicitudRH?.vacaciones?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="solicitudRH.status.label" default="Status" />
		
	</label>
	<g:select name="status" from="${solicitudRH.constraints.status.inList}" value="${solicitudRH?.status}" valueMessagePrefix="solicitudRH.status" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudRH, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="solicitudRH.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${solicitudRH?.empresa?.id}" class="many-to-one"/>
</div>

