<%@ page import="mx.edu.um.rh.Observaciones" %>



<div class="fieldcontain ${hasErrors(bean: observaciones, field: 'observaciones', 'error')} required">
	<label for="observaciones">
		<g:message code="observaciones.observaciones.label" default="Observaciones" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="500" required="" value="${observaciones?.observaciones}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: observaciones, field: 'solicitudRH', 'error')} required">
	<label for="solicitudRH">
		<g:message code="observaciones.solicitudRH.label" default="Solicitud RH" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="solicitudRH" name="solicitudRH.id" from="${mx.edu.um.rh.SolicitudRH.list()}" optionKey="id" required="" value="${observaciones?.solicitudRH?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: observaciones, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="observaciones.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${general.Usuario.list()}" optionKey="id" required="" value="${observaciones?.usuario?.id}" class="many-to-one"/>
</div>

