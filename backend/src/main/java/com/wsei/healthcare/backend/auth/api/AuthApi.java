package com.wsei.healthcare.backend.auth.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

// TODO: move web doc to the web infra adapter
// TODO: Add error response schema (TO ERRORS IN ENDPOINTS)
// TODO: Rename User<dto> requests to be auth related
@Tag(
        name = "Auth API",
        description = "Endpoints for user registration, authentication, and session management"
)
public interface AuthApi {

    void createAuthIdentity(AuthIdentityCreationRequest request);

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
    JwtResponse login(
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

    //TODO: adjust
    @Operation(
            summary = "Logout user",
            description = "Logs out the currently authenticated user. " +
                    "The user is identified via the security context (Authentication object)."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User successfully logged out"),
            @ApiResponse(responseCode = "401", description = "User is not authenticated")
    })
    void logout(LogoutRequest authentication);
}
