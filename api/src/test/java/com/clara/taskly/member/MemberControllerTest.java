package com.clara.taskly.member;

import java.util.Arrays;
import java.util.List;

import com.clara.taskly.web.MemberController;
import com.clara.taskly.web.TestWebSecurityConfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@Import(TestWebSecurityConfig.class)
class MemberControllerTest {

    @Autowired MockMvc mvc;
    @MockitoBean MemberService memberService;

    private static JwtRequestPostProcessor jwt(String companyId, String userId, String... roles) {
        GrantedAuthority[] authorities = Arrays.stream(roles)
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .toArray(GrantedAuthority[]::new);
        return SecurityMockMvcRequestPostProcessors.jwt()
                .authorities(authorities)
                .jwt(b -> b
                        .subject(userId)
                        .claim("company_id", companyId)
                        .claim("https://taskly.clara.team/roles", List.of(roles)));
    }

    // ── Provided: passes out of the box ──────────────────────────────────────

    @Test
    void anonymousCannotListMembers() throws Exception {
        mvc.perform(get("/api/members"))
           .andExpect(status().isUnauthorized());
    }

    @Test
    void memberCannotInvite() throws Exception {
    }

    @Test
    void adminCanInviteNewMember() throws Exception {
    }

    @Test
    void duplicateEmailReturns409() throws Exception {
    }

    @Test
    void activateTransitionsPendingToActive() throws Exception {
    }

    @Test
    void activatingAlreadyActiveMemberReturns422() throws Exception {
    }

    @Test
    void adminCanPromoteMember() throws Exception {
    }

    @Test
    void cannotChangeOwnRole() throws Exception {
    }

    @Test
    void demotingLastAdminReturns409() throws Exception {
    }

    @Test
    void adminCanOffboardMember() throws Exception {
    }

    @Test
    void cannotOffboardYourself() throws Exception {
    }

    @Test
    void offboardingLastAdminReturns409() throws Exception {
    }

    @Test
    void offboardingWithOpenTasksReturnsExpectedStatus() throws Exception {
    }
}
