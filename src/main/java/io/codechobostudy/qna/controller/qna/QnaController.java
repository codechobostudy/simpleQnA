package io.codechobostudy.qna.controller.qna;


import io.codechobostudy.qna.domain.qna.Answer;
import io.codechobostudy.qna.domain.qna.Question;
import io.codechobostudy.qna.repository.qna.AnswerRepository;
import io.codechobostudy.qna.repository.qna.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class QnaController {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/questions")
    public String questions(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int size) {

        Page<Question> questionPage = questionRepository.findAll(
                new PageRequest(page - 1, size, Sort.Direction.DESC, "contents.createDate")
        );

        model.addAttribute("questions", questionPage.getContent());
        model.addAttribute("questionPage", questionPage.getTotalPages());

        return "qna/questions";
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/questions/add", method = GET)
    public String addQuestion() {
        return "qna/addQuestion";
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/questions/add", method = POST)
    public String addQuestion(@RequestParam String title, @RequestParam String body) {

        Question question = new Question();
        question.setTitle(title);
        question.getContents().setBody(body);
        question.getContents().setCreateDate(new Date());

        questionRepository.save(question);

        return "redirect:/questions";
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal == #question.contents.user")
    @RequestMapping(value = "/questions/{questionId}/edit", method = GET)
    public String editQuestion(Model model,
            @PathVariable(value = "questionId") Question question) {

        model.addAttribute(question);
        return "qna/editQuestion";
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal == #question.contents.user")
    @RequestMapping(value = "/questions/{questionId}/edit", method = POST)
    public String editQuestion(
            @PathVariable(value = "questionId") Question question,
            @RequestParam String title,
            @RequestParam String body) {

        question.setTitle(title);
        question.getContents().setBody(body);
        question.getContents().setModifyDate(new Date());

        questionRepository.save(question);

        return "redirect:/questions/" + question.getId();
    }


    @PreAuthorize("hasAuthority('ADMIN') or principal == #question.contents.user")
    @RequestMapping(value = "/questions/{questionId}/delete")
    public String deleteQuestion(
            @PathVariable(value="questionId") long questionId
    ) {
        questionRepository.delete(questionId);
        return "redirect:/questions";
    }

    @RequestMapping("/questions/{questionId}")
    public String question(Model model, @PathVariable long questionId) {
        Question question = questionRepository.findOne(questionId);
        model.addAttribute("question", question);

        return "qna/question";
    }


    @ResponseBody
    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/questions/{questionId}/answers", method = POST)
    public void addAnswer(
            @PathVariable(value="questionId") Question question,
            @RequestParam String body) {

        Answer answer = new Answer();
        answer.getContents().setBody(body);
        answer.getContents().setCreateDate(new Date());
        answer.setQuestion(question);

        answerRepository.save(answer);
    }


    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN') or principal == #answer.contents.user")
    @RequestMapping(value = "/questions/{questionId}/answers/{answerId}/edit", method = POST)
    public void editAnswer(
            @PathVariable(value = "questionId") Question question,
            @PathVariable(value = "answerId") Answer answer,
            @RequestParam String body) {

        answer.getContents().setBody(body);
        answer.getContents().setModifyDate(new Date());
        answer.setQuestion(question);

        answerRepository.save(answer);
    }

    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN') or principal == #answer.contents.user")
    @RequestMapping(value = "/questions/{questionId}/answers/{answerId}", method = DELETE)
    public void deleteAnswer(
            @PathVariable long answerId) {
        answerRepository.delete(answerId);
    }


    @InitBinder
    public void qnaInitBinder(WebDataBinder binder) {
        // 질문 바인딩
        binder.registerCustomEditor(Question.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String questionIdStr) throws IllegalArgumentException {
                Long questionId = new Long(questionIdStr);
                setValue(questionRepository.findOne(questionId));
            }
        });

        binder.registerCustomEditor(Answer.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String answerIdStr) throws IllegalArgumentException {
                Long answerId = new Long(answerIdStr);
                setValue(answerRepository.findOne(answerId));
            }
        });
    }


}
