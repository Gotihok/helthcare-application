package com.wsei.healthcare.backend.doctor.integration;

import com.wsei.healthcare.backend.doctor.util.DoctorCreationRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DoctorDeleteIT extends AbstractDoctorIT {

    @Test
    public void deleteAuthenticatedPatientProfile_shouldDeleteProfile_whenPatientExists() throws Exception {
        // Create a patient profile
        doctorWebHelper.performDoctorProfileRegister(
                DoctorCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isOk());

        // Assure the patient profile exists
        doctorWebHelper.performDoctorProfileRetrieve(token)
                        .andExpect(status().isOk());

        // Delete the patient profile
        doctorWebHelper.performDoctorProfileDelete(token)
                .andExpect(status().isNoContent());

        // Assure the patient profile doesn't exist
        doctorWebHelper.performDoctorProfileRetrieve(token)
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteAuthenticatedPatientProfile_shouldReturnNotFound_whenPatientDoesNotExist() throws Exception {
        doctorWebHelper.performDoctorProfileDelete(token)
                .andExpect(status().isNotFound());
    }
}