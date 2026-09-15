package com.clara.taskly.task;

import com.clara.taskly.member.MemberRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
class TaskServiceImpl implements TaskService {

    private final TaskRepository   taskRepository;
    private final MemberRepository memberRepository;

    TaskServiceImpl(TaskRepository taskRepository, MemberRepository memberRepository) {
        this.taskRepository   = taskRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Page<Task> list(String companyId, TaskStatus status, String assigneeId, Pageable pageable) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Task create(String companyId, CreateTaskRequest request, String createdBy) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Task updateStatus(String companyId, String taskId, TaskStatus newStatus, String requestorId, boolean isAdmin) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Task assign(String companyId, String taskId, String assigneeId) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void delete(String companyId, String taskId) {
        throw new UnsupportedOperationException("not implemented");
    }
}
