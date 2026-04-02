package com.wsei.healthcare.backend.patient.integration;

import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.util.DoctorCreationRequestBuilder;
import com.wsei.healthcare.backend.doctor.util.DoctorWebHelper;
import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.util.PatientCreationRequestBuilder;
import com.wsei.healthcare.backend.user.util.UserRegisterRequestBuilder;
import com.wsei.healthcare.backend.user.util.UserTestDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientDoctorAssignmentIT extends AbstractPatientIT {

    @Autowired
    private DoctorWebHelper doctorWebHelper;

    @Test
    public void setPersonalDoctor_shouldAssignDoctor_whenValidDoctorId() throws Exception {
        // Register patient
        String emailPatient = UserTestDataProvider.validEmail() + UUID.randomUUID();
        String tokenUserPatient = authTokenFactory.createAuthenticatedUserToken(
                UserRegisterRequestBuilder.getValidDefault()
                        .setEmail(emailPatient)
                        .build()
        );
        patientWebHelper.performPatientProfileRegister(
                PatientCreationRequestBuilder.getValidDefault().build(),
                tokenUserPatient
        ).andExpect(status().isOk());

        // Register doctor
        String emailDoctor = UserTestDataProvider.validEmail() + UUID.randomUUID();
        String tokenUserDoctor = authTokenFactory.createAuthenticatedUserToken(
                UserRegisterRequestBuilder.getValidDefault()
                        .setEmail(emailDoctor)
                        .build()
        );
        DoctorProfileResponse doctorProfileResponse = doctorWebHelper.getDoctorProfileResponse(
                doctorWebHelper.performDoctorProfileRegister(
                        DoctorCreationRequestBuilder.getValidDefault().build(),
                        tokenUserDoctor
                ).andExpect(status().isOk())
        );

        // Assign the personal doctor to the patient
        patientWebHelper.performPersonalDoctorAssignment(
                tokenUserPatient,
                doctorProfileResponse.doctor().id()
        ).andExpect(status().isOk());

        // Check if the patient has the doctor assigned
        PatientProfileResponse patientProfileResponse = patientWebHelper.getPatientProfileResponse(
                patientWebHelper.performPatientProfileRetrieve(tokenUserPatient)
                        .andExpect(status().isOk())
        );

        assertNotNull(patientProfileResponse.user());
        assertNotNull(patientProfileResponse.patient());
        assertNotNull(patientProfileResponse.personalDoctor());
        assertEquals(
                doctorProfileResponse.doctor().id(),
                patientProfileResponse.personalDoctor().doctor().id()
        );
    }

    @Test
    public void setPersonalDoctor_shouldReturnBadRequest_whenDoctorDoesNotExist() throws Exception {
        patientWebHelper.performPatientProfileRegister(
                PatientCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isOk());

        patientWebHelper.performPersonalDoctorAssignment(
                token,
                0L
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void setPersonalDoctor_shouldReturnBadRequest_whenDoctorIdIsInvalid() throws Exception {
        patientWebHelper.performPatientProfileRegister(
                PatientCreationRequestBuilder.getValidDefault().build(),
                token
        ).andExpect(status().isOk());

        patientWebHelper.performPersonalDoctorAssignment(
                token,
                -2L
        ).andExpect(status().isBadRequest());
    }
}