package com.example.LLM_Springboot;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
@CrossOrigin("*")
public class OpenAi_Controller {

    public OpenAiChatModel chatModel;

    public OpenAi_Controller(OpenAiChatModel chatModel){
        this.chatModel = chatModel;
    }

     @GetMapping("/{question}")
    public ResponseEntity<String> getResponse(@PathVariable String question){

        String response = chatModel.call(question);

        return ResponseEntity.ok(response);

    }
}

