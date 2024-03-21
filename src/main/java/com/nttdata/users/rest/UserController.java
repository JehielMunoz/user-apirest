package com.nttdata.users.rest;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nttdata.users.dom.ErrorResponse;
import com.nttdata.users.dom.Response;
import com.nttdata.users.dom.User;
import com.nttdata.users.dto.ResponseDTO;
import com.nttdata.users.dto.UserRequestDTO;
import com.nttdata.users.dto.UserResponseDTO;
import com.nttdata.users.service.UserPhoneService;
import com.nttdata.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "UsersAPI")
public class UserController {

  private UserService userService;
  private UserPhoneService userPhoneService;

  public UserController(UserService userService, UserPhoneService userPhoneService) {
    this.userService = userService;
    this.userPhoneService = userPhoneService;
  }


  @GetMapping("/user-list/")
  @Operation(summary = "Get users", description = "Retrieve all users")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved user list with data",
          content = {@Content(mediaType = "application/json")}),
      @ApiResponse(responseCode = "204", description = "Successfully retrieved user list without data"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")})
  public ResponseEntity<List<User>> getUsers() {
    List<User> userList = userService.getUsers();
    return new ResponseEntity<>(userList, HttpStatus.OK);
  }


  @GetMapping("/find-user/{mail}")
  @Operation(summary = "Get User by mail", description = "Retrieve an user using its mail",
      parameters = {@Parameter(name = "mail", description = "User Mail", required = true)})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved user with data",
          content = {@Content(mediaType = "application/json")}),
      @ApiResponse(responseCode = "204", description = "Successfully retrieved user without data"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")})
  public ResponseEntity<User> getUsers(@PathVariable String mail) {
    return new ResponseEntity<>(userService.getUserByMail(mail), HttpStatus.OK);
  }


  @PostMapping("/create")
  @Operation(summary = "Create user", description = "Create a new user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successfully created user",
          content = {@Content(mediaType = "application/json")}),
      @ApiResponse(responseCode = "400", description = "Bad request returned")})
  public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequest) {
    try {
      userPhoneService.validateUser(userRequest);
      UserResponseDTO userResponse = userPhoneService.createUser(userRequest);
      return ResponseEntity.ok(userResponse);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<ErrorResponse>(e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response<>("An error occurred while processing the request."));
    }
  }


  @PutMapping("/update-user/{userId}")
  @Operation(summary = "Update user", description = "Update user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successfully moved mail",
          content = {@Content(mediaType = "application/json")}),
      @ApiResponse(responseCode = "400", description = "Bad request")})
  public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User patchRequest) {
    try {
      return ResponseEntity.ok(userService.updateUser(userId, patchRequest));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<ErrorResponse>(e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response<>("An error occurred while processing the request."));
    }
  }


  @PatchMapping("/modify-user/{userId}")
  @Operation(summary = "Modify user", description = "Patch user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully moved mail",
          content = {@Content(mediaType = "application/json")}),
      @ApiResponse(responseCode = "400", description = "Bad request")})
  public ResponseEntity<?> patchUser(@PathVariable Long userId, @RequestBody User patchRequest) {
    try {
      return ResponseEntity.ok(userService.modifyUser(userId, patchRequest));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<ErrorResponse>(e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new Response<>("An error occurred while processing the request."));
    }
  }

  @DeleteMapping("/delete/{mail}")
  @Operation(summary = "Delete user", description = "Remove user using its mail")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successfully removed user",
          content = {@Content(mediaType = "application/json")}),
      @ApiResponse(responseCode = "400", description = "Bad request")})
  public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String mail) {
    return new ResponseEntity<>(userService.deleteUser(mail), HttpStatus.OK);
  }
}
