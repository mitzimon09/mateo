<%@ page import="mx.edu.um.rh.JefeCCosto" %>



<div class="fieldcontain ${hasErrors(bean: jefeCCosto, field: 'ccosto', 'error')} ">
	<label for="ccosto">
		<g:message code="jefeCCosto.ccosto.label" default="Ccosto" />
		
	</label>
	<g:textField name="ccosto" value="${jefeCCosto?.ccosto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jefeCCosto, field: 'ejercicio', 'error')} ">
	<label for="ejercicio">
		<g:message code="jefeCCosto.ejercicio.label" default="Ejercicio" />
		
	</label>
	<g:textField name="ejercicio" value="${jefeCCosto?.ejercicio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jefeCCosto, field: 'jefe', 'error')} required">
	<label for="jefe">
		<g:message code="jefeCCosto.jefe.label" default="Jefe" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="jefe" name="jefe.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" required="" value="${jefeCCosto?.jefe?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jefeCCosto, field: 'subjefe', 'error')} ">
	<label for="subjefe">
		<g:message code="jefeCCosto.subjefe.label" default="Subjefe" />
		
	</label>
	<g:select id="subjefe" name="subjefe.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" value="${jefeCCosto?.subjefe?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jefeCCosto, field: 'userCaptura', 'error')} required">
	<label for="userCaptura">
		<g:message code="jefeCCosto.userCaptura.label" default="User Captura" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="userCaptura" name="userCaptura.id" from="${general.Usuario.list()}" optionKey="id" required="" value="${jefeCCosto?.userCaptura?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jefeCCosto, field: 'fechaCaptura', 'error')} required">
	<label for="fechaCaptura">
		<g:message code="jefeCCosto.fechaCaptura.label" default="Fecha Captura" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCaptura" precision="day" value="${jefeCCosto?.fechaCaptura}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: jefeCCosto, field: 'miJefe', 'error')} ">
	<label for="miJefe">
		<g:message code="jefeCCosto.miJefe.label" default="Mi Jefe" />
		
	</label>
	<g:select id="miJefe" name="miJefe.id" from="${mx.edu.um.rh.Empleado.list()}" optionKey="id" value="${jefeCCosto?.miJefe?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: jefeCCosto, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="jefeCCosto.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${jefeCCosto?.status}"/>
</div>

