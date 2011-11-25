import mx.edu.um.rh.*

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        log.info("Inicializando aplicacion")

        def organizacion
        if (general.Organizacion.count() == 0) {
            organizacion = new general.Organizacion (
                codigo : 'UM'
                , nombre : 'UM'
                , nombreCompleto : 'Universidad de Montemorelos A.C.'
            ).save()
        }

        def empresa
        if (general.Empresa.count() == 0) {
            if (!organizacion) {
                def p = [:]
                p.max = 1
                def organizaciones = general.Organizacion.list(p)
                organizacion = organizaciones[0]
            }
            empresa = new general.Empresa (
                codigo : 'CTL'
                , nombre : 'CENTRAL'
                , nombreCompleto : 'CENTRAL'
                , organizacion : organizacion
            ).save()
        }

        log.info "Validando roles"
        def rolAdmin = general.Rol.findByAuthority('ROLE_ADMIN')
        if (general.Rol.count() != 8) {
            if (!rolAdmin) {
                rolAdmin = new general.Rol(authority: 'ROLE_ADMIN').save(flush:true)
            }
            def rolOrg = general.Rol.findByAuthority('ROLE_ORG')
            if (!rolOrg) {
                rolOrg = new general.Rol(authority: 'ROLE_ORG').save(flush:true)
            }
            def rolEmp = general.Rol.findByAuthority('ROLE_EMP')
            if (!rolEmp) {
                rolEmp = new general.Rol(authority: 'ROLE_EMP').save(flush:true)
            }
            def rolUser = general.Rol.findByAuthority('ROLE_USER')
            if (!rolUser) {
                rolUser = new general.Rol(authority: 'ROLE_USER').save(flush:true)
            }
            def rolCompras = general.Rol.findByAuthority('ROLE_COMPRAS')
            if (!rolCompras) {
            	rolCompras = new general.Rol(authority: 'ROLE_COMPRAS').save(flush:true)
            }
            def rolDirFin = general.Rol.findByAuthority('ROLE_DIRFIN')
            if (!rolDirFin) {
            	rolDirFin = new general.Rol(authority: 'ROLE_DIRFIN').save(flush:true)
            }
            def rolCcp = general.Rol.findByAuthority('ROLE_CCP')
            if (!rolCcp) {
            	rolCcp = new general.Rol(authority: 'ROLE_CCP').save(flush:true)
        	}
            def rolNom = general.Rol.findByAuthority('ROLE_NOM')
            if (!rolNom) {
                rolNom = new general.Rol(authority: 'ROLE_NOM').save(flush:true)
            }
            def rolDirRH = general.Rol.findByAuthority('ROLE_DIRRH')
            if (!rolDirRH) {
                rolDirRH = new general.Rol(authority: 'ROLE_DIRRH').save(flush:true)
            }
            def rolRHOper = general.Rol.findByAuthority('ROLE_RHOPER')
            if (!rolRHOper) {
                rolRHOper = new general.Rol(authority: 'ROLE_RHOPER').save(flush:true)
            }
        }

        log.info "Validando usuarios"
        def admin = general.UsuarioRol.findByRol(rolAdmin)
        if (!admin) {
            if (!empresa) {
                def p = [:]
                p.max = 1
                def empresas = general.Empresa.list(p)
                empresa = empresas[0]
            }
            def password = springSecurityService.encodePassword('admin')
            admin = new general.Usuario(
                username:'admin'
                ,password:password
                ,nombre:'David'
                ,apellido:'Mendoza'
                ,correo:'david.mendoza@um.edu.mx'
                ,empresa:empresa
            )
            admin.save(flush:true)
            general.UsuarioRol.create(admin, rolAdmin, true)
        }

        /*log.info "Agregando Percepcion de Prueba a BD"

        PerDed perded = new PerDed(
            clave: "PD000",
            nombre: "PERCEPCION PRUEBA",
            naturaleza: "C",
            frecuenciaPago: "PERIODO 1",
            status: "A",
            formula: "%",
        ).save(flush:true)

        Atributo atributo = new Atributo(
            nombre: "ATTR_A",
            descripcion : "ATTR_A",
            simbolo : "TE",
            perded: perded
        )
        perded.addToAtributos(atributo)

        Atributo atributo2 = new Atributo(
            nombre: "ATTR_B",
            descripcion : "ATTR_B",
            simbolo : "TS",
            perded : perded
        )
        perded.addToAtributos(atributo2)

        perded.save(flush:true)*/
        
        log.info("Aplicacion inicializada")
    }

    def destroy = {
    }
    

}
