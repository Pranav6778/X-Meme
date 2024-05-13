package com.crio.starter.exchange;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class PostMemeRequest {
    @NonNull
    private String name;
    @NonNull
    private String url ;
    
    @NonNull
    private String caption;
  
    
    public PostMemeRequest(@NonNull String name, @NonNull String url, @NonNull String caption) {
        this.name = name;
        this.url = url;
        this.caption = caption;
    }
    public String getName(){
        return this.name;
    }
    public String getUrl(){
        return this.url;
    }
    public String getCaption(){
        return this.caption;
    }
}
