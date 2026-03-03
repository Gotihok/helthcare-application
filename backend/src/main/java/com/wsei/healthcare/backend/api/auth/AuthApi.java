package com.wsei.healthcare.backend.api.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

// TODO: Add error response schema (TO ERRORS IN ENDPOINTS)
// TODO: Rename User<dto> requests to be auth related
@Tag(
        name = "Auth API",
        description = "Endpoints for user registration, authentication, and session management"
)
public interface AuthApi {

    @Operation(summary = "Register a new user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User successfully registered",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid registration data (validation error)"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    ResponseEntity<JwtResponse> register(
            @RequestBody(
                    description = "User register credentials",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RegisterRequest.class)
                    )
            )
            RegisterRequest request
    );

    // ------------------------------------------------------------

    @Operation(summary = "Authenticate user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Authentication successful",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid login data (validation error)"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials or account is disabled or locked"),
    })
    ResponseEntity<JwtResponse> login(
            @RequestBody(
                    description = "User login credentials",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = LoginRequest.class)
                    )
            )
            LoginRequest request
    );

    // ------------------------------------------------------------

    @Operation(
            summary = "Logout user",
            description = "Logs out the currently authenticated user. " +
                    "The user is identified via the security context (Authentication object)."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User successfully logged out"),
            @ApiResponse(responseCode = "401", description = "User is not authenticated")
    })
    ResponseEntity<Void> logout(Authentication authentication);
}
