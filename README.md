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

- [x] Eureka (service registration and discovery)

	execute `mvn spring-boot:run -Dspring.profiles.active=peer1` and `mvn spring-boot:run -Dspring.profiles.active=peer2` to start eureka server cluster
	
	execute `mvn spring-boot:run -Dserver.port=8080`, `mvn spring-boot:run -Dserver.port=8081`, `mvn spring-boot:run -Dserver.port=8082`, `mvn spring-boot:run -Dserver.port=8083` to register multiple services
	
	execute `mvn spring-boot:run -Dserver.port=8100` to start gateway, then invoke service registered in eureka server cluster

- [x] Hystrix (circuit breaker) and Hystrix Dashboard (monitor stream)

	execute `mvn spring-boot:run -Dserver.port=8200` to start hystrix dashboard
	
	input `http://127.0.0.1:8300/turbine.stream` on hystrix dashboard home page, monitor micro service running state

- [x] Turbine (gather hystrix metrics stream)

	execute `mvn spring-boot:run -Dserver.port=8300` to start turbine

- [x] Ribbon (client side load balancing)

- [x] Feign (easy rest client)

- [x] Archaius (dynamic configuration)

- [ ] Zuul (intelligent routing)

- [ ] Metrics (Spectator/Servo, metrics collection; Atlas, metrics management backend of dimensional time series data)

	download atlas jar, and execute `java -jar atlas-1.4.6-standalone.jar  memory.conf` to start atlas server
	execute `curl http://127.0.0.1:7101/api/v1/tags` in terminal, or enter `http://127.0.0.1:7101/api/v1/graph?q=name,rest,:eq,:avg` in browser to query metrics

- [ ] RxJava (reactive programming)

- [x] update version

    mvn versions:set versions:commit -DnewVersion=`<version>`