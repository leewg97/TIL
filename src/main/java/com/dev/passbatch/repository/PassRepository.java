package com.dev.passbatch.repository;

import com.dev.passbatch.domain.Pass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassRepository extends JpaRepository<Pass, Long> {
}
