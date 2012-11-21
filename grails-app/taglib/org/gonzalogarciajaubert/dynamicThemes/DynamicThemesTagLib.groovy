package org.gonzalogarciajaubert.dynamicThemes

/**
 *
 * @author Gonzalo Garcia Jaubert
 *
 * HACK: DON'T USE "default" IN THE NAME OF THE TEMPLATE ENGINE!!
 *
 */
class DynamicThemesTagLib {
	/**
	 * Render "<link rel="stylesheet" href="${resource(dir:'/resource/css',file:"${themeName}")}" />"
	 * 
	 * This taglib need ResourceController
	 * 
	 * @attr themeName 
	 *  
	 */
	def injectCss = { attrs, body ->
		def vars = ""
		attrs.variables?.each {key, value -> 
			vars += "variables.${key}=${value}"
		}
		
		out <<  """<link rel="stylesheet" href="${resource(dir:'/resource/css',absolute: false, file:"${attrs?.themeName}?ImagesPath=${attrs?.ImagesPath}&${vars}")}" />"""
	}
	
	/**
	 * Render the gsp. It's a silly tag
	 * 
	 * TODO: process the theme here. Need more attributes.
	 *
	 * @attr "instance" is the instance name
	 *
	 */
	def injectGSP = { attrs, body ->
		out <<  """${attrs.instance}"""
	}
}
