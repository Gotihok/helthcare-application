package com.wsei.healthcare.backend.patient.util;

import com.wsei.healthcare.backend.patient.api.PatientCreationRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class PatientCreationRequestBuilder {

    private PatientCreationRequestBuilder() {}

    public static PatientCreationRequestBuilder getValidDefault() {
        PatientCreationRequestBuilder builder = new PatientCreationRequestBuilder();
        return builder;
    }

    public static PatientCreationRequestBuilder getInvalidDefault() {
        PatientCreationRequestBuilder builder = new PatientCreationRequestBuilder();
        return builder;
    }

    public PatientCreationRequest build() {
        return new PatientCreationRequest();
    }
}
