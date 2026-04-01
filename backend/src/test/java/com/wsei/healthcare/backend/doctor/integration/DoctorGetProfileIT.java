package com.wsei.healthcare.backend.doctor.integration;

import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.util.DoctorCreationRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DoctorGetProfileIT extends AbstractDoctorIT {

    @Test
    public void getAuthenticatedDoctorProfile_shouldReturnProfile_whenDoctorExists() throws Exception {
        // Create a doctor profile
        doctorWebHelper.performDoctorProfileRegister(
                DoctorCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isOk());

        // Get the doctor profile
        DoctorProfileResponse response = doctorWebHelper.getDoctorProfileResponse(
                doctorWebHelper.performDoctorProfileRetrieve(token)
                        .andExpect(status().isOk()));

        assertNotNull(response.user());
        assertNotNull(response.doctor());
        assertTrue(response.patients().isEmpty());
    }

    @Test
    public void getAuthenticatedDoctorProfile_shouldReturnNotFound_whenDoctorDoesNotExist() throws Exception {
        doctorWebHelper.getDoctorProfileResponse(
                doctorWebHelper.performDoctorProfileRetrieve(token)
                        .andExpect(status().isNotFound()));
    }
}