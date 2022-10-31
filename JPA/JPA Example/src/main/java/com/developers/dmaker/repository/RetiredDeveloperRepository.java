package com.developers.dmaker.repository;

import com.developers.dmaker.entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetiredDeveloperRepository extends JpaRepository<RetiredDeveloper, Long> {
}
