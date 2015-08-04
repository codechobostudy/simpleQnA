package io.codechobostudy.qna.service.qna;

import io.codechobostudy.qna.domain.auth.User;
import io.codechobostudy.qna.domain.qna.Answer;
import io.codechobostudy.qna.domain.qna.Question;
import io.codechobostudy.qna.dto.qna.AnswerForm;

public interface AnswerService {

    Answer create(AnswerForm answerForm, Question question, User user);

    Answer edit(AnswerForm answerForm, User user);

    void delete(long answerId);
}
