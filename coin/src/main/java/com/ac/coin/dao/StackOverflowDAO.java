package com.ac.coin.dao;

import com.ac.coin.po.*;
import org.neo4j.driver.internal.value.RelationshipValue;

import java.text.ParseException;
import java.util.List;

public interface StackOverflowDAO{

    List<Tags> findHottestTags(int year);

    List<RelationshipValue> findHottestRelations(int year);

    Tags findTargetTag(String name);

    List<Tags> findTargetSubTarget(String name);

    List<RelationshipValue> findTargetSubRelation(String name);

    tagTrend findTargetNodesChart(String name);

    List<Questions> findQuestions(String name);

    List<String> findNodesFuzzy(String name);

}
