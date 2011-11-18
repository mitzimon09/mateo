<%@ page import="mx.edu.um.rh.Evento" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evento.label', default: 'Evento')}" />
		<title><g:message code="Subir Archivo para {0}" args="[evento.nombre]"/></title>
	    <meta name="layout" content="main" />
	</head>
	<body>
		<a href="#create-evento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="list" action="ver" id="${evento.id}"><g:message code="Pase de lista" /></g:link></li>
			</ul>
		</div>
        <div class="body">
            <h1><g:message code="Subir archivo" /></h1>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${activo}">
                <div class="errors">
                    <g:renderErrors bean="${activo}" as="list" />
                </div>
            </g:hasErrors>
            <g:form action="sube" method="post" enctype="multipart/form-data" >
                <g:hiddenField name="id" value="${evento.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="archivo"><g:message code="Seleccione el archivo"/></label>
                                </td>
                                <td valign="top" class="value">
                                    <input type="file" name="archivo" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="edita" class="save" value="${message(code: 'default.button.edita.label', default: 'Actualiza')}" /></span>
                </div>
            </g:form>
        </div>
        <g:javascript>
            $(document).ready(function() {
                $("#archivo").focus();
            });
        </g:javascript>
    </body>
</html>
