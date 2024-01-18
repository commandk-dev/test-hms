package com.project.dw;

import com.project.entity.Patient;

public class RSService {

    public void savePatientDataToRedshift(Patient patient) {
  String sql = "INSERT INTO patients (pid, name, birthdate, gender, emailID, mobileNo, adharNo, country, state, city, residentialAddress, permanentAddress, bloodGroup, chronicDiseases, medicineAllergy, doctorId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  
  String residentialAddress = patient.getAddress().getResidentialAddress();
  String permanentAddress = patient.getAddress().getPermanentAddress();

  ExecuteStatementRequest request = ExecuteStatementRequest.builder()
          .clusterIdentifier("yourClusterIdentifier")
          .dbUser("yourDbUser")
          .sql(sql)
          .parameters(Parameter.builder().parameterName("1").parameterValue(patient.getPid()).build(),
                    Parameter.builder().parameterName("2").parameterValue(patient.getName().toString()).build(),
                    Parameter.builder().parameterName("3").parameterValue(patient.getBirthdate()).build(),
                    Parameter.builder().parameterName("4").parameterValue(patient.getGender()).build(),
                    Parameter.builder().parameterName("5").parameterValue(patient.getEmailID()).build(),
                    Parameter.builder().parameterName("6").parameterValue(Long.toString(patient.getMobileNo())).build(),
                    Parameter.builder().parameterName("7").parameterValue(Long.toString(patient.getAdharNo())).build(),
                    Parameter.builder().parameterName("8").parameterValue(patient.getCountry()).build(),
                    Parameter.builder().parameterName("9").parameterValue(patient.getState()).build(),
                    Parameter.builder().parameterName("10").parameterValue(patient.getCity()).build(),
                    Parameter.builder().parameterName("11").parameterValue(residentialAddress).build(),
                    Parameter.builder().parameterName("12").parameterValue(permanentAddress).build(),
                    Parameter.builder().parameterName("13").parameterValue(patient.getBloodGroup()).build(),
                    Parameter.builder().parameterName("14").parameterValue(patient.getChronicDiseases()).build(),
                    Parameter.builder().parameterName("15").parameterValue(patient.getMedicineAllergy()).build(),
                    Parameter.builder().parameterName("16").parameterValue(patient.getDoctorId()).build())
          .build();

  redshiftClient.executeStatement(request);
}
    
}
