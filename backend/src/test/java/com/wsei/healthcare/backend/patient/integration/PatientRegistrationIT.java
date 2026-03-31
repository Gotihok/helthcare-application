package com.wsei.healthcare.backend.patient.integration;

import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.util.PatientCreationRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientRegistrationIT extends AbstractPatientIT {

    @Test
    public void createPatientProfile_shouldRegisterNewPatient_whenValidRequest() throws Exception {
        PatientProfileResponse response = patientWebHelper.getPatientProfileResponse(
                patientWebHelper.performPatientProfileRegister(
                                PatientCreationRequestBuilder.getValidDefault().build(),
                                token
                        ).andExpect(status().isOk()));

        assertNotNull(response.user());
        assertNotNull(response.patient());
        assertNull(response.personalDoctor());
    }

    @Test
    public void createPatientProfile_shouldReturnConflict_whenPatientAlreadyExists() throws Exception {
        // Create a patient profile
        patientWebHelper.performPatientProfileRegister(
                PatientCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isOk());

        // Try to create the same patient profile
        patientWebHelper.performPatientProfileRegister(
                PatientCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isConflict());
    }

//    @Test
//    public void createPatientProfile_shouldReturnBadRequest_whenInvalidRequest() throws Exception {
//        //TODO: implement when the dto becomes non-empty
//    }
}
