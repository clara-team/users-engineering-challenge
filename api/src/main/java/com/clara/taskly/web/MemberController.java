package com.clara.taskly.web;

import java.util.List;
import java.util.UUID;

import com.clara.taskly.member.Member;
import com.clara.taskly.member.MemberRole;
import com.clara.taskly.member.MemberService;
import com.clara.taskly.member.MemberStatus;
import com.clara.taskly.security.CompanyContext;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

/**
 * User lifecycle endpoints — implement the method bodies.
 * Company identity comes from {@link CompanyContext#companyId(Jwt)} only — never from request params.
 */
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public record InviteRequest(@Email @NotBlank String email, @NotNull MemberRole role) {}
    public record ChangeRoleRequest(@NotNull MemberRole role) {}

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Member> invite(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid InviteRequest request) {
        return null;
    }

    // Called by the invited user on first login — not admin-only.
    // jwt.getSubject() is the Auth0 subject of the activating user.
    @PostMapping("/{id}/activate")
    public ResponseEntity<Member> activate(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id) {
        return null;
    }

    @PatchMapping("/{id}/role")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Member> changeRole(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id,
            @RequestBody @Valid ChangeRoleRequest request) {
        return null;
    }

    @PostMapping("/{id}/offboard")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Member> offboard(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id) {
        return null;
    }

    @GetMapping
    public List<Member> list(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam(required = false) MemberStatus status) {
        return null;
    }

    @GetMapping("/{id}")
    public Member get(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id) {
        return null;
    }
}
