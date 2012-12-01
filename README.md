# DynamicThemes Plugin

Author: Gonzalo Garcia Jaubert

Version: 0.1.0

## Introduction

The **DynamicThemes plugin** allows your Grails application to load and render pages with your own theme ( **folders** with GSP templates and css) dinamically outside the scope of a web request. If you've ever used Tumblr or Wordpress then you know what a **theme** is and how it works.

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
* See [blog](http://dancingrails.com)
* And follow @ggjaubert 

### Licensing

`
This plugin is released under the Apache License. 
`

## Quick Start

### Install the plugin

Installing the plugin can be done in one of two ways:

#### Installing the plugin using then Grails dependency DSL (Recommended)


**Note: Not avaliable yet. Contribute to http://grails.org/plugins/pending**


In BuildConfig.groovy, add the dependency to your 'plugins' section:

```groovy
plugins {
       	...
        	compile ':dynamicthemes:0.1'
       	...
    }
```
    
Note that the version (0.1 in the example above) should be change to refelect the version you would like to us


#### Installing the plugin 'on-the-fly'


**Note: Not avaliable yet. Contribute to http://grails.org/plugins/pending**


Simply run the command `grails install-plugin dynamicthemes`. Note that this will install the latest version into the global scope, rather than the compile scope which is slightly cleaner, above.

### Configure the plugin

Edit `grails-appl-base-dir/conf/Config.groovy` to add:

```groovy
dynamicThemes {
    development {
		resourceController.useCacheControl = true
		preprocessor.urlThemes = 'themes' 
		preprocessor.fileSystemPath = 'web-app/themes'
    }
    production {
		resourceController.useCacheControl = true
		preprocessor.urlThemes = 'themes' 
		preprocessor.fileSystemPath =  'themes'
    }
}
```

You can define values for you development and production enviroment.

- useCacheControl: Override to control the css cached.
- urlThemes: Relative url for themes.
- fileSystemPath: Relative file system path for themes. Default is web-app/themes


## Scripts

After installation you can call the DyncamicThemes script **grails DynamicThemes**, which will copy several files into your project. 

`
  grails DynamicThemes
`

This script will create a themes folder under web-app and copy to themes into it (default and default2).

## HowTo's

You can test the plugin with the next views:

* partialGSPHowTo: Example of partial gsp injection
* GSPHowto: Example of full gsp injection
* cssHowTo: Example of css injection

If you want full examples, visit [DynamicThemesExample](https://github.com/gonzalogarciajaubert/DynamicThemesExample)  

## Themes

Themes are folders with the following example structure:

```groovy
themeName
- \icons\*.png
- \images\*.png
- themeName.css
- themeName.html
- themeName.snapshot.png
```

The only expected file is themeName.html. You can see two themes examples in the webapp dir: default and default2.

The default.html file contains a link to **webapps\themes\images\grails_logo.png** with the next code: 

`groovy
	<img alt="imagen1" src='${ImagesPath}/grails_logo.png'">
`

This html (a gsp) use the default.css stylesheet:

`groovy
	<div class="fondodiv">
`

And show two ways to see a list of elements. First with grails code:

```groovy
		<g:each var="element" in="${elements}">			
				<h2>${element}</h2>
				<hr>
				<div class="row">
					<div class="span11">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed neque magna, ...
					</div>					
				</div>
		</g:each>
```

And with sections:

```groovy
		<ul>
		  #beginLoop#
		    <li>Element: <b>${element}</b>
		  #endLoop#
		</ul>
```

## Using the plugin

Using the **DynamicThemes plugin** in your application requires a few simple steps:

Process your theme and return to view:

```groovy
	def theme = preprocessorService.preprocess(themeName)
	[heme: theme]
```

**Optional**: If you want to inject sections (see _sections_):

```groovy
	// Example of sections replace
	def elements = "['Post 1', 'Post 2', 'Post 3']"
	def sections = ["#beginLoop#": """<g:each var="element" in="${elements}">""", """#endLoop#""": """</g:each>"""]
```

**Optional**: If you want to inject models (see _models_):

```groovy
	def themeName = "default"
	def elementsList = ['Post 1', 'Post 2', 'Post 3']
	def model = [ImagesPath: preprocessorService.getConfigParams(themeName).urlFolderImages, elements:elementsList]
```

**Optional**: Example for a snapshot (see _more_)

```groovy
	def snap = preprocessorService.getConfigParams(themeName).urlSnapshot
```

**Optional**: Process your theme and return to view with your optional params:

```groovy
	def theme = preprocessorService.preprocess(themeName , sections, model)
```

## Using the Tag Library

To inject a dynamic css in your view use the injectCss tag:

```groovy
	<head>
		<meta name="layout" content="main">
		<title>cssHowTo</title>
		<g:injectCss themeName="${themeName}" ImagesPath="true" />
		<g:injectCss themeName="${themeName}" ImagesPath="false" model="[var1: 'hello']"/>
	</head>
```

To inject a dynamic html/gsp in your view use the injectGSP tag: 

```groovy
	<body>
		An css and gsp DynamicThemes example. View source code for the css DynamicThemes.
		<g:injectGSP instance="${result}"/>
	</body>
```


## Parameters

When using the plugin you can use the next parameters:

#### sections

Sections are code replaced before processing. Example:

```groovy
		<ul>
		  #beginLoop#
		    <li>Element: <b>${element}</b>
		  #endLoop#
		</ul>
```

in your controller replace the secctions with your own code with:

```groovy
	// Example of sections replace
	def elements = "['Post 1', 'Post 2', 'Post 3']"
	def sections = ["#beginLoop#": """<g:each var="element" in="${elements}">""", """#endLoop#""": """</g:each>"""]
```


#### models

You can inject models in your theme. Example:

```groovy
    <g:each var="element" in="${elements}">			
				<h2>${element}</h2>
				<hr>
				<div class="row">
					<div class="span11">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed neque magna, ...
					</div>					
				</div>
		</g:each>
```

In your controller use:
```groovy
	def themeName = "default"
	def elementsList = ['Post 1', 'Post 2', 'Post 3']
	def model = [ImagesPath: preprocessorService.getConfigParams(themeName).urlFolderImages, elements:elementsList]
```

#### others

You can inject other files. For example the snapshot of the theme with:

```groovy
		<div class="hero-unit">
			<h2>Snapshot for the theme</h2>
			<img src="${snapshot}" style="width:200px"/>
		</div>
```

In your controller:

```groovy
	def snap = preprocessorService.getConfigParams(themeName).urlSnapshot
```

Note: The urlSnapshot is the root of the theme

## Changelog

;0.1.0 Initial version (November 29, 2012)

