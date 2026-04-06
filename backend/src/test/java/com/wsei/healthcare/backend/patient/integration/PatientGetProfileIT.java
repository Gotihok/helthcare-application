package com.wsei.healthcare.backend.patient.integration;

import com.wsei.healthcare.backend.patient.api.dto.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.util.PatientCreationRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientGetProfileIT extends AbstractPatientIT {

    @Test
    public void getAuthenticatedPatientProfile_shouldReturnProfile_whenPatientExists() throws Exception {
        // Create a patient profile
        patientWebHelper.performPatientProfileRegister(
                PatientCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isOk());

        // Get the patient profile
        PatientProfileResponse response = patientWebHelper.getPatientProfileResponse(
                patientWebHelper.performPatientProfileRetrieve(token)
                        .andExpect(status().isOk()));

        assertNotNull(response.user());
        assertNotNull(response.patient());
        assertNull(response.personalDoctor());
    }

    @Test
    public void getAuthenticatedPatientProfile_shouldReturnNotFound_whenPatientDoesNotExist() throws Exception {
        patientWebHelper.getPatientProfileResponse(
                patientWebHelper.performPatientProfileRetrieve(token)
                        .andExpect(status().isNotFound()));
    }
}