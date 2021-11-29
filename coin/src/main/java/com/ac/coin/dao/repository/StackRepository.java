package com.ac.coin.dao.repository;

import com.ac.coin.po.Relation;
import com.ac.coin.po.Tags;
import com.ac.coin.po.contains;
import org.neo4j.driver.internal.value.MapValue;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StackRepository extends Neo4jRepository<contains,Long> {

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(r.count) return r order by toInt(r.count) DESC limit 5")
    List<RelationshipValue> findRelatedHottestSubTags(@Param("FatherId") Long FatherId);

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId return r")
    List<RelationshipValue> findTargetRelatedSubTags(@Param("FatherId") Long FatherId);
//
//    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(m.count_$year) return r order by toInt(m.count_$year) DESC limit 5")
//    List<RelationshipValue> findRelatedHottestSubTagsByYear(@Param("FatherId") Long FatherId,@Param("year")int year);

}
