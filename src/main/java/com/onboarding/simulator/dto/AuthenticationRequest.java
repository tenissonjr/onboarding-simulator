package com.onboarding.simulator.dto;

/**
 * Represents authentication credentials used to request tokens.
 * Contains a companyId UUID and a Base64-encoded secretKey.
 */
public record AuthenticationRequest(
    String companyId,
    String secretKey
) {}