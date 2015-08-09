package io.codechobostudy.qna.service.qna;

import io.codechobostudy.qna.domain.auth.User;
import io.codechobostudy.qna.domain.qna.Question;
import io.codechobostudy.qna.domain.qna.QuestionContent;
import io.codechobostudy.qna.domain.qna.Tag;
import io.codechobostudy.qna.dto.qna.QuestionForm;
import io.codechobostudy.qna.repository.qna.QuestionContentRepository;
import io.codechobostudy.qna.repository.qna.QuestionRepository;
import io.codechobostudy.qna.repository.qna.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionContentRepository questionContentRepository;
    @Autowired
    TagRepository tagRepository;

    @Override
    public Page<Question> findPages(int page, int size) {
        Page<Question> questionPage = questionRepository.findAll(
                new PageRequest(page - 1, size, Sort.Direction.DESC, "content.date")
        );

        return questionPage;
    }

    @Override
    public Question create(QuestionForm questionForm, User user) {
        QuestionContent content = convertToQuestionContent(questionForm, user);
        questionContentRepository.save(content);

        Question question = new Question();

        // FIXME 리팩토링 필요
        for (String tagStr : questionForm.getTags()) {
            Tag tag = tagRepository.findOneByName(tagStr);
            if (tag == null) tag = tagRepository.save(new Tag(tagStr));
            question.getTags().add(tag);
        }

        question.setContent(content);
        question.getContentHistory().add(content);

        return questionRepository.save(question);
    }

    @Override
    public Question edit(QuestionForm questionForm, User user) {
        QuestionContent content = convertToQuestionContent(questionForm, user);
        questionContentRepository.save(content);

        Question question = questionRepository.findOne(questionForm.getId());
        question.setContent(content);
        question.getContentHistory().add(content);

        // FIXME 리팩토링 필요
        for (String tagStr : questionForm.getTags()) {
            Tag tag = tagRepository.findOneByName(tagStr);
            if (tag == null) tag = tagRepository.save(new Tag(tagStr));
            question.getTags().add(tag);
        }

        return questionRepository.save(question);
    }

    @Override
    public void delete(long questionId) {
        questionRepository.delete(questionId);
    }


    private QuestionContent convertToQuestionContent(QuestionForm form, User user) {
        QuestionContent content = new QuestionContent();
        content.setTitle(form.getTitle());
        content.setBody(form.getBody());
        content.setDate(new Date());
        content.getTags().addAll(form.getTags());
        content.setUser(user);
        content.setChangeLog(form.getChangeLog());

        return content;
    }
}
