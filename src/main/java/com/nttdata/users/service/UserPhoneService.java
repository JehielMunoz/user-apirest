package com.nttdata.users.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.nttdata.users.dom.Phone;
import com.nttdata.users.dom.User;
import com.nttdata.users.dto.PhoneRequestDTO;
import com.nttdata.users.dto.UserRequestDTO;
import com.nttdata.users.dto.UserResponseDTO;
import com.nttdata.users.repository.UserRepository;

@Service
public class UserPhoneService {
  @Value("${users.passRegex}")
  private String passRegex;

  private final UserService userService;
  private final PhoneService phoneService;
  private final UserRepository userRepository;

  public UserPhoneService(UserService userService, PhoneService phoneService, UserRepository userRepository) {
    this.userService = userService;
    this.phoneService = phoneService;
    this.userRepository = userRepository;
  }

  public UserResponseDTO createUser(UserRequestDTO userRequest) {
    UserResponseDTO responseUser = new UserResponseDTO();
    User rUser = saveUserWithPhones(userRequest, true);
    responseUser.setUserId(rUser.getUserId());
    responseUser.setActive(rUser.isActive());
    responseUser.setCreationDate(rUser.getCreationDate());
    responseUser.setLastLogin(rUser.getCreationDate());
    responseUser.setUpdatedDate(rUser.getUpdatedDate());
    return responseUser;
  }

  public User saveUserWithPhones(UserRequestDTO userRequest, boolean isCreate) {
    User newUser = new User();
    newUser.setName(userRequest.getName());
    newUser.setMail(userRequest.getMail());
    newUser.setPass(userRequest.getPass());

    newUser.setPhones(new ArrayList<>());
    if (isCreate) {
      newUser.setCreationDate(LocalDateTime.now());
      newUser.setLastLogin(LocalDateTime.now());
    }
    newUser.setUpdatedDate(LocalDateTime.now());
    newUser.setActive(true);

    for (PhoneRequestDTO phoneNumber : userRequest.getPhones()) {
      Phone phone = new Phone();
      phone.setNumber(phoneNumber.getNumber());
      phone.setCityCode(phoneNumber.getCityCode());
      phone.setCountryCode(phoneNumber.getCountryCode());
      phone.setUser(newUser);
      newUser.addPhone(phone);
    }
    return userRepository.save(newUser);
  }

  public void validateUser(UserRequestDTO userRequest) {
    if (!userRequest.getMail().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.cl$")) {
      throw new IllegalArgumentException("Invalid email format");
    }
    if (!userRequest.getPass().matches(passRegex)) {
      throw new IllegalArgumentException("Invalid password format");
    }
    if (checkMailExist(userRequest)) {
      throw new IllegalArgumentException("Mail already exists");
    }
  }

  public boolean checkMailExist(UserRequestDTO userRequest) {
    return userRepository.findByMail(userRequest.getMail()).isPresent();
  }
}

