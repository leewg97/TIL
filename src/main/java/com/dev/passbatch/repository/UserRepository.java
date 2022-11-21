package com.dev.passbatch.repository;

import com.dev.passbatch.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
