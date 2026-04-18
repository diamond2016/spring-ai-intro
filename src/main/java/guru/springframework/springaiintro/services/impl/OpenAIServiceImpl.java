package guru.springframework.springaiintro.services.impl;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.Question;
import guru.springframework.springaiintro.services.OpenAIService;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;
    
    public OpenAIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }


    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatModel.call(prompt);
  
        String response = chatResponse.getResult().getOutput().getText();
        return new Answer(response);
    }


    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(new Question(question).question());
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatModel.call(prompt);
  
        String response = chatResponse.getResult().getOutput().getText();
        return response;
    }

}