package com.clara.taskly.member;

import java.util.List;
import java.util.UUID;

import com.clara.taskly.task.TaskRepository;

import org.springframework.stereotype.Service;

@Service
class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final TaskRepository taskRepository;

    MemberServiceImpl(MemberRepository memberRepository, TaskRepository taskRepository) {
        this.memberRepository = memberRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Member invite(String companyId, InviteRequest request, String invitedBy) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<Member> list(String companyId, MemberStatus status) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Member get(String companyId, UUID memberId) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Member activate(String companyId, UUID memberId, String userId) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Member changeRole(String companyId, UUID memberId, MemberRole newRole, String requestorId) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Member offboard(String companyId, UUID memberId, String requestorId) {
        // Design decision: how to handle open tasks assigned to this member.
        // Choose a strategy, implement it, and write a test that proves the behaviour.
        throw new UnsupportedOperationException("not implemented");
    }
}
