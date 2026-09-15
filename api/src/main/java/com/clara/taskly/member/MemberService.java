package com.clara.taskly.member;

import java.util.List;
import java.util.UUID;

public interface MemberService {

    Member invite(String companyId, InviteRequest request, String invitedBy);

    List<Member> list(String companyId, MemberStatus status);

    Member get(String companyId, UUID memberId);

    Member activate(String companyId, UUID memberId, String userId);

    Member changeRole(String companyId, UUID memberId, MemberRole newRole, String requestorId);

    Member offboard(String companyId, UUID memberId, String requestorId);

    record InviteRequest(String email, MemberRole role) {}
}
