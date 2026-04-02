package com.wsei.healthcare.backend.patient.util;

import com.wsei.healthcare.backend.patient.api.dto.PatientProfileUpdateRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class PatientProfileUpdateRequestBuilder {

    private PatientProfileUpdateRequestBuilder() {}

    public static PatientProfileUpdateRequestBuilder getValidDefault() {
        PatientProfileUpdateRequestBuilder builder = new PatientProfileUpdateRequestBuilder();
        return builder;
    }

    public static PatientProfileUpdateRequestBuilder getInvalidDefault() {
        PatientProfileUpdateRequestBuilder builder = new PatientProfileUpdateRequestBuilder();
        return builder;
    }

    public PatientProfileUpdateRequest build() {
        return new PatientProfileUpdateRequest();
    }
}
