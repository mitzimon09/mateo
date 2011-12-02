<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="admin.label" default="RH" /></title>
    </head>
    <body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/>&nbsp;&gt;</a></li>
				<li><g:link class="list" controller="catalogo"><g:message code="catalogo.list.label" default="Catálogos" /></g:link></li>
				<li><g:link class="list" controller="empleado"><g:message code="empleado.list.label" default="Empleado" /></g:link></li>
			    <li><g:link class="list" controller="solicitudVacaciones"><g:message code="permisos.list.label" default="Solicitud Vacaciones" /></g:link></li>
			    <li><g:link class="list" controller="vacaciones"><g:message code="permisos.list.label" default="Vacaciones" /></g:link></li>
			</ul>
		</div>
        <div class="content">
            <h1><g:message code="admin.label" default="Recursos Humanos" /></h1>
        </div>
    </body>
</html>
