package com.wsei.healthcare.backend.patient.integration;

import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.util.PatientCreationRequestBuilder;
import com.wsei.healthcare.backend.patient.util.PatientProfileUpdateRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientUpdateIT extends AbstractPatientIT {

    @Test
    public void updateAuthenticatedPatientProfile_shouldUpdateProfile_whenValidRequest() throws Exception {
        // Create a patient profile
        patientWebHelper.performPatientProfileRegister(
                PatientCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isOk());

        // Update the patient profile
        PatientProfileResponse response = patientWebHelper.getPatientProfileResponse(
                patientWebHelper.performPatientProfileUpdate(
                        PatientProfileUpdateRequestBuilder.getValidDefault().build(),
                        token
                ).andExpect(status().isOk()));

        assertNotNull(response.user());
        assertNotNull(response.patient());
        assertNull(response.personalDoctor());
    }

//    @Test
//    public void updateAuthenticatedPatientProfile_shouldReturnBadRequest_whenInvalidRequest() throws Exception {
//        //TODO: implement when the dto becomes non-empty
//    }

    @Test
    public void updateAuthenticatedPatientProfile_shouldReturnNotFound_whenPatientDoesNotExist() throws Exception {
        patientWebHelper.performPatientProfileUpdate(
                PatientProfileUpdateRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isNotFound());
    }
}
