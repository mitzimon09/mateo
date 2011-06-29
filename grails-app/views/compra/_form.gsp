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

	      <th><g:message code="articulo.compra.label" default="Compra" /></th>

      </tr>
    </thead>
    <tbody>
    <g:each in="${compra.articulos}" status="i" var="articuloInstance">
      <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

	      <td><g:link action="ver" id="${articuloInstance.id}">${fieldValue(bean: articuloInstance, field: "descripcion")}</g:link></td>

	      <td>${fieldValue(bean: articuloInstance, field: "cantidad")}</td>

	      <td>${fieldValue(bean: articuloInstance, field: "precioUnitario")}</td>

	      <td>${fieldValue(bean: articuloInstance, field: "total")}</td>

	      <td>${fieldValue(bean: articuloInstance, field: "compra")}</td>

      </tr>
    </g:each>
    </tbody>
  </table>

</div>

