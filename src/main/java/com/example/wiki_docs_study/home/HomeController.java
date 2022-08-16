package com.example.wiki_docs_study.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello Worasdasdlasdasdad";
    }


    @RequestMapping("/")
    public String root() {
        return "redirect:/question/list";
    }

    @RequestMapping("/kakao2")
    public String kakao2() {
        return "kakao2";
    }
}