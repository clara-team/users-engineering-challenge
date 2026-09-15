package com.clara.taskly.security;

import org.springframework.security.oauth2.jwt.Jwt;

/**
 * Extracts the company identity from a validated JWT — provided, do not modify.
 *
 * <p>This is the only place in the codebase where company_id is read from a token.
 * Controllers and services must call this helper; they must never accept a company id
 * from a request parameter, path variable, or request body.
 */
public final class CompanyContext {

    private CompanyContext() {}

    public static String companyId(Jwt jwt) {
        return jwt.getClaimAsString("company_id");
    }
}
