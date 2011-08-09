package contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import groovy.sql.*

@Secured(['ROLE_EMP'])
class TraductorController {

    def index = { }

    def leer = {
        def sql = Sql.newInstance("jdbc:oracle:thin:@172.16.254.14:1521:oreb", "noe","arcopacto", "oracle.jdbc.driver.OracleDriver")
        //def sql = Sql.newInstance("jdbc:oracle:thin:@oreb.um.edu.mx:1521:oreb", "noe","arcopacto", "oracle.jdbc.driver.OracleDriver")
        def movimientos = new StringBuilder()
        movimientos.append("<table><tbody>")
        sql.eachRow("select * from mateo.cont_movimiento where fecha > to_date('20-06-2011','dd-mm-yyyy')") { row ->
            movimientos.append("<tr>")
            movimientos.append("<td>")
            movimientos.append(row)
            movimientos.append("</td>")
            movimientos.append("</tr>")
        }
        movimientos.append("</tbody></table>")
        sql.close()
        render movimientos.toString()
    }
}