package io.codechobostudy.qna.service.qna;

import io.codechobostudy.qna.domain.auth.User;
import io.codechobostudy.qna.domain.qna.Answer;
import io.codechobostudy.qna.domain.qna.Contents;
import io.codechobostudy.qna.domain.qna.Question;
import io.codechobostudy.qna.dto.qna.QuestionForm;
import io.codechobostudy.qna.repository.qna.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Page<Question> findPages(int page, int size) {
        Page<Question> questionPage = questionRepository.findAll(
                new PageRequest(page - 1, size, Sort.Direction.DESC, "contents.createDate")
        );

        return questionPage;
    }

    @Override
    public Question create(QuestionForm questionForm, User user) {
        Question question = new Question();
        question.setTitle(questionForm.getTitle());
        Contents qContents = question.getContents();
        qContents.setBody(questionForm.getBody());
        qContents.setCreateDate(new Date());
        qContents.setUser(user);

        questionRepository.save(question);

        return question;
    }

    @Override
    public Question edit(QuestionForm questionForm, User user) {
        Question question = questionRepository.findOne(questionForm.getId());
        question.setTitle(questionForm.getTitle());
        Contents qContents = question.getContents();
        qContents.setBody(questionForm.getBody());
        qContents.setCreateDate(new Date());
        qContents.setUser(user);

        return questionRepository.save(question);
    }

    @Override
    public void delete(long questionId) {
        questionRepository.delete(questionId);
    }

    @Override
    public void addAnswer(Question question, Answer answer) {
        question.getAnswers().add(answer);
        questionRepository.save(question);
    }
}
