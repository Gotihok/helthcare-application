package com.wsei.healthcare.backend.user.rest.contract;

import com.wsei.healthcare.backend.user.rest.dto.JwtResponse;
import com.wsei.healthcare.backend.user.rest.dto.UserLoginRequest;
import com.wsei.healthcare.backend.user.rest.dto.UserLogoutRequest;
import com.wsei.healthcare.backend.user.rest.dto.UserRegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

// TODO: Add error response schema (TO ERRORS IN ENDPOINTS)
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
                            schema = @Schema(implementation = UserRegisterRequest.class)
                    )
            )
            UserRegisterRequest request
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
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "403", description = "User account is disabled or locked")
    })
    ResponseEntity<JwtResponse> login(
            @RequestBody(
                    description = "User login credentials",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UserLoginRequest.class)
                    )
            )
            UserLoginRequest request
    );

    // ------------------------------------------------------------

    @Operation(summary = "Logout user")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User successfully logged out"),
            @ApiResponse(responseCode = "401", description = "User is not authenticated")
    })
    ResponseEntity<Void> logout(
            @RequestBody(
                    description = "Logout request payload",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UserLogoutRequest.class)
                    )
            )
            UserLogoutRequest request
    );
}
