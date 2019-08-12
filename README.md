# FreshSmell
http://freshsmell.netsoc.com

Alternate: http://alt.freshsmell.netsoc.com/

A web-based code-smell analyzer for Java using Spring Boot, Tomcat and .jsp.

Getting Started (user):
1. Access at freshsmell.netsoc.com
	- Only upload of files is supported, though you can upload multiple at a time
2. If local access needed:
	- Ensure Tomcat and Maven are installed
	- If using intelliJ please acess the edit configurations screen, which is shown below:
	<br>
	<img align="center" src="/README_Images/editConfiguration.png">
	- At this point create a new tomcat server, with the following settings applied:
	<br>
	<img align="center" src="/README_Images/deploymentEditScreen.png">
	<br>
	<img align="center" src="/README_Images/serverEditScreen.png">
3. Non-web testing also available through LocalApplicationTest.java


Getting started (dev):
- Install Maven, Spring (as Maven dependency) and Tomcat
- In terminal (at project location), enter:
	- mvn clean
	- mvn compile
	- mvn package
- Add run configuration using TomcatServer-Local
- After 'Build' in 'Before Launch', add 'Run Maven Goal -> 'clean'
- Repeat the above step, this time adding 'Run Maven Goal' -> 'package' right after clean
- Mark '/data/testfiles/' as excluded from builds/linting/compilation

To Add a SmellerService
- Decide whether or not you need to add a new testable attribute to the Model
- If so:
	- Go to the method ParsingProfile.parse()
	- type 'file.' and find what you need from the Intellisense menu
	- Add the relevant attribute (with variable, getter and setter) to both ParsedClass/Method and Class/MethodModel
	- In ParsingProfile.parse(), add the relevant mapping from that attribute in JavaClass to ParsedClass
	- Do the same thing in MappingProfile.map() from ParsedClass/Method to Class/MethodModel
	- ----- IMPORTANT ----
		- If AT ALL POSSIBLE (ie if you don't need individual object data), store in the Model as a String or boolean. This makes several other methods I'm using a lot easier.
			- For example: if you just need to query the value or existence of an attribute, a String is enough.
		- If storing as a boolean, either let me know or add an exclusion to JSONUtility's Constructor's declaration of _json excludes as I have done with the other attributes.
- Write your smell in Services/Smells/Concrete and make sure it implements ISmeller
	- Don't change ISmeller without asking me!
	- All new methods should be private
- Add a mapping to your smell in SmellerService.init()
