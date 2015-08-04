package io.codechobostudy.qna.config;

import com.thedeanda.lorem.Lorem;
import io.codechobostudy.qna.domain.auth.Role;
import io.codechobostudy.qna.domain.auth.User;
import io.codechobostudy.qna.domain.qna.Answer;
import io.codechobostudy.qna.domain.qna.Contents;
import io.codechobostudy.qna.domain.qna.Question;
import io.codechobostudy.qna.domain.qna.Tag;
import io.codechobostudy.qna.dto.auth.UserCreateForm;
import io.codechobostudy.qna.repository.qna.AnswerRepository;
import io.codechobostudy.qna.repository.qna.QuestionRepository;
import io.codechobostudy.qna.repository.qna.TagRepository;
import io.codechobostudy.qna.service.auth.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// TODO 나중에 정리하기
@Configuration
public class SampleDataInitializer {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserService userService;
    @Autowired
    TagRepository tagRepository;

    @PostConstruct
    public void initQna() throws InterruptedException {

        //create user
        UserCreateForm userCreateForm = new UserCreateForm();
        userCreateForm.setEmail("admin@localhost");
        userCreateForm.setPassword("admin");
        userCreateForm.setPasswordRepeated("admin");

        User user = userService.create(userCreateForm, Role.ADMIN);

        //create tags
        List<Tag> tags = new ArrayList<Tag>() {{
            add(new Tag("Java"));
            add(new Tag("Spring"));
            add(new Tag("Spring boot"));
            add(new Tag("Ruby"));
            add(new Tag("Python"));
            add(new Tag("Database"));
            add(new Tag("MySql"));
            add(new Tag("Elastic search"));
            add(new Tag("Hadoop"));
        }};
        tagRepository.save(tags);

        Date current = new Date();
        List<Question> questions = new ArrayList<>();

        for (int idx = 0; idx < 30; idx++) {
            Question question = new Question();
            question.setTitle(Lorem.getTitle(2, 7));
            question.setContents(new Contents(Lorem.getHtmlParagraphs(2, 5), current, user));
            questions.add(question);
        }

        for (Question question : questions) {
            //랜덤으로 tag 가져오기
            Collections.shuffle(tags);

            int max = (int)(Math.random() * 3);
            for (int rndIdx = 0; rndIdx < max; rndIdx++) {
                Tag tag = tags.get(rndIdx);
                question.getTags().add(tag);
            }
        }
        questionRepository.save(questions);

        List<Answer> answers = new ArrayList<>();
        for (Question question : questions) {
            int max = (int) (10 * Math.random());
            for (int idx = 0; idx < max; idx++) {
                Answer answer = new Answer(new Contents(Lorem.getHtmlParagraphs(2, 5), current, user));
                question.getAnswers().add(answer);
                answers.add(answer);
            }
        }
        answerRepository.save(answers);
        questionRepository.save(questions);
    }

}
