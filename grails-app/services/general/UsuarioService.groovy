package general

class UsuarioService {

    Usuario getUsuarioByUsername(String username) throws NullPointerException{
        def usuario=Usuario.findByUsername(username)
        if(!usuario){
            throw new NullPointerException("UsuarioEmpleado.inexistente")
        }
        return usuario
    }
}
