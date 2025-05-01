package com.onboarding.simulator.dto;

/**
 * Represents the response from the authentication endpoint.
 * Contains the company name, token expiration time in seconds,
 * and the JWT access token.
 */
public record AuthenticationResponse(
    String name,
    int tokenExpirationTime,
    String accessToken
) {}