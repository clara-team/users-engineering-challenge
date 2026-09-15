package com.clara.taskly.security;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyIdValidatorTest {

    private final CompanyIdValidator validator = new CompanyIdValidator();

    @Test
    void presentCompanyIdPasses() {
        Jwt jwt = jwt(Map.of("sub", "user|1", "company_id", "company_alpha"));
        assertThat(validator.validate(jwt).hasErrors()).isFalse();
    }

    @Test
    void missingCompanyIdFails() {
        Jwt jwt = jwt(Map.of("sub", "user|1"));
        OAuth2TokenValidatorResult result = validator.validate(jwt);
        assertThat(result.hasErrors()).isTrue();
    }

    @Test
    void blankCompanyIdFails() {
        Jwt jwt = jwt(Map.of("sub", "user|1", "company_id", "  "));
        OAuth2TokenValidatorResult result = validator.validate(jwt);
        assertThat(result.hasErrors()).isTrue();
    }

    private static Jwt jwt(Map<String, Object> claims) {
        return Jwt.withTokenValue("token")
                .header("alg", "RS256")
                .claims(c -> c.putAll(claims))
                .build();
    }
}
