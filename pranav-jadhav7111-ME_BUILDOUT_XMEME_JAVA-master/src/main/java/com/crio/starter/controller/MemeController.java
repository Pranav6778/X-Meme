package com.crio.starter.controller;

import java.util.Collections;
import java.util.List;
import com.crio.starter.data.Meme;
import com.crio.starter.exceptions.PostNotAllowed;
import com.crio.starter.exchange.PostMemeRequest;
import com.crio.starter.exchange.ResponseId;
import com.crio.starter.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memes")
public class MemeController {
    @Autowired
    private MemeService memeService;

    @PostMapping("/")
    public ResponseEntity<ResponseId> postMeme(@RequestBody PostMemeRequest postMemeRequest){
        if(postMemeRequest.getName() == null && postMemeRequest.getUrl() == null && postMemeRequest.getCaption() == null){
            return ResponseEntity.notFound().build();
        }
        try{
            Meme meme = memeService.postMeme(postMemeRequest);
            ResponseId responseId = new ResponseId(meme.getId());
            //String id = memeService.getIdByUrl(postMemeRequest.getUrl());
            //return ResponseEntity.ok().body(Collections.singletonMap("id", postedMeme.getId()));
            return ResponseEntity.ok().body(responseId);
        }catch(PostNotAllowed e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Meme>> getLatestMemes(){
        List <Meme> memes = memeService.getLatestMemes();
        if(memes.isEmpty()){
            return ResponseEntity.ok().body(Collections.emptyList());
        }else{
            return ResponseEntity.ok().body(memes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meme> getMemeById(@PathVariable String id){
        Meme meme = memeService.getMemeById(id);
        if(meme == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(meme);
        }
    }
}
