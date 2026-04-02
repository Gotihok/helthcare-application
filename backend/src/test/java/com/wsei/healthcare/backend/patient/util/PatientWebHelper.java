package com.wsei.healthcare.backend.patient.util;

import com.wsei.healthcare.backend.patient.api.dto.PatientCreationRequest;
import com.wsei.healthcare.backend.patient.api.dto.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.api.dto.PatientProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tools.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Component
@RequiredArgsConstructor
public class PatientWebHelper {

    private static final String BASE_PATIENT_URL = "/api/patients";
    private static final String PATIENT_REGISTER_URL = BASE_PATIENT_URL + "/me/register";
    private static final String PATIENT_PROFILE_UPDATE_URL = BASE_PATIENT_URL + "/me/update";
    private static final String PATIENT_PERSONAL_URL = BASE_PATIENT_URL + "/me";
    private static final String PATIENT_DOCTOR_ASSIGNMENT_URL = BASE_PATIENT_URL + "/me/doctor/{doctorId}";

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    public PatientProfileResponse getPatientProfileResponse
            (ResultActions request) throws UnsupportedEncodingException {
        String response = request.andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(response, PatientProfileResponse.class);
    }

    public ResultActions performPatientProfileRegister(
            PatientCreationRequest request,
            String token
    ) throws Exception {
        return mockMvc.perform(post(PATIENT_REGISTER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("Authorization", "Bearer " + token));
    }

    public ResultActions performPatientProfileUpdate(
            PatientProfileUpdateRequest request,
            String token
    ) throws Exception {
        return mockMvc.perform(post(PATIENT_PROFILE_UPDATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("Authorization", "Bearer " + token));
    }

    public ResultActions performPatientProfileRetrieve(String token) throws Exception {
        return mockMvc.perform(get(PATIENT_PERSONAL_URL)
                .header("Authorization", "Bearer " + token));
    }

    public ResultActions performPatientProfileDelete(String token) throws Exception {
        return mockMvc.perform(delete(PATIENT_PERSONAL_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token));
    }

//    public ResultActions performPersonalDoctorAssignment(String token, Long doctorId) throws Exception {
//        return mockMvc.perform(post(PATIENT_DOCTOR_ASSIGNMENT_URL, doctorId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + token));
//    }
}
