package org.gonzalogarciajaubert.dynamicThemes

import org.gonzalogarciajaubert.dynamicThemes.process.PreprocessorService

/**
 * 
 * @author Gonzalo Garcia Jaubert
 *
 */
class HowToController {
	PreprocessorService preprocessorService

	
	/**
	 * Only the CSS
	 * 
	 * @return
	 */
	def cssHowTo() {
		[themeName: "default"]
	}
	
	/**
	 * The CSS + Partial GSP.
	 * Sections and model are optional!
	 * 
	 * @return
	 */
	def partialGSPHowTo() {
		def themeName = "default"
		// ImagesPath is a gsp variable in default.html
		def elements = "[1,2,3,'hello']"
		def sections = ["#beginLoop#": """<g:each var="element" in="${elements}">""", """#endLoop#""": """</g:each>"""]
		def elementsList = ['Post 1', 'Post 2', 'Post 3']
		def model = [ImagesPath: preprocessorService.getConfigParams(themeName ).urlFolderImages, elements:elementsList]
		// Sections and model are optional!
		def result = preprocessorService.preprocess(themeName , sections, model)
		[themeName: themeName, result: result]
	}
	
	/**
	 * The CSS + GSP.
	 * Sections and model are optional!
	 * 
	 * It's the same!!!
	 *
	 * @return
	 */
	def GSPHowTo() {
		def themeName = "default"
		// ImagesPath is a gsp variable in default.html
		def elements = "[1,2,3,'hello']"
		def sections = ["#beginLoop#": """<g:each var="element" in="${elements}">""", """#endLoop#""": """</g:each>"""]
		def elementsList = ['Post 1', 'Post 2', 'Post 3']
		def model = [ImagesPath: preprocessorService.getConfigParams(themeName ).urlFolderImages, elements:elementsList]

		// Sections and Variables are optional!
		def result = preprocessorService.preprocess(themeName , sections, model)
		[themeName: themeName, result: result]
	}
	

}
