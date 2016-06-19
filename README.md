description
=======
micro service architected by netflix

tech stack
=======
spring cloud

build
=======
spring cloud config
define the non-static bean with @RefreshScope annotation, execute `curl -X POST http://localhost:8080/refresh` to refresh the attribute value

update version
mvn versions:set versions:commit -DnewVersion=`<version>`