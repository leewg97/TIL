package com.won.tutorial.repository;

import com.won.tutorial.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Username 을 기준으로 User 정보를 가져올 때, 권한 정보도 같이 가져옴
    @EntityGraph(attributePaths = "authorities")    // 해당 쿼리가 수행될 때 Eager 조회로 authorities 정보를 같이 가져옴
    Optional<User> findOneWithAuthoritiesByUsername(String username);  

}
