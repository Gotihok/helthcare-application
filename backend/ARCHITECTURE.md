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
- isolated through explicit interfaces
- extractable to microservices if required

The architecture enforces **strict module boundaries**.  
Modules communicate **only through their public API layer**.

---

# 2. Internal Module Structure

Every module follows the same structure:

    module
    ├── domain
    ├── application
    ├── api
    └── infra

Each layer has clearly defined responsibilities and dependency rules.

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
- Contains core business logic and invariants
- Completely isolated from other modules
- Does **not depend on any module APIs**

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
- coordinate repository usage
- transform API DTOs into domain models
- expose internal use cases

### Rules

- depends only on:
    - `domain`
    - `api.dto` of the same module
- contains no framework code
- contains no persistence logic
- services are stateless
- does **not call other modules directly**

Cross-module communication **must go through module APIs**.

### Example

- `PatientService`
- `DoctorService`
- `AppointmentService`
- `MedicalService`

---

## 2.3 API

Defines the **public contract of the module**.

    api
    ├── contract
    └── dto

### Contents

    contract
    ModuleApi.java

    dto
    RequestDTO
    ResponseDTO

### Responsibilities

- define the **only allowed entry points** to the module
- provide request and response DTOs
- expose business capabilities to:
    - other modules
    - web controllers
    - external systems

### Rules

- defines **public module interfaces**
- contains **DTOs used for communication**
- framework-free (except optional OpenAPI annotations)
- does **not contain business logic**
- does **not contain framework implementations**

### Important

The API layer serves **two roles simultaneously**:

1. **External system contract** (REST / HTTP)
2. **Internal module communication contract**

All inter-module calls must go through **API interfaces**.

### Example

- `PatientApi`
- `DoctorApi`
- `AppointmentApi`
- `MedicalApi`

---

## 2.4 Infrastructure

Contains **all framework integrations and adapters**.

    infra
    ├── persistence
    ├── web
    ├── integration
    └── config

### Responsibilities

- implement repository interfaces
- expose REST endpoints
- adapt API contracts to the outside world
- perform cross-module API calls
- configure framework integrations

### Persistence

    infra/persistence

- JPA entities
- repository implementations
- ORM mappings

Example:

- `JpaPatientRepository`
- `JpaDoctorRepository`

---

### Web

    infra/web

- REST controllers
- HTTP request mapping
- API interface implementations

Controllers **implement the module API interfaces** and delegate to application services.

Example:

- `PatientController`
- `DoctorController`
- `AppointmentController`

---

### Integration

    infra/integration

Responsible for **calling other modules' APIs**.

This layer contains adapters that depend on **API interfaces of other modules**.

Example:

- `PatientApiClient`
- `DoctorApiClient`
- `AppointmentApiClient`

These adapters translate between:

- external module DTOs
- local domain models

---

### Config

    infra/config

- Spring configuration
- module wiring
- bean definitions

Example:

- `PatientModuleConfig`
- `AppointmentModuleConfig`

---

### Rules

Infrastructure may depend on:

- `domain`
- `application`
- `api`
- APIs of other modules

Infrastructure is the **only layer allowed to know about frameworks**.

---

# 3. Core Modules

---

## 3.1 Identity Module

Purpose: **user authentication and authorization data**.

    identity
    ├── domain
    │ ├── IdentityUser
    │ └── IdentityUserRepository
    │
    ├── application
    │ └── IdentityService
    │
    ├── api
    │ ├── IdentityApi
    │ └── dto
    │
    └── infra
        ├── persistence
        │ └── JpaIdentityUserRepository
        └── web
            └── IdentityController

### Responsibilities

- user accounts
- roles
- user lookup
- identity management

### Rules

- no Spring Security dependencies in domain
- authentication frameworks interact through `shared.security`
- other modules interact **only through `IdentityApi`**

---

## 3.2 Auth Module

Purpose: **authentication flows and token issuing**.

    auth
    ├── domain
    │ └── RefreshToken
    │
    ├── application
    │ └── AuthService
    │
    ├── api
    │ ├── AuthApi
    │ └── dto
    │
    └── infra
        └── web
            └── AuthController

### Responsibilities

- user login
- user registration
- token issuing (JWT)
- token refresh
- logout handling

### Dependencies

- `IdentityApi`

### Important

Auth module **does not access identity repositories directly**.  
It communicates with the identity module through **`IdentityApi`**.

Token validation is handled by `shared.security`.

---

## 3.3 Patient Module

Purpose: **patient profile management**.

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
        ├── web
        │   └── PatientController
        └── integration

### Responsibilities

- manage patient data
- assign doctors
- expose patient operations

Other modules access patient functionality **through `PatientApi` only**.

---

## 3.4 Doctor Module

Purpose: **doctor profile management**.

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
        ├── web
        │   └── DoctorController
        └── integration

### Responsibilities

- manage doctor profiles
- manage availability
- expose doctor data

Other modules must use **`DoctorApi`**.

---

## 3.5 Appointment Module

Purpose: **appointment scheduling and lifecycle**.

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
        ├── web
        │   └── AppointmentController
        └── integration

### Responsibilities

- schedule appointments
- update bookings
- cancel bookings
- enforce scheduling rules

### Dependencies

- `PatientApi`
- `DoctorApi`

Infrastructure adapters call those APIs.

The domain model references **only IDs**, never external entities.

---

## 3.6 Medical Module

Purpose: **medical records and prescriptions**.

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
        ├── web
        │   └── MedicalController
        └── integration

### Responsibilities

- visit records
- prescriptions
- patient medical history

### Dependencies

- `AppointmentApi`
- `PatientApi`
- `DoctorApi`

These APIs are accessed through **integration adapters**.

---

## 3.7 Dashboard Module

Purpose: **aggregated read views**.

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
        ├── web
        │   └── DashboardController
        └── integration

### Characteristics

- read-only module
- aggregates data from multiple modules
- optimized for query performance

### Dependencies

- `PatientApi`
- `DoctorApi`
- `AppointmentApi`
- `MedicalApi`

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

- shared exception hierarchy
- security adapters
- application configuration
- cross-module infrastructure utilities

Shared modules must **not contain business logic**.

---

# 5. Module Dependency Rules

## Allowed Dependencies

    infra → application
    infra → domain
    infra → api

    application → domain
    application → api.dto

    infra → other-module.api

---

## Forbidden Dependencies

    domain → any other layer
    domain → other modules

    application → infra
    application → other-module.application

    api → infra

    module → other-module.domain

---

## Inter-Module Communication

Modules communicate **only through API interfaces**.

Allowed flow:

    Module A (infra/integration)
        ↓
    Module B API interface
        ↓
    Module B application service
        ↓
    Module B domain

This guarantees:

- module isolation
- explicit contracts
- easy microservice extraction

---

# 6. Database Strategy

Modules share a **single database**, but maintain **strict ownership of their data model**.

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

Domain models **never depend on persistence entities**.

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
    IdentityApi
        ↓
    IdentityService
        ↓
    IdentityUserRepository

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

    auth
        ↓
    identity

All dependencies are **API-based**.

Properties:

- no circular dependencies
- contract-based communication
- strict domain isolation

---

# 9. Extension Strategy

New modules can be added **without modifying existing modules**.

Examples:

- `payments`
- `notifications`
- `reporting`
- `analytics`

### Rules

New modules must:

- expose an `api` layer
- encapsulate their domain model
- communicate with other modules **only through APIs**
- keep business logic inside their own domain and application layers

This architecture ensures:

- clear module boundaries
- stable contracts
- high maintainability
- easy migration to microservices