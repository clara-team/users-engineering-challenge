package com.clara.taskly.member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    // TODO: Add query methods. All queries must be scoped to companyId. Examples:
    //
    //   Optional<Member> findByIdAndCompanyId(UUID id, String companyId);
    //
    //   Optional<Member> findByCompanyIdAndEmail(String companyId, String email);
    //
    //   Optional<Member> findByCompanyIdAndUserId(String companyId, String userId);
    //
    //   List<Member> findByCompanyIdAndStatus(String companyId, MemberStatus status);
    //
    //   // Used for the last-admin guard in changeRole and offboard:
    //   long countByCompanyIdAndRoleAndStatus(String companyId, MemberRole role, MemberStatus status);
}
