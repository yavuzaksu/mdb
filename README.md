mdb
===

dashboard


* hot to create eclipse files 
using command promt drive ot parent folder and use "mvn eclipse:eclipse" to be able import projest into your eclipse


project profiles 

* to run default profile 
mvn install

* to run integration test
mvn install -Pint-tests

* to run selenium tests 
mvn install -Pui-tests

* to run integration tests and selenium test 
mvn install
mvn install  -Pfull-tests