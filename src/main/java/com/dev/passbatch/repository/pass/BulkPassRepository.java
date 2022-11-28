package com.dev.passbatch.repository.pass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BulkPassRepository extends JpaRepository<BulkPassEntity, Integer> {
}
