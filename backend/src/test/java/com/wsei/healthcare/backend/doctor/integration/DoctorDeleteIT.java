package com.wsei.healthcare.backend.doctor.integration;

import com.wsei.healthcare.backend.doctor.util.DoctorCreationRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DoctorDeleteIT extends AbstractDoctorIT {

    @Test
    public void deleteAuthenticatedDoctorProfile_shouldDeleteProfile_whenDoctorExists() throws Exception {
        // Create a doctor profile
        doctorWebHelper.performDoctorProfileRegister(
                DoctorCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isOk());

        // Assure the doctor profile exists
        doctorWebHelper.performDoctorProfileRetrieve(token)
                        .andExpect(status().isOk());

        // Delete the doctor profile
        doctorWebHelper.performDoctorProfileDelete(token)
                .andExpect(status().isNoContent());

        // Assure the doctor profile doesn't exist
        doctorWebHelper.performDoctorProfileRetrieve(token)
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteAuthenticatedDoctorProfile_shouldReturnNotFound_whenDoctorDoesNotExist() throws Exception {
        doctorWebHelper.performDoctorProfileDelete(token)
                .andExpect(status().isNotFound());
    }
}