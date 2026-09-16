# Taskly — Technical Assessment

A company-scoped task board secured with Auth0.

Each user belongs to a company. The company identity is carried in the JWT and used to scope every data access. Members can view tasks and update the status of tasks assigned to them. Admins have full create, assign, and delete capabilities.

Your job is to implement the API service layer and the React web components.

---

## Roles

- **admin** — full CRUD; can assign tasks to any company member
- **member** — read all tasks; can update status only on tasks assigned to them

---

## What you need to implement

### API

- User lifecycle: invite, activate, change role, offboard
- Task management: create, list, assign, update status, delete
- JPA entity mapping and repository queries

### Web

- Authentication flow and token handling
- Task list and task card components
- Role-aware UI (admin controls vs. member view)

Full requirements, Auth0 setup (including the post-login Action), and submission instructions are in [BRIEF.md](BRIEF.md).

AI is allowed and expected. Fill in [AI_USAGE.md](AI_USAGE.md) as part of the submission.

---

## Prerequisites

- JDK 21+
- Node 20+

---

## Setup

### Auth0

See [BRIEF.md](BRIEF.md) for required Auth0 objects (API, Application, Roles, Action).

### API config

```bash
cp api/src/main/resources/application.example.yml \
   api/src/main/resources/application.yml
# Fill in issuer-uri and audience
```

### Web config

```bash
cp web/.env.example web/.env.local
# Fill in domain, client ID, and audience
```

---

## Run

```bash
# API on :8080
cd api && ./mvnw spring-boot:run

# SPA on :5173
cd web && npm install && npm run dev
```

---

## Tests

```bash
cd api && ./mvnw test
```

The test suite runs offline — no real Auth0 tenant is needed for unit or controller tests.

These pass out of the box:

- `CompanyIdValidatorTest` — missing or blank `company_id` is rejected
- `MemberControllerTest#anonymousCannotListMembers` — anonymous access rejected
- `TaskControllerTest#anonymousRequestIsRejectedWith401` — anonymous access rejected

The remaining tests are stubs. Implement them alongside your feature code.
