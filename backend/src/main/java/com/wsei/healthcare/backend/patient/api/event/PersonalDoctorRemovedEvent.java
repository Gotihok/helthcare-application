package com.wsei.healthcare.backend.patient.api.event;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.context.ApplicationEvent;

//TODO: implement doctor removal events with the doctor notifications when the notifications are introduced
@Getter
@Accessors(chain = true)
public class PersonalDoctorRemovedEvent extends ApplicationEvent {
    private final Long patientId;
    private final Long removedDoctorId;

    public PersonalDoctorRemovedEvent(Object source, Long patientId, Long removedDoctorId) {
        super(source);
        this.patientId = patientId;
        this.removedDoctorId = removedDoctorId;
    }
}
