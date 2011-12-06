<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'empleado.label', default: 'Empleado')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<r:require module="jquery-ui" />
	</head>
	<body>
		<a href="#list-empleado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-empleado" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:form method="post" >
			    <div class="fieldcontain ${hasErrors(bean: empleado, field: 'nombreCompleto', 'error')} required">
	                <label for="empleado">
		                <g:message code="producto.empleado.label" default="Empleado" />
		                <span class="required-indicator">*</span>
	                </label>
	                <g:hiddenField id="empleadoId" name="empleado.id" value="empleado?.id" />
	                <g:textField id="nombreCompleto" name="nombreCompleto" value="${fieldValue(bean: empleado, field: 'nombreCompleto')}"/>
                </div>
                <fieldset class="buttons">
					<g:actionSubmit class="save" action="cargarEmpleado" value="${message(code: 'default.button.update.label', default: 'Cargar Empleado')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
	<r:script>
	    $(document).ready(function() {
		    $("input#nombreCompleto").autocomplete({
			    source: '${createLink(action:'buscarEmpleado')}',
      			select: function(event, ui) {
	                $("input#empleadoId").val(ui.item.id);
      			}
            });
	    });
    </r:script>
</html>
