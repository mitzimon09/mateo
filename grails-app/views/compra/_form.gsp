<%@ page import="general.Compra" %>
<div class="fieldcontain ${hasErrors(bean: compra, field: 'folio', 'error')} ">
	<label for="folio">
		<g:message code="compra.folio.label" default="Folio" />
	</label>
	<g:fieldValue bean="${compra}" field="folio"/>
</div>

<div class="fieldcontain ${hasErrors(bean: compra, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="compra.total.label" default="Total" />
	</label>
	<g:fieldValue bean="${compra}" field="total"/>
</div>

<div class="fieldcontain ${hasErrors(bean: compra, field: 'articulos', 'error')} ">
  <table>
    <thead>
      <tr>

	      <g:sortableColumn property="descripcion" title="${message(code: 'articulo.descripcion.label', default: 'Descripcion')}" />

	      <g:sortableColumn property="cantidad" title="${message(code: 'articulo.cantidad.label', default: 'Cantidad')}" />

	      <g:sortableColumn property="precioUnitario" title="${message(code: 'articulo.precioUnitario.label', default: 'Precio Unitario')}" />

	      <g:sortableColumn property="total" title="${message(code: 'articulo.total.label', default: 'Total')}" />

      </tr>
    </thead>
    <tbody>
    <g:each in="${compra.articulos}" status="i" var="articulo">
      <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

	      <td><g:link controller="articulo" action="edita" id="${articulo.id}">${fieldValue(bean: articulo, field: "descripcion")}</g:link></td>

	      <td>${fieldValue(bean: articulo, field: "cantidad")}</td>

	      <td>${fieldValue(bean: articulo, field: "precioUnitario")}</td>

	      <td>${fieldValue(bean: articulo, field: "total")}</td>

      </tr>
    </g:each>
    </tbody>
  </table>

</div>

