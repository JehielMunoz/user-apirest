package com.nttdata.users.dom;

import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.Comment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Comment(value = "The table contains the users")
@Table(schema = "users_data", name = "user_data")
public class User {

  @Id
  @Comment(value = "Identifier of the user")
  @Column(name = "user_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Comment(value = "User name")
  @Column(name = "name")
  private String name;

  @Comment(value = "User mail")
  @Column(name = "mail")
  private String mail;

  @Comment(value = "User pass")
  @Column(name = "pass")
  private String pass;

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

  @Comment(value = "User phones")
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Phone> phones;

  public User() {
    super();
  }

  public User(Long userId, String name, String mail, String pass, LocalDateTime creationDate, LocalDateTime updatedDate,
      LocalDateTime lastLogin, boolean isActive, List<Phone> phones) {
    super();
    this.userId = userId;
    this.name = name;
    this.mail = mail;
    this.pass = pass;
    this.creationDate = creationDate;
    this.updatedDate = updatedDate;
    this.lastLogin = lastLogin;
    this.isActive = isActive;
    this.phones = phones;
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
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the mail
   */
  public String getMail() {
    return mail;
  }

  /**
   * @param mail the mail to set
   */
  public void setMail(String mail) {
    this.mail = mail;
  }

  /**
   * @return the pass
   */
  public String getPass() {
    return pass;
  }

  /**
   * @param pass the pass to set
   */
  public void setPass(String pass) {
    this.pass = pass;
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

  /**
   * @return the phones
   */
  public List<Phone> getPhones() {
    return phones;
  }

  /**
   * @param phones the phones to set
   */
  public void setPhones(List<Phone> phones) {
    this.phones = phones;
  }

  public void addPhone(Phone phone) {
    phones.add(phone);
    phone.setUser(this);
  }

  @Override
  public String toString() {
    return "User [userId=" + userId + ", name=" + name + ", mail=" + mail + ", pass=" + pass + ", creationDate="
        + creationDate + ", updatedDate=" + updatedDate + ", lastLogin=" + lastLogin + ", isActive=" + isActive
        + ", phones=" + phones + "]";
  }

}
