package org.gonzalogarciajaubert.dynamicThemes



import grails.test.mixin.*

import org.codehaus.groovy.grails.web.pages.*
import org.gonzalogarciajaubert.dynamicThemes.process.PreprocessorService
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(HowToController)
class HowToOldControllerTests {
        def groovyPagesTemplateEngine
        def preprocessorService = new PreprocessorService()
        
                
        void testCssHowTo() {
                def model = controller.cssHowTo()
                assert model.themeName == 'default'
        }
        
        
}