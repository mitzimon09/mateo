<%@ page import="mx.edu.um.rh.SolicitudPermiso" %>



<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'empleado', 'error')} required">
	<label for="empleado">
		<g:message code="solicitudPermiso.empleado.label" default="Empleado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empleado" name="empleado.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${solicitudPermiso?.empleado?.id}" class="many-to-one"/>
</div>
<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'folio', 'error')} required">
	<label for="folio">
		<g:message code="solicitudPermiso.folio.label" default="Folio" />
	</label>
	<g:textField name="folio"  value="${solicitudPermiso?.folio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'fechaInicial', 'error')} required">
	<label for="fechaInicial">
		<g:message code="solicitudPermiso.fechaInicial.label" default="Fecha Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaInicial" precision="day"  value="${solicitudPermiso?.fechaInicial}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'fechaFinal', 'error')} required">
	<label for="fechaFinal">
		<g:message code="solicitudPermiso.fechaFinal.label" default="Fecha Final" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaFinal" precision="day"  value="${solicitudPermiso?.fechaFinal}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'telContacto', 'error')} ">
	<label for="telContacto">
		<g:message code="solicitudPermiso.telContacto.label" default="Tel Contacto" />
		
	</label>
	<g:textField name="telContacto" value="${solicitudPermiso?.telContacto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="solicitudPermiso.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${solicitudPermiso?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'motivo', 'error')} required">
	<label for="motivo">
		<g:message code="solicitudPermiso.motivo.label" default="Motivo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="motivo" required="" value="${solicitudPermiso?.motivo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'actualizacion', 'error')} ">
	<label for="actualizacion">
		<g:message code="solicitudPermiso.actualizacion.label" default="Actualizacion" />
		
	</label>
	<g:checkBox name="actualizacion" value="${solicitudPermiso?.actualizacion}" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'personal', 'error')} ">
	<label for="personal">
		<g:message code="solicitudPermiso.personal.label" default="Personal" />
		
	</label>
	<g:checkBox name="personal" value="${solicitudPermiso?.personal}" />
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="solicitudPermiso.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${solicitudPermiso?.empresa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'hospedaje', 'error')} required">
	<label for="hospedaje">
		<g:message code="solicitudPermiso.hospedaje.label" default="Hospedaje" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="hospedaje" required="" value="${fieldValue(bean: solicitudPermiso, field: 'hospedaje')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'otros', 'error')} required">
	<label for="otros">
		<g:message code="solicitudPermiso.otros.label" default="Otros" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="otros" required="" value="${fieldValue(bean: solicitudPermiso, field: 'otros')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudPermiso, field: 'viaticos', 'error')} required">
	<label for="viaticos">
		<g:message code="solicitudPermiso.viaticos.label" default="Viaticos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="viaticos" required="" value="${fieldValue(bean: solicitudPermiso, field: 'viaticos')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: solicitudVacaciones, field: 'observacion', 'error')} ">
	<label for="observacion">
		<g:message code="solicitudVacaciones.observacion.label" default="Observaciones" />
	</label>
	<g:textField name="observacion"/>
</div>

