package com.example.grpc.service;

import com.example.grpc.GreetingRequest;
import com.example.grpc.GreetingResponse;
import com.example.grpc.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        String message = request.getMessage();
        System.out.println("Received message: "+message);

        GreetingResponse response = GreetingResponse.newBuilder().setMesaage("Received your "+message+". Hello from server").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
