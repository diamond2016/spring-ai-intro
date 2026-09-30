package guru.springframework.springaiintro.services.impl;

import java.util.Map;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.GetCapitalResponse;
import guru.springframework.springaiintro.model.Question;
import guru.springframework.springaiintro.services.OpenAIService;

@Slf4j
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;
    
    @Autowired 
    ObjectMapper objectMapper;
    
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
    public GetCapitalResponse getCapital(GetCapitalRequest capitalRequest) {
        // PromptTemplate promptTemplate = new PromptTemplate("What is the capital of " + capitalRequest.stateOrCountry() + "?");
        BeanOutputConverter<GetCapitalResponse> beanOutputConverter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = beanOutputConverter.getFormat();
        log.info("Format of prompt {}", format);
        
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry(), "format", format));
        ChatResponse chatResponse = chatModel.call(prompt);
        String response = chatResponse.getResult().getOutput().getText();
        /*
        String responseString;

        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            responseString = jsonNode.get("answer").asText();    
        } catch (JsonProcessingException e) {
            throw new RuntimeException( "Error in reading JSON response " + e.getMessage());
        }
        */
        return beanOutputConverter.convert(Objects.requireNonNull(response));
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