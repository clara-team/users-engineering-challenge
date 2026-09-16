# AI Usage

AI is **allowed and expected**. This file is part of the submission, not optional documentation.

Fill in every section. Empty sections or “I used Copilot a bit” is not enough. We use this in the debrief: unexplained generated code does not pass.

Do not paste secrets (`application.yml`, `.env.local`, Auth0 client secrets).

---

## Tools used

| Tool | What you used it for |
|------|----------------------|
| e.g. Cursor / Claude / ChatGPT / Copilot | e.g. JPA mapping, member state machine, tests, React |

---

## Prompts

Paste the **actual** prompts you used (edit only for secrets). One prompt per subsection. If you iterated, keep the version that produced the code you shipped.

### 1. Tenant boundary

`CompanyContext.companyId(jwt)` only. Cross-company ids → **404, never 403**. No company id on path, query, or body.

> Paste prompt.

### 2. Member lifecycle

Invite → activate → change role → offboard. Last-admin guards. Offboard policy for open tasks. JWT `admin`/`member` vs enum `ADMIN`/`MEMBER`. Local table only — no Auth0 Management API.

> Paste prompt.

### 3. Task domain

Org-scoped queries, `TODO` → `IN_PROGRESS` → `DONE`, assignee must be `ACTIVE` in the same company, member can only update status on assigned tasks.

> Paste prompt.

### 4. HTTP mapping

Exception types / `@ControllerAdvice` (or equivalent) so tests can expect 404 / 403 / 409 / 422.

> Paste prompt.

### 5. Unit / controller test standard (required if AI wrote tests)

Do **not** use “write the tests”. The prompt must define a standard, for example:

- fill in the stubs in `TaskControllerTest`, `TaskServiceTest`, `MemberControllerTest`, `MemberServiceTest`
- use the provided `jwt(...)` helper
- one business rule per test
- prove 404-not-403, last-admin, invalid transition, offboard policy
- do not add tests for behavior you did not implement unless marked as an assumption

> Paste prompt.

### 6. React / role-aware UI

`api.ts`, `TaskList` / `TaskCard` / `CreateTask` / `StatusPicker`. Admin-only controls **absent from the DOM** for members, not CSS-hidden. No member-management UI.

> Paste prompt.

---

## What AI produced vs what you own

| Area | AI-assisted? | You changed / reviewed? |
|------|----------------|-------------------------|
| JPA / repositories | | |
| Member lifecycle | | |
| Offboard + open tasks | | |
| Task status machine | | |
| Exception → HTTP mapping | | |
| Test stubs filled in | | |
| React components | | |
| Auth0 Action / tenant setup | | |

---

## Accepted suggestions

List suggestions you kept, and why they fit this take-home (4–6 hours, company on the token, no Management API).

-

---

## Rejected suggestions

List suggestions you threw away, and why. Typical rejects: Auth0 Organizations, Management API sync, a `Company` table, taking `companyId` from the request, 403 on cross-company ids, mixing `ROLE_ADMIN` with `hasRole('admin')`.

| Suggestion | Why rejected |
|------------|----------------|
| | |

---

## Corrections

Where the model was wrong and you fixed it (wrong status code, last-admin hole, JWT vs DB role mix-up, activate stamping the admin’s `sub`, UI still showing Delete for members, etc.).

-

---

## What you will defend in the interview

Three things you can walk through without the model in the room:

1.
2.
3.
