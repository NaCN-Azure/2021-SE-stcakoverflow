package com.ac.coin.dao.repository;

import com.ac.coin.po.*;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StackRelationRepository extends Neo4jRepository<StackRelation,Long> {
    @Query("match (n:Questions)-[r]-(a:Tags) where n.name=$questions return r")
    List<RelationshipValue> findBelongsByQuestionName(@Param("questions") String questions);

    @Query("match (m:Replies)-[r]-(n:Questions) where n.name=$questions return r")
    List<RelationshipValue> findAnswersByQuestionName(@Param("questions") String questions);


}
