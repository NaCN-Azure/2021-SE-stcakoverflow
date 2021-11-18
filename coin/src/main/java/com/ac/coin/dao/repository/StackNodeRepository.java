package com.ac.coin.dao.repository;

import com.ac.coin.po.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StackNodeRepository extends Neo4jRepository<StackNode,Long> {
    @Query("match (n:Questions)-[r]-(a:Tags) where n.name=$questions return a")
    List<Tags> findTagsByQuestionName(@Param("questions") String questions);

    @Query("match (m:Replies)-[r]-(n:Questions) where n.name=$questions return m")
    List<Replies> findRepliesByQuestionName(@Param("questions") String questions);

    @Query("match (n:Questions)  where n.name=$questions return n")
    Optional<Questions> findQuestion(@Param("questions") String questions);

}
