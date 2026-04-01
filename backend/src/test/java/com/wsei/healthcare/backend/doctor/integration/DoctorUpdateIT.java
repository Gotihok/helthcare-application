package com.wsei.healthcare.backend.doctor.integration;

import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.util.DoctorCreationRequestBuilder;
import com.wsei.healthcare.backend.doctor.util.DoctorProfileUpdateRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DoctorUpdateIT extends AbstractDoctorIT {

    @Test
    public void updateAuthenticatedDoctorProfile_shouldUpdateProfile_whenValidRequest() throws Exception {
        // Create a doctor profile
        doctorWebHelper.performDoctorProfileRegister(
                DoctorCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isOk());

        // Update the doctor profile
        DoctorProfileResponse response = doctorWebHelper.getDoctorProfileResponse(
                doctorWebHelper.performDoctorProfileUpdate(
                        DoctorProfileUpdateRequestBuilder.getValidDefault().build(),
                        token
                ).andExpect(status().isOk()));

        assertNotNull(response.user());
        assertNotNull(response.doctor());
        assertTrue(response.patients().isEmpty());
    }

//    @Test
//    public void updateAuthenticatedDoctorProfile_shouldReturnBadRequest_whenInvalidRequest() throws Exception {
//        //TODO: implement when the dto becomes non-empty
//    }

    @Test
    public void updateAuthenticatedDoctorProfile_shouldReturnNotFound_whenPatientDoesNotExist() throws Exception {
        doctorWebHelper.performDoctorProfileUpdate(
                DoctorProfileUpdateRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isNotFound());
    }
}
