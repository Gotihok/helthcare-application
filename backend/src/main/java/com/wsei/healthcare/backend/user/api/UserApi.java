package com.wsei.healthcare.backend.user.api;

import com.wsei.healthcare.backend.auth.api.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface UserApi {

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
    UserResponse register(
            @RequestBody(
                    description = "User register credentials",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UserRegisterRequest.class)
                    )
            )
            UserRegisterRequest request
    );

//    //TODO: change to user response when introduced
//    UserResponse createUser(UserCreateRequest userCreateRequest);

    UserResponse updateUserEmail(UserEmailUpdateRequest userEmailUpdateRequest, String authName);

    UserResponse getById(Long id);
}
