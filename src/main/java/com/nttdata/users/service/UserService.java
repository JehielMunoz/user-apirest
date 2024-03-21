package com.nttdata.users.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.nttdata.users.dom.Phone;
import com.nttdata.users.dom.User;
import com.nttdata.users.dto.ResponseDTO;
import com.nttdata.users.repository.PhoneRepository;
import com.nttdata.users.repository.UserRepository;

@Service
public class UserService {

  @Value("${users.passRegex}")
  private String passRegex;

  private UserRepository userRepository;
  private PhoneRepository phoneRepository;

  public UserService(UserRepository userRepository, PhoneRepository phoneRepository) {
    this.userRepository = userRepository;
    this.phoneRepository = phoneRepository;
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  public List<User> getUsers() {
    return userRepository.getAllUsers();
  }

  public User getUserByMail(String mail) {
    return userRepository.findUserByMail(mail);
  }

  public ResponseDTO deleteUser(String mail) {
    User userDel = userRepository.findUserByMail(mail);
    if (userDel != null) {
      userDel.setActive(false);
      userRepository.save(userDel);
      return new ResponseDTO(HttpStatus.OK.toString(), "User removed");
    } else {
      return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "User not found");
    }
  }

  public User modifyUser(Long userId, User patchRequest) {
    validateUser(patchRequest);
    User user = userRepository.findUserByUserId(userId);
    patchPhones(user, patchRequest);
    patchUserEntity(user, patchRequest);
    return userRepository.save(user);
  }

  public ResponseDTO updateUser(Long userId, User patchRequest) {
    validateUser(patchRequest);
    User userU = userRepository.findUserByUserId(userId);
    if (userU != null) {
      patchRequest.setActive(userU.isActive());
      patchRequest.setCreationDate(userU.getCreationDate());
      patchRequest.setLastLogin(userU.getLastLogin());
      patchRequest.setUpdatedDate(LocalDateTime.now());

      if (patchRequest.getPhones() != null) {
        patchPhones(userU, patchRequest);
      }

      patchUserEntity(userU, patchRequest);
      userRepository.save(userU);
      return new ResponseDTO(HttpStatus.OK.toString(), "User updated");
    } else {
      return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "User not found");
    }
  }

  public User patchPhones(User user, User patchRequest) {
    for (Phone updatedPhone : patchRequest.getPhones()) {
      updatedPhone.setUser(user);
    }
    return patchRequest;
  }

  private void patchUserEntity(User user, User patchRequest) {
    BeanUtils.copyProperties(patchRequest, user, getNullPropertyNames(patchRequest));
  }

  private String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();
    for (java.beans.PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null)
        emptyNames.add(pd.getName());
    }
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }

  public void validateUser(User userRequest) {
    if (userRequest.getMail() != null && (!userRequest.getMail().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.cl$"))) {
      throw new IllegalArgumentException("Invalid email format");
    }
    if (userRequest.getPass() != null && (!userRequest.getPass().matches(passRegex))) {
      throw new IllegalArgumentException("Invalid password format");
    }
  }

}
