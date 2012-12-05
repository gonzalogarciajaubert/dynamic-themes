// configuration for plugin testing - will not be included in the plugin zip

log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

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
}


// DynamicThemes
dynamicThemes {
	development {
		resourceController.useCacheControl = true			// Override to control the css cached
		preprocessor.urlThemes = 'themes'					// Relative url for themes.
		preprocessor.fileSystemPath = 'web-app/themes'		// Relative file system path for themes. Default is web-app/themes
	}
	production {
		resourceController.useCacheControl = true			// Override to control the css cached
		preprocessor.urlThemes = 'themes'					// Relative url for themes.
		preprocessor.fileSystemPath =  "themes"				// Relative file system path for themes (servletContext)
	}
}
