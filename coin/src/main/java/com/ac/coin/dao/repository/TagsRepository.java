package com.ac.coin.dao.repository;

import com.ac.coin.po.Node;
import com.ac.coin.po.Tags;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagsRepository extends Neo4jRepository<Tags,Long> {

    @Query("match (n:Tags) where exists(n.count) return n order by toInt(n.count) DESC limit 5")
    List<Tags> findHottestTags();

    @Query("match (n:Tags) where exists(n.count_$year) return n order by toInt(n.count_$year) DESC limit 5")
    List<Tags> findHottestTagsByYear(@Param("year") int year);

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(m.count) return m order by toInt(m.count) DESC limit 5")
    List<Tags> findRelatedHottestSubTags(@Param("FatherId") Long FatherId);

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(m.count_$year) return m order by toInt(m.count_$year) DESC limit 5")
    List<Tags> findRelatedHottestSubTagsByYear(@Param("FatherId") Long FatherId,@Param("year")int year);
}
