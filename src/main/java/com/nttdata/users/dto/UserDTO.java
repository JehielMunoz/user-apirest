package com.nttdata.users.dto;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO representing an User")
public class UserDTO {

  @Schema(description = "Identifier of the user")
  private Long userId;

  @Schema(description = "User name")
  private String name;

  @Schema(description = "User mail")
  private String mail;

  @Schema(description = "User pass")
  private String pass;

  @Schema(description = "Creation date")
  private LocalDateTime created_at;

  @Schema(description = "Update date")
  private LocalDateTime updated_at;

  @Schema(description = "Last login")
  private LocalDateTime last_login;

  @Schema(description = "If user is active, active = true")
  private boolean isActive;

  @Schema(description = "User phones")
  private String phones;

  public UserDTO() {
    super();
  }

}
