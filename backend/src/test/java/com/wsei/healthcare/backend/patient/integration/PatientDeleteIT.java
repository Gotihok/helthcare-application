package com.wsei.healthcare.backend.patient.integration;

import com.wsei.healthcare.backend.patient.util.PatientCreationRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientDeleteIT extends AbstractPatientIT {

    @Test
    public void deleteAuthenticatedPatientProfile_shouldDeleteProfile_whenPatientExists() throws Exception {
        // Create a patient profile
        patientWebHelper.performPatientProfileRegister(
                PatientCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isOk());

        // Assure the patient profile exists
        patientWebHelper.performPatientProfileRetrieve(token)
                .andExpect(status().isOk());

        // Delete the patient profile
        patientWebHelper.performPatientProfileDelete(token)
                .andExpect(status().isNoContent());

        // Assure the patient profile doesn't exist
        patientWebHelper.performPatientProfileRetrieve(token)
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteAuthenticatedPatientProfile_shouldReturnNotFound_whenPatientDoesNotExist() throws Exception {
        patientWebHelper.performPatientProfileDelete(token)
                .andExpect(status().isNotFound());
    }
}