package io.codechobostudy.qna.repository.auth;

import io.codechobostudy.qna.domain.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);
}
