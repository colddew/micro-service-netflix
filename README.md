description
=======
micro service architected by netflix

tech stack
=======
spring cloud and netflix open source components

build
=======
- [x] spring cloud config
	
	execute `mvn spring-boot:run -Dspring.profiles.active=configserver1` and `mvn spring-boot:run -Dspring.profiles.active=configserver2` to start multiple config servers, then configure `spring:cloud:config:uri:` with domain name in config client, eventually implement load balance and high availability by nginx
	
    define the non-static bean with @RefreshScope annotation, execute `curl -X POST http://localhost:8080/refresh` to refresh the attribute value

- [ ] Eureka (service registration and discovery)

	execute `mvn spring-boot:run -Dspring.profiles.active=peer1` and `mvn spring-boot:run -Dspring.profiles.active=peer2` to start eureka server cluster
	
	execute `mvn spring-boot:run -Dserver.port=8080` and `mvn spring-boot:run -Dserver.port=8081` to register service
	
	execute `mvn spring-boot:run -Dserver.port=8100` to start gateway, then invoke service registered in eureka server cluster

- [ ] Hystrix (circuit breaker)

- [ ] Turbine

- [ ] Ribbon (client side load balancing)

- [ ] Feign (easy rest client)

- [ ] Archaius

- [ ] Zuul (intelligent routing)

- [ ] Metrics

- [ ] RxJava (reactive programming)

- [x] update version

    mvn versions:set versions:commit -DnewVersion=`<version>`
