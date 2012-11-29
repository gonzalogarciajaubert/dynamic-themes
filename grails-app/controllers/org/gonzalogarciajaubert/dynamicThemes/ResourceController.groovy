package org.gonzalogarciajaubert.dynamicThemes

import org.apache.http.impl.cookie.DateUtils
import org.gonzalogarciajaubert.dynamicThemes.process.PreprocessorService


/**
 * Require: http://mavenhub.com/mvn/central/org.apache.httpcomponents/httpclient/4.0
 * 
 * @author Gonzalo Garcia Jaubert
 *
 */
class ResourceController {
	def groovyPagesTemplateEngine
	PreprocessorService preprocessorService
	
	/**
	 * Origin: http://www.wthr.us/2010/02/16/preprocessing-css-in-grails/
	 * By: Kevin M. Gill 
	 * Intercept <link rel="stylesheet" href="${resource(dir:'/resource/css',file:'aaaa')}" />  and inject the css 
	 * 
	 */
	def css ={
		def slash = File.separator
		def themeName = (params.id? params.id : "default")
		def configParams = preprocessorService.getConfigParams(themeName)
		def cssPath = new File(configParams.FileSystemPathCss)
		if (!cssPath.exists()) {
			render(text:message(code: 'resource.filenotfound'),status:404)
			return
		}
		
		// Cache
		def ifModifiedSince = request.getHeader("If-Modified-Since")
		if ((ifModifiedSince) && (configParams.useCacheControl == true)) {
		   String[] formats = [DateUtils.PATTERN_ASCTIME, DateUtils.PATTERN_RFC1036, DateUtils.PATTERN_RFC1123]
		   def dt_ifModifiedSince = DateUtils.parseDate(ifModifiedSince, formats)

		   long lastMod = (long) (cssPath.lastModified() / 1000)
		   long isModSince = (long) (dt_ifModifiedSince.getTime() / 1000)
		   if (lastMod <= isModSince) {
			   render (status:304)
			   return
		   }
	   }
		if (configParams.useCacheControl == true) {
			def now = new Date(System.currentTimeMillis() + (2693000L * 1000))
			response.setHeader("Expires", DateUtils.formatDate(now, DateUtils.PATTERN_RFC1123))
			response.setHeader("Cache-Control", "public")
			response.setHeader("Vary", "Accept-Encoding")
		}
		response.addHeader("Last-Modified", DateUtils.formatDate(new Date(cssPath.lastModified()), DateUtils.PATTERN_RFC1123))
			
		// Inject CSS
		try {
			Map model = (params?.model ? params?.model : [:])
			def cssText = preprocessorService.resourceProcessor(cssPath.getText(), themeName, model, params?.ImagesPath)	
			render(text:cssText, contentType:"text/css")
		} catch (Exception ex) {
			log.error "Failed to process template", ex
			render(text:"Failed to process template", status:500)
		}
	}
}
