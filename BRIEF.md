# Taskly — Candidate brief

Build the service layer and React UI for a company-scoped task board. Auth0 issues the access token; this API only decides whether to trust it.

**Time box: 4–6 hours.** Stop when the API tests you were asked to fill in are green and a member vs admin can use the board. Styling is optional. Do not add an Auth0 Management API client.

AI is **allowed and expected** (Cursor, Copilot, ChatGPT, Claude, etc.). Complete **[AI_USAGE.md](AI_USAGE.md)** — actual prompts, accepted suggestions, rejected suggestions and why, and corrections. Generated code is fine; unexplained generated code is not. The debrief will ask you to defend it.

---

## What is already provided

Do not modify these (except config copies listed in the README):

- Resource-server security: JWKS, issuer, audience, `company_id` presence
- `CompanyContext` — the only legal way to read `company_id` from the JWT
- `MeController`, `useApi`, `App` shell (login / logout / `/api/me` wiring)
- Controller method signatures, service interfaces, enums, test helpers

You implement: JPA mapping, repositories, service bodies, controller bodies, exception → HTTP mapping, the `api.ts` helpers, and the React components marked TODO.

---

## Auth0 setup (~1 hour)

Do **not** use Auth0 Organizations. One tenant, one SPA, one API.

### 1. API

- Identifier: match `spring.security.oauth2.resourceserver.jwt.audiences` (e.g. `https://taskly.clara.team/api`)
- Enable **RBAC**
- Signing algorithm RS256

### 2. Application

- Type: Single Page Application
- Allowed Callback / Logout / Web Origins: `http://localhost:5173`

### 3. Roles

Create roles named exactly `admin` and `member` (lowercase). `@PreAuthorize("hasRole('admin')")` maps to the claim value `admin`.

Assign each test user a role on this API.

### 4. Users and `app_metadata`

For each user, set:

```json
{
  "company_id": "company_alpha"
}
```

Use a second user with `"company_id": "company_beta"` if you want to click through the 404-not-403 case live. The automated tests do not need a real tenant.

### 5. Post-login Action

Create a Login / Post Login Action, deploy it, and bind it to the Login flow.

```javascript
exports.onExecutePostLogin = async (event, api) => {
  const roles = event.authorization?.roles ?? [];
  api.accessToken.setCustomClaim('https://taskly.clara.team/roles', roles);

  const companyId = event.user.app_metadata?.company_id;
  if (companyId) {
    // Access-token claim (not namespaced). CompanyContext reads this exact name.
    api.accessToken.setCustomClaim('company_id', companyId);
  }
};
```

`https://taskly.clara.team/roles` must match `taskly.roles-claim` in `application.yml`.

---

## What to build

### Tenant boundary (the important signal)

- Company identity always comes from `CompanyContext.companyId(jwt)`. Never from a path, query, or body field.
- Every query is scoped by `companyId`. A task or member id that exists in another company is **404**, never 403.

### Members

Local table only — do not sync to Auth0.

| Action | Rules |
|---|---|
| Invite | Admin. Email unique per company. Starts `PENDING`. |
| Activate | The invited user, on first login. `jwt.getSubject()` becomes `userId`. `PENDING` → `ACTIVE` only. |
| Change role | Admin. Cannot change own role. Cannot demote the last `ACTIVE` admin. Not valid for `OFFBOARDED`. |
| Offboard | Admin. Cannot offboard self. Cannot offboard the last `ACTIVE` admin. Choose how open tasks assigned to that member are handled, and prove it with a test. |

Authorization (`@PreAuthorize`, `hasRole`) reads the **JWT**. `Member.role` in the database is for listing and the last-admin guard. They can drift; do not call Auth0 to fix that.

JWT role strings are `admin` / `member`. The JPA enum is `ADMIN` / `MEMBER`. Map them; do not mix them in `@PreAuthorize`.

### Tasks

Status machine: `TODO` → `IN_PROGRESS` → `DONE` only (`TaskStatus#canTransitionTo`). Invalid transition → 422.

- Admin: create, assign, delete, update status on any task in the company
- Member: list all tasks in the company; update status only when `assigneeId` equals their subject
- If `assigneeId` is set on create/assign, that member must be `ACTIVE` in the same company

You will need exception types and a `@ControllerAdvice` (or equivalent) so tests can expect 404 / 403 / 409 / 422. None of that is provided.

### Web

- Implement the `api.ts` helpers (`fetchMe` is required for the provided `App`)
- `TaskList` / `TaskCard` / `CreateTask` / `StatusPicker` per the comments in those files
- Admin-only controls must be **absent from the DOM** for members, not CSS-hidden
- No member-management UI is required

---

## Tests

```bash
cd api && ./mvnw test
```

Offline. Fill in the stubs in:

- `TaskControllerTest`, `TaskServiceTest`
- `MemberControllerTest`, `MemberServiceTest`

`TestWebSecurityConfig` is already imported so `@PreAuthorize` and CSRF behave in `@WebMvcTest`. Use the provided `jwt(...)` helper.

---

## What to submit

1. A git remote we can clone (private is fine; grant access).
2. In the PR / README note: JDK 21, how to run API + SPA, and that `./mvnw test` is green.
3. Filled-in [AI_USAGE.md](AI_USAGE.md).
4. Do not commit `application.yml` or `.env.local`.

---

## Delivery checklist

- [ ] Company only from `CompanyContext` — never from the request
- [ ] Cross-company id → 404, not 403
- [ ] Member lifecycle + last-admin guards
- [ ] Offboard open-tasks policy, with a test
- [ ] Task transitions; invalid → 422
- [ ] Stub tests filled in; `./mvnw test` green
- [ ] Admin-only UI absent from the DOM for members
- [ ] [AI_USAGE.md](AI_USAGE.md) has prompts, accepted / rejected / corrections
- [ ] No `application.yml` or `.env.local` committed

We will look at tenant isolation, role enforcement, the member state machine (especially last-admin and offboard), whether the UI matches the token, and whether you own the AI-assisted parts — not at visual polish.
