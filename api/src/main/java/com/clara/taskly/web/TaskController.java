package com.clara.taskly.web;

import com.clara.taskly.security.CompanyContext;
import com.clara.taskly.task.Task;
import com.clara.taskly.task.TaskPriority;
import com.clara.taskly.task.TaskService;
import com.clara.taskly.task.TaskStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

/**
 * Task CRUD, scoped to the caller's company — method signatures provided, implement the bodies.
 *
 * <p>Company identity always comes from {@link CompanyContext#companyId(Jwt)}.
 * No endpoint accepts a company id as a path variable, query param, or request body field.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public record CreateTaskRequest(
            @NotBlank String title,
            String description,
            @NotNull TaskPriority priority,
            String assigneeId) {}

    public record UpdateStatusRequest(@NotNull TaskStatus status) {}

    public record AssignRequest(@NotBlank String assigneeId) {}

    @GetMapping
    public Page<Task> list(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) String assigneeId,
            Pageable pageable) {
        return null;
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Task> create(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid CreateTaskRequest request) {
        return null;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable String id,
            @RequestBody @Valid UpdateStatusRequest request) {
        return null;
    }

    @PatchMapping("/{id}/assignee")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Task> assign(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable String id,
            @RequestBody @Valid AssignRequest request) {
        return null;
    }

    /**
     * A task from a different company must return 404 — never 403.
     * Do not confirm to the caller that the id exists in another company.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable String id) {
        return null;
    }
}
