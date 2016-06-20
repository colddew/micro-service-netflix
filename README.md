description
=======
micro service architected by netflix

tech stack
=======
spring cloud and netflix open source components

build
=======
- [x] spring cloud config
	
	execute `mvn spring-boot:run -Dspring.profiles.active=configserver1` and `mvn spring-boot:run -Dspring.profiles.active=configserver2` to start multiple config servers, then configure `spring:cloud:config:uri:` with domain name in config client, eventually implement load balance by nginx
	
    define the non-static bean with @RefreshScope annotation, execute `curl -X POST http://localhost:8080/refresh` to refresh the attribute value

- [ ] Eureka (service registration and discovery)

	execute `mvn spring-boot:run -Dspring.profiles.active=peer1` and `mvn spring-boot:run -Dspring.profiles.active=peer2` to start eureka server cluster

- [ ] Hystrix

- [ ] Turbine

- [ ] Ribbon (client side load balancing)

- [ ] Feign (easy rest client)

- [ ] Archaius

- [ ] Zuul

- [ ] Metrics

- [ ] RxJava (reactive programming library)

- [x] update version

    mvn versions:set versions:commit -DnewVersion=`<version>`
