package com.nttdata.users.dom;

import org.hibernate.annotations.Comment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Comment(value = "The table contains the users phones")
@Table(schema = "users_data", name = "phones")
public class Phone {

  @Id
  @Comment(value = "Identifier of the phone")
  @Column(name = "phone_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long phoneId;

  @Comment(value = "Phone number")
  @Column(name = "number")
  private String number;

  @Comment(value = "City code")
  @Column(name = "city_code")
  private String cityCode;

  @Comment(value = "Country code")
  @Column(name = "countryCode")
  private String countryCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;


  public Phone() {
    super();
  }


  public Phone(Long phoneId, String number, String cityCode, String countryCode, User user) {
    super();
    this.phoneId = phoneId;
    this.number = number;
    this.cityCode = cityCode;
    this.countryCode = countryCode;
    this.user = user;
  }


  /**
   * @return the phoneId
   */
  public Long getPhoneId() {
    return phoneId;
  }


  /**
   * @param phoneId the phoneId to set
   */
  public void setPhoneId(Long phoneId) {
    this.phoneId = phoneId;
  }


  /**
   * @return the number
   */
  public String getNumber() {
    return number;
  }


  /**
   * @param number the number to set
   */
  public void setNumber(String number) {
    this.number = number;
  }


  /**
   * @return the cityCode
   */
  public String getCityCode() {
    return cityCode;
  }


  /**
   * @param cityCode the cityCode to set
   */
  public void setCityCode(String cityCode) {
    this.cityCode = cityCode;
  }


  /**
   * @return the countryCode
   */
  public String getCountryCode() {
    return countryCode;
  }


  /**
   * @param countryCode the countryCode to set
   */
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }


  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }


  /**
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }


  @Override
  public String toString() {
    return "Phone [phoneId=" + phoneId + ", number=" + number + ", cityCode=" + cityCode + ", countryCode="
        + countryCode + ", user=" + user + "]";
  }

}
