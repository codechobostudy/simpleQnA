package io.codechobostudy.qna.service.auth.current;


import io.codechobostudy.qna.domain.auth.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
