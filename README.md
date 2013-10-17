Contexts and Dependency Injection for Java EE (CDI) Magical Mystery Tour Demo Code
==================================================================================
The code was run against WebLogic 12c, but should work on any Java EE 6 compatible
container. 

In order to run the code, you must make sure to bind a data source to the JNDI location
'java:global/jdbc/AcmeBankDB' using your application server's facilities (in Java EE 7
this can be done portably).

Also note that the build is set to skip tests. This is because I used a WebLogic running
instance for the tests via Arquillian. You can run the tests once you get WebLogic up 
and running (look at the arquillian.xml file in the code for the expected WebLogic
settings). You should be able to test against any Arquillian supported container if you
want.