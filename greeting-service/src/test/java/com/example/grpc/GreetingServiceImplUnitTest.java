package com.example.grpc;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.grpc.service.GreetingServiceImpl;

import io.grpc.internal.testing.StreamRecorder;

@SpringBootTest
public class GreetingServiceImplUnitTest {

  private final GreetingServiceImpl greetingService = new GreetingServiceImpl();

//  @BeforeEach
//  public void setup() {
//    greetingService = new GreetingServiceImpl();
//  }

  @Test
  void testSayHello() throws Exception {
    GreetingRequest request = GreetingRequest.newBuilder()
        .setMessage("How are you....")
        .build();
    StreamRecorder<GreetingResponse> responseObserver = StreamRecorder.create();
    greetingService.greeting(request, responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      fail("The call did not terminate in time");
    }


    assertNull(responseObserver.getError());
    List<GreetingResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    GreetingResponse response = results.getFirst();
    assertEquals(GreetingResponse.newBuilder()
        .setMesaage("Received your How are you....." +
            " Hello from server")
        .build(), response);
  }

}
