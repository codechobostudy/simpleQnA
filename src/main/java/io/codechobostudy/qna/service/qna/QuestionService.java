package io.codechobostudy.qna.service.qna;

import io.codechobostudy.qna.domain.auth.User;
import io.codechobostudy.qna.domain.qna.Answer;
import io.codechobostudy.qna.domain.qna.Question;
import io.codechobostudy.qna.dto.qna.QuestionForm;
import org.springframework.data.domain.Page;

public interface QuestionService {

    Page<Question> findPages(int page, int size);

    Question create(QuestionForm questionForm, User user);

    Question edit(QuestionForm questionForm, User user);

    void delete(long questionId);

    void addAnswer(Question question, Answer answer);
}
