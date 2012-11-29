package org.gonzalogarciajaubert.dynamicThemes

import static org.junit.Assert.*

import org.junit.*

class DynamicThemesTagLibTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testInjectCSSTag() {
		assertEquals """<link rel="stylesheet" href="/resource/css/default?ImagesPath=true&" />""", new DynamicThemesTagLib().injectCss(themeName:"default", ImagesPath:"true").toString();
    }
	
	@Test
	void testInjectGSPTag() {
		assertEquals "example", new DynamicThemesTagLib().injectGSP(instance:"example").toString();
    }

}
