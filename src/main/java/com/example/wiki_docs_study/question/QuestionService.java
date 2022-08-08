package com.example.wiki_docs_study.question;

import com.example.wiki_docs_study.error.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {

        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void postQuestion(String subject, String content) {
        Question q = new Question();

        q.setContent(content);
        q.setSubject(subject);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
}