package com.clara.taskly.task;

import java.time.Instant;
import java.util.UUID;

public class Task {

    private UUID id;

    // Tenant boundary — set from CompanyContext.companyId(jwt) at creation; never updated.
    private String companyId;

    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;

    // Auth0 subject (user|xxx) of the assignee — nullable.
    private String assigneeId;

    // Auth0 subject of the creator.
    private String createdBy;

    private Instant createdAt;
    private Instant updatedAt;
}
