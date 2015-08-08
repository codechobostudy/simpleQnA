package io.codechobostudy.qna.config;

import com.thedeanda.lorem.Lorem;
import io.codechobostudy.qna.domain.auth.Role;
import io.codechobostudy.qna.domain.auth.User;
import io.codechobostudy.qna.domain.qna.Question;
import io.codechobostudy.qna.dto.auth.UserCreateForm;
import io.codechobostudy.qna.dto.qna.AnswerForm;
import io.codechobostudy.qna.dto.qna.QuestionForm;
import io.codechobostudy.qna.repository.qna.TagRepository;
import io.codechobostudy.qna.service.auth.user.UserService;
import io.codechobostudy.qna.service.qna.AnswerService;
import io.codechobostudy.qna.service.qna.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO 나중에 정리하기
@Configuration
public class SampleDataInitializer {
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;
    @Autowired
    TagRepository tagRepository;

    @PostConstruct
    public void initQna() throws InterruptedException {

        //create user
        UserCreateForm adminUserCreateForm = new UserCreateForm();
        adminUserCreateForm.setEmail("admin@localhost");
        adminUserCreateForm.setPassword("admin");
        adminUserCreateForm.setPasswordRepeated("admin");

        User admin = userService.create(adminUserCreateForm, Role.ADMIN);

        //create user
        UserCreateForm defaultUserCreateForm = new UserCreateForm();
        defaultUserCreateForm.setEmail("user@localhost");
        defaultUserCreateForm.setPassword("user");
        defaultUserCreateForm.setPasswordRepeated("user");

        userService.create(defaultUserCreateForm, Role.USER);

        //create tags
        List<String> tags = new ArrayList<String>() {{
            add("Java");
            add("Spring");
            add("Spring boot");
            add("Ruby");
            add("Python");
            add("Database");
            add("MySql");
            add("Elastic search");
            add("Hadoop");
        }};

        List<Question> questions = new ArrayList<>();

        for (int idx = 0; idx < 30; idx++) {
            QuestionForm questionForm = new QuestionForm();
            questionForm.setTitle(Lorem.getTitle(2, 7));
            questionForm.setBody(Lorem.getHtmlParagraphs(2, 5));

            Collections.shuffle(tags);
            int max = (int) (5 * Math.random());
            for (int tagIdx = 0; tagIdx < max; tagIdx++) {
                questionForm.getTags().add(tags.get(tagIdx));
            }

            Question question = questionService.create(questionForm, admin);
            questions.add(question);
        }

        for (Question question : questions) {
            int max = (int) (10 * Math.random());
            for (int idx = 0; idx < max; idx++) {
                AnswerForm answerForm = new AnswerForm();
                answerForm.setBody(Lorem.getHtmlParagraphs(2, 5));
                answerService.create(answerForm, question, admin);
            }
        }


    }

}
