package com.clara.taskly.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    // TODO: Add query methods. All queries must be scoped to companyId — never return tasks
    // from other companies. Examples to consider:
    //
    //   Page<Task> findByCompanyId(String companyId, Pageable pageable);
    //
    //   Page<Task> findByCompanyIdAndStatus(String companyId, TaskStatus status, Pageable pageable);
    //
    //   Optional<Task> findByIdAndCompanyId(UUID id, String companyId);
    //
    // Tip: Spring Data derives queries from method names. Use @Query only when the derived
    // name becomes unreadable.
}
