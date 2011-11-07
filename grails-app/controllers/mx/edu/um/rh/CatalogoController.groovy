package mx.edu.um.rh

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_RHOPER'])
class CatalogoController {

    def index() { }
}
