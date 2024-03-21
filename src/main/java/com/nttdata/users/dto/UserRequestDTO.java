package com.nttdata.users.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO representing an User request")
public class UserRequestDTO {

  @Schema(description = "User name")
  private String name;

  @Schema(description = "User mail")
  private String mail;

  @Schema(description = "User pass")
  private String pass;

  @Schema(description = "User phones")
  private List<PhoneRequestDTO> phones;

  public UserRequestDTO() {
    super();
  }

  public UserRequestDTO(String name, String mail, String pass, List<PhoneRequestDTO> phones) {
    super();
    this.name = name;
    this.mail = mail;
    this.pass = pass;
    this.phones = phones;
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
   * @return the phones
   */
  public List<PhoneRequestDTO> getPhones() {
    return phones;
  }

  /**
   * @param phones the phones to set
   */
  public void setPhones(List<PhoneRequestDTO> phones) {
    this.phones = phones;
  }
}
