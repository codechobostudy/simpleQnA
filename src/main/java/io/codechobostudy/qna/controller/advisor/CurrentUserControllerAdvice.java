package io.codechobostudy.qna.controller.advisor;

import io.codechobostudy.qna.domain.auth.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

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
