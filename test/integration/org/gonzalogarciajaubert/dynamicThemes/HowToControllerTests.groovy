package org.gonzalogarciajaubert.dynamicThemes

import org.gonzalogarciajaubert.dynamicThemes.process.PreprocessorService
import org.gonzalogarciajaubert.dynamicThemes.HowToController
import org.junit.*
import static org.junit.Assert.*

class HowToControllerTests extends GroovyTestCase   {
	PreprocessorService preprocessorService
	org.gonzalogarciajaubert.dynamicThemes.HowToController controller
	def LS
	

    @Before
    void setUp() {
		controller = new HowToController()
		controller.preprocessorService = preprocessorService
		LS = System.getProperty('line.separator')
    }

    @After
    void tearDown() {
        // Tear down logic here
    }
	
	@Test
	void testCssHowTo() {
		Assert.assertEquals 'default', controller.cssHowTo().themeName
	}

    @Test
    void testGSPHowTo() {
		def expectedResult = '''<!-- @author: Gonzalo Garcia Jaubert -->
	<div class="fondodiv">
			
		<div class="row">
			<div class="span3">
				<img alt="imagen1" src='http://localhost/themes/default/images/grails_logo.png'">
			</div>
			<div class="span6">
				<h2>You're watching default theme</h2>
			</div>
		</div>
		<hr>
		Html: themes/default/default.html<br>
		Css: themes/default/default.css (Only in PartialGSP example)<br>
		Image with model injected example: themes/default/images/grails_logo.png<br>
				<h2>Post 1</h2>
				<hr>
				<div class="row">
					<div class="span11">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed neque magna, non suscipit nulla. Etiam sed lacus ut nibh suscipit aliquam sed semper tortor. Vestibulum mollis nibh quam, sed imperdiet felis. Quisque eu tortor in purus convallis euismod. Aenean augue dui, suscipit id consequat id, tempus semper velit. Donec nisl lacus, elementum id tincidunt eget, vehicula et diam. Donec velit mauris, malesuada eu tempus ut, hendrerit vel ante. Ut et sapien eu eros mattis commodo a eget ipsum. Aliquam ac arcu augue. Quisque auctor viverra eros vitae fringilla. Suspendisse ac ipsum eros, at sagittis metus. Quisque nec risus felis, vitae aliquet neque. Suspendisse id nibh tortor, sit amet convallis dolor. Maecenas vestibulum, purus quis tristique tempus, tellus tortor molestie diam, vel dapibus erat nunc vehicula orci. Cras blandit, ante imperdiet pretium imperdiet, velit est bibendum ligula, eget accumsan purus urna et ipsum.
					</div>					
				</div>
			
				<h2>Post 2</h2>
				<hr>
				<div class="row">
					<div class="span11">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed neque magna, non suscipit nulla. Etiam sed lacus ut nibh suscipit aliquam sed semper tortor. Vestibulum mollis nibh quam, sed imperdiet felis. Quisque eu tortor in purus convallis euismod. Aenean augue dui, suscipit id consequat id, tempus semper velit. Donec nisl lacus, elementum id tincidunt eget, vehicula et diam. Donec velit mauris, malesuada eu tempus ut, hendrerit vel ante. Ut et sapien eu eros mattis commodo a eget ipsum. Aliquam ac arcu augue. Quisque auctor viverra eros vitae fringilla. Suspendisse ac ipsum eros, at sagittis metus. Quisque nec risus felis, vitae aliquet neque. Suspendisse id nibh tortor, sit amet convallis dolor. Maecenas vestibulum, purus quis tristique tempus, tellus tortor molestie diam, vel dapibus erat nunc vehicula orci. Cras blandit, ante imperdiet pretium imperdiet, velit est bibendum ligula, eget accumsan purus urna et ipsum.
					</div>					
				</div>
			
				<h2>Post 3</h2>
				<hr>
				<div class="row">
					<div class="span11">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed neque magna, non suscipit nulla. Etiam sed lacus ut nibh suscipit aliquam sed semper tortor. Vestibulum mollis nibh quam, sed imperdiet felis. Quisque eu tortor in purus convallis euismod. Aenean augue dui, suscipit id consequat id, tempus semper velit. Donec nisl lacus, elementum id tincidunt eget, vehicula et diam. Donec velit mauris, malesuada eu tempus ut, hendrerit vel ante. Ut et sapien eu eros mattis commodo a eget ipsum. Aliquam ac arcu augue. Quisque auctor viverra eros vitae fringilla. Suspendisse ac ipsum eros, at sagittis metus. Quisque nec risus felis, vitae aliquet neque. Suspendisse id nibh tortor, sit amet convallis dolor. Maecenas vestibulum, purus quis tristique tempus, tellus tortor molestie diam, vel dapibus erat nunc vehicula orci. Cras blandit, ante imperdiet pretium imperdiet, velit est bibendum ligula, eget accumsan purus urna et ipsum.
					</div>					
				</div>
			
		<br><br>
		Injecting sections
		<ul>
		    <li>Element: <b>1</b>
		    <li>Element: <b>2</b>
		    <li>Element: <b>3</b>
		    <li>Element: <b>hello</b>
		  
		</ul>
		
	</div>'''
        def model = controller.GSPHowTo()
		assert model.themeName == 'default'
		
		def resultado = aplanar(model.result)
		def experado = aplanar(expectedResult)
		Assert.assertEquals (resultado, experado)
    }
	
