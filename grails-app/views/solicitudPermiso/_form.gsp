<%@ page import="mx.edu.um.rh.SolicitudPermiso" %>



<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="solicitudPermiso.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${solicitudPermisoInstance?.empleado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'fechaInicial', 'error')} required">
	<label for="fechaInicial">
		<g:message code="solicitudPermiso.fechaInicial.label" default="Fecha Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaInicial" precision="day"  value="${solicitudPermisoInstance?.fechaInicial}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'fechaFinal', 'error')} required">
	<label for="fechaFinal">
		<g:message code="solicitudPermiso.fechaFinal.label" default="Fecha Final" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaFinal" precision="day"  value="${solicitudPermisoInstance?.fechaFinal}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'usuarioCrea', 'error')} required">
	<label for="usuarioCrea">
		<g:message code="solicitudPermiso.usuarioCrea.label" default="Usuario Crea" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuarioCrea" name="usuarioCrea.id" from="${general.Usuario.list()}" optionKey="id" required="" value="${solicitudPermisoInstance?.usuarioCrea?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'usuarioRecibe', 'error')} ">
	<label for="usuarioRecibe">
		<g:message code="solicitudPermiso.usuarioRecibe.label" default="Usuario Recibe" />
		
	</label>
	<g:select id="usuarioRecibe" name="usuarioRecibe.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudPermisoInstance?.usuarioRecibe?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'fechaRecibe', 'error')} ">
	<label for="fechaRecibe">
		<g:message code="solicitudPermiso.fechaRecibe.label" default="Fecha Recibe" />
		
	</label>
	<g:datePicker name="fechaRecibe" precision="day"  value="${solicitudPermisoInstance?.fechaRecibe}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'usuarioAutoriza', 'error')} ">
	<label for="usuarioAutoriza">
		<g:message code="solicitudPermiso.usuarioAutoriza.label" default="Usuario Autoriza" />
		
	</label>
	<g:select id="usuarioAutoriza" name="usuarioAutoriza.id" from="${general.Usuario.list()}" optionKey="id" value="${solicitudPermisoInstance?.usuarioAutoriza?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'fechaAutoriza', 'error')} ">
	<label for="fechaAutoriza">
		<g:message code="solicitudPermiso.fechaAutoriza.label" default="Fecha Autoriza" />
		
	</label>
	<g:datePicker name="fechaAutoriza" precision="day"  value="${solicitudPermisoInstance?.fechaAutoriza}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="solicitudPermiso.observaciones.label" default="Observaciones" />
		
	</label>
	<g:select id="observaciones" name="observaciones.id" from="${mx.edu.um.rh.Observaciones.list()}" optionKey="id" value="${solicitudPermisoInstance?.observaciones?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'telContacto', 'error')} ">
	<label for="telContacto">
		<g:message code="solicitudPermiso.telContacto.label" default="Tel Contacto" />
		
	</label>
	<g:textField name="telContacto" value="${solicitudPermisoInstance?.telContacto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="solicitudPermiso.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${solicitudPermisoInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'solicitudSalida', 'error')} ">
	<label for="solicitudSalida">
		<g:message code="solicitudPermiso.solicitudSalida.label" default="Solicitud Salida" />
		
	</label>
	<g:select id="solicitudSalida" name="solicitudSalida.id" from="${mx.edu.um.rh.SolicitudSalida.list()}" optionKey="id" value="${solicitudPermisoInstance?.solicitudSalida?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'vacaciones', 'error')} ">
	<label for="vacaciones">
		<g:message code="solicitudPermiso.vacaciones.label" default="Vacaciones" />
		
	</label>
	<g:select id="vacaciones" name="vacaciones.id" from="${mx.edu.um.rh.Vacaciones.list()}" optionKey="id" value="${solicitudPermisoInstance?.vacaciones?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="solicitudPermiso.status.label" default="Status" />
		
	</label>
	<g:select name="status" from="${solicitudPermisoInstance.constraints.status.inList}" value="${solicitudPermisoInstance?.status}" valueMessagePrefix="solicitudPermiso.status" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'jefeCCosto', 'error')} ">
	<label for="jefeCCosto">
		<g:message code="solicitudPermiso.jefeCCosto.label" default="Jefe CC osto" />
		
	</label>
	<g:select id="jefeCCosto" name="jefeCCosto.id" from="${mx.edu.um.rh.JefeCCosto.list()}" optionKey="id" value="${solicitudPermisoInstance?.jefeCCosto?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'folio', 'error')} ">
	<label for="folio">
		<g:message code="solicitudPermiso.folio.label" default="Folio" />
		
	</label>
	<g:textField name="folio" value="${solicitudPermisoInstance?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'motivo', 'error')} required">
	<label for="motivo">
		<g:message code="solicitudPermiso.motivo.label" default="Motivo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="motivo" required="" value="${solicitudPermisoInstance?.motivo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'actualizacion', 'error')} ">
	<label for="actualizacion">
		<g:message code="solicitudPermiso.actualizacion.label" default="Actualizacion" />
		
	</label>
	<g:checkBox name="actualizacion" value="${solicitudPermisoInstance?.actualizacion}" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="solicitudPermiso.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${solicitudPermisoInstance?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'hospedaje', 'error')} required">
	<label for="hospedaje">
		<g:message code="solicitudPermiso.hospedaje.label" default="Hospedaje" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="hospedaje" required="" value="${fieldValue(bean: solicitudPermisoInstance, field: 'hospedaje')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'otros', 'error')} required">
	<label for="otros">
		<g:message code="solicitudPermiso.otros.label" default="Otros" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="otros" required="" value="${fieldValue(bean: solicitudPermisoInstance, field: 'otros')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'totalDeGastos', 'error')} required">
	<label for="totalDeGastos">
		<g:message code="solicitudPermiso.totalDeGastos.label" default="Total De Gastos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="totalDeGastos" required="" value="${fieldValue(bean: solicitudPermisoInstance, field: 'totalDeGastos')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermisoInstance, field: 'viaticos', 'error')} required">
	<label for="viaticos">
		<g:message code="solicitudPermiso.viaticos.label" default="Viaticos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="viaticos" required="" value="${fieldValue(bean: solicitudPermisoInstance, field: 'viaticos')}"/>
</div>

