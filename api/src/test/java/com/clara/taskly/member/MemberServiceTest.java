package com.clara.taskly.member;

import com.clara.taskly.task.TaskRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock MemberRepository memberRepository;
    @Mock TaskRepository    taskRepository;

    @InjectMocks MemberServiceImpl service;

    private static final String COMPANY_ID = "company_alpha";
    private static final String ADMIN_ID   = "user|admin";
    private static final String MEMBER_ID  = "user|member";

    @Test
    void inviteFailsWhenEmailAlreadyExistsInCompany() {
    }

    @Test
    void activateFailsWhenMemberIsAlreadyActive() {
    }

    @Test
    void changeRoleFailsWhenRequestorChangesOwnRole() {
    }

    @Test
    void changeRoleFailsWhenDemotingLastAdmin() {
    }

    @Test
    void changeRoleFailsForOffboardedMember() {
    }

    @Test
    void offboardFailsWhenRequestorOffboardsThemselves() {
    }

    @Test
    void offboardFailsWhenLastActiveAdmin() {
    }

    @Test
    void offboardHandlesOpenTasksPerChosenStrategy() {
    }
}
