package guru.springframework.springaiintro.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.Question;

@SpringBootTest
public class OpenAIServiceImplTest {
    
    @Autowired
    OpenAIServiceImpl openAIServiceImpl;

    @Test
    void testGetAnswer() {
        Answer answer = openAIServiceImpl.getAnswer(new Question("Pls give a brief introduction of the LLM model responding. "));
        System.out.println(answer);
    }

    @Test
    void testGetAnswerWithString() {
        Answer answer = openAIServiceImpl.getAnswer("Pls describe briefly how divide in tokens the expression '(24 + (3 - 2) * 5) / 4'");
        System.out.println(answer);
    }
}

