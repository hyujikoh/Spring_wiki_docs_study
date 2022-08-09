package com.example.wiki_docs_study.question;


import com.example.wiki_docs_study.answer.AnswerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {


    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {

        Question q = this.questionService.getQuestion(id);
        model.addAttribute("question", q);
        return "question_detail";
    }

    @RequestMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }


    @PostMapping("/create")
    public String postQuestion(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.postQuestion(questionForm.getSubject(), questionForm.getContent());

        return "question_list";
    }




}