	@Test
	void testPartialGSPHowTo() {
		def expectedResult = '''<!-- @author: Gonzalo Garcia Jaubert -->
	<div class="fondodiv">
			
		<div class="row">
			<div class="span3">
				<img alt="imagen1" src='http://localhost/themes/default/images/grails_logo.png'">
			</div>
			<div class="span6">
				<h2>You're watching default theme</h2>
			</div>
		</div>
		<hr>
		Html: themes/default/default.html<br>
		Css: themes/default/default.css (Only in PartialGSP example)<br>
		Image with model injected example: themes/default/images/grails_logo.png<br>
				<h2>Post 1</h2>
				<hr>
				<div class="row">
					<div class="span11">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed neque magna, non suscipit nulla. Etiam sed lacus ut nibh suscipit aliquam sed semper tortor. Vestibulum mollis nibh quam, sed imperdiet felis. Quisque eu tortor in purus convallis euismod. Aenean augue dui, suscipit id consequat id, tempus semper velit. Donec nisl lacus, elementum id tincidunt eget, vehicula et diam. Donec velit mauris, malesuada eu tempus ut, hendrerit vel ante. Ut et sapien eu eros mattis commodo a eget ipsum. Aliquam ac arcu augue. Quisque auctor viverra eros vitae fringilla. Suspendisse ac ipsum eros, at sagittis metus. Quisque nec risus felis, vitae aliquet neque. Suspendisse id nibh tortor, sit amet convallis dolor. Maecenas vestibulum, purus quis tristique tempus, tellus tortor molestie diam, vel dapibus erat nunc vehicula orci. Cras blandit, ante imperdiet pretium imperdiet, velit est bibendum ligula, eget accumsan purus urna et ipsum.
					</div>					
				</div>
			
				<h2>Post 2</h2>
				<hr>
				<div class="row">
					<div class="span11">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed neque magna, non suscipit nulla. Etiam sed lacus ut nibh suscipit aliquam sed semper tortor. Vestibulum mollis nibh quam, sed imperdiet felis. Quisque eu tortor in purus convallis euismod. Aenean augue dui, suscipit id consequat id, tempus semper velit. Donec nisl lacus, elementum id tincidunt eget, vehicula et diam. Donec velit mauris, malesuada eu tempus ut, hendrerit vel ante. Ut et sapien eu eros mattis commodo a eget ipsum. Aliquam ac arcu augue. Quisque auctor viverra eros vitae fringilla. Suspendisse ac ipsum eros, at sagittis metus. Quisque nec risus felis, vitae aliquet neque. Suspendisse id nibh tortor, sit amet convallis dolor. Maecenas vestibulum, purus quis tristique tempus, tellus tortor molestie diam, vel dapibus erat nunc vehicula orci. Cras blandit, ante imperdiet pretium imperdiet, velit est bibendum ligula, eget accumsan purus urna et ipsum.
					</div>					
				</div>
			
				<h2>Post 3</h2>
				<hr>
				<div class="row">
					<div class="span11">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed neque magna, non suscipit nulla. Etiam sed lacus ut nibh suscipit aliquam sed semper tortor. Vestibulum mollis nibh quam, sed imperdiet felis. Quisque eu tortor in purus convallis euismod. Aenean augue dui, suscipit id consequat id, tempus semper velit. Donec nisl lacus, elementum id tincidunt eget, vehicula et diam. Donec velit mauris, malesuada eu tempus ut, hendrerit vel ante. Ut et sapien eu eros mattis commodo a eget ipsum. Aliquam ac arcu augue. Quisque auctor viverra eros vitae fringilla. Suspendisse ac ipsum eros, at sagittis metus. Quisque nec risus felis, vitae aliquet neque. Suspendisse id nibh tortor, sit amet convallis dolor. Maecenas vestibulum, purus quis tristique tempus, tellus tortor molestie diam, vel dapibus erat nunc vehicula orci. Cras blandit, ante imperdiet pretium imperdiet, velit est bibendum ligula, eget accumsan purus urna et ipsum.
					</div>					
				</div>
			
		<br><br>
		Injecting sections
		<ul>
		    <li>Element: <b>1</b>
		    <li>Element: <b>2</b>
		    <li>Element: <b>3</b>
		    <li>Element: <b>hello</b>
		  
		</ul>
		
	</div>'''
		def model = controller.partialGSPHowTo()
		assert model.themeName == 'default'
		
		def resultado = aplanar(model.result)
		def experado = aplanar(expectedResult)
		Assert.assertEquals (resultado, experado)
	}
	
	def aplanar(cadena) {
		// WTF: denormalize, expand, replace, \t...!
		return cadena.toString().denormalize().unexpand().replaceAll("\t", "").replaceAll(LS, "").replaceAll("\n", "").replaceAll(" ", "")
	}
}
