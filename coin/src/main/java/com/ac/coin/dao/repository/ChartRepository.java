package com.ac.coin.dao.repository;
import com.ac.coin.po.tagTrend;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChartRepository extends MongoRepository<tagTrend,Integer> {

}
