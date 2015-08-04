package io.codechobostudy.qna.controller.common;

import io.codechobostudy.qna.domain.auth.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 모든뷰에 currentUser를 추가한다.
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
        if (authentication == null)
            return null;
        else
            return (CurrentUser) authentication.getPrincipal();
    }
}
