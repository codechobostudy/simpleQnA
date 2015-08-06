package io.codechobostudy.qna.service.qna;

import io.codechobostudy.qna.domain.auth.User;
import io.codechobostudy.qna.domain.qna.Answer;
import io.codechobostudy.qna.domain.qna.AnswerContent;
import io.codechobostudy.qna.domain.qna.Question;
import io.codechobostudy.qna.dto.qna.AnswerForm;
import io.codechobostudy.qna.repository.qna.AnswerContentRepository;
import io.codechobostudy.qna.repository.qna.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    AnswerContentRepository answerContentRepository;

    @Override
    public Answer create(AnswerForm answerForm, Question question, User user) {
        AnswerContent content = convertToAnswerContent(answerForm, user);
        answerContentRepository.save(content);

        Answer answer = new Answer();
        answer.setContent(content);
        answer.getContentHistory().add(content);
        answer.setQuestion(question);
        answerRepository.save(answer);

        return answer;
    }

    @Override
    public Answer edit(AnswerForm answerForm, User user) {
        AnswerContent content = convertToAnswerContent(answerForm, user);
        answerContentRepository.save(content);

        Answer answer = answerRepository.findOne(answerForm.getId());
        answer.setContent(content);
        answer.getContentHistory().add(content);
        answerRepository.save(answer);

        return answer;
    }

    @Override
    public void delete(long answerId) {
        answerRepository.delete(answerId);
    }

    private AnswerContent convertToAnswerContent(AnswerForm form, User user){
        AnswerContent answerContent = new AnswerContent();
        answerContent.setBody(form.getBody());
        answerContent.setDate(new Date());
        answerContent.setChangeLog(form.getChangeLog());
        answerContent.setUser(user);

        return answerContent;
    }
}
