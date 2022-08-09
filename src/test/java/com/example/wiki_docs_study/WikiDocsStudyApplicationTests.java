package com.example.wiki_docs_study;

import com.example.wiki_docs_study.answer.Answer;
import com.example.wiki_docs_study.answer.AnswerRepository;
import com.example.wiki_docs_study.question.Question;
import com.example.wiki_docs_study.question.QuestionRepository;
import com.example.wiki_docs_study.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WikiDocsStudyApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionService questionService;
    @Test
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);  // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장
    }

    @Test
    void findAll() {
        List<Question> result = this.questionRepository.findAll();
        assertEquals(2, result.size());
        Question a = result.get(0);
        assertEquals("sbb가 무엇인가요?", a.getSubject());
    }
    @Test
    void findById() {
        Optional<Question> result = this.questionRepository.findById(1); // 객체가 들어감, null 이 들어갈까봐 그런거다.

        System.out.println(result);
        if(result.isPresent()) {
            Question q = result.get(); // 객체의 값을 끄집어냄
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }

    @Test
    void findBySubject() {
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, q.getId());
    }

    @Test
    void findBySubjectAndContent() {
        Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?","sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());
    }

    @Test
    void findBySubjectLike() {
        List<Question> result = this.questionRepository.findBySubjectLike("sbb%");// like 하기
        Question q = result.get(0);
        assertEquals(1, q.getId());
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    void modify() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

    @Test
    void delete() {
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }

    @Test
    void 답변_데이터_생성_후_저장하기(){
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);


    }


    @Test
    void 답변_조회하기() {
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
    }
    @Transactional
    @Test
    void 답변에연결된질문_질문에연결된답변찾기() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }
    //sbb에 대해서 알고 싶습니다.


    @Test
    void 샘플데이터만들기() {
        for (int i = 1; i <= 303; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            this.questionService.postQuestion(subject, content,null);
        }
    }

    @Test
    void author수정() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            this.questionService.postQuestion(subject, content, null);
        }
    }


}
