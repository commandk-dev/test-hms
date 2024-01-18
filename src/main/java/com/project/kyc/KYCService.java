package com.project.service;

import com.project.model.AadhaarVerificationRequest;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;
import org.springframework.stereotype.Service;

@Service
public class KYCService {

 private final SqsClient sqsClient;
 private final String queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/MyQueue"; // replace with your Queue URL

 public KYCService() {
    this.sqsClient = SqsClient.create();
 }

 public void initiateVerification(AadhaarVerificationRequest request) {
    String messageBody = "{\"aadhaarNumber\":\"" + request.getAadhaarNumber() + "\", \"name\":\"" + request.getName() + "\", \"dob\":\"" + request.getDob() + "\", \"gender\":\"" + request.getGender() + "\"}";

    SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
            .queueUrl(queueUrl)
            .messageBody(messageBody)
            .build();

    sqsClient.sendMessage(sendMsgRequest);
 }
}