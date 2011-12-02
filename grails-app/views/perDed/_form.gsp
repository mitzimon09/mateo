<%@ page import="mx.edu.um.rh.PerDed" %>



<div class="fieldcontain ${hasErrors(bean: perDed, field: 'clave', 'error')} required">
	<label for="clave">
		<g:message code="perDed.clave.label" default="Clave" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="clave" maxlength="6" required="" value="${perDed?.clave}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: perDed, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="perDed.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="50" required="" value="${perDed?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: perDed, field: 'naturaleza', 'error')} required">
	<label for="naturaleza">
		<g:message code="perDed.naturaleza.label" default="Naturaleza" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="naturaleza" maxlength="1" required="" value="${perDed?.naturaleza}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: perDed, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="perDed.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="status" maxlength="1" required="" value="${perDed?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: perDed, field: 'formula', 'error')} required">
	<label for="formula">
		<g:message code="perDed.formula.label" default="Formula" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="formula" cols="40" rows="5" maxlength="255" required="" value="${perDed?.formula}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: perDed, field: 'atributos', 'error')} ">
	<label for="atributos">
		<g:message code="perDed.atributos.label" default="Atributos" />
		
	</label>
	<g:select name="atributos" from="${mx.edu.um.rh.Atributo.list()}" multiple="multiple" optionKey="id" size="5" value="${perDed?.atributos*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: perDed, field: 'frecuenciaPago', 'error')} ">
	<label for="frecuenciaPago">
		<g:message code="perDed.frecuenciaPago.label" default="Frecuencia Pago" />
		
	</label>
	<g:textField name="frecuenciaPago" value="${perDed?.frecuenciaPago}"/>
</div>

