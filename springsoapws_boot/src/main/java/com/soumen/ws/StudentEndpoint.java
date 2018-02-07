/*
@Endpoint: Indicates an annotated class for web service end point.
@PayloadRoot: Marks the method to be handler for incoming request.
@ResponsePayload: Bounds the method return type to the response payload.
@RequestPayload : Bounds the method parameter to the request payload.
*/
package com.soumen.ws;

import com.soumen.soap.StudentRequest;
import com.soumen.soap.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StudentEndpoint {

    private static final String NAMESPACE_URI = "http://soumen.com/soap";
    @Autowired
    private StudentUtility studentUtility;
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "StudentRequest")
    @ResponsePayload
    public StudentResponse getStudent(@RequestPayload StudentRequest request) {

        StudentResponse response = new StudentResponse();
        response.setStudent(studentUtility.getStudent(request.getStudentId()));
        return response;
    }

}
