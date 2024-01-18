package com.project.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.project.entity.Patient;

public class SmsService {
   private static final String ACCOUNT_SID = "your_account_sid";
   private static final String AUTH_TOKEN = "your_auth_token";
   private static final PhoneNumber FROM_PHONE_NUMBER = new PhoneNumber("your_twilio_phone_number");

   public void sendOtpToPatient(Patient patient, String otp) {
       Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

       String messageBody = "Your OTP is: " + otp;

       Message message = Message.creator(
               new PhoneNumber(patient.getMobileNo()), // to
               FROM_PHONE_NUMBER, // from
               messageBody)
           .create();

       System.out.println("OTP sent to patient: " + patient.getPid());
   }
}