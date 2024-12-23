## Grpc Setup

1. Create 2 modules common and service under parent project.
2. common module contains proto files which will define request, response structure and service in .protofile
3. Run `./mvnw clean install` which will generate required java classes from the proto file (POJO & Service classes)
4. Add common module as a dependency in service module to use the generated java classes (for Greeting API) in service module
5. In service module create your own service class annotated with @GrpcService, extend to *ImplBase abstract class and override "greeting" method (service logic)
6. service-pom.xml add the following dependency for a grpc server
```shell
<dependency>
	<groupId>net.devh</groupId>
	<artifactId>grpc-server-spring-boot-starter</artifactId>
	<version>2.12.0.RELEASE</version>
</dependency>
```

### Run and test the application

1. Start the grpc server by running the `[GrpcSpringBootDemoApplication.java](greeting-service%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fgrpc%2FGrpcSpringBootDemoApplication.java)`
2. Hit the server with grpc requests using any of the following client (1. Terminal using grpcurl , 2. Another client module , 3. Postman)
3. From postman , create a new grpc request -> specify endpoint "localhost:9090" -> Import service defination by importing this proto file [greeting.proto](greeting-common%2Fsrc%2Fmain%2Fproto%2Fgreeting.proto)
