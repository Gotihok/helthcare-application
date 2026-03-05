# DentoCare Portal – Architecture Plan

---

# 1. Architectural Model

## 1.1 Modular Monolith

The system is organized by **business capabilities** rather than technical layers.

Each module encapsulates:

- `domain`
- `application`
- `api`
- `infra`

Modules are:

- independently understandable
- isolated by interfaces
- extractable to microservices if required

---

# 2. Internal Module Structure

Every module follows the same structure:

    module
    ├── domain
    ├── application
    ├── api
    └── infra

---

## 2.1 Domain

Contains the **pure business model**.

    domain
    ├── entities
    ├── value objects
    └── repository interfaces

### Rules

- Pure Java (no frameworks)
- No Spring, JPA, or infrastructure code
- Defines repository interfaces used by the application layer
- Does not depend on any other module

### Example

- `PatientProfile`
- `DoctorProfile`
- `Appointment`
- `MedicalRecord`

---

## 2.2 Application

Implements **use cases and business orchestration**.

    application
    └── services

### Responsibilities

- orchestrate domain logic
- enforce business rules
- coordinate repository calls

### Rules

- depends only on `domain` and `API DTOs`
- contains no infrastructure code
- services are stateless

### Example

- `PatientService`
- `DoctorService`
- `AppointmentService`
- `MedicalService`

---

## 2.3 API

Defines the **external contract** of the module.

    api
    ├── contract
    └── dto

### Contents

    contract
    ModuleApi.java

    dto
    RequestDTO
    ResponseDTO

### Rules

- defines public module interface
- contains request/response DTOs
- framework-free except optional Swagger/OpenAPI annotations
- treated as a contract layer

Controllers implement API interfaces in infrastructure.

### Example

- `PatientApi`
- `DoctorApi`
- `AppointmentApi`

---

## 2.4 Infrastructure

Contains **framework integrations and adapters**.

    infra
    ├── persistence
    ├── web
    └── config

### Responsibilities

#### Persistence

- JPA entities
- repository implementations
- database mappings

#### Web

- REST controllers
- API interface implementations

#### Config

- Spring configuration
- module wiring

### Example

- `JpaPatientRepository`
- `PatientController`
- `PatientModuleConfig`

### Rules

- may depend on `domain`, `application`, and `api`
- contains all framework dependencies

---

# 3. Core Modules

---

## 3.1 Identity Module

**Purpose:** user authentication and authorization data.

    identity
    ├── domain
    │ ├── IdentityUser
    │ └── IdentityUserRepository
    │
    ├── application
    │ └── IdentityService
    │
    ├── api
    │ └── (optional)
    │
    └── infra
    └── JpaIdentityUserRepository

### Responsibilities

- user accounts
- roles
- authentication lookup

### Rules

- no Spring Security dependencies in domain
- used by the security adapter in `shared.security`

---

## 3.2 Patient Module

**Purpose:** patient profile management.

    patient
    ├── domain
    │ ├── PatientProfile
    │ └── PatientRepository
    │
    ├── application
    │ └── PatientService
    │
    ├── api
    │ ├── PatientApi
    │ └── dto
    │
    └── infra
    ├── persistence
    │ └── JpaPatientRepository
    └── web
    └── PatientController

### Responsibilities

- manage patient data
- assign doctors

---

## 3.3 Doctor Module

**Purpose:** doctor profile management.

    doctor
    ├── domain
    │ ├── DoctorProfile
    │ └── DoctorRepository
    │
    ├── application
    │ └── DoctorService
    │
    ├── api
    │ ├── DoctorApi
    │ └── dto
    │
    └── infra
    ├── persistence
    │ └── JpaDoctorRepository
    └── web
    └── DoctorController

### Responsibilities

- manage doctor data
- maintain doctor availability

---

## 3.4 Appointment Module

**Purpose:** appointment scheduling and lifecycle.

    appointment
    ├── domain
    │ ├── Appointment
    │ └── AppointmentRepository
    │
    ├── application
    │ └── AppointmentService
    │
    ├── api
    │ ├── AppointmentApi
    │ └── dto
    │
    └── infra
    ├── persistence
    │ └── JpaAppointmentRepository
    └── web
    └── AppointmentController

### Responsibilities

- schedule appointments
- update or cancel bookings
- enforce scheduling rules

### Dependencies

- `PatientRepository`
- `DoctorRepository`

### Important

- references external entities **by ID only**
- does **not depend on other modules' services**

---

## 3.5 Medical Module

**Purpose:** medical records and prescriptions.

    medical
    ├── domain
    │ ├── MedicalRecord
    │ ├── Prescription
    │ └── MedicalRepository
    │
    ├── application
    │ └── MedicalService
    │
    ├── api
    │ ├── MedicalApi
    │ └── dto
    │
    └── infra
    ├── persistence
    │ └── JpaMedicalRepository
    └── web
    └── MedicalController

### Responsibilities

- visit records
- prescriptions
- patient medical history

### Dependencies

- `AppointmentRepository`
- `PatientRepository`
- `DoctorRepository`

---

## 3.6 Dashboard Module

**Purpose:** aggregated read views.

    dashboard
    ├── application
    │ ├── PatientDashboardService
    │ └── DoctorDashboardService
    │
    ├── api
    │ ├── DashboardApi
    │ └── dto
    │
    └── infra
    └── web
    └── DashboardController

### Characteristics

- read-only module
- aggregates data from multiple modules
- optimized queries

### Dependencies

- `PatientRepository`
- `DoctorRepository`
- `AppointmentRepository`
- `MedicalRepository`

---

# 4. Cross-Cutting Modules

    shared
    ├── exception
    │ └── ApplicationException
    │
    ├── security
    │ ├── SpringUserDetailsService
    │ └── UserPrincipal
    │
    └── config
    └── AppConfig

### Responsibilities

- global exception types
- security adapters
- application configuration

---

# 5. Module Dependency Rules

### Allowed Dependencies

    infra → api
    infra → application
    infra → domain

    application → domain
    application → api.dto

### Forbidden Dependencies

    domain → any other layer
    application → infra
    api → infra

### Inter-module Communication

- repository interfaces only
- no direct service calls
- no domain entity sharing

---

# 6. Database Strategy

Modules share a **database** but maintain **internal mappings**.

Persistence entities exist **only in `infra.persistence`.**

### Example Tables

- `users`
- `patients`
- `doctors`
- `appointments`
- `medical_records`
- `prescriptions`

### Mapping

    JPA Entity → Domain Object

---

# 7. Security Integration

Security is implemented as an **adapter layer**.

### Structure

    identity.domain.IdentityUser
    identity.domain.IdentityUserRepository

    identity.infra.JpaIdentityUserRepository

    shared.security.SpringUserDetailsService
    shared.security.UserPrincipal

### Flow

    Spring Security
    ↓
    SpringUserDetailsService
    ↓
    IdentityUserRepository
    ↓
    JpaIdentityUserRepository

`UserPrincipal` adapts `IdentityUser` to Spring Security.

---

# 8. Module Dependency Graph

    dashboard
    ↓
    [patient, doctor, appointment, medical]

    appointment
    ↓
    [patient, doctor]

    medical
    ↓
    [appointment, patient, doctor]

    security
    ↓
    identity

### Properties

- no circular dependencies
- interface-based communication
- domain isolation

---

# 9. Extension Strategy

New modules can be added **without modifying existing ones**.

### Examples

- `payments`
- `notifications`
- `reporting`
- `analytics`

### Rules

- follow the same module structure
- depend only on repository interfaces
- keep domain isolated