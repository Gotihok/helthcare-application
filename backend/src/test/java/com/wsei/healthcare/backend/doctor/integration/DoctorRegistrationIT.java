package com.wsei.healthcare.backend.doctor.integration;

import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.util.DoctorCreationRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DoctorRegistrationIT extends AbstractDoctorIT {

    @Test
    public void createDoctorProfile_shouldRegisterNewDoctor_whenValidRequest() throws Exception {
        DoctorProfileResponse response = doctorWebHelper.getDoctorProfileResponse(
                doctorWebHelper.performDoctorProfileRegister(
                        DoctorCreationRequestBuilder.getValidDefault().build(),
                                token
                        ).andExpect(status().isOk()));

        assertNotNull(response.user());
        assertNotNull(response.doctor());
        assertTrue(response.patients().isEmpty());
    }

    @Test
    public void createDoctorProfile_shouldReturnConflict_whenDoctorAlreadyExists() throws Exception {
        // Create a doctor profile
        doctorWebHelper.performDoctorProfileRegister(
                DoctorCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isOk());

        // Try to create the same doctor profile
        doctorWebHelper.performDoctorProfileRegister(
                DoctorCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isConflict());
    }

//    @Test
//    public void createDoctorProfile_shouldReturnBadRequest_whenInvalidRequest() throws Exception {
//        //TODO: implement when the dto becomes non-empty
//    }
}
