package com.example.LLM_Springboot;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.ai.chat.client.ChatClient.create;

@RestController
@RequestMapping("/api/anthropic")
@CrossOrigin("*")
public class Anthropic_Controller {

    //1st way to do it
    private ChatClient chatClient;              //1 -> it built top of chatModel, 2 ->it has extra features than chatModel
    // 3 -> it also add one more layer of abstraction.

    public Anthropic_Controller(AnthropicChatModel chatModel) {  // The ONLY WAY TO CREATE chatClient is using chatModel(AnthropicChatModel chatModel)
        this.chatClient = ChatClient.create(chatModel);  // we are creating chatClient using chatModel
    }

    @GetMapping("/{question}")
    public ResponseEntity<String> getResponse(@PathVariable String question) {

        String response = chatClient.prompt(question) // chatClient.prompt(question) -> it creates a prompt with the question
                .call()          // .call() -> it calls the model and get the response
                .content();    // .content() -> it gets the content of the response


        return ResponseEntity.ok(response);

    }
}


//    // this is 2nd way to do it
//
////    public AnthropicChatModel chatModel;
////
////    public Anthropic_Controller(AnthropicChatModel chatModel){
////        this.chatModel = chatModel;
////    }
////    @GetMapping("/{question}")
////    public ResponseEntity<String> getResponse(@PathVariable String question){
////
////        String response = chatModel.call(question);
////
////        return ResponseEntity.ok(response);
////
////    }

