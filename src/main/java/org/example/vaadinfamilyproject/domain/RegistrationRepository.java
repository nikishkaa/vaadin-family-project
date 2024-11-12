package org.example.vaadinfamilyproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    //Default crud operation
}
