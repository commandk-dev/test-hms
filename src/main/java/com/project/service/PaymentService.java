package com.project.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.RazorpayOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

   @Value("${razorpay.key}")
   private String key;

   @Value("${razorpay.secret}")
   private String secret;

   public Order createOrder(int amount) throws RazorpayException {
       RazorpayClient client = new RazorpayClient(key, secret);
       Map<String, Object> options = new HashMap<>();
       options.put("amount", amount * 100); // Amount is in paise
       options.put("currency", "INR");
       return client.Orders.create(options);
   }

   public boolean verifySignature(String razorpaySignature, String orderId, int amount) throws RazorpayException {
       RazorpayClient client = new RazorpayClient(key, secret);
       return client.Utility.verifyWebhookSignature(razorpaySignature, orderId, amount);
   }
}