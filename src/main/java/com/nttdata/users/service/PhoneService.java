package com.nttdata.users.service;

import org.springframework.stereotype.Service;
import com.nttdata.users.dom.Phone;
import com.nttdata.users.repository.PhoneRepository;

@Service
public class PhoneService {
  private final PhoneRepository phoneRepository;

  public PhoneService(PhoneRepository phoneRepository) {
    this.phoneRepository = phoneRepository;
  }

  public Phone savePhone(Phone phone) {
    return phoneRepository.save(phone);
  }
}
