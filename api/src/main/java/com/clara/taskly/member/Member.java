package com.clara.taskly.member;

import java.time.Instant;
import java.util.UUID;

public class Member {

    private UUID id;

    // Tenant boundary — always from CompanyContext.companyId(jwt), never from the request.
    private String companyId;

    // Auth0 subject (user|xxx) — null while status is PENDING.
    private String userId;

    private String email;
    private MemberRole role;
    private MemberStatus status;
    private String invitedBy;
    private Instant invitedAt;
    private Instant activatedAt;
    private Instant offboardedAt;
}
