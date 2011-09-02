<%@ page import="general.Imagen" %>



<div class="fieldcontain ${hasErrors(bean: imagen, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="imagen.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="64" required="" value="${imagen?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imagen, field: 'tipoContenido', 'error')} required">
	<label for="tipoContenido">
		<g:message code="imagen.tipoContenido.label" default="Tipo Contenido" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="tipoContenido" maxlength="128" required="" value="${imagen?.tipoContenido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imagen, field: 'tamano', 'error')} required">
	<label for="tamano">
		<g:message code="imagen.tamano.label" default="Tamano" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="tamano" required="" value="${fieldValue(bean: imagen, field: 'tamano')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: imagen, field: 'archivo', 'error')} required">
	<label for="archivo">
		<g:message code="imagen.archivo.label" default="Archivo" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="archivo" name="archivo" />
</div>

