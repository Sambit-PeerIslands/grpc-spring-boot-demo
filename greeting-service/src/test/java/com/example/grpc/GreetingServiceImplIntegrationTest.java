package com.example.grpc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.devh.boot.grpc.client.inject.GrpcClient;

import com.example.grpc.service.GreetingServiceImpl;

@SpringBootTest(properties = {
    "grpc.server.inProcessName=test", // Enable inProcess server
    "grpc.server.port=-1", // Disable external server
    "grpc.client.inProcess.address=in-process:test" // Configure the client to connect to the inProcess server
})
@SpringJUnitConfig(classes = { MyServiceIntegrationTestConfiguration.class })
// Spring doesn't start without a config (might be empty)
@DirtiesContext // Ensures that the grpc-server is properly shutdown after each test
// Avoids "port already in use" during tests
public class GreetingServiceImplIntegrationTest {

  @GrpcClient("inProcess")
  private GreetingServiceImpl myService;

//  @Test
//  @DirtiesContext
//  public void testSayHello() {
//    GreetingRequest request = GreetingRequest.newBuilder()
//        .setMessage("How are you....")
//        .build();
////    GreetingResponse response = myService.greeting(request);
////    assertNotNull(response);
////    assertEquals("Hello ==> Test", response.getMessage())
//  }

}