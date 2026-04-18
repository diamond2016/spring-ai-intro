package guru.springframework.springaiintro.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenAIServiceImplTest {
    
    @Autowired
    OpenAIServiceImpl openAIServiceImpl;

    @Test
    void testGetAnswer() {
        String answer = openAIServiceImpl.getAnswer("Pls give an introduction of the LLM model responding?");
        System.out.println(answer);
    }
}
