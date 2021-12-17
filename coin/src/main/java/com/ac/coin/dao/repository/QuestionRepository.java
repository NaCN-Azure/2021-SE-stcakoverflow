package com.ac.coin.dao.repository;

import com.ac.coin.po.tag2Question;
import com.ac.coin.po.tagTrend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface QuestionRepository extends MongoRepository<tag2Question,String> {


    @Query("{'TagName':?0}")
    tag2Question findByTagName(String tagName);
}
