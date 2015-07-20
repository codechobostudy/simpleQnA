package io.codechobostudy.qna.controller;


import io.codechobostudy.qna.domain.Question;
import io.codechobostudy.qna.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QnAController {
    @Autowired
    QuestionRepository questionRepository;

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping("/questions")
    public String questions(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int size) {

        Page<Question> questionPage = questionRepository.findAll(new PageRequest(page - 1, size));
        model.addAttribute("questions", questionPage.getContent());

        return "qna/questions";
    }

    @RequestMapping("/questions/{questionId}")
    public String questions(Model model, @PathVariable long questionId) {
        Question question = questionRepository.findOne(questionId);
        model.addAttribute("question", question);

        return "qna/question";
    }
}
