package org.example.vaadinfamilyproject.registration;

import org.example.vaadinfamilyproject.domain.Registration;
import org.example.vaadinfamilyproject.domain.RegistrationRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
class RegistrationService {

    @Autowired
    private RegistrationRepository repository;


    public void register(Registration registration) {
        repository.save(registration);
    }

}