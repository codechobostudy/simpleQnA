package io.codechobostudy.qna.controller.qna;


import io.codechobostudy.qna.domain.qna.Answer;
import io.codechobostudy.qna.domain.qna.Question;
import io.codechobostudy.qna.repository.qna.AnswerRepository;
import io.codechobostudy.qna.repository.qna.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        Page<Question> questionPage = questionRepository.findAll(new PageRequest(page - 1, size, Sort.Direction.DESC, "contents.createDate"));
        model.addAttribute("questions", questionPage.getContent());

        return "qna/questions";
    }

    @RequestMapping(value = "/questions/add", method = GET)
    public String addQuestion() {
        return "qna/addQuestion";
    }


    @RequestMapping(value = "/questions/add", method = POST)
    public String addQuestion(@RequestParam String title, @RequestParam String body) {
        Question question = new Question();
        question.setTitle(title);
        question.getContents().setBody(body);
        question.getContents().setCreateDate(new Date());

        questionRepository.save(question);

        return "redirect:/questions";
    }

    @RequestMapping(value = "/questions/{questionId}/edit", method = GET)
    public String editQuestion(Model model, @PathVariable long questionId) {
        Question question = questionRepository.findOne(questionId);
        model.addAttribute("question", question);

        return "qna/editQuestion";
    }


    @RequestMapping(value = "/questions/{questionId}/edit", method = POST)
    public String editQuestion(
            Model model,
            @PathVariable long questionId,
            @RequestParam String title,
            @RequestParam String body
    ) {
        Question question = questionRepository.findOne(questionId);
        question.setTitle(title);
        question.getContents().setBody(body);
        question.getContents().setModifyDate(new Date());

        questionRepository.save(question);

        return "redirect:/questions/" + question.getId();
    }


    @RequestMapping(value = "/questions/{questionId}/delete")
    public String deleteQuestion(@PathVariable long questionId) {
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
    @RequestMapping(value = "/questions/{questionId}/answers", method = POST)
    public void addAnswer(@PathVariable long questionId, @RequestParam String body) {
        Question question = questionRepository.findOne(questionId);

        Answer answer = new Answer();
        answer.getContents().setBody(body);
        answer.getContents().setCreateDate(new Date());
        answer.setQuestion(question);

        answerRepository.save(answer);
    }


    @ResponseBody
    @RequestMapping(value = "/questions/{questionId}/answers/{answerId}/edit", method = POST)
    public void editAnswer(
            @PathVariable long questionId,
            @PathVariable long answerId,
            @RequestParam String body) {
        Question question = questionRepository.findOne(questionId);
        Answer answer = answerRepository.findOne(answerId);

        answer.getContents().setBody(body);
        answer.getContents().setModifyDate(new Date());
        answer.setQuestion(question);

        answerRepository.save(answer);
    }

    @ResponseBody
    @RequestMapping(value = "/questions/{questionId}/answers/{answerId}", method = DELETE)
    public void deleteAnswer(
            @PathVariable long answerId) {
        answerRepository.delete(answerId);
    }


}
