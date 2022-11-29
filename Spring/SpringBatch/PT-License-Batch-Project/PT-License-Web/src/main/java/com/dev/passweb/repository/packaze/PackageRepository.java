package com.dev.passweb.repository.packaze;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {

    List<PackageEntity> findAllByOrderByPackageName();

}
