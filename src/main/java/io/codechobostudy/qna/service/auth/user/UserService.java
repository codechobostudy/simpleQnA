package io.codechobostudy.qna.service.auth.user;

import io.codechobostudy.qna.domain.auth.User;
import io.codechobostudy.qna.dto.auth.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}
