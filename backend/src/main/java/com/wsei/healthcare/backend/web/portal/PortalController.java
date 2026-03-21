package com.wsei.healthcare.backend.web.portal;

import com.wsei.healthcare.backend.api.portal.AppointmentResponse;
import com.wsei.healthcare.backend.api.portal.BookAppointmentRequest;
import com.wsei.healthcare.backend.api.portal.PrescriptionResponse;
import com.wsei.healthcare.backend.domain.user.UserRole;
import com.wsei.healthcare.backend.infra.security.UserPrincipal;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/api/portal")
public class PortalController {

    private static final AtomicLong ID = new AtomicLong(100);
    private final List<AppointmentResponse> patientAppointments = new ArrayList<>();

    @GetMapping("/patient/appointments")
    public ResponseEntity<List<AppointmentResponse>> patientAppointments(Authentication authentication) {
        assertRole(authentication, UserRole.PATIENT);
        if (patientAppointments.isEmpty()) {
            patientAppointments.add(new AppointmentResponse(1L, "2026-03-26", "11:20", "dr Anna Kowalska", "Ty", "ZAPLANOWANA"));
        }
        return ResponseEntity.ok(patientAppointments);
    }

    @PostMapping("/patient/appointments")
    public ResponseEntity<AppointmentResponse> bookAppointment(
            Authentication authentication,
            @RequestBody @Valid BookAppointmentRequest request
    ) {
        assertRole(authentication, UserRole.PATIENT);
        AppointmentResponse appointment = new AppointmentResponse(
                ID.incrementAndGet(),
                request.date(),
                request.time(),
                "dr Anna Kowalska",
                "Ty",
                "ZAPLANOWANA"
        );
        patientAppointments.add(appointment);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/patient/prescriptions")
    public ResponseEntity<List<PrescriptionResponse>> patientPrescriptions(Authentication authentication) {
        assertRole(authentication, UserRole.PATIENT);
        return ResponseEntity.ok(List.of(
                new PrescriptionResponse(11L, "RX-2044-9921", "2026-03-15", "dr Anna Kowalska", "Metformina 500 mg", "1 tabletka rano"),
                new PrescriptionResponse(12L, "RX-2044-9988", "2026-03-01", "dr Anna Kowalska", "Witamina D3", "1 kapsulka dziennie")
        ));
    }

    @GetMapping("/doctor/appointments")
    public ResponseEntity<List<AppointmentResponse>> doctorAppointments(Authentication authentication) {
        assertRole(authentication, UserRole.DOCTOR);
        return ResponseEntity.ok(List.of(
                new AppointmentResponse(200L, "2026-03-26", "11:20", "dr Anna Kowalska", "Jan Nowak", "ZAPLANOWANA"),
                new AppointmentResponse(201L, "2026-03-26", "12:00", "dr Anna Kowalska", "Ewa Zielinska", "ZAPLANOWANA")
        ));
    }

    private void assertRole(Authentication authentication, UserRole expectedRole) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        if (principal.getRole() != expectedRole) {
            throw new ResponseStatusException(FORBIDDEN, "Brak dostepu do tego zasobu");
        }
    }
}
