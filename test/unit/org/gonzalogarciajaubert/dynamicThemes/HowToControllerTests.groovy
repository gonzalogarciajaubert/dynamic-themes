package org.gonzalogarciajaubert.dynamicThemes



import grails.test.mixin.*

import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import org.codehaus.groovy.grails.web.pages.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(HowToController)
class HowToControllerTests {
	def groovyPagesTemplateEngine
		
	void testCssHowTo() {
		def model = controller.cssHowTo()
		assert model.themeName == 'default'
	}
	
	/*
	 * TODO: Implement test. How to resolve "template_default: 6: unable to resolve class grails.util.GrailsUtil" ?
	 * 
	 */
	void testGSPHowTo() {
		defineBeans {
			preprocessorService(org.gonzalogarciajaubert.dynamicThemes.process.PreprocessorService)
			preprocessorService.grailsApplication = new org.codehaus.groovy.grails.commons.DefaultGrailsApplication()
			preprocessorService.grailsLinkGenerator = new org.codehaus.groovy.grails.web.mapping.DefaultLinkGenerator("/dynamicthemes")
			preprocessorService.groovyPagesTemplateEngine = new GroovyPagesTemplateEngine() // new org.codehaus.groovy.grails.web.pages.GroovyPagesTemplateEngine()
		}
	
		
		def model = controller.GSPHowTo()
		assert model.themeName == 'default'
		assert model.result == ''	// TODO: result
	}
	
	
}
