package io.codechobostudy.qna.config;

import io.codechobostudy.qna.domain.Answer;
import io.codechobostudy.qna.domain.Contents;
import io.codechobostudy.qna.domain.Question;
import io.codechobostudy.qna.repository.AnswerRepository;
import io.codechobostudy.qna.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class SampleDataInitializer {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @PostConstruct
    public void initSample() {
        Date current = new Date();
        List<Question> questions = new ArrayList<>();
        for (int idx = 0; idx < 100; idx++) {
            Question question = new Question();
            question.setTitle("title" + idx);
            question.setContents(new Contents("body" + idx, current));
            questionRepository.save(question);
            questions.add(question);
        }

        for (Question question : questions) {
            for (int idx = 0; idx < 3; idx++) {
                String body = "question : " + question.getTitle() + "answer : " + idx;
                Answer answer = new Answer(question, new Contents(body, current));
                question.getAnswers().add(answer);
                answerRepository.save(answer);
                questionRepository.save(question);
            }

        }
    }
}
