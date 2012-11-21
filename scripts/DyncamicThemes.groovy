includeTargets << grailsScript("_GrailsInit")
target(main: "Install DynamicThemes default themes into project") {
	
	println ("Clearing folders...")
	if(new File("${basedir}/web-app/themes").exists()) {
			ant.delete(dir:"${basedir}/web-app/themes")
	}
	
	println ("Creating folders...")
	ant.mkdir(dir: "${basedir}/web-app/themes")
	
	println ("Copying files...")
	ant.copy ( todir :"${basedir}/web-app/themes") { 
						fileset ( dir :"${dynamicThemesPluginDir}/web-app/themes")
	}
	println ("Files copied")
}

setDefaultTarget(main)