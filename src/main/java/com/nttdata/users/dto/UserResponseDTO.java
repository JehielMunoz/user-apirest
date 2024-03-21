package com.nttdata.users.dto;

import java.time.LocalDateTime;
import org.hibernate.annotations.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Schema(description = "DTO representing an User response")
public class UserResponseDTO {

  @Id
  @Comment(value = "Identifier of the user")
  @Column(name = "user_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Comment(value = "Creation date")
  @Column(name = "created_at")
  private LocalDateTime creationDate;

  @Comment(value = "Update date")
  @Column(name = "updated_at")
  private LocalDateTime updatedDate;

  @Comment(value = "Last login")
  @Column(name = "last_login")
  private LocalDateTime lastLogin;

  @Comment(value = "If user is active, active = true")
  @Column(name = "active")
  private boolean isActive;

  public UserResponseDTO() {
    super();
  }

  public UserResponseDTO(Long userId, LocalDateTime creationDate, LocalDateTime updatedDate, LocalDateTime lastLogin,
      boolean isActive) {
    super();
    this.userId = userId;
    this.creationDate = creationDate;
    this.updatedDate = updatedDate;
    this.lastLogin = lastLogin;
    this.isActive = isActive;
  }

  /**
   * @return the userId
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * @return the creationDate
   */
  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  /**
   * @param creationDate the creationDate to set
   */
  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  /**
   * @return the updatedDate
   */
  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  /**
   * @param updatedDate the updatedDate to set
   */
  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  /**
   * @return the lastLogin
   */
  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  /**
   * @param lastLogin the lastLogin to set
   */
  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  /**
   * @return the isActive
   */
  public boolean isActive() {
    return isActive;
  }

  /**
   * @param isActive the isActive to set
   */
  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public String toString() {
    return "UserResponseDTO [userId=" + userId + ", creationDate=" + creationDate + ", updatedDate=" + updatedDate
        + ", lastLogin=" + lastLogin + ", isActive=" + isActive + "]";
  }

}
