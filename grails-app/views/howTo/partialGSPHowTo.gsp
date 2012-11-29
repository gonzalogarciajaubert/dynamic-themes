<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>partialGSPHowTo</title>
		<g:injectCss themeName="${themeName}"/>
		<g:injectCss themeName="${themeName}" ImagesPath="true" />
		<g:injectCss themeName="${themeName}" ImagesPath="false" model="[var1: 'hello']"/>		
	</head>
	<body>
		An css and gsp DynamicThemes example. View source code for the css DynamicThemes.
		
		<g:injectGSP instance="${result}"/>
	</body>
</html>
