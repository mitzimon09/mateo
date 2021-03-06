// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

grails.config.locations = [ "file:${userHome}/.grails/${appName}-config.groovy" ]

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = ''

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%d [MATEO] %5p [%c{2}] | %m%n')
    }

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'

    debug  'grails.app','test.integration'//,'org.hibernate'

    //trace  'org.hibernate.type'

    off    'grails.app.services.org.grails.plugin.resource',
           'grails.app.taglib.org.grails.plugin.resource',
           'grails.app.resourceMappers.org.grails.plugin.resource'
}
/*
grails.gorm.default.mapping = {
   cache true
   //id generator:'identity'
}
*/
grails.gorm.failOnError = true

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'general.Usuario'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'general.UsuarioRol'
grails.plugins.springsecurity.authority.className = 'general.Rol'
grails.plugins.springsecurity.roleHierarchy = '''
   ROLE_ADMIN > ROLE_ORG
   ROLE_ORG > ROLE_EMP
   ROLE_ORG > ROLE_COMPRAS
   ROLE_ORG > ROLE_DIRFIN
   ROLE_ORG > ROLE_DIRRH
   ROLE_ORG > ROLE_CCP
   ROLE_ORG > ROLE_DIRRH
   ROLE_EMP > ROLE_USER
   ROLE_COMPRAS > ROLE_USER
   ROLE_DIRFIN > ROLE_USER
   ROLE_DIRRH > ROLE_USER
   ROLE_DIRRH > ROLE_RHOPER
   ROLE_RHOPER > ROLE_USER
   ROLE_CCP > ROLE_USER
'''

grails.plugins.springsecurity.useSecurityEventListener = true
grails.plugins.springsecurity.onInteractiveAuthenticationSuccessEvent = { e, appCtx ->
    def domain = general.Usuario.executeQuery("select new map(usuario.empresa.organizacion.nombre as organizacion, usuario.empresa.nombre as empresa) from Usuario usuario where usuario.username = ?", [e.source.principal.username])
    def request = org.codehaus.groovy.grails.plugins.springsecurity.SecurityRequestHolder.getRequest()
    def session = request.getSession(false)
    session.organizacion = domain[0].organizacion
    session.empresa = domain[0].empresa
    def empleado = general.UsuarioEmpleado.executeQuery("select ue.empleado from Usuario u, UsuarioEmpleado ue where ue.usuario = u.id and u.username = ?", [e.source.principal.username])
    session.empleado = empleado[0].id
}

//To specify a property of the userPrincipal to be logged as the actor name (the person performing the action which triggered the event)
//auditLog {
//  actorKey = 'userPrincipal.name'
//  actorKey = 'userPrincipal.id'
//}

//If you are using a custom authentication system in your controller that puts the user data into the session you can set up the actorKey to work with this data instead.
//auditLog {
//  actorKey = 'session.username'
  //actorKey = 'userPrincipal.id'
//}
