package com.wsei.healthcare.backend.doctor.util;

import com.wsei.healthcare.backend.doctor.api.dto.DoctorCreationRequest;
import com.wsei.healthcare.backend.doctor.api.dto.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.api.dto.DoctorProfileUpdateRequest;
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
public class DoctorWebHelper {

    private static final String BASE_DOCTOR_URL = "/api/doctors";
    private static final String DOCTOR_REGISTER_URL = BASE_DOCTOR_URL + "/me/register";
    private static final String DOCTOR_PROFILE_UPDATE_URL = BASE_DOCTOR_URL + "/me/update";
    private static final String DOCTOR_PERSONAL_URL = BASE_DOCTOR_URL + "/me";
    private static final String DOCTOR_PATIENT_MANAGEMENT_URL = BASE_DOCTOR_URL + "/me/patients/{patientId}";

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    public DoctorProfileResponse getDoctorProfileResponse
            (ResultActions request) throws UnsupportedEncodingException {
        String response = request.andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(response, DoctorProfileResponse.class);
    }

    public ResultActions performDoctorProfileRegister(
            DoctorCreationRequest request,
            String token
    ) throws Exception {
        return mockMvc.perform(post(DOCTOR_REGISTER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("Authorization", "Bearer " + token));
    }

    public ResultActions performDoctorProfileUpdate(
            DoctorProfileUpdateRequest request,
            String token
    ) throws Exception {
        return mockMvc.perform(post(DOCTOR_PROFILE_UPDATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("Authorization", "Bearer " + token));
    }

    public ResultActions performDoctorProfileRetrieve(String token) throws Exception {
        return mockMvc.perform(get(DOCTOR_PERSONAL_URL)
                .header("Authorization", "Bearer " + token));
    }

    public ResultActions performDoctorProfileDelete(String token) throws Exception {
        return mockMvc.perform(delete(DOCTOR_PERSONAL_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token));
    }

//    public ResultActions performPersonalDoctorAssignment(String token, Long doctorId) throws Exception {
//        return mockMvc.perform(post(PATIENT_DOCTOR_ASSIGNMENT_URL, doctorId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + token));
//    }
}
