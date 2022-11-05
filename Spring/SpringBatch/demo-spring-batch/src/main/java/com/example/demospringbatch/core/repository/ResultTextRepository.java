package com.example.demospringbatch.core.repository;

import com.example.demospringbatch.core.domain.ResultText;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultTextRepository extends JpaRepository<ResultText, Integer> {
}
