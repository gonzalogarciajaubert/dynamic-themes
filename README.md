# DynamicThemes Plugin

Author: Gonzalo Garcia Jaubert

Version: 0.1.0

## Introduction

The **DynamicThemes plugin** allows your Grails application to load and render your pages with your own theme (**folders** with GSP templates and css) dinamically outside the scope of a web request. If you've ever used Tumblr or Wordpress then you know what a **theme** is and how it works.

You can process models and sections (your own tags) in your themes. 

This plugin provides the following feature:

* Easy way to add themes to your project
* Build external gsp and css themes
* Change the style of your pages with external css
* Load external views
* Load external templates

### Further Reading 

* See [DynamicThemesExample](https://github.com/gonzalogarciajaubert/DynamicThemesExample)  application for examples.
* See [DynamicThemes official page](http://dynamicthemes.dancingrails.com) 
* See [blog](http://www.dancingrails.com)
* And follow @ggjaubert 

### Licensing

`
This plugin is released under the Apache License. 
`

## Quick Start

### Install the plugin

Installing the plugin can be done in one of two ways:

#### Installing the plugin using then Grails dependency DSL (Recommended)

`
Note: Not avaliable yet. Contribute to http://grails.org/plugins/pending
`

In BuildConfig.groovy, add the dependency to your 'plugins' section:

```
plugins {
       	...
        	compile ':dynamicthemes:0.1'
       	...
    }
```
    
Note that the version (0.1 in the example above) should be change to refelect the version you would like to us


#### Installing the plugin 'on-the-fly'

`
Note: Not avaliable yet. Contribute to http://grails.org/plugins/pending
`

Simply run the command grails install-plugin dynamicthemes. Note that this will install the latest version into the global scope, rather than the compile scope which is slightly cleaner, above.

### Configure the plugin

Edit grails-appl-base-dir/conf/Config.groovy to add:

```
dynamicThemes {
    development {
		resourceController.useCacheControl = true			// Override to control the css cached
		preprocessor.urlThemes = 'themes'					// Relative url for themes. 
		preprocessor.fileSystemPath = 'web-app/themes'		// Relative file system path for themes. Default is web-app/themes
    }
    production {
		resourceController.useCacheControl = true			// Override to control the css cached
		preprocessor.urlThemes = 'themes'					// Relative url for themes. 
		preprocessor.fileSystemPath =  "themes"				// Relative file system path for themes (servletContext)
    }
}
```


# Scripts

After installation you can call the DyncamicThemes script **grails DynamicThemes**, which will copy several files into your project. 

`
  grails DynamicThemes
`

This script will create a themes folder under web-app and copy to themes into it (default and default2).

### HowTo's

You can test the plugin with the next views:

* partialGSPHowTo: Example of partial gsp injection
* GSPHowto: Example of full gsp injection
* cssHowTo: Example of css injection

If you want full examples, visit [DynamicThemesExample](https://github.com/gonzalogarciajaubert/DynamicThemesExample)  

## Themes

Themes are folders with the following example structure:

`
themeName
	\icons\*.png
	\images\*.png
	themeName.css
	themeName.html
	themeName.snapshot.png
`

The only expected file is themeName.html. You can see two themes examples in the webapp dir: default and default2.

The default.html file contains a link to "webapps\themes\images\grails_logo.png" with the next code: 

`
	<img alt="imagen1" src='${ImagesPath}/grails_logo.png'">
`

This html (a gsp) use the default.css stylesheet:

`
	<div class="fondodiv">
`

And show two ways to see a list of elements. First with grails code:

```
		<g:each var="element" in="${elements}">			
				<h2>${element}</h2>
				<hr>
				<div class="row">
					<div class="span11">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed neque magna, non suscipit nulla. Etiam sed lacus ut nibh suscipit aliquam sed semper tortor. Vestibulum mollis nibh quam, sed imperdiet felis. Quisque eu tortor in purus convallis euismod. Aenean augue dui, suscipit id consequat id, tempus semper velit. Donec nisl lacus, elementum id tincidunt eget, vehicula et diam. Donec velit mauris, malesuada eu tempus ut, hendrerit vel ante. Ut et sapien eu eros mattis commodo a eget ipsum. Aliquam ac arcu augue. Quisque auctor viverra eros vitae fringilla. Suspendisse ac ipsum eros, at sagittis metus. Quisque nec risus felis, vitae aliquet neque. Suspendisse id nibh tortor, sit amet convallis dolor. Maecenas vestibulum, purus quis tristique tempus, tellus tortor molestie diam, vel dapibus erat nunc vehicula orci. Cras blandit, ante imperdiet pretium imperdiet, velit est bibendum ligula, eget accumsan purus urna et ipsum.
					</div>					
				</div>
		</g:each>
```

And with sections:

```
		<ul>
		  #beginLoop#
		    <li>Element: <b>${element}</b>
		  #endLoop#
		</ul>
```

## Using the plugin

Using the '''DynamicThemes plugin''' in your application requires a few simple steps:

**Optional**: If you want to inject sections (see _sections_):

```
	// Example of sections replace
	def elements = "['Post 1', 'Post 2', 'Post 3']"
	def sections = ["#beginLoop#": """<g:each var="element" in="${elements}">""", """#endLoop#""": """</g:each>"""]
```

**Optional**: If you want to inject models (see _models_):

```
	def themeName = "default"
	def elementsList = ['Post 1', 'Post 2', 'Post 3']
	def model = [ImagesPath: preprocessorService.getConfigParams(themeName).urlFolderImages, elements:elementsList]
```

**Optional**: Example for a snapshot (see _more_)

```
	def snap = preprocessorService.getConfigParams(themeName).urlSnapshot
```

Process your theme and return to view:

```
	def theme = preprocessorService.preprocess(themeName , sections, model)
	[themes: themes, theme: theme, css: cssName, cssSelect: params?.cssSelect, themeSelect: params?.themeSelect, snapshot: snap]
```

### Using the Tag Library

To inject a dynamic css in your view use the injectCss tag:

```
	<head>
		<meta name="layout" content="main">
		<title>cssHowTo</title>
		<g:injectCss themeName="${themeName}" ImagesPath="true" />
		<g:injectCss themeName="${themeName}" ImagesPath="false" model="[var1: 'hello']"/>
	</head>
```

To inject a dynamic html/gsp in your view use the injectGSP tag: 

```
	<body>
		An css and gsp DynamicThemes example. View source code for the css DynamicThemes.
		<g:injectGSP instance="${result}"/>
	</body>
```


### Parameters

When using the plugin you can use the next parameters:

#### sections

Sections are code replaced before processing. Example:

```
		<ul>
		  #beginLoop#
		    <li>Element: <b>${element}</b>
		  #endLoop#
		</ul>
```

in your controller replace the secctions with your own code with:

```
	// Example of sections replace
	def elements = "['Post 1', 'Post 2', 'Post 3']"
	def sections = ["#beginLoop#": """<g:each var="element" in="${elements}">""", """#endLoop#""": """</g:each>"""]
```


#### models

You can inject models in your theme. Example:

```
    <g:each var="element" in="${elements}">			
				<h2>${element}</h2>
				<hr>
				<div class="row">
					<div class="span11">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed neque magna, non suscipit nulla. Etiam sed lacus ut nibh suscipit aliquam sed semper tortor. Vestibulum m...
					</div>					
				</div>
		</g:each>
```

In your controller use:
```
	def themeName = "default"
	def elementsList = ['Post 1', 'Post 2', 'Post 3']
	def model = [ImagesPath: preprocessorService.getConfigParams(themeName).urlFolderImages, elements:elementsList]
```

#### more

You can inject other files. For example the snapshot of the theme with:

```
		<div class="hero-unit">
			<h2>Snapshot for the theme</h2>
			<img src="${snapshot}" style="width:200px"/>
		</div>
```

In your controller:

```
	def snap = preprocessorService.getConfigParams(themeName).urlSnapshot
```

Note: The urlSnapshot is the root of the theme

## Changelog

;0.1.0 Initial version (November 29, 2012)

