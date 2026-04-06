package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.patient.api.DoctorAssignmentApi;
import com.wsei.healthcare.backend.patient.api.event.PersonalDoctorRemovedEvent;
import com.wsei.healthcare.backend.patient.domain.DoctorAssignmentRequest;
import com.wsei.healthcare.backend.patient.domain.Patient;
import com.wsei.healthcare.backend.patient.domain.PatientRepository;
import com.wsei.healthcare.backend.patient.domain.PatientRequestRepository;
import com.wsei.healthcare.backend.shared.enums.RequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class DoctorAssignmentFacade implements DoctorAssignmentApi {

    private final PatientRepository patientRepository;
    private final PatientRequestRepository patientRequestRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void requestAssignment(Long actorId, Long doctorId) {
        Patient patient = patientRepository.findByUserId(actorId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + actorId));

        DoctorAssignmentRequest doctorAssignmentRequest = new DoctorAssignmentRequest()
                .setPatientId(patient.getId())
                .setDoctorId(doctorId)
                .setStatus(RequestStatus.PENDING)
                .setCreatedAt(Instant.now());

        patientRequestRepository.save(doctorAssignmentRequest);
    }

    @Override
    public void removeAssignment(Long actorId) {
        Patient retrievedPatient = patientRepository.findByUserId(actorId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + actorId));
        Long removedDoctorId = retrievedPatient.getPersonalDoctorId();
        retrievedPatient.setPersonalDoctorId(null);
        Patient savedPatient = patientRepository.save(retrievedPatient);

        eventPublisher.publishEvent(
                new PersonalDoctorRemovedEvent(
                        this,
                        savedPatient.getId(),
                        removedDoctorId
                ));
    }

    @Override
    public void acceptRequest(Long actorId, Long requestId) {
        Patient patient = patientRepository.findByUserId(actorId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + actorId));
        DoctorAssignmentRequest doctorAssignmentRequest = patientRequestRepository.findById(requestId)
                .orElseThrow(() -> new DoctorAssignmentRequestNotFoundException(
                        "Doctor assignment request not found with id: " + requestId));

        patient.setPersonalDoctorId(doctorAssignmentRequest.getDoctorId());

        doctorAssignmentRequest
                .setStatus(RequestStatus.APPROVED)
                .setProcessedAt(Instant.now());
        patientRequestRepository.save(doctorAssignmentRequest);
        //TODO: maybe throw PersonalDoctorSetEvent
    }

    @Override
    public void rejectRequest(Long actorId, Long requestId) {
        DoctorAssignmentRequest doctorAssignmentRequest = patientRequestRepository.findById(requestId)
                .orElseThrow(() -> new DoctorAssignmentRequestNotFoundException(
                        "Doctor assignment request not found with id: " + requestId));
        doctorAssignmentRequest
                .setStatus(RequestStatus.REJECTED)
                .setProcessedAt(Instant.now());
        patientRequestRepository.save(doctorAssignmentRequest);
    }
}
