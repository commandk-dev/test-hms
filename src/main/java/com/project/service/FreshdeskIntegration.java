package com.project.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entity.Patient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class FreshdeskIntegration {
   public void sendPatientDataToFreshdesk(Patient patient) throws IOException {
       Map<String, Object> map = new HashMap<>();
       map.put("subject", "New Patient Data");
       map.put("description", patient.toString());
       map.put("status", 2);
       map.put("priority", 1);
         map.put("email", patient.getEmail());
         map.put("name", patient.getName());
            map.put("phone", patient.getPhone());

       ObjectMapper mapper = new ObjectMapper();
       String json = mapper.writeValueAsString(map);

       URL url = new URL("https://yourdomain.freshdesk.com/api/v2/tickets");
       HttpURLConnection conn = (HttpURLConnection) url.openConnection();

       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "application/json");
       conn.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(("api_key:" + "X").getBytes()));
       conn.setDoOutput(true);

       OutputStream os = conn.getOutputStream();
       os.write(json.getBytes());
       os.flush();

       int responseCode = conn.getResponseCode();
       System.out.println("Response Code : " + responseCode);

       BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
       String inputLine;
       StringBuffer response = new StringBuffer();

       while ((inputLine = in.readLine()) != null) {
           response.append(inputLine);
       }
       in.close();

       System.out.println("Response Body : " + response.toString());
   }
}