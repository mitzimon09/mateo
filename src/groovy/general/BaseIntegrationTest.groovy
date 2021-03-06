package general

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.authentication.TestingAuthenticationToken

class BaseIntegrationTest extends GroovyTestCase {

    def authenticateAdmin() {
        def credentials = 'admin'
        def user = new Usuario(
                username:'admin'
                ,password:credentials
            )
        def authorities = [new GrantedAuthorityImpl('ROLE_ADMIN')]
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,132)
        authenticate(principal,credentials,authorities)
    }
    
    def authenticateCCP() {
        def credentials = 'ccp'
        def user = new Usuario(
                username:'ccp'
                ,password:credentials
            )
        def authorities = [new GrantedAuthorityImpl('ROLE_CCP')]
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,132)
        authenticate(principal,credentials,authorities)
    }
    
    def authenticateCompras() {
        def credentials = 'compras'
        def user = new Usuario(
                username:'compras'
                ,password:credentials
            )
        def authorities = [new GrantedAuthorityImpl('ROLE_COMPRAS')]
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,132)
        authenticate(principal,credentials,authorities)
    }
    
    def authenticateDirfin() {
        def credentials = 'dirfin'
        def user = new Usuario(
                username:'dirfin'
                ,password:credentials
            )
        def authorities = [new GrantedAuthorityImpl('ROLE_DIRFIN')]
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,132)
        authenticate(principal,credentials,authorities)
    }
    
    def authenticateEmp() {
        def credentials = 'emp'
        def user = new Usuario(
                username:'emp'
                ,password:credentials
            )
        def authorities = [new GrantedAuthorityImpl('ROLE_EMP')]
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,132)
        authenticate(principal,credentials,authorities)
    }
    
    def authenticateOrg() {
        def credentials = 'org'
        def user = new Usuario(
                username:'org'
                ,password:credentials
            )
        def authorities = [new GrantedAuthorityImpl('ROLE_ORG')]
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,132)
        authenticate(principal,credentials,authorities)
    }
    
    def authenticateUser() {
        def credentials = 'user'
        def user = new Usuario(
                username:'user'
                ,password:credentials
            )
        def authorities = [new GrantedAuthorityImpl('ROLE_USER')]
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,132)
        authenticate(principal,credentials,authorities)
    }
    
    def authenticateDirRH() {
        def credentials = 'dirRH'
        def user = new Usuario(
                username:'dirRH'
                ,password:credentials
            )
        def authorities = [new GrantedAuthorityImpl('ROLE_DIRRH')]
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,132)
        authenticate(principal,credentials,authorities)
    }
    
    def authenticateRHOper() {
        def credentials = 'RHOper'
        def user = new Usuario(
                username:'RHOper'
                ,password:credentials
            )
        def authorities = [new GrantedAuthorityImpl('ROLE_RHOPER')]
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,205)
        authenticate(principal,credentials,authorities)
    }

    def authenticate(principal, credentials, authorities) {
        def authentication = new TestingAuthenticationToken(principal, credentials, authorities as GrantedAuthority[])
        authentication.authenticated = true
        SCH.context.authentication = authentication
        return authentication
    }

    def logout() {
        SCH.context.authentication = null
    }
}
