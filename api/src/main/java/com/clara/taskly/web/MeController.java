package com.clara.taskly.web;

import java.util.List;

import com.clara.taskly.config.TasklyProperties;
import com.clara.taskly.security.CompanyContext;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Returns the caller's identity as seen by the API — provided, do not modify.
 *
 * <p>This is the reference implementation. Notice:
 * <ul>
 *   <li>Company identity comes from {@link CompanyContext#companyId(Jwt)}, not from the request.
 *   <li>Roles come from the namespaced JWT claim, not from a database query.
 *   <li>The controller has zero business logic.
 * </ul>
 */
@RestController
@RequestMapping("/api/me")
public class MeController {

    private final TasklyProperties taskly;

    MeController(TasklyProperties taskly) {
        this.taskly = taskly;
    }

    @GetMapping
    public MeResponse me(@AuthenticationPrincipal Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList(taskly.rolesClaim());
        return new MeResponse(
                jwt.getSubject(),
                CompanyContext.companyId(jwt),
                roles != null ? roles : List.of());
    }

    public record MeResponse(String subject, String companyId, List<String> roles) {}
}
