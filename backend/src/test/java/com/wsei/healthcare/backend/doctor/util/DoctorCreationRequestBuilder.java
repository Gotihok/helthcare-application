package com.wsei.healthcare.backend.doctor.util;

import com.wsei.healthcare.backend.doctor.api.DoctorCreationRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class DoctorCreationRequestBuilder {

    private DoctorCreationRequestBuilder() {}

    public static DoctorCreationRequestBuilder getValidDefault() {
        DoctorCreationRequestBuilder builder = new DoctorCreationRequestBuilder();
        return builder;
    }

    public static DoctorCreationRequestBuilder getInvalidDefault() {
        DoctorCreationRequestBuilder builder = new DoctorCreationRequestBuilder();
        return builder;
    }

    public DoctorCreationRequest build() {
        return new DoctorCreationRequest();
    }
}
