package com.wsei.healthcare.backend.patient.integration;

//public class PatientDoctorAssignmentIT extends AbstractPatientIT {
//
//    @Autowired
//    private DoctorWebHelper doctorWebHelper;
//
//    @Test
//    public void setPersonalDoctor_shouldAssignDoctor_whenValidDoctorId() throws Exception {
//        // Register patient
//        String emailPatient = UserTestDataProvider.validEmail() + UUID.randomUUID();
//        String tokenUserPatient = authTokenFactory.createAuthenticatedUserToken(
//                UserRegisterRequestBuilder.getValidDefault()
//                        .setEmail(emailPatient)
//                        .build()
//        );
//        patientWebHelper.performPatientProfileRegister(
//                PatientCreationRequestBuilder.getValidDefault().build(),
//                tokenUserPatient
//        ).andExpect(status().isOk());
//
//        // Register doctor
//        String emailDoctor = UserTestDataProvider.validEmail() + UUID.randomUUID();
//        String tokenUserDoctor = authTokenFactory.createAuthenticatedUserToken(
//                UserRegisterRequestBuilder.getValidDefault()
//                        .setEmail(emailDoctor)
//                        .build()
//        );
//        DoctorProfileResponse doctorProfileResponse = doctorWebHelper.getDoctorProfileResponse(
//                doctorWebHelper.performDoctorProfileRegister(
//                        DoctorCreationRequestBuilder.getValidDefault().build(),
//                        tokenUserDoctor
//                ).andExpect(status().isOk())
//        );
//
//        // Assign the personal doctor to the patient
//        patientWebHelper.performPersonalDoctorAssignment(
//                tokenUserPatient,
//                doctorProfileResponse.doctor().id()
//        ).andExpect(status().isOk());
//
//        // Check if the patient has the doctor assigned
//        PatientProfileResponse patientProfileResponse = patientWebHelper.getPatientProfileResponse(
//                patientWebHelper.performPatientProfileRetrieve(tokenUserPatient)
//                        .andExpect(status().isOk())
//        );
//
//        assertNotNull(patientProfileResponse.user());
//        assertNotNull(patientProfileResponse.patient());
//        assertNotNull(patientProfileResponse.personalDoctor());
//        assertEquals(
//                doctorProfileResponse.doctor().id(),
//                patientProfileResponse.personalDoctor().doctor().id()
//        );
//    }
//
//    @Test
//    public void setPersonalDoctor_shouldReturnBadRequest_whenDoctorDoesNotExist() throws Exception {
//        patientWebHelper.performPatientProfileRegister(
//                PatientCreationRequestBuilder.getValidDefault().build(),
//                token
//        ).andExpect(status().isOk());
//
//        patientWebHelper.performPersonalDoctorAssignment(
//                token,
//                0L
//        ).andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void setPersonalDoctor_shouldReturnBadRequest_whenDoctorIdIsInvalid() throws Exception {
//        patientWebHelper.performPatientProfileRegister(
//                PatientCreationRequestBuilder.getValidDefault().build(),
//                token
//        ).andExpect(status().isOk());
//
//        patientWebHelper.performPersonalDoctorAssignment(
//                token,
//                -2L
//        ).andExpect(status().isBadRequest());
//    }
//}