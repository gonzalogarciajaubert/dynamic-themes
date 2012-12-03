class DynamicThemesGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "dynamic-themes Plugin" // Headline display name of the plugin
    def author = "Garcia Jaubert, Gonzalo"
    def authorEmail = "gjaubert@gmail.com"
    def description = '''\
The dynamic-themes plugin allows your Grails application to load \
and render your pages with your own theme (folders with GSP templates \
and css) dinamically outside the scope of a web request. \
You can process models and sections (your own tags) in your templates.  \
See DynamicThemesExamples (https://github.com/gonzalogarciajaubert/DynamicThemesExample/wiki) \
application for examples.
'''
    def documentation = "http://dynamicthemes.dancingrails.com/"
    def license = "APACHE"
    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "github", url: "https://github.com/gonzalogarciajaubert/dynamic-themes/issues" ]
    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/gonzalogarciajaubert/dynamic-themes" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
