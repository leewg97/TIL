package com.dev.passbatch.repository;

import com.dev.passbatch.domain.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {
}
