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
        String answer = openAIServiceImpl.getAnswer("What is the best Japanese food?");
        System.out.println(answer);
    }
}
