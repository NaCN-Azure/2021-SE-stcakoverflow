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

    @Query("match (n:Tags) where exists(n.count_2015) return n order by toInt(n.count_2015) DESC limit 5")
    List<Tags> findHottestTags2015();

    @Query("match (n:Tags) where exists(n.count_2016) return n order by toInt(n.count_2016) DESC limit 5")
    List<Tags> findHottestTags2016();

    @Query("match (n:Tags) where exists(n.count_2017) return n order by toInt(n.count_2017) DESC limit 5")
    List<Tags> findHottestTags2017();

    @Query("match (n:Tags) where exists(n.count_2018) return n order by toInt(n.count_2018) DESC limit 5")
    List<Tags> findHottestTags2018();

    @Query("match (n:Tags) where exists(n.count_2019) return n order by toInt(n.count_2019) DESC limit 5")
    List<Tags> findHottestTags2019();

    @Query("match (n:Tags) where exists(n.count_2020) return n order by toInt(n.count_2020) DESC limit 5")
    List<Tags> findHottestTags2020();

    @Query("match (n:Tags) where exists(n.count_2021) return n order by toInt(n.count_2021) DESC limit 5")
    List<Tags> findHottestTags2021();

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(r.count) return m order by toInt(r.count) DESC limit 5")
    List<Tags> findRelatedHottestSubTags(@Param("FatherId") Long FatherId);

    @Query("match (n:Tags) where n.name=$name return n")
    Tags findTargetNode(@Param("name") String name);

    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId return m")
    List<Tags> findTargetSubNodesAll(@Param("FatherId") Long FatherId);

//    @Query("match (n:Tags)-[r:contains]->(m) where id(n)=$FatherId and exists(m.count_$year) return m order by toInt(m.count_$year) DESC limit 5")
//    List<Tags> findRelatedHottestSubTagsByYear(@Param("FatherId") Long FatherId,@Param("year")int year);
}
