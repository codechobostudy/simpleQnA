package io.codechobostudy.qna.controller.qna;


import io.codechobostudy.qna.domain.auth.CurrentUser;
import io.codechobostudy.qna.domain.qna.Question;
import io.codechobostudy.qna.dto.qna.AnswerForm;
import io.codechobostudy.qna.dto.qna.QuestionForm;
import io.codechobostudy.qna.service.qna.AnswerService;
import io.codechobostudy.qna.service.qna.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class QnaController {

    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionService questionService;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/questions")
    String questions(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int size) {

        // FIXME 서비스에서 처리하는 page-> 0부터 시작. UI 에서는 1에서 시작. 어케 처리해야 깔끔할까???
        Page<Question> questionPage = questionService.findPages(page, size);

        model.addAttribute("questions", questionPage.getContent());
        model.addAttribute("totalPages", questionPage.getTotalPages());
        model.addAttribute("currentPage", questionPage.getNumber() + 1);

        return "qna/questions";
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/questions/add", method = GET)
    String addQuestion() {
        return "qna/addQuestion";
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/questions/add", method = POST)
    String addQuestion(QuestionForm questionForm,
                       @AuthenticationPrincipal CurrentUser currentUser) {

        Question question = questionService.create(questionForm, currentUser.getUser());
        return "redirect:/questions/" + question.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal == #question.contents.user")
    @RequestMapping(value = "/questions/{questionId}/edit", method = GET)
    String editQuestion(Model model,
                        @PathVariable(value = "questionId") Question question) {

        model.addAttribute(question);
        return "qna/editQuestion";
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal == #question.contents.user")
    @RequestMapping(value = "/questions/{questionId}/edit", method = POST)
    String editQuestion(@PathVariable(value = "questionId") long questionId,
                        QuestionForm questionForm,
                        @AuthenticationPrincipal CurrentUser currentUser) {

        questionService.edit(questionForm, currentUser.getUser());
        return "redirect:/questions/" + questionId;
    }


    @PreAuthorize("hasAuthority('ADMIN') or principal == #question.contents.user")
    @RequestMapping(value = "/questions/{questionId}/delete")
    String deleteQuestion(@PathVariable long questionId) {

        questionService.delete(questionId);
        return "redirect:/questions";
    }

    //  pathVariable을 사용하여 바로 question을 가져오는 방식이 간단하긴 한데,
    // Serivce를 거치지 않는다는 점에서 이걸 써도 되는건지 고민됨
    // 우선 최소한만 고친다가 원칙이므로 여기는 손대지 않는다.
    @RequestMapping("/questions/{questionId}")
    String question(Model model,
                    @PathVariable(value = "questionId") Question question) {

        model.addAttribute("question", question);
        return "qna/question";
    }

    @ResponseBody
    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/questions/{questionId}/answers", method = POST)
    void addAnswer(@PathVariable(value = "questionId") Question question,
                   @AuthenticationPrincipal CurrentUser currentUser,
                   AnswerForm answerForm) {

        answerService.create(answerForm, question, currentUser.getUser());
    }

    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN') or principal == #answer.contents.user")
    @RequestMapping(value = "/questions/{questionId}/answers/{answerId}/edit", method = POST)
    void editAnswer(@AuthenticationPrincipal CurrentUser currentUser,
                    AnswerForm answerForm) {

        answerService.edit(answerForm, currentUser.getUser());
    }

    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN') or principal == #answer.contents.user")
    @RequestMapping(value = "/questions/{questionId}/answers/{answerId}/delete", method = POST)
    void deleteAnswer(@PathVariable long answerId) {

        answerService.delete(answerId);
    }

}
