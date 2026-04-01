package com.wsei.healthcare.backend.doctor.util;

import com.wsei.healthcare.backend.doctor.api.DoctorProfileUpdateRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class DoctorProfileUpdateRequestBuilder {

    private DoctorProfileUpdateRequestBuilder() {}

    public static DoctorProfileUpdateRequestBuilder getValidDefault() {
        DoctorProfileUpdateRequestBuilder builder = new DoctorProfileUpdateRequestBuilder();
        return builder;
    }

    public static DoctorProfileUpdateRequestBuilder getInvalidDefault() {
        DoctorProfileUpdateRequestBuilder builder = new DoctorProfileUpdateRequestBuilder();
        return builder;
    }

    public DoctorProfileUpdateRequest build() {
        return new DoctorProfileUpdateRequest();
    }
}
