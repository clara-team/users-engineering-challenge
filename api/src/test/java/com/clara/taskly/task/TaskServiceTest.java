package com.clara.taskly.task;

import com.clara.taskly.member.MemberRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock TaskRepository   taskRepository;
    @Mock MemberRepository memberRepository;

    @InjectMocks TaskServiceImpl service;

    private static final String COMPANY_ALPHA = "company_alpha";
    private static final String COMPANY_BETA  = "company_beta";
    private static final String ADMIN_ID      = "user|admin";
    private static final String MEMBER_ID     = "user|member";

    @Test
    void createFailsWhenAssigneeIsNotActiveInCompany() {
        // TODO: assigneeId set, but member is missing, PENDING, OFFBOARDED, or in COMPANY_BETA
    }

    @Test
    void updateStatusFailsWhenTaskBelongsToAnotherCompany() {
        // TODO: task exists in COMPANY_BETA; caller is COMPANY_ALPHA → not-found, not forbidden
    }

    @Test
    void updateStatusFailsOnInvalidTransition() {
        // TODO: e.g. TODO → DONE, or DONE → anything; per TaskStatus#canTransitionTo
    }

    @Test
    void memberCannotUpdateStatusOnUnassignedTask() {
        // TODO: isAdmin=false, task.assigneeId != MEMBER_ID → 403
    }

    @Test
    void adminCanUpdateStatusOnUnassignedTask() {
        // TODO: isAdmin=true, same company, valid transition — assignee does not matter
    }

    @Test
    void assignFailsWhenAssigneeBelongsToAnotherCompany() {
        // TODO: task in COMPANY_ALPHA, assignee's company is COMPANY_BETA
    }

    @Test
    void deleteFailsWhenTaskBelongsToAnotherCompany() {
        // TODO: same as updateStatus — not-found, never expose that the id exists in COMPANY_BETA
    }
}
