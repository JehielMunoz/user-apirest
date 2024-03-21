package com.nttdata.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO representing users phones")
public class PhoneRequestDTO {

  @Schema(description = "Phone number")
  private String number;

  @Schema(description = "City code")
  private String cityCode;

  @Schema(description = "Country code")
  private String countryCode;

  public PhoneRequestDTO() {
    super();
  }

  public PhoneRequestDTO(String number, String cityCode, String countryCode) {
    super();
    this.number = number;
    this.cityCode = cityCode;
    this.countryCode = countryCode;
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

  @Override
  public String toString() {
    return "PhoneRequestDTO [number=" + number + ", cityCode=" + cityCode + ", countryCode=" + countryCode + "]";
  }

}
