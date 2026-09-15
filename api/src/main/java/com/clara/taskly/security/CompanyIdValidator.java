package com.clara.taskly.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * Rejects tokens that are missing a non-blank company_id claim.
 * Without it, CompanyContext.companyId(jwt) returns null and all tenant-scoped queries break.
 */
class CompanyIdValidator implements OAuth2TokenValidator<Jwt> {

    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        String companyId = jwt.getClaimAsString("company_id");
        if (companyId == null || companyId.isBlank()) {
            return OAuth2TokenValidatorResult.failure(
                    new OAuth2Error("invalid_token", "Token is missing company_id claim", null));
        }
        return OAuth2TokenValidatorResult.success();
    }
}
