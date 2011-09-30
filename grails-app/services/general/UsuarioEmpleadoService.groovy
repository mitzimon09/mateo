package general
import mx.edu.um.rh.*

class UsuarioEmpleadoService {

    def usuarioService
    UsuarioEmpleado getUsuarioEmpleadoByUsername(String username) throws NullPointerException{
        def usuario=usuarioService.getUsuarioByUsername(username)
        log.debug "usuario :) " + usuario.username
        def usuarioEmpleado = new UsuarioEmpleado()
        usuarioEmpleado.usuario = usuario
        log.debug "usuarioEmpleado.usuario :D " + usuarioEmpleado.usuario.username
        usuarioEmpleado = UsuarioEmpleado.getEmpleado(usuarioEmpleado).list().get(0)
        if(!usuarioEmpleado){
            throw new NullPointerException("UsuarioEmpleado.inexistente")
        }
        return usuarioEmpleado
    }
    
    Empleado getEmpleadoByUsername(String username) throws NullPointerException{
        def usuarioEmpleado = getUsuarioEmpleadoByUsername(username)
        if(!usuarioEmpleado.empleado){
            throw new NullPointerException("Empleado.inexistente")
        }
        return usuarioEmpleado.empleado
    }
}
