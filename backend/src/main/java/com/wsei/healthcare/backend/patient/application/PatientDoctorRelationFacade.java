package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.patient.api.PatientDoctorRelationApi;
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

//TODO: check for transactions needed
@Service
@RequiredArgsConstructor
public class PatientDoctorRelationFacade implements PatientDoctorRelationApi {

    private final PatientRepository patientRepository;
    private final PatientRequestRepository patientRequestRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void postPersonalDoctorAssignmentRequestByUserId(Long userId, Long doctorId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + userId));

        DoctorAssignmentRequest doctorAssignmentRequest = new DoctorAssignmentRequest()
                .setPatientId(patient.getId())
                .setDoctorId(doctorId)
                .setStatus(RequestStatus.PENDING)
                .setCreatedAt(Instant.now());

        patientRequestRepository.save(doctorAssignmentRequest);
    }

    @Override
    public void removePersonalDoctorByUserId(Long userId) {
        Patient retrievedPatient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + userId));
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
    public void acceptPersonalDoctorAssignmentRequestByUserId(Long userId, Long requestId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + userId));
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
    public void rejectPersonalDoctorAssignmentRequestByUserId(Long userId, Long requestId) {
        DoctorAssignmentRequest doctorAssignmentRequest = patientRequestRepository.findById(requestId)
                .orElseThrow(() -> new DoctorAssignmentRequestNotFoundException(
                        "Doctor assignment request not found with id: " + requestId));
        doctorAssignmentRequest
                .setStatus(RequestStatus.REJECTED)
                .setProcessedAt(Instant.now());
        patientRequestRepository.save(doctorAssignmentRequest);
    }
}
