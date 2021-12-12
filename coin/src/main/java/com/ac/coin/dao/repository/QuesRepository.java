package com.ac.coin.dao.repository;

import com.ac.coin.po.Questions;
import com.ac.coin.po.Tags;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuesRepository extends Neo4jRepository<Questions,Long> {

    @Query("match (n:Questions)-[r:belongs]->(m:Tags) where m.name=$name and exists(n.views) return n")
    List<Questions> findQuestions(@Param("name") String name);

}
