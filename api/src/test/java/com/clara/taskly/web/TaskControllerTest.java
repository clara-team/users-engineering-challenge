package com.clara.taskly.web;

import java.util.Arrays;
import java.util.List;

import com.clara.taskly.task.TaskService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
@Import(TestWebSecurityConfig.class)
class TaskControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    TaskService taskService;

    private static JwtRequestPostProcessor jwt(String companyId, String... roles) {
        GrantedAuthority[] authorities = Arrays.stream(roles)
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .toArray(GrantedAuthority[]::new);
        return SecurityMockMvcRequestPostProcessors.jwt()
                .authorities(authorities)
                .jwt(b -> b
                        .subject("user|test-subject")
                        .claim("company_id", companyId)
                        .claim("https://taskly.clara.team/roles", List.of(roles)));
    }

    // --- Provided: this test passes out of the box ---

    @Test
    void anonymousRequestIsRejectedWith401() throws Exception {
        mvc.perform(get("/api/tasks"))
           .andExpect(status().isUnauthorized());
    }

    // --- TODO: implement the following tests ---

    @Test
    void memberCanListTasks() throws Exception {
        // TODO: stub taskService.list(...) to return an empty page
        // Perform GET /api/tasks with jwt("company_alpha", "member")
        // Assert 200 OK
    }

    @Test
    void memberCannotCreateTask() throws Exception {
        // TODO: perform POST /api/tasks with jwt("company_alpha", "member") and a valid body
        // Assert 403 Forbidden
    }

    @Test
    void adminCanCreateTask() throws Exception {
        // TODO: stub taskService.create(...) to return a Task
        // Perform POST /api/tasks with jwt("company_alpha", "admin") and a valid body
        // Assert 201 Created
    }

    @Test
    void deleteFromOtherCompanyReturns404() throws Exception {
        // TODO: stub taskService.delete(...) to throw a not-found exception
        // Perform DELETE /api/tasks/{id} with jwt("company_alpha", "admin")
        // The task should belong to company_beta so the service throws not-found
        // Assert 404 — never 403
    }

    @Test
    void invalidStatusTransitionReturns422() throws Exception {
        // TODO: stub taskService.updateStatus(...) to throw an invalid-transition exception
        // Perform PATCH /api/tasks/{id}/status with jwt("company_alpha", "admin")
        // Assert 422 Unprocessable Entity
    }

    @Test
    void memberCanOnlyUpdateStatusOnAssignedTask() throws Exception {
        // TODO: stub taskService.updateStatus(...) to throw 403 when a member updates
        // a task that is not assigned to them
        // Assert 403
    }
}
