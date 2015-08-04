package io.codechobostudy.qna.service.qna;

import io.codechobostudy.qna.domain.auth.User;
import io.codechobostudy.qna.domain.qna.Answer;
import io.codechobostudy.qna.domain.qna.Contents;
import io.codechobostudy.qna.domain.qna.Question;
import io.codechobostudy.qna.dto.qna.AnswerForm;
import io.codechobostudy.qna.repository.qna.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    QuestionService questionService;

    @Override
    public Answer create(AnswerForm answerForm, Question question, User user) {
        Contents answerContents = new Contents(answerForm.getBody(), new Date(), user);
        Answer answer = new Answer(answerContents);
        answerRepository.save(answer);
        questionService.addAnswer(question, answer);

        return answer;
    }

    @Override
    public Answer edit(AnswerForm answerForm, User user) {
        Answer answer = answerRepository.findOne(answerForm.getId());
        answer.getContents().setBody(answerForm.getBody());

        answerRepository.save(answer);

        return answer;
    }

    @Override
    public void delete(long answerId) {
        answerRepository.delete(answerId);
    }
}
