<%@ page import="mx.edu.um.rh.Empleado" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Datos del Empleados</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/redmond/jquery-ui.css" type="text/css" media="all" />
 </head>
<body>

 <div class="tabs">
   <div id="tabs">
	<ul>
		<li><a href="#tabs-1">Datos Emplado</a></li>
                <li><a href="#tabs-2"><span>Percepciones</span></a></li>
		<li><a href="#tabs-3">Dependientes</a></li>
	</ul>
	<div id="tabs-1">
  
  <div id="accordion" style="padding:10px; width:800px;" class="ui-widget-content">
   <h3><a href="#">Empleado Generales</a></h3>
    <div>
      <div class="fieldcontain ${hasErrors(bean: empleado, field: 'clave', 'error')} required">
	<label for="clave">
		<g:message code="empleado.clave.label" default="Clave" />
	</label>
	<g:fieldValue bean="${empleado}" field="clave"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="empleado.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="50" required="" value="${empleado?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'apPaterno', 'error')} required">
	<label for="apPaterno">
		<g:message code="empleado.apPaterno.label" default="Ap Paterno" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apPaterno" maxlength="30" required="" value="${empleado?.apPaterno}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'apMaterno', 'error')} required">
	<label for="apMaterno">
		<g:message code="empleado.apMaterno.label" default="Ap Materno" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apMaterno" maxlength="30" required="" value="${empleado?.apMaterno}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'fechaNacimiento', 'error')} required">
	<label for="fechaNacimiento">
		<g:message code="empleado.fechaNacimiento.label" default="Fecha Nacimiento" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaNacimiento" precision="day" value="${empleado?.fechaNacimiento}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="empleado.direccion.label" default="Direccion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="direccion" maxlength="100" required="" value="${empleado?.direccion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'genero', 'error')} required">
    <label for="genero">
        <g:message code="empleado.genero.label" default="Genero" />
        <span class="required-indicator">*</span>
    </label>
    <g:select name="genero" from="${empleado.constraints.genero.inList}" value="${empleado?.genero}" valueMessagePrefix="empleado.genero" />
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'status', 'error')} required">
    <label for="status">
        <g:message code="empleado.status.label" default="Status" />
        <span class="required-indicator">*</span>
    </label>
    <g:select name="status" from="${empleado.constraints.status.inList}" value="${empleado?.status}" valueMessagePrefix="empleado.status"  />
</div>

      
<div class="fieldcontain ${hasErrors(bean: empleado, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="empleado.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${general.Empresa.list()}" optionKey="id" required="" value="${empleado?.empresa?.id}" class="many-to-one"/>
</div>

</div>
    <h3><a href="#">Empleado Laborales</a></h3>
    <div>
      <div class="fieldcontain ${hasErrors(bean: empleado, field: 'cuenta', 'error')} ">
	<label for="cuenta">
		<g:message code="empleado.cuenta.label" default="Cuenta" />
		
	</label>
	<g:textField name="cuenta" maxlength="16" value="${empleado?.cuenta}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'curp', 'error')} required">
	<label for="curp">
		<g:message code="empleado.curp.label" default="Curp" />
                <span class="required-indicator">*</span>
		
	</label>
	<g:textField name="curp" maxlength="30" required="" value="${empleado?.curp}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'escalafon', 'error')} required">
	<label for="escalafon">
		<g:message code="empleado.escalafon.label" default="Escalafon" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="escalafon" required="" value="${fieldValue(bean: empleado, field: 'escalafon')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'imms', 'error')} ">
	<label for="imms">
		<g:message code="empleado.imms.label" default="Imms" />
		
	</label>
	<g:textField name="imms" maxlength="15" value="${empleado?.imms}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'rfc', 'error')} required">
	<label for="rfc">
		<g:message code="empleado.rfc.label" default="Rfc" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="rfc" maxlength="15" required="" value="${empleado?.rfc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'modalidad', 'error')} required">
    <label for="modalidad">
        <g:message code="empleado.modalidad.label" default="Modalidad" />
        <span class="required-indicator">*</span>
    </label>
    <g:select name="modalidad" from="${empleado.constraints.modalidad.inList}" value="${empleado?.modalidad}" valueMessagePrefix="empleado.modalidad"  />
</div>


