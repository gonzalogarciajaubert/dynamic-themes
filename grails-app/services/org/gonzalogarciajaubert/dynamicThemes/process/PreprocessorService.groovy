package org.gonzalogarciajaubert.dynamicThemes.process


import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import org.codehaus.groovy.grails.web.context.ServletContextHolder


/**
 * 
 * @author Gonzalo Garcia Jaubert
 * 
 * HACK: DON'T USE "default" IN THE NAME OF THE TEMPLATE ENGINE!!
 * TODO: Actually use of 'groovyPagesTemplateEngine' instead of 'groovyPageRenderer'  as it is only compatible with the version 2. And... =>
 *  => groovyPageRenderer: 'Find a view for a path. For example /foo/bar will search for /WEB-INF/grails-app/views/foo/bar.gsp in production and grails-app/views/foo/bar.gsp at development time' 
 *
 */
class PreprocessorService {
	static transactional = false
	
	def grailsApplication				// Read config properties
	def groovyPagesTemplateEngine		// The core
	LinkGenerator grailsLinkGenerator 	// Read config properties

	
	/**
	 * The business logic.
	 * 
	 * @param themeName: Folder name
	 * @param sections:	 Elements to replace before processing
	 * @param model: Model to replace
	 * 
	 * @return preprocessed gsp
	 */
    def preprocess(themeName, sections, model) {
		def configParams = getConfigParams(themeName)
		
		def output = new StringWriter()
		if (new File(configParams.FileSystemPathHtml).exists()) {
			def input = new File(configParams.FileSystemPathHtml)
			def text = input.text
			
			if (sections)
				if (sections.size() > 0)
					text = preprocessSections(text, sections)
			
			def replace = false
			if (model)
				if (model.size() > 0)
					replace  = true
					
			if (replace) {
				grails.gsp.PageRenderer
				groovyPagesTemplateEngine.createTemplate(text, "template_${themeName}").make(model).writeTo(output)
			} 
			else {
				groovyPagesTemplateEngine.createTemplate(text, "template_${themeName}").make().writeTo(output)
			}
		}
		return output
    }
	
	/**
	 * Inject css to an existing gsp
	 * 
	 * @param css text
	 * @param model: Model to replace
	 * @return css injected
	 */
	def resourceProcessor(css, themeName, model, ImagesPath) {
		def configParams = getConfigParams(themeName)
		def replace = false
		if (model) 
			if (model.size() > 0) 
				replace  = true

		// Authomatic injection of ImagesPath
		if (ImagesPath) {
			def imageMap = ['ImagesPath': configParams.urlFolderImages]
			model.putAll(imageMap)
			replace = true
		}
		
		def writer = new StringWriter()
		if (replace) {
			groovyPagesTemplateEngine.createTemplate(css,"css_name").make(model).writeTo(writer)
		}
		else {
			groovyPagesTemplateEngine.createTemplate(css,"css_name").make().writeTo(writer)
		}
		def buffer = writer.toString()
		return buffer
	}
	
	/**
	 * Replace tags in the gsp with your code
	 * 
	 * @param text
	 * @param sections
	 * 
	 * @return
	 */
	def private preprocessSections(text, sections) {
		for (section in sections) {
			text = text.replaceAll(section.key, section.value)
		}
		return text
	}
	
	
	def getConfigParams(themeName) {
		try {
			def slash = File.separator
			def useCacheControl 
			def urlThemes
			def fileSystemPath
			if (grailsApplication.isWarDeployed()) {
				urlThemes = grailsApplication.config.dynamicThemes.production.preprocessor.urlThemes
				fileSystemPath = ServletContextHolder.servletContext.getRealPath(grailsApplication.config.dynamicThemes.production.preprocessor.fileSystemPath).toString()
				useCacheControl = grailsApplication.config.dynamicThemes.production.resourceController.useCacheControl
			} else {
				urlThemes = grailsApplication.config.dynamicThemes.development.preprocessor.urlThemes
				fileSystemPath = grailsApplication.config.dynamicThemes.development.preprocessor.fileSystemPath
				useCacheControl = grailsApplication.config.dynamicThemes.development.resourceController.useCacheControl
			}
			
			def urlFolderImages = grailsLinkGenerator.resource(dir: "${urlThemes}/${themeName}/images", absolute:true)
			def FileSystemPathHtml = "${fileSystemPath}${slash}${themeName}${slash}${themeName}.html"
			def FileSystemPathCss = "${fileSystemPath}${slash}${themeName}${slash}${themeName}.css"	
			def urlSnapshot = grailsLinkGenerator.resource(dir: "${urlThemes}/${themeName}", file:"${themeName}.snapshot.png", absolute:true)
			
			return [urlThemes: urlThemes, 
					fileSystemPath: fileSystemPath, 
					urlFolderImages: urlFolderImages, 
					FileSystemPathHtml: FileSystemPathHtml, 
					urlSnapshot: urlSnapshot, 
					FileSystemPathCss: 
					FileSystemPathCss, 
					useCacheControl: useCacheControl ]
		} catch (Exception ex) {
			log.error "Failed to process config", ex
			throw new RuntimeException("Failed to process config")
		}
	}
}
