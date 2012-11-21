package org.gonzalogarciajaubert.dynamicThemes.process


import org.codehaus.groovy.grails.web.mapping.LinkGenerator


/**
 * 
 * @author Gonzalo Garcia Jaubert
 * 
 * HACK: DON'T USE "default" IN THE NAME OF THE TEMPLATE ENGINE!!
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
	 * @param themeName: zip name
	 * @param sections:	 Elements to replace before processing
	 * @param variables: Variables to replace
	 * 
	 * @return preprocessed gsp
	 */
    def preprocess(themeName, sections, variables) {
		def configParams = getConfigParams(themeName)
		
		def output = new StringWriter()
		if (new File(configParams.FileSystemPathHtml).exists()) {
			def input = new File(configParams.FileSystemPathHtml)
			def text = input.text
			
			if (sections)
				if (sections.size() > 0)
					text = preprocessSections(text, sections)
			
			def replace = false
			if (variables)
				if (variables.size() > 0)
					replace  = true
					
			if (replace) {
				groovyPagesTemplateEngine.createTemplate(text, "template_${themeName}").make(variables).writeTo(output)
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
	 * @param variables: Variables to replace
	 * @return css injected
	 */
	def resourceProcessor(css, themeName, variables, ImagesPath) {
		def configParams = getConfigParams(themeName)
		def replace = false
		if (variables) 
			if (variables.size() > 0) 
				replace  = true

		// Authomatic injection of ImagesPath
		if (ImagesPath) {
			def imageMap = ['ImagesPath': configParams.urlFolderImages]
			variables.putAll(imageMap)
			replace = true
		}
		
		def writer = new StringWriter()
		if (replace) {
			groovyPagesTemplateEngine.createTemplate(css,"css_name").make(variables).writeTo(writer)
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
			
			def urlThemes = grailsApplication.config.dynamicThemes.preprocessor.urlThemes
			
			//def fileSystemPath = grailsApplication.config.dynamicThemes.preprocessor.fileSystemPath
			def fileSystemPath
			if (grailsApplication.isWarDeployed()) {
				fileSystemPath = grailsApplication.parentContext.getResource("themes")
			} else {
				fileSystemPath = System.properties['base.dir'] + "themes"
			}
			
			println "grailsApplication.parentContext: " + grailsApplication.parentContext.getResource("themes")
			println "System.properties: " + System.properties['base.dir'] + "\themes"
			
			def useCacheControl = grailsApplication.config.dynamicThemes.resourceController.useCacheControl
			
			
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
