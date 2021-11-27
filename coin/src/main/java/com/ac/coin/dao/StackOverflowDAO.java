package com.ac.coin.dao;

import com.ac.coin.po.*;
import com.ac.coin.vo.RelationVO;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.neo4j.driver.types.Relationship;

import java.util.List;
import java.util.Optional;

public interface StackOverflowDAO{

    List<Tags> findHottestTags(int year);

    List<RelationshipValue> findHottestRelations(int year);

}