<div class="fieldcontain ${hasErrors(bean: empleado, field: 'turno', 'error')} required">
	<label for="turno">
		<g:message code="empleado.turno.label" default="Turno" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="turno" required="" value="${fieldValue(bean: empleado, field: 'turno')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'fechaAlta', 'error')} required">
	<label for="fechaAlta">
		<g:message code="empleado.fechaAlta.label" default="Fecha Alta" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaAlta" precision="day" value="${empleado?.fechaAlta}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'antiguedadBase', 'error')} required">
	<label for="antiguedadBase">
		<g:message code="empleado.antiguedadBase.label" default="Antiguedad Base" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="antiguedadBase" required="" value="${fieldValue(bean: empleado, field: 'antiguedadBase')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'antiguedadFiscal', 'error')} required">
	<label for="antiguedadFiscal">
		<g:message code="empleado.antiguedadFiscal.label" default="Antiguedad Fiscal" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="antiguedadFiscal" required="" value="${fieldValue(bean: empleado, field: 'antiguedadFiscal')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'adventista', 'error')} ">
	<label for="adventista">
		<g:message code="empleado.adventista.label" default="Adventista" />
		
	</label>
	<g:checkBox name="adventista" value="${empleado?.adventista}" />
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'experienciaFueraUM', 'error')} ">
	<label for="experienciaFueraUM">
		<g:message code="empleado.experienciaFueraUM.label" default="Experiencia Fuera UM" />
		
	</label>
	<g:field type="number" name="experienciaFueraUM" value="${fieldValue(bean: empleado, field: 'experienciaFueraUM')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'fechaAntiguedadBase', 'error')} ">
	<label for="fechaAntiguedadBase">
		<g:message code="empleado.fechaAntiguedadBase.label" default="Fecha Antiguedad Base" />
		
	</label>
	<g:datePicker name="fechaAntiguedadBase" precision="day" value="${empleado?.fechaAntiguedadBase}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'fechaBaja', 'error')} ">
	<label for="fechaBaja">
		<g:message code="empleado.fechaBaja.label" default="Fecha Baja" />
		
	</label>
	<g:datePicker name="fechaBaja" precision="day" value="${empleado?.fechaBaja}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'ife', 'error')} ">
	<label for="ife">
		<g:message code="empleado.ife.label" default="Ife" />
		
	</label>
	<g:textField name="ife" value="${empleado?.ife}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'rango', 'error')} ">
	<label for="rango">
		<g:message code="empleado.rango.label" default="Rango" />
		
	</label>
	<g:textField name="rango" value="${empleado?.rango}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="empleado.tipo.label" default="Tipo" />
		
	</label>
	<g:select id="tipo" name="tipo.id" from="${mx.edu.um.rh.TipoEmpleado.list()}" optionKey="id" value="${empleado?.tipo?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'grupo', 'error')} ">
	<label for="grupo">
		<g:message code="empleado.grupo.label" default="Grupo" />
		
	</label>
	<g:select id="grupo" name="grupo.id" from="${mx.edu.um.rh.Grupo.list()}" optionKey="id" value="${empleado?.grupo?.id}" class="many-to-one" />
</div>
        </div>
    <h3><a href="#">Empleado Personales</a></h3>
    <div>
<div class="fieldcontain ${hasErrors(bean: empleado, field: 'estadoCivil', 'error')} required">
    <label for="estadoCivil">
        <g:message code="empleado.estadoCivil.label" default="Estado Civil" />
        <span class="required-indicator">*</span>
    </label>
    <g:select name="estadoCivil" from="${empleado.constraints.estadoCivil.inList}" value="${empleado?.estadoCivil}" valueMessagePrefix="empleado.EstadoCivil"  />
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'madre', 'error')} required">
	<label for="madre">
		<g:message code="empleado.madre.label" default="Madre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="madre" maxlength="50" required="" value="${empleado?.madre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'padre', 'error')} required">
	<label for="padre">
		<g:message code="empleado.padre.label" default="Padre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="padre" maxlength="50" required="" value="${empleado?.padre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'conyuge', 'error')} ">
	<label for="conyuge">
		<g:message code="empleado.conyuge.label" default="Conyuge" />
		
	</label>
	<g:textField name="conyuge" maxlength="50" value="${empleado?.conyuge}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'fechaMatrimonio', 'error')} ">
	<label for="fechaMatrimonio">
		<g:message code="empleado.fechaMatrimonio.label" default="Fecha Matrimonio" />
		
	</label>
	<g:datePicker name="fechaMatrimonio" precision="day" value="${empleado?.fechaMatrimonio}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'finadoPadre', 'error')} ">
	<label for="finadoPadre">
		<g:message code="empleado.finadoPadre.label" default="Finado Padre" />
		
	</label>
	<g:field type="number" name="finadoPadre" value="${fieldValue(bean: empleado, field: 'finadoPadre')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'finadoMadre', 'error')} ">
	<label for="finadoMadre">
		<g:message code="empleado.finadoMadre.label" default="Finado Madre" />
		
	</label>
	<g:field type="number" name="finadoMadre" value="${fieldValue(bean: empleado, field: 'finadoMadre')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'iglesia', 'error')} ">
	<label for="iglesia">
		<g:message code="empleado.iglesia.label" default="Iglesia" />
		
	</label>
	<g:textField name="iglesia" value="${empleado?.iglesia}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empleado, field: 'responsabilidad', 'error')} ">
	<label for="responsabilidad">
		<g:message code="empleado.responsabilidad.label" default="Responsabilidad" />
		
	</label>
	<g:textField name="responsabilidad" value="${empleado?.responsabilidad}"/>
</div>
    </div>
  <h3><a href="#"></a></h3>
</div>
</div>
     	<div id="tabs-2">
          
          
          <div class="fieldcontain ${hasErrors(bean: empleado, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="empleado.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="50" required="" value="${empleado?.nombre}"/>
</div>
</div>	
	<div id="tabs-3">
</div>
</div>

</div>
       

  
   <script type="text/javascript">
   $(function() {
		$( "#accordion" ).accordion({
              collapsible: true,
			fillSpace: true,
         //active: false,
                      
		});
                
	});
	$(function() {
		$( "#accordionResizer" ).resizable({
                  
			minHeight: 140,
			resize: function() {
				$( "#accordion" ).accordion( "resize" );
			}
		});

	});
$(function() {
		$( "#tabs" ).tabs({
			event: "mouseover"
		});
	});
     


	</script>
</body>
</html>
