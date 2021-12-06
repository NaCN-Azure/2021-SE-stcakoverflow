package com.ac.coin.dao.repository;
import com.ac.coin.po.tagTrend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChartRepository extends MongoRepository<tagTrend,String> {

    @Query("{'TagName':?0}")
    tagTrend findByTagName(String tagName);

}
