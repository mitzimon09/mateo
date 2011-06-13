package inventario

import general.Cliente

class Salida {//implements java.io.Serializable {
    String folio
    String comentarios
    BigDecimal iva = new BigDecimal("0.00")
    BigDecimal total = new BigDecimal("0.00")
    String empleado
    String reporte
    //String atendio //el empleado ya está, si una persona atendió ¿qué hace el empleado?
    String departamento
    String estatus
    Boolean facturada = false
    Cliente cliente
    Almacen almacen
    Date dateCreated
    Date lastUpdated
    Set lotes = [] //de salidas
    //Boolean cerrada = false //esto está in incluido en el estatus
    FacturaAlmacen facturaAlmacen

    static belongsTo = [Cliente, Almacen, FacturaAlmacen]

    static hasMany = [lotes: LoteSalida]

    static constraints = {
        folio(unique:'almacen', maxSize:64, blank:false)
        //atendio(nullable:true,maxSize:64)
        reporte(nullable:true,maxSize:64)
        iva(scale:2,precision:8)
        total(scale:2,precision:8)
        empleado(nullable:true,maxSize:64)
        departamento(nullable:true,maxSize:64)
        comentarios(nullable:true,maxSize:254)
        facturaAlmacen(nullable:true)
        estatus(maxSize:64, inList:['ABIERTA','CERRADA','CANCELADA'])
    }

    static mapping = {
        table 'salidas'
        almacen index:'salida_almacen_idx'
        estatus index:'salida_estatus_idx'
        folio index:'salida_folio_idx'
        reporte index:'salida_reporte_idx'
    }

    static namedQueries = {
        buscaPorAlmacen { filtro ->
            almacen {
                eq 'id', filtro.id
            }
        }

        relaciones {
            join 'estatus'
            join 'cliente'
            estatus {
                order 'prioridad', 'asc'
            }
            order 'folio','desc'
        }

        buscaPorFiltro { filtro ->
            filtro = "%$filtro%"
            or {
                ilike 'folio', filtro
                ilike 'reporte', filtro
                //ilike 'atendio', filtro
                ilike 'empleado', filtro
                ilike 'departamento', filtro
                ilike 'comentarios', filtro
                estatus {
                    ilike 'nombre', filtro
                }
                cliente {
                    or {
                        ilike 'nombre', filtro
                        ilike 'rfc', filtro
                    }
                }
            }
        }

        buscaPorFecha { fechaInicial, fechaFinal ->
            between 'lastUpdated', fechaInicial, fechaFinal
        }

        listaParaFacturar { almacenId, filtro ->
            filtro = "%$filtro%"
            ilike 'folio', filtro
            eq 'facturada', false
            almacen {
                idEq(almacenId)
            }
            estatus {
                eq 'nombre', 'estatus.cerrada'
            }
            order 'lastUpdated', 'desc'
        }
    }

    String toString() {
        "$folio : $reporte : $total"
    }

    boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Salida) {
                def salida = (Salida)o
                if (salida.id == this.id) {
                    return true
                }
            }
        }
        return false
    }
}


