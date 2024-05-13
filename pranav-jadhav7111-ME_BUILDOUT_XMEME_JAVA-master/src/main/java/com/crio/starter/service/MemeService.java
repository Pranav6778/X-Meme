package com.crio.starter.service;
import java.util.*;
import java.util.stream.Collectors;
import com.crio.starter.data.Meme;
import com.crio.starter.exceptions.PostNotAllowed;
import com.crio.starter.exchange.PostMemeRequest;
import com.crio.starter.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeService {
    @Autowired
    private MemeRepository memeRepository;
    public Meme postMeme(PostMemeRequest postMemeRequest) throws PostNotAllowed{
        String name = postMemeRequest.getName();
        String url = postMemeRequest.getUrl();
        String caption = postMemeRequest.getCaption();
        int count1 = (int)memeRepository.findAll().stream().count() + 1;
        String id = String.valueOf(count1);
        if(memeRepository.existsByName(name) && memeRepository.existsByUrl(url) && memeRepository.existsByCaption(caption)){
            System.out.println("Post not Allowed because name/url/caption already exists .....");
            // return false;
            throw new PostNotAllowed();
        }else{
            Meme postedMeme = new Meme(id, name, url, caption);
            memeRepository.save(postedMeme);
            return postedMeme;
        }
        
    }

    public List<Meme> getLatestMemes(){
        List <Meme> RevMemes = memeRepository.findAll();
        Collections.reverse(RevMemes);
        List<Meme> memes = RevMemes.stream().limit(100).collect(Collectors.toList());
        //Collections.reverse(memes);
        return memes;
    }
    public Meme getMemeById(String id){
        return memeRepository.findById(id).orElse(null);
    }

    public String getIdByUrl(String url) {
        Meme meme = memeRepository.findByUrl(url);
        return meme.getId();
    }
    
}
