package com.crio.starter.repository;

import com.crio.starter.data.Meme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MemeRepository extends MongoRepository<Meme,String>{
    boolean existsByName(String name);
    boolean existsByUrl(String url);
    boolean existsByCaption(String caption);
    Meme findByUrl(String url);

}
