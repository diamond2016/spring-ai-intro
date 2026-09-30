package guru.springframework.springaiintro.services.impl;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.Question;
import guru.springframework.springaiintro.services.OpenAIService;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;
    
    public OpenAIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;
    @Value("classpath:templates/get-capital-with-info-prompt.st")
    private Resource getCapitalWithInfoPrompt;

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatModel.call(prompt);

        String response = chatResponse.getResult().getOutput().getText();
        return new Answer(response);
    }

    public String getAnswer(String question) {
        Answer ans = getAnswer(new Question(question));
        return ans.answer();
    }

    @Override
    public Answer getCapital(GetCapitalRequest capitalRequest) {
        // PromptTemplate promptTemplate = new PromptTemplate("What is the capital of " + capitalRequest.stateOrCountry() + "?");
        
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry()));
        ChatResponse chatResponse = chatModel.call(prompt);

        String response = chatResponse.getResult().getOutput().getText();
        return new Answer(response);
    }

    @Override 
    public Answer getCapitalWithInfo(GetCapitalRequest capitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithInfoPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry()));
        ChatResponse chatResponse = chatModel.call(prompt);

        String response = chatResponse.getResult().getOutput().getText();
        return new Answer(response);
    } 

}