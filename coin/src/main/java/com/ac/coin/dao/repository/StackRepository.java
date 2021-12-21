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

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(r.count) return r order by toInt(r.count) DESC limit 10")
    List<RelationshipValue> findRelatedHottestSubTags(@Param("FatherId") Long FatherId);

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(r.count_2015) return r order by toInt(r.count_2015) DESC limit 10")
    List<RelationshipValue> findRelatedHottestSubTags2015(@Param("FatherId") Long FatherId);

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(r.count_2016) return r order by toInt(r.count_2016) DESC limit 10")
    List<RelationshipValue> findRelatedHottestSubTags2016(@Param("FatherId") Long FatherId);

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(r.count_2017) return r order by toInt(r.count_2017) DESC limit 10")
    List<RelationshipValue> findRelatedHottestSubTags2017(@Param("FatherId") Long FatherId);

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(r.count_2018) return r order by toInt(r.count_2018) DESC limit 10")
    List<RelationshipValue> findRelatedHottestSubTags2018(@Param("FatherId") Long FatherId);

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(r.count_2019) return r order by toInt(r.count_2019) DESC limit 10")
    List<RelationshipValue> findRelatedHottestSubTags2019(@Param("FatherId") Long FatherId);

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(r.count_2020) return r order by toInt(r.count_2020) DESC limit 10")
    List<RelationshipValue> findRelatedHottestSubTags2020(@Param("FatherId") Long FatherId);

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(r.count_2021) return r order by toInt(r.count_2021) DESC limit 10")
    List<RelationshipValue> findRelatedHottestSubTags2021(@Param("FatherId") Long FatherId);


    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId return r")
    List<RelationshipValue> findTargetRelatedSubTags(@Param("FatherId") Long FatherId);


}
