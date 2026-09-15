package com.clara.taskly.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    /**
     * Returns a page of tasks for the given company.
     * Both {@code status} and {@code assigneeId} are optional filters — pass null to omit.
     */
    Page<Task> list(String companyId, TaskStatus status, String assigneeId, Pageable pageable);

    /**
     * Creates a new task in the given company.
     * If {@code request.assigneeId()} is non-null, it must belong to the same company.
     */
    Task create(String companyId, CreateTaskRequest request, String createdBy);

    /**
     * Updates the status of a task.
     *
     * <p>Rules:
     * <ul>
     *   <li>The task must belong to {@code companyId} — otherwise throw a not-found exception.
     *   <li>The transition must be valid per {@link TaskStatus#canTransitionTo} — otherwise throw
     *       an invalid-transition exception (mapped to 422).
     *   <li>A member may only update tasks where {@code task.assigneeId} equals {@code requestorId}.
     *   <li>An admin may update any task in the company.
     * </ul>
     */
    Task updateStatus(String companyId, String taskId, TaskStatus newStatus, String requestorId, boolean isAdmin);

    /**
     * Assigns (or reassigns) a task to a member of the same company.
     * The task and the target assignee must both belong to {@code companyId}.
     */
    Task assign(String companyId, String taskId, String assigneeId);

    /**
     * Deletes a task. If the task does not exist or belongs to a different company, throw a
     * not-found exception — never expose whether the id exists in another company.
     */
    void delete(String companyId, String taskId);

    record CreateTaskRequest(String title, String description, TaskPriority priority, String assigneeId) {}
}
